
package daixin.me.bugua.ui.model.music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pagebean {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("ret_code")
    @Expose
    private Integer retCode;
    @SerializedName("songlist")
    @Expose
    private List<Songlist> songlist = new ArrayList<Songlist>();
    @SerializedName("total_song_num")
    @Expose
    private Integer totalSongNum;

    /**
     * 
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The retCode
     */
    public Integer getRetCode() {
        return retCode;
    }

    /**
     * 
     * @param retCode
     *     The ret_code
     */
    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    /**
     * 
     * @return
     *     The songlist
     */
    public List<Songlist> getSonglist() {
        return songlist;
    }

    /**
     * 
     * @param songlist
     *     The songlist
     */
    public void setSonglist(List<Songlist> songlist) {
        this.songlist = songlist;
    }

    /**
     * 
     * @return
     *     The totalSongNum
     */
    public Integer getTotalSongNum() {
        return totalSongNum;
    }

    /**
     * 
     * @param totalSongNum
     *     The total_song_num
     */
    public void setTotalSongNum(Integer totalSongNum) {
        this.totalSongNum = totalSongNum;
    }

}
