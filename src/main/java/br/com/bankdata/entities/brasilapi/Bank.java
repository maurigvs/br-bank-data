package br.com.bankdata.entities.brasilapi;

import java.io.Serializable;

public class BankInfo implements Serializable {

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
}
