package fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import Adapters.homeRecycler;
import Internals.Config;
import netwox.DownloadXml;
import netwox.XmlFactory;
import zw.co.zimcybers.kinsleykajiva.a263chatcom.R;

/**
 * Created by kinsley kajiva on 4/19/2016.
 */
public class home extends Fragment {
    private RecyclerView mRecyclerView;
    private View layout;
    private homeRecycler adapter;
    private SimpleArcDialog mProgDialog;
    private ArrayList<HashMap<String, String>> myOptions2 ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_home, container, false);
        mProgDialog = new SimpleArcDialog(getActivity());
        mProgDialog.setConfiguration(new ArcConfiguration(getActivity()));
        mProgDialog.setCanceledOnTouchOutside(false);
           mProgDialog.show();

     //   Log.e("testing", "About to dive in");
        SitesDownloadTask sitesDownloadTask =  new SitesDownloadTask();
        if(sitesDownloadTask!=null){
            sitesDownloadTask.cancel(true);

        }else{
            sitesDownloadTask =  new SitesDownloadTask();
            sitesDownloadTask.execute();
        }
        if(isNetworkAvailable()){
            Log.e("testing", " is connection");
    new SitesDownloadTask().execute();
        }else{
            Log.e("testing", "no connection");
            try {
                SettingAdapter();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return layout;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void SettingAdapter() throws Exception {
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_home);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        myOptions2= XmlFactory.processXML(getActivity());
       /* if(myOptions2.isEmpty()){
            Log.e("my", "is empty");
        }else{
            Log.e("my", "not empty");
        }*/

        adapter = new homeRecycler(getActivity(),myOptions2);
        mRecyclerView.setAdapter(adapter);
        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {

                DownloadXml.DownloadFromUrl(Config.XmlUrl,getActivity().openFileOutput(Config.XmlFile, Context.MODE_PRIVATE));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            try {
                SettingAdapter();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


}
