package com.littlepay.au;

public class Taps {

	private Integer id;
	private String dateTimeUTC;
	private String tapType;
	private String stopId;
	private String companyId;
	private String busId;
	private String pan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateTimeUTC() {
		return dateTimeUTC;
	}

	public void setDateTimeUTC(String dateTimeUTC) {
		this.dateTimeUTC = dateTimeUTC;
	}

	public String getTapType() {
		return tapType;
	}

	public void setTapType(String tapType) {
		this.tapType = tapType;
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@Override
	public String toString() {
		return "Taps [id=" + id + ", dateTimeUTC=" + dateTimeUTC + ", tapType=" + tapType + ", stopId=" + stopId
				+ ", companyId=" + companyId + ", busId=" + busId + ", pan=" + pan + "]";
	}

}
