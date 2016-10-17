package com.retail.springservice.model;

import java.io.*;

public class SalesRepDetails implements Serializable
{
	private static final long serialVersionUID = 1L;  
	
	private int sales_rep_id;
	private String first_name;
	private String last_name;
	private String contact_no;
	private String email;
	private String password;
	private String desig;
	
	@Override
	public String toString() {
		return "SalesRepDetails [sales_rep_id=" + sales_rep_id
				+ ", first_name=" + first_name + ", last_name=" + last_name
				+ ", contact_no=" + contact_no + ", email=" + email
				+ ", password=" + password + ", desig=" + desig + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSales_rep_id() {
		return sales_rep_id;
	}
	public void setSales_rep_id(int sales_rep_id) {
		this.sales_rep_id = sales_rep_id;
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
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getDesig() {
		return desig;
	}

	
	
	
}
