package com.retail.springservice.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Queue implements Serializable{

	private static final long serialVersionUID = 1L;  
	
	 private int queueId;
	 private String fName;
	 private String lName; 
	 private String phone;
	 private String reason;
	 private String type; 
	 private String qComment;
	 private Timestamp qDate;
	 private String SALES_REP_ASSIGNED;
	 private String status;
	 
	 public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getqComment() {
		return qComment;
	}
	public void setqComment(String qComment) {
		this.qComment = qComment;
	}
	public Timestamp getqDate() {
		return qDate;
	}
	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}

	public void setSALES_REP_ASSIGNED(String sALES_REP_ASSIGNED) {
		SALES_REP_ASSIGNED = sALES_REP_ASSIGNED;
	}
	@Override
	public String toString() {
		return "Queue [queueId=" + queueId + ", fName=" + fName + ", lName="
				+ lName + ", phone=" + phone + ", reason=" + reason + ", type="
				+ type + ", qComment=" + qComment + ", qDate=" + qDate
				+ ", SALES_REP_ASSIGNED=" + SALES_REP_ASSIGNED + "]";
	}
	public String getSALES_REP_ASSIGNED() {
		return SALES_REP_ASSIGNED;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
	 
	 

}
