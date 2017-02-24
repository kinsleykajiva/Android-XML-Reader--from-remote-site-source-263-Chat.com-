package netwox;

/**
 * Created by kinsley kajiva on 4/19/2016.
 */

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import Data_items.home_items;
import Internals.Config;

public class XmlFactory {
    public static List<home_items> getStackSitesFromFile(Context ctx) {

        // List of StackSites that we will return
        List<home_items> stackSites = new ArrayList<>();

        // temp holder for current StackSite while parsing
        home_items curStackSite = null;
        // temp holder for current text value while parsing
        String curText = "";


        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput(Config.XmlFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(Config.KEY_SITE)) {

                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            curStackSite = new home_items();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(Config.KEY_SITE)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            stackSites.add(curStackSite);
                        } else if (tagname.equalsIgnoreCase(Config.KEY_NAME)) {
                            // if </name> use setName() on curSite
                            curStackSite.setArticleTitle(curText);
                            Log.e("xmlfactory", "" + curText);
                        } else if (tagname.equalsIgnoreCase(Config.KEY_LINK)) {
                            // if </link> use setLink() on curSite
                            // curStackSite.set(curText);
                        } else if (tagname.equalsIgnoreCase(Config.KEY_DATE)) {
                            // if </about> use setAbout() on curSite
                            curStackSite.setArticlePubDate(curText);
                        } else if (tagname.equalsIgnoreCase(Config.KEY_DESCRIPTION)) {
                            // if </image> use setImgUrl() on curSite
                            curStackSite.setArticleDescription(curText);
                        }
                        break;

                    default:
                        Log.e("pano", "not found pano");
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the populated list.
        return stackSites;
    }


    private static final String TAG = "XmlParsing";

    public static ArrayList<HashMap<String, String>> processXML(Context ctx) throws Exception {
        FileInputStream fis = ctx.openFileInput(Config.XmlFile);
        InputStream inputStream = fis;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xmlDocument = documentBuilder.parse(inputStream);
        Element rootElement = xmlDocument.getDocumentElement();
        // Log.e("rootElement",""+rootElement.getTagName());//should give us the root i.e rss
        NodeList itemsList = rootElement.getElementsByTagName("item");
        NodeList itemChildren = null;
        Node currentItem = null;
        Node currentChild = null;
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        HashMap<String, String> currentMap = null;
        for (int i = 0; i < itemsList.getLength(); i++) {//this loop will print all items
            currentItem = itemsList.item(i);
            itemChildren = currentItem.getChildNodes();
            //Log.e(TAG,""+currentItem);//will print items
            currentMap = new HashMap<>();
            for (int j = 0; j < itemChildren.getLength(); j++) {//this loop will print verything in side one item
                currentChild = itemChildren.item(j);


                if (currentChild.getNodeName().equalsIgnoreCase("title")) {
                    if (currentChild.getTextContent() != null || !currentChild.getTextContent().isEmpty()) {
                   //     Log.e(TAG, "" + currentChild.getTextContent());//will print all titles
                        currentMap.put("title", currentChild.getTextContent());

                    }

                }
                if (currentChild.getNodeName().equalsIgnoreCase("pubDate")) {
                    if (currentChild.getTextContent() != null || !currentChild.getTextContent().isEmpty()) {
                        //   Log.e(TAG,""+currentChild.getTextContent());//will print all pubDate
                        currentMap.put("pubDate", currentChild.getTextContent());

                    }


                }
                if (currentChild.getNodeName().equalsIgnoreCase("description")) {
                    if (currentChild.getTextContent() != null || !currentChild.getTextContent().isEmpty()) {
                        //    Log.e(TAG,""+currentChild.getTextContent());//will print all description
                        currentMap.put("description", currentChild.getTextContent());

                    }

                }
                if (currentChild.getNodeName().equalsIgnoreCase("category")) {
                    if (currentChild.getTextContent() != null || !currentChild.getTextContent().isEmpty()) {
                        //  Log.e(TAG,""+currentChild.getTextContent());//will print all category
                        currentMap.put("category", currentChild.getTextContent());

                    }

                }


            }

            if (currentMap != null && !currentMap.isEmpty()) {
                results.add(currentMap);
            }


        }
        return results;
    }








}
