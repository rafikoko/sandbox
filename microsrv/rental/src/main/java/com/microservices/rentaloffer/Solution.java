package com.microservices.rentaloffer;

public class Solution {

	private SolutionType name;
	private String value;
	private long lastHandlerUUID;
	
	//public final static String OFFER_PRICE = "OFFER_PRICE";
	//public final static String BEST_OFFER_PRICE = "BEST_OFFER_PRICE";
	//public final static String MEMBERSHIP = "MEMBERSHIP";
	
        public enum SolutionType{
            OFFER_PRICE,
            BEST_OFFER_PRICE,
            MEMBERSHIP
        }
        
	public Solution(SolutionType name, String value, long lastHandlerUUID) {
		super();
		this.name = name;
		this.value = value;
		this.lastHandlerUUID = lastHandlerUUID;
	}
	public SolutionType getName() {
		return name;
	}
	public void setName(SolutionType name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getLastHandlerUUID() {
		return lastHandlerUUID;
	}
	public void setLastHandlerUUID(long lastHandlerUUID) {
		this.lastHandlerUUID = lastHandlerUUID;
	}
	
}
