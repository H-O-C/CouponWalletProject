package com.example.mylogin;

public class Coupons {
    //thing
    //region Data Values
    public String ID;
    public String store;
    public String merch;
    public String offer;
    public String title;
    public String desc;
    public String code;
    public String terms;
    public String cate;
    public String feat;
    public String url;
    public String link;
    public String image;
    public String type;
    public String values;
    public String status;
    public String startdate;
    public String enddate;
    //endregion

    //region get methods
    public String getCate() {
        return cate;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getMerch() {
        return merch;
    }

    public String getOffer() {
        return offer;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getStore() {
        return store;
    }

    public String getTerms() {
        return terms;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getID() {
        return ID;
    }

    public String isFeat() {
        return feat;
    }

    public String isStatus() {
        return status;
    }

    public String getValues() {
        return values;
    }
    //endregion

    //region set methods
    public void setCate(String cate) {
        this.cate = cate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMerch(String merch) {
        this.merch = merch;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setValues(String values) {
        this.values = values;
    }
//endregion


    void setCoupon(String _id, String _store, String _merch, String _offer, String _title, String _desc, String _code, String _terms, String _cate, String _feat, String _url, String _link, String _image, String _type, String _values, String _status, String _startdate, String _enddate) {
        ID = _id ;
        store = _store;
        merch = _merch;
        offer = _offer;
        title = _title;
        desc = _desc;
        code = _code;
        terms = _terms;
        cate = _cate;
        feat = _feat;
        url = _url;
        link = _link;
        image = _image;
        type = _type;
        values = _values;
        status = _status;
        startdate = _startdate;
        enddate = _enddate;


    }


}