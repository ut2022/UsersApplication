package com.example.sampleapplication.login.model;

public class RegModel {
  private  String registername;

    public String getRegistername() {
        return registername;
    }

    public void setRegistername(String registername) {
        this.registername = registername;
    }

    public String getRegisterphno() {
        return registerphno;
    }

    public void setRegisterphno(String registerphno) {
        this.registerphno = registerphno;
    }

    public String getRegisteremail() {
        return registeremail;
    }

    public void setRegisteremail(String registeremail) {
        this.registeremail = registeremail;
    }

    public String getRegisterpassword() {
        return registerpassword;
    }

    public void setRegisterpassword(String registerpassword) {
        this.registerpassword = registerpassword;
    }

    private String registerphno;
  private String registeremail;
  private String registerpassword;
}
