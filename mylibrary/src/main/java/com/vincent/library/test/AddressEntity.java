package com.vincent.library.test;


import java.io.Serializable;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/5/9 14:05
 *
 * @version 1.0
 */

public class AddressEntity implements Serializable {


    /**
     * address : ？？？？-|？？？？？？？？？？¤§？？|
     * addressType : R
     * appUserId : 1094
     * city : ？·±？？3
     * consignee : ？？？？？？？？？
     * contactNumber : 18696855784
     * createdTime : 1494300951557
     * defaultAddressFlag : false
     * district : ？？？？±±？？o
     * id : 1050
     * lastModifiedTime : 1494300951557
     * lineNo : 1
     * logIdentityValue : 1050
     * longId : 1050
     * new : false
     * notNew : true
     * province : ？1？？？？
     * version : 1
     */

    private String address;
    private String addressType;
    private String appUserId;
    private String city;
    private String consignee;
    private String contactNumber;
    private long createdTime;
    private boolean defaultAddressFlag;
    private String district;
    private int id;
    private long lastModifiedTime;
    private int lineNo;
    private String logIdentityValue;
    private int longId;
    private boolean newX;
    private boolean notNew;
    private String province;
    private int version;

    private String deliveryCount;//提货数量
    private  String volume;//体积
    private boolean trailerFlag;//是否需要拖车

    public String getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(String deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public boolean isTrailerFlag() {
        return trailerFlag;
    }

    public void setTrailerFlag(boolean trailerFlag) {
        this.trailerFlag = trailerFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isDefaultAddressFlag() {
        return defaultAddressFlag;
    }

    public void setDefaultAddressFlag(boolean defaultAddressFlag) {
        this.defaultAddressFlag = defaultAddressFlag;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getLogIdentityValue() {
        return logIdentityValue;
    }

    public void setLogIdentityValue(String logIdentityValue) {
        this.logIdentityValue = logIdentityValue;
    }

    public int getLongId() {
        return longId;
    }

    public void setLongId(int longId) {
        this.longId = longId;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public boolean isNotNew() {
        return notNew;
    }

    public void setNotNew(boolean notNew) {
        this.notNew = notNew;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
