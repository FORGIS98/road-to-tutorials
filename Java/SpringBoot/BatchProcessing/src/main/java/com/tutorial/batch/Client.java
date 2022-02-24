package com.tutorial.batch;

public class Client {

    private String idParty;
    private String commercialBrand;
    private String contactMediumType;
    private String contactMediumValue;
    private String verified;

    public Client() {
    }

    public Client(String idParty, String commercialBrand, String contactMediumType, String contactMediumValue,
            String verified) {
        this.idParty = idParty;
        this.commercialBrand = commercialBrand;
        this.contactMediumType = contactMediumType;
        this.contactMediumValue = contactMediumValue;
        this.verified = verified;
    }

    public void setIdParty(String idParty) {
        this.idParty = idParty;
    }

    public String getIdParty() {
        return idParty;
    }

    public void setCommercialBrand(String commercialBrand) {
        this.commercialBrand = commercialBrand;
    }

    public String getCommercialBrand() {
        return commercialBrand;
    }

    public void setContactMediumType(String contactMediumType) {
        this.contactMediumType = contactMediumType;
    }

    public String getContactMediumType() {
        return contactMediumType;
    }

    public void setContactMediumValue(String contactMediumValue) {
        this.contactMediumValue = contactMediumValue;
    }

    public String getContactMediumValue() {
        return contactMediumValue;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getVerified() {
        return verified;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("idParty: " + idParty);
        str.append(" - commercialBrand: " + commercialBrand);
        str.append(" - contactMediumType: " + contactMediumType);
        str.append(" - contactMediumValue: " + contactMediumValue);
        str.append(" - verified: " + verified);

        return str.toString();
    }

}
