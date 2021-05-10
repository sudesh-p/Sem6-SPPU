package org.example.example;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {

    public String execute() {
        try {
            CurrencySingleton singleton = CurrencySingleton.getSingleton();
            singleton.inr = Double.parseDouble(inrString);
            singleton.setCurrencies();
        } catch (Exception e) {
            addActionError("You have to entre a number");
            return ERROR;
        }

        return SUCCESS;
    }

    private String inrString;

    public String getInrString() {
        return inrString;
    }

    public void setInrString(String inrString) {
        this.inrString = inrString;
    }
}
