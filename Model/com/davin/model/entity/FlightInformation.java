package com.davin.model.entity;

public class FlightInformation {

	private String fromTo;
	private String eta;
	private String lowFare;
	private String hiFlyer;
	public String getFromTo() {
		return fromTo;
	}
	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getLowFare() {
		return lowFare;
	}
	public void setLowFare(String lowFare) {
		this.lowFare = lowFare;
	}
	public String getHiFlyer() {
		return hiFlyer;
	}
	public void setHiFlyer(String hiFlyer) {
		this.hiFlyer = hiFlyer;
	}
	
}
