package Data_items;

/**
 * Created by kinsley kajiva on 4/19/2016.
 */
public class home_items {
    private String articleTitle;
    private String articlePubDate;
    private String articleDescription;
    private String articleCategory;

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticlePubDate(String articlePubDate) {
        this.articlePubDate = articlePubDate;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticlePubDate() {
        return articlePubDate;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public home_items() {
    }
    @Override
    public String toString() {
        return "StackSite [name=" + articleTitle + ", link=" + articlePubDate + ", about="
                + articleDescription + ", imgUrl=" + articleCategory + "]";
    }

    public home_items(String articleTitle, String articlePubDate, String articleDescription, String articleCategory) {
        this.articleTitle = articleTitle;
        this.articlePubDate = articlePubDate;
        this.articleDescription = articleDescription;
        this.articleCategory = articleCategory;
    }
}
