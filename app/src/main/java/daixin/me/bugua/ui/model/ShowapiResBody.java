
package daixin.me.bugua.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowapiResBody {

    @SerializedName("pagebean")
    @Expose
    public Pagebean pagebean;
    @SerializedName("ret_code")
    @Expose
    public Integer retCode;


}
