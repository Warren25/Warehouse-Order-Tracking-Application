package org.warehouseOrderTrackingApplication.project;
import java.util.ArrayList;
import java.util.Date;

public class CustomerOrder implements Order {
	
	private int customerOrderID;
	private int customerID;
	private int productID;
	private float quantity;
	private float price;
	private Date arrivalDate;
	
	WarehouseWorker worker;
	Product product;
	ArrayList<Product> products = new ArrayList<Product>();
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void addProducttoOrder(Product addedProduct) {
		this.products.add(addedProduct);
	}
	
	public void setWorker(WarehouseWorker workerParam) {
		this.worker = workerParam;
	}
	
	public WarehouseWorker getWorker() {
		return this.worker;
	}
		
	private CustomerOrder(CustomerOrderBuilder builder) {
		this.customerOrderID = builder.customerOrderID;
		this.customerID = builder.customerID;
		this.productID = builder.productID;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.arrivalDate = builder.arrivalDate;
		//this.dateProcessed = builder.dateProcessed;
	}
	
	public int getCustomerOrderID() {
		return customerOrderID;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public int getProductID() {
		return productID;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public float getPrice() {
		return price;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void createOrder() {		
	}
	
	public void editOrder() {
	}
	
	
	
	/* Customer Order Builder Class */
	public static class CustomerOrderBuilder {
		private int customerOrderID;
		private int customerID;
		private int productID;
		private float quantity;
		private float price;
		private Date arrivalDate;
		//private Date dateProcessed;
		
		public CustomerOrderBuilder(int value) {
			this.customerOrderID = value;
		}
		
		public CustomerOrderBuilder customerID(int value) {
			this.customerID = value;
			return this;
		}
		
		public CustomerOrderBuilder productID(int value) {
			this.productID = value;
			return this;
		}
		
		public CustomerOrderBuilder quantity(float value) {
			this.quantity = value;
			return this;
		}
		
		public CustomerOrderBuilder price(float value) {
			this.price = value;
			return this;
		}
		
		public CustomerOrderBuilder arrivalDate(Date value) {
			this.arrivalDate = value;
			return this;
		}
		
		public CustomerOrder build() {
			return new CustomerOrder(this);
		}
	}
	/**************************************************************/

}


