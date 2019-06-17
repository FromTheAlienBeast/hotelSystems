package cn.etity;

import java.util.Date;

public class Orders {

	private int id;
	private int table_id;
	private String orderDate;
	private Double totalPrice;
	private int orderStatus=0;
	private int numbers=1;
	private String foodName;
	private double price;
	
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Double getTotalPrice() {
		return (getPrice()*getNumbers());
	}
	/*public void setTotalPrice(double totalPrice) {
		totalPrice=this.price*this.numbers;
	}*/
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
	
	
}
