package com.imobile.model;

public class Tariff implements Comparable<Tariff> {
    
    private String id;
    private String name;
    private String operator;
    private double payroll;
    private CallPrices callPrices;
    private double smsPrice;
    private Parameters parameters;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public double getPayroll() {
        return payroll;
    }
    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }
    public CallPrices getCallPrices() {
        return callPrices;
    }
    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }
    public double getSmsPrice() {
        return smsPrice;
    }
    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }
    public Parameters getParameters() {
        return parameters;
    }
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	
	sb.append("Tariff id = ").append(id).append("\n")
		.append("name: ").append(name)
		.append("\nopearator: ").append(operator)
		.append("\nparoll: ").append(payroll)
		.append("\ncall-prices:");
        		
	if (callPrices != null) {
	    	sb.append("\n\tWithinNetwork: ").append(callPrices.getWithinNetwork())
		.append("\n\tother-networks: ").append(callPrices.getOtherNetwork())
		.append("\n\tlandlines: ").append(callPrices.getLandlines());
	}
		sb.append("\nSMS-price: ").append(smsPrice)
    		.append("\nParameters: ");
	if (parameters != null) {
        	for (String number : parameters.getFavoriteNumbers()) {
        	    sb.append("\n\tfavorite-number: ").append(number);
        	}
    		sb.append("\n\ttarification: ").append(parameters.getTarification())
    		.append("\n\tconnection-fee: ").append(parameters.getConnectionFee());
	}
		
        return sb.toString();
    }
    
    @Override
    public int compareTo(Tariff other) {
	return id.compareTo(other.getId());
    }
    
}
