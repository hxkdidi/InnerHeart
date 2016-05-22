
package daixin.me.bugua.ui.model.music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Songlist implements Serializable{


    @SerializedName("albumid")
    @Expose
    private Integer albumid;
    @SerializedName("albummid")
    @Expose
    private String albummid;
    @SerializedName("albumpic_big")
    @Expose
    private String albumpicBig;
    @SerializedName("albumpic_small")
    @Expose
    private String albumpicSmall;
    @SerializedName("downUrl")
    @Expose
    private String downUrl;
    @SerializedName("seconds")
    @Expose
    private Integer seconds;
    @SerializedName("singerid")
    @Expose
    private Integer singerid;
    @SerializedName("singername")
    @Expose
    private String singername;
    @SerializedName("songid")
    @Expose
    private Integer songid;
    @SerializedName("songname")
    @Expose
    private String songname;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The albumid
     */
    public Integer getAlbumid() {
        return albumid;
    }

    /**
     * 
     * @param albumid
     *     The albumid
     */
    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    /**
     * 
     * @return
     *     The albummid
     */
    public String getAlbummid() {
        return albummid;
    }

    /**
     * 
     * @param albummid
     *     The albummid
     */
    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    /**
     * 
     * @return
     *     The albumpicBig
     */
    public String getAlbumpicBig() {
        return albumpicBig;
    }

    /**
     * 
     * @param albumpicBig
     *     The albumpic_big
     */
    public void setAlbumpicBig(String albumpicBig) {
        this.albumpicBig = albumpicBig;
    }

    /**
     * 
     * @return
     *     The albumpicSmall
     */
    public String getAlbumpicSmall() {
        return albumpicSmall;
    }

    /**
     * 
     * @param albumpicSmall
     *     The albumpic_small
     */
    public void setAlbumpicSmall(String albumpicSmall) {
        this.albumpicSmall = albumpicSmall;
    }

    /**
     * 
     * @return
     *     The downUrl
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**
     * 
     * @param downUrl
     *     The downUrl
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    /**
     * 
     * @return
     *     The seconds
     */
    public Integer getSeconds() {
        return seconds;
    }

    /**
     * 
     * @param seconds
     *     The seconds
     */
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    /**
     * 
     * @return
     *     The singerid
     */
    public Integer getSingerid() {
        return singerid;
    }

    /**
     * 
     * @param singerid
     *     The singerid
     */
    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    /**
     * 
     * @return
     *     The singername
     */
    public String getSingername() {
        return singername;
    }

    /**
     * 
     * @param singername
     *     The singername
     */
    public void setSingername(String singername) {
        this.singername = singername;
    }

    /**
     * 
     * @return
     *     The songid
     */
    public Integer getSongid() {
        return songid;
    }

    /**
     * 
     * @param songid
     *     The songid
     */
    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    /**
     * 
     * @return
     *     The songname
     */
    public String getSongname() {
        return songname;
    }

    /**
     * 
     * @param songname
     *     The songname
     */
    public void setSongname(String songname) {
        this.songname = songname;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Songlist{" +
                "albumid=" + albumid +
                ", albummid='" + albummid + '\'' +
                ", albumpicBig='" + albumpicBig + '\'' +
                ", albumpicSmall='" + albumpicSmall + '\'' +
                ", downUrl='" + downUrl + '\'' +
                ", seconds=" + seconds +
                ", singerid=" + singerid +
                ", singername='" + singername + '\'' +
                ", songid=" + songid +
                ", songname='" + songname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
