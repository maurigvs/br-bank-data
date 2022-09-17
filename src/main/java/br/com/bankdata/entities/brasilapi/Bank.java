package br.com.bankdata.entities.brasilapi;

import java.io.Serializable;

public class Bank implements Serializable {

    private String ispb;
    private String name;
    private String code;
    private String fullName;

    public String getIspb() {
        return ispb;
    }

    public void setIspb(String ispb) {
        this.ispb = ispb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        if(code != null && code.length() < 3){
            StringBuilder fullCode = new StringBuilder(code);
            while(fullCode.length() < 3){
                fullCode.reverse().append("0").reverse();
            }
            code = fullCode.toString();
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName.toUpperCase();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Bank {" +
                "ispb = '" + ispb + '\'' +
                ", name = '" + name + '\'' +
                ", code = '" + code + '\'' +
                ", fullName = '" + fullName + '\'' +
                '}';
    }
}
