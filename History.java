package com.retail.springservice.model;

import java.sql.Date;
import java.sql.Timestamp;

public class History {
	private int QUEUEID; 
	private String FNAME; 
	private String LNAME; 
	private String PHONE; 
	private Timestamp ARRIVAL_TIME; 
	private Timestamp ASSIST_TIME; 
	private Timestamp NA_TIME; 
	private Timestamp CLOSE_TIME; 
	private String SALES_REP_ASSIGNED;
	private Date QDATE;
	private String REASON;
	
	public int getQUEUEID() {
		return QUEUEID;
	}
	public void setQUEUEID(int qUEUEID) {
		QUEUEID = qUEUEID;
	}
	
	
	@Override
	public String toString() {
		return "History [QUEUEID=" + QUEUEID + ", FNAME=" + FNAME + ", LNAME="
				+ LNAME + ", PHONE=" + PHONE + ", ARRIVAL_TIME=" + ARRIVAL_TIME
				+ ", ASSIST_TIME=" + ASSIST_TIME + ", NA_TIME=" + NA_TIME
				+ ", CLOSE_TIME=" + CLOSE_TIME + ", SALES_REP_ASSIGNED="
				+ SALES_REP_ASSIGNED + ", QDATE=" + QDATE + ", REASON="
				+ REASON + "]";
	}
	public String getFNAME() {
		return FNAME;
	}
	public void setFNAME(String fNAME) {
		FNAME = fNAME;
	}
	public String getLNAME() {
		return LNAME;
	}
	public void setLNAME(String lNAME) {
		LNAME = lNAME;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public Timestamp getARRIVAL_TIME() {
		return ARRIVAL_TIME;
	}
	public void setARRIVAL_TIME(Timestamp aRRIVAL_TIME) {
		ARRIVAL_TIME = aRRIVAL_TIME;
	}
	public Timestamp getASSIST_TIME() {
		return ASSIST_TIME;
	}
	public void setASSIST_TIME(Timestamp aSSIST_TIME) {
		ASSIST_TIME = aSSIST_TIME;
	}
	public Timestamp getNA_TIME() {
		return NA_TIME;
	}
	public void setNA_TIME(Timestamp nA_TIME) {
		NA_TIME = nA_TIME;
	}
	public Timestamp getCLOSE_TIME() {
		return CLOSE_TIME;
	}
	public void setCLOSE_TIME(Timestamp cLOSE_TIME) {
		CLOSE_TIME = cLOSE_TIME;
	}
	public String getSALES_REP_ASSIGNED() {
		return SALES_REP_ASSIGNED;
	}
	public void setSALES_REP_ASSIGNED(String sALES_REP_ASSIGNED) {
		SALES_REP_ASSIGNED = sALES_REP_ASSIGNED;
	}
	public void setQDATE(Date qDATE) {
		QDATE = qDATE;
	}
	public Date getQDATE() {
		return QDATE;
	}
	public void setREASON(String rEASON) {
		REASON = rEASON;
	}
	public String getREASON() {
		return REASON;
	}
}
