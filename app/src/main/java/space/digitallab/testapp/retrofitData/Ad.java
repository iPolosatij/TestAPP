package space.digitallab.testapp.retrofitData;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad implements Serializable
{

    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("text")
    @Expose
    private String text;
    private final static long serialVersionUID = 3409543114080518022L;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}