package com.project.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.*;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_order_id")
	private int id;
	private String customerName;
	private String customerEmail;
	private int quantity;
	private String status;
	private int totalCost;
	private long CustomerMobileNumber;
	private String orderCreatedTime;
	private String orderDeliveredTime;
	
	@ManyToOne
	private Staff staff;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Item> items;
	public List<Item> getItems(){
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public long getCustomerMobileNumber() {
		return CustomerMobileNumber;
	}
	public void setCustomerMobileNumber(long customerMobileNumber) {
		CustomerMobileNumber = customerMobileNumber;
	}
	public String getOrderCreatedTime() {
		return orderCreatedTime;
	}
	public void setOrderCreatedTime(String orderCreatedTime) {
		this.orderCreatedTime = orderCreatedTime;
	}
	public String getOrderDeliveredTime() {
		return orderDeliveredTime;
	}
	public void setOrderDeliveredTime(String orderDeliveredTime) {
		this.orderDeliveredTime = orderDeliveredTime;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}
