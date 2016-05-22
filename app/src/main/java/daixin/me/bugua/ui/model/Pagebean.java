
package daixin.me.bugua.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pagebean {

    @SerializedName("allNum")
    @Expose
    public Integer allNum;
    @SerializedName("allPages")
    @Expose
    public Integer allPages;
    @SerializedName("contentlist")
    @Expose
    public List<Contentlist> contentlist = new ArrayList<Contentlist>();
    @SerializedName("currentPage")
    @Expose
    public Integer currentPage;
    @SerializedName("maxResult")
    @Expose
    public Integer maxResult;


}
