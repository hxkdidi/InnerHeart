
package daixin.me.bugua.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeixinResult {

    @SerializedName("showapi_res_code")
    @Expose
    public Integer showapiResCode;
    @SerializedName("showapi_res_error")
    @Expose
    public String showapiResError;
    @SerializedName("showapi_res_body")
    @Expose
    public ShowapiResBody showapiResBody;


}
