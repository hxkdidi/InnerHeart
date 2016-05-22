
package daixin.me.bugua.ui.model.meizhi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("budgetNum")
    @Expose
    private String budgetNum;
    @SerializedName("cert")
    @Expose
    private String cert;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("isHunjia")
    @Expose
    private String isHunjia;
    @SerializedName("isJiaju")
    @Expose
    private String isJiaju;
    @SerializedName("isLanv")
    @Expose
    private String isLanv;
    @SerializedName("isSelf")
    @Expose
    private String isSelf;
    @SerializedName("isVip")
    @Expose
    private String isVip;
    @SerializedName("lanvName")
    @Expose
    private String lanvName;
    @SerializedName("orgName")
    @Expose
    private String orgName;
    @SerializedName("portrait")
    @Expose
    private String portrait;
    @SerializedName("resUrl")
    @Expose
    private String resUrl;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userSign")
    @Expose
    private String userSign;

    /**
     * 
     * @return
     *     The budgetNum
     */
    public String getBudgetNum() {
        return budgetNum;
    }

    /**
     * 
     * @param budgetNum
     *     The budgetNum
     */
    public void setBudgetNum(String budgetNum) {
        this.budgetNum = budgetNum;
    }

    /**
     * 
     * @return
     *     The cert
     */
    public String getCert() {
        return cert;
    }

    /**
     * 
     * @param cert
     *     The cert
     */
    public void setCert(String cert) {
        this.cert = cert;
    }

    /**
     * 
     * @return
     *     The contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 
     * @param contactName
     *     The contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 
     * @return
     *     The isHunjia
     */
    public String getIsHunjia() {
        return isHunjia;
    }

    /**
     * 
     * @param isHunjia
     *     The isHunjia
     */
    public void setIsHunjia(String isHunjia) {
        this.isHunjia = isHunjia;
    }

    /**
     * 
     * @return
     *     The isJiaju
     */
    public String getIsJiaju() {
        return isJiaju;
    }

    /**
     * 
     * @param isJiaju
     *     The isJiaju
     */
    public void setIsJiaju(String isJiaju) {
        this.isJiaju = isJiaju;
    }

    /**
     * 
     * @return
     *     The isLanv
     */
    public String getIsLanv() {
        return isLanv;
    }

    /**
     * 
     * @param isLanv
     *     The isLanv
     */
    public void setIsLanv(String isLanv) {
        this.isLanv = isLanv;
    }

    /**
     * 
     * @return
     *     The isSelf
     */
    public String getIsSelf() {
        return isSelf;
    }

    /**
     * 
     * @param isSelf
     *     The isSelf
     */
    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    /**
     * 
     * @return
     *     The isVip
     */
    public String getIsVip() {
        return isVip;
    }

    /**
     * 
     * @param isVip
     *     The isVip
     */
    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    /**
     * 
     * @return
     *     The lanvName
     */
    public String getLanvName() {
        return lanvName;
    }

    /**
     * 
     * @param lanvName
     *     The lanvName
     */
    public void setLanvName(String lanvName) {
        this.lanvName = lanvName;
    }

    /**
     * 
     * @return
     *     The orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 
     * @param orgName
     *     The orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 
     * @return
     *     The portrait
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * 
     * @param portrait
     *     The portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 
     * @return
     *     The resUrl
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 
     * @param resUrl
     *     The resUrl
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The userSign
     */
    public String getUserSign() {
        return userSign;
    }

    /**
     * 
     * @param userSign
     *     The userSign
     */
    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "budgetNum='" + budgetNum + '\'' +
                ", cert='" + cert + '\'' +
                ", contactName='" + contactName + '\'' +
                ", isHunjia='" + isHunjia + '\'' +
                ", isJiaju='" + isJiaju + '\'' +
                ", isLanv='" + isLanv + '\'' +
                ", isSelf='" + isSelf + '\'' +
                ", isVip='" + isVip + '\'' +
                ", lanvName='" + lanvName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", portrait='" + portrait + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSign='" + userSign + '\'' +
                '}';
    }
}
