package com.retail.springservice.model;

public class ExistingCustomer {

	private String phone;
	private String first_name;
	private String last_name;
	private String status;
	@Override
	public String toString() {
		return "ExistingCustomer [phone=" + phone + ", first_name="
				+ first_name + ", last_name=" + last_name + ", status="
				+ status + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
