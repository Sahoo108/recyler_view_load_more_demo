package com.nexdev.enyason.nestedrv.Model;

public class Resurantlistdatahorizontal {
    private int resturant_id;
    private String name;
    private String Resturantshortdesc;
    private String Offerdesc;
    private String retviw;
    private String closingtime;
    private String openingtime;
    private String image;
    private String discount;

    private String budget;
    private String ribbondiscount;
    private String delivercharge;
    private String deliverytime;
    private String packcharge;
    private String cupon;
    private String gst;
    private String rest_carge;
    private String rest_mobileno;
    public static final int hresturantlist_layout = 3;

    //*******************ViewType Layout Return****************************//
    public int getViewtype() {
        return viewtype;
    }

    public void setViewtype(int viewtype) {
        this.viewtype = viewtype;
    }

    private int viewtype;

    public int getResturant_id() {
        return resturant_id;
    }

    public void setResturant_id(int resturant_id) {
        this.resturant_id = resturant_id;
    }

    public String getResturantshortdesc() {
        return Resturantshortdesc;
    }

    public void setResturantshortdesc(String resturantshortdesc) {
        Resturantshortdesc = resturantshortdesc;
    }

    public String getOfferdesc() {
        return Offerdesc;
    }

    public void setOfferdesc(String offerdesc) {
        Offerdesc = offerdesc;
    }

    public String getRetviw() {
        return retviw;
    }

    public void setRetviw(String retviw) {
        this.retviw = retviw;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public void setClosingtime(String closingtime) {
        this.closingtime = closingtime;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRibbondiscount() {
        return ribbondiscount;
    }

    public void setRibbondiscount(String ribbondiscount) {
        this.ribbondiscount = ribbondiscount;
    }

    public String getDelivercharge() {
        return delivercharge;
    }

    public void setDelivercharge(String delivercharge) {
        this.delivercharge = delivercharge;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public String getPackcharge() {
        return packcharge;
    }

    public void setPackcharge(String packcharge) {
        this.packcharge = packcharge;
    }

    public String getCupon() {
        return cupon;
    }

    public void setCupon(String cupon) {
        this.cupon = cupon;
    }

    public String getGst() {
        return gst;
    }

    public String getRest_carge() {
        return rest_carge;
    }

    public void setRest_carge(String rest_carge) {
        this.rest_carge = rest_carge;
    }

    public String getRest_mobileno() {
        return rest_mobileno;
    }

    public void setRest_mobileno(String rest_mobileno) {
        this.rest_mobileno = rest_mobileno;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public static int getHresturantlist_layout() {
        return hresturantlist_layout;
    }

    //*******************ViewType Layout Return****************************//
    public Resurantlistdatahorizontal(int resturant_id, int viewtype, String Resturantname, String Resturantshortdesc, String Offerdesc, String retviw, String closingtime, String openingtime, String image,
                                      String discount, String budget, String ribbondiscount, String delivercharge, String deliverytime, String packcharge, String cupon, String gst, String rest_carge, String rest_mobileno) {
        this.resturant_id = resturant_id;
        this.viewtype=viewtype;
        this.name = Resturantname;
        this.Resturantshortdesc = Resturantshortdesc;
        this.Offerdesc = Offerdesc;
        this.retviw = retviw;
        this.closingtime = closingtime;
        this.openingtime = openingtime;
        this.image = image;
        this.discount=discount;
        this.budget = budget;
        this.ribbondiscount = ribbondiscount;
        this.delivercharge = delivercharge;
        this.deliverytime = deliverytime;
        this.packcharge = packcharge;
        this.cupon = cupon;
        this.gst = gst;
        this.rest_carge = rest_carge;
        this.rest_mobileno = rest_mobileno;

    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

}
