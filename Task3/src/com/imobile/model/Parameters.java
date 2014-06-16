package com.imobile.model;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
    private List<String> favoriteNumber;
    private String tarification;
    private double connectionFee;
    
    public Parameters() {
	favoriteNumber = new ArrayList<String>();
    }
    
    public String getFavoriteNumber(int index) {
        return favoriteNumber.get(index);
    }
    public List<String> getFavoriteNumbers() {
	return favoriteNumber;
    }
    public void setFavoriteNumber(String favoriteNumber) {
        this.favoriteNumber.add( favoriteNumber );
    }
    public String getTarification() {
        return tarification;
    }
    public void setTarification(String tarification) {
        this.tarification = tarification;
    }
    public double getConnectionFee() {
        return connectionFee;
    }
    public void setConnectionFee(double connectionFee) {
        this.connectionFee = connectionFee;
    }
    
    
    
}
