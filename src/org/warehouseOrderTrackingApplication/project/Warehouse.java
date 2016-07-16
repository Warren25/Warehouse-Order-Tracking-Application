package org.warehouseOrderTrackingApplication.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Warehouse {
	private ArrayList<WarehouseWorker> workers;
	private ArrayList<CustomerOrder> custOrders;
	private ArrayList<Product> products;

	public Warehouse() {
		
		workers = new ArrayList<WarehouseWorker>();
		custOrders = new ArrayList<CustomerOrder>();
		products = new ArrayList<Product>();
		
		// Hard code Warehouse Worker dummy data
		WarehouseWorker John = new WarehouseWorker(1234576, "John Smith", "123 Fake Street");
		WarehouseWorker Mary = new WarehouseWorker(1234567, "Mary Sue", "234 Fake Lane");
		WarehouseWorker Benn = new WarehouseWorker(2345678, "Benn Grimm", "321 Fake Road");
		
		// Hard code Order dummy data
		Date date = new Date();
		Date shiftDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(shiftDate);
		cal.add(Calendar.HOUR_OF_DAY, 8);
		shiftDate = cal.getTime();
		
		CustomerOrder order1 = new CustomerOrder.CustomerOrderBuilder(123).customerID(158).productID(2687).quantity(5).price(10).arrivalDate(date).build();
		CustomerOrder order2 = new CustomerOrder.CustomerOrderBuilder(124).customerID(158).productID(3098).quantity(5).price(10).arrivalDate(date).build();
		CustomerOrder order3 = new CustomerOrder.CustomerOrderBuilder(125).customerID(158).productID(4666).quantity(5).price(10).arrivalDate(shiftDate).build();
		
		// Hard code Product Dummy Data
		Product blueGnome = new Product("Blue Gnome", 12345, 5, true, "A1", false);
		Product redGnome = new Product("Red Gnome", 12345, 10, true, "B2", false);
		Product yellowGnome = new Product("Yellow Gnome", 12345, 20, true, "C3", true);
		Product greenGnome = new Product("Green Gnome", 12345, 35, false, "D4", true);
		Product blackGnome = new Product("Black Gnome", 12345, 55, false, "E5", true);
		
		// Implement Dummy Data
		addWorker(John);
		addWorker(Mary);
		addWorker(Benn);
		addOrder(order1);
		addOrder(order2);
		addOrder(order3);
		addProduct(blackGnome);
		addProduct(greenGnome);
		addProduct(yellowGnome);
		addProduct(redGnome);
		addProduct(blueGnome);
		order1.addProducttoOrder(blackGnome);
		order1.addProducttoOrder(greenGnome);
		order1.addProducttoOrder(yellowGnome);
		order2.addProducttoOrder(redGnome);
		order2.addProducttoOrder(blueGnome);
		order3.addProducttoOrder(blackGnome);
	}
	
	public ArrayList<CustomerOrder> getOrders() {
		return custOrders;
	}

	public CustomerOrder getOrder(int x) {
		for (CustomerOrder coInList : custOrders) {
			if (coInList.getCustomerOrderID() == x)
				return coInList;
		}
		return null;
	}

	public WarehouseWorker getWorker(int x) {
		for (WarehouseWorker workerInList : workers) {
			if (workerInList.getEmployeeID() == x)
				return workerInList;
		}
		return null;
	}

	public void addProduct(Product addedProduct) {
		this.products.add(addedProduct);
	}

	public void removeProduct(Product removedProduct) {
		this.products.remove(removedProduct);
	}

	public void addOrder(CustomerOrder addedOrder) {
		this.custOrders.add(addedOrder);
	}

	public void removeOrder(CustomerOrder removedOrder) {
		this.custOrders.remove(removedOrder);
	}

	public void addWorker(WarehouseWorker addedWorker) {
		this.workers.add(addedWorker);
	}

	public void removeWorker(CustomerOrder removedWorker) {
		this.workers.remove(removedWorker);
	}

	public ArrayList<WarehouseWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(ArrayList<WarehouseWorker> workers) {
		this.workers = workers;
	}

	public ArrayList<CustomerOrder> getCustOrders() {
		return custOrders;
	}

	public void setCustOrders(ArrayList<CustomerOrder> custOrders) {
		this.custOrders = custOrders;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public ArrayList<CustomerOrder> getOrdersForWorker(WarehouseWorker a) {
		ArrayList<CustomerOrder> result = new ArrayList<CustomerOrder>();
		for (CustomerOrder anOrder : this.getOrders()) {
			if((anOrder.getWorker() != null) && (anOrder.getWorker().getName().equals(a.getName())))
			{
				result.add(anOrder);
			}
		}
		
		return result;
	}
	
	public boolean assignOrderToWorker(CustomerOrder orderToBeCatched, WarehouseWorker catcher) {
		
		if (orderToBeCatched.getWorker() == null)
		{
			orderToBeCatched.setWorker(catcher);
			return true;
		}
			else {
				return false;
			}	
		}
}