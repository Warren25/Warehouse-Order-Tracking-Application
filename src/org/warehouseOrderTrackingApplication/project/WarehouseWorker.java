package org.warehouseOrderTrackingApplication.project;
import java.util.Calendar;
import java.util.Date;

import org.warehouseOrderTrackingApplication.project.CustomerOrder.CustomerOrderBuilder;

 class WarehouseWorker {

	// attributes of WarehouseWorker
	private int employeeID;
	private String name;
	private String address;
	
	CustomerOrder order = new CustomerOrder.CustomerOrderBuilder(123)
	.customerID(158)
	.productID(2)
	.quantity(5)
	.price(10)
	//.arrivalDate(date)
	.build();
	
	public WarehouseWorker(int employeeIDParam, String nameParam, String addressParam) {
		employeeID = employeeIDParam;
		name = nameParam;
		address = addressParam;
	}
	
	//set employee ID
	public void setEmployeeID(int param) {
		this.employeeID = param;
	}
	
	//get employee ID
	public int getEmployeeID() {
		return employeeID;
	}
	
	//set worker name
	public void setName(String param) {
		this.name = param;
	}
	
	//get worker name
	public String getName() {
		return name;
	}
	
	//set worker address
	public void setAddress(String param) {
		this.address = param;
	}
	
	//get worker address
	public String getAddress() {
		return address;
	}
	
	//get the start of worker shift
	public String getStartShift() {
		// Instantiate a Date object
		Date startOfShift = new Date();
		
	    // display time and date using toString()
		return this.getName() + " : " + startOfShift.toString();
	}
	
	public String getEndShift() {
		Date endOfShift = new Date();
		
		/* logic to add 8 hours to determine end of shit */
		Calendar cal = Calendar.getInstance();
		cal.setTime(endOfShift);
		cal.add(Calendar.HOUR_OF_DAY, 8);
		endOfShift = cal.getTime();
		return this.getName() + " : " + endOfShift.toString();
	}
	
	public boolean dateProcessed() {
		return false;
	}
	
	public String checkOutOrder() {
		return this.getName() + " is checking out order " + order.getCustomerID();
	}
	
	//main method to test
	public static void main(String[] args) {
		 WarehouseWorker test = new WarehouseWorker(999999, "Warren Jones", "123 fake street");
		 test.getStartShift();
		 System.out.println(test.getStartShift());
		 System.out.println(test.getEndShift());
		 System.out.println(test.checkOutOrder());
	 }
	
}
