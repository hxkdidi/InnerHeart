
package daixin.me.bugua.ui.model.meizhi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MeiZhiResult {

    @SerializedName("col")
    @Expose
    private String col;
    @SerializedName("imgs")
    @Expose
    private List<Img> imgs = new ArrayList<Img>();
    @SerializedName("returnNumber")
    @Expose
    private int returnNumber;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("startIndex")
    @Expose
    private int startIndex;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("tag3")
    @Expose
    private String tag3;
    @SerializedName("totalNum")
    @Expose
    private int totalNum;

    /**
     *
     * @return
     *     The col
     */
    public String getCol() {
        return col;
    }

    /**
     *
     * @param col
     *     The col
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     *
     * @return
     *     The imgs
     */
    public List<Img> getImgs() {
        return imgs;
    }

    /**
     *
     * @param imgs
     *     The imgs
     */
    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }

    /**
     *
     * @return
     *     The returnNumber
     */
    public int getReturnNumber() {
        return returnNumber;
    }

    /**
     *
     * @param returnNumber
     *     The returnNumber
     */
    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    /**
     *
     * @return
     *     The sort
     */
    public String getSort() {
        return sort;
    }

    /**
     *
     * @param sort
     *     The sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     *
     * @return
     *     The startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     *
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     *
     * @return
     *     The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     *     The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     *
     * @return
     *     The tag3
     */
    public String getTag3() {
        return tag3;
    }

    /**
     *
     * @param tag3
     *     The tag3
     */
    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    /**
     *
     * @return
     *     The totalNum
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     *
     * @param totalNum
     *     The totalNum
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "MeiZhiResult{" +
                "col='" + col + '\'' +
                ", imgs=" + imgs +
                ", returnNumber=" + returnNumber +
                ", sort='" + sort + '\'' +
                ", startIndex=" + startIndex +
                ", tag='" + tag + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", totalNum=" + totalNum +
                '}';
    }
}
