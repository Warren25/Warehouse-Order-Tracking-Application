package org.warehouseOrderTrackingApplication.project;

import java.util.ArrayList;

public class Product {
	
	private String name;
	private int productID;
	private int quantity;
	private boolean inStock;
	private String location;
	private boolean pWare;
	
		
	public Product(String nameParam, int productIDParam, int quantityParam, boolean inStockParam, String locationParam, boolean pWareParam){
		this.name = nameParam;
		this.productID = productIDParam;
		this.quantity = quantityParam;
		this.inStock = inStockParam;
		this.location = locationParam;
		this.pWare = pWareParam;
	}
		
	public String getName(){
		return name;
	}
	
	public int getProductID(){
		return productID;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public boolean getInStock(){
		return inStock;
	}
	
	public String getLocation(){
		return location;
	}
	
	public boolean getPWare(){
		return pWare;
	}
	
	public void printProduct() {
		System.out.println("Product Id | " + " Product Name | " + " Quantity | " + " Location | " + " PorousWare Applicable to Item?");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println(getProductID() + "         " + this.getName() + "               " + this.getQuantity() + "              " + this.getLocation() + "         " + this.getPWare());
		System.out.println("\n");
	}
	
	public static void main(String[] args){
		Product rake = new Product("Rake", 12345, 5, true, "A1", true);
		rake.printProduct();
	}
	
}
