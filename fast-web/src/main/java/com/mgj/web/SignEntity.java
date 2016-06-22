package com.mgj.web;

/**
 * Created by yanqu on 2016/6/21.
 */
public class SignEntity {
    private String txtmobile = "";
    private String txtuser_name = "";
    private String txtindustry = "";
    private String txtcompany = "";

    public String getTxtmobile() {
        return txtmobile;
    }

    public void setTxtmobile(String txtmobile) {
        this.txtmobile = txtmobile;
    }

    public String getTxtuser_name() {
        return txtuser_name;
    }

    public void setTxtuser_name(String txtuser_name) {
        this.txtuser_name = txtuser_name;
    }

    public String getTxtindustry() {
        return txtindustry;
    }

    public void setTxtindustry(String txtindustry) {
        this.txtindustry = txtindustry;
    }

    public String getTxtcompany() {
        return txtcompany;
    }

    public void setTxtcompany(String txtcompany) {
        this.txtcompany = txtcompany;
    }

    @Override
    public String toString() {
        return "SignEntity{" +
                "txtmobile='" + txtmobile + '\'' +
                ", txtuser_name='" + txtuser_name + '\'' +
                ", txtindustry='" + txtindustry + '\'' +
                ", txtcompany='" + txtcompany + '\'' +
                '}';
    }
}
