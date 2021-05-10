package org.example.example;

import com.opensymphony.xwork2.ActionSupport;

public class Success extends ActionSupport {

    @Override
    public String execute() throws Exception {
        CurrencySingleton singleton = CurrencySingleton.getSingleton();
        this.inr = singleton.inr;
        this.usd = Double.parseDouble(String.format("%.3f", singleton.usd));
        this.gbp = Double.parseDouble(String.format("%.3f", singleton.gbp));
        this.eur = Double.parseDouble(String.format("%.3f", singleton.eur));
        return SUCCESS;
    }

    public double inr;
    public double usd;
    public double eur;
    public double gbp;

    public double getInr() {
        return inr;
    }

    public void setInr(double inr) {
        this.inr = inr;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getGbp() {
        return gbp;
    }

    public void setGbp(double gbp) {
        this.gbp = gbp;
    }
}
