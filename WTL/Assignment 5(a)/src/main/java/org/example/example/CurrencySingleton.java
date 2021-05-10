package org.example.example;

import com.opensymphony.xwork2.ActionSupport;

public class CurrencySingleton extends ActionSupport {
    public static CurrencySingleton singleton;

    public static CurrencySingleton getSingleton() {
        if (singleton == null) {
            singleton = new CurrencySingleton();
        }
        return CurrencySingleton.singleton;
    }

    public void setCurrencies() {
        usd = inr * 0.014;
        eur = inr * 0.011;
        gbp = inr * 0.0098;
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
