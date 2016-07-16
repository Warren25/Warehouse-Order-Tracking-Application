package org.warehouseOrderTrackingApplication.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WOTSDemoApplication {	
	/***********************************************************************************************************/	
	// Declares and initializes the warehouse dummy data
	private Warehouse warehouse = new Warehouse();
	
	// Variable to determine employee's id
	private int employeeID;
	
	// Variable to determine if there was not a valid match to any customer order id's for employee checkouts   
	int errorCount = 0;
	
	// Variable to set maximum number of orders a Warehouse Worker can checkout, initialized to zero
	int maxOrders = 0;
	
	// Loop Control Variables
	boolean hasEnteredValidCustomerOrderId = false;
	boolean hasEnteredValidEmployeeID = false;
	boolean hasEnteredZero = false;
	
	// User input
	int input = 1;
	/***********************************************************************************************************/

	public static void main(String[] args) {
		WOTSDemoApplication demo = new WOTSDemoApplication();		
	}
	
	public WOTSDemoApplication(){

		Scanner in = new Scanner(System.in);
		
		/* Application code START */
		
		// Prompts user for login and assigns to employeeID
		employeeID = loginEmployee();
		
		// Prints menu and order list
		menu();
		printOutOrders();
		
		// Employee views orders
		viewOrders();
		
		// Loop Control Variable reset
		hasEnteredValidCustomerOrderId = false;
		
		// Employee assigns orders to themselves
		orderCheckOut(employeeID);
			
		System.out.println("      ***** Application End *****" + "\n" + "\n");
		System.out.println("***** WAREHOUSE ORDER TRACKING *****" + "\n" + "\n");
		
		/* Application Code END */
		
	}
	
	public int loginEmployee(){
		/*****************************************EMPLOYEE ID PROMPT LOGIC**********************************************/
		Scanner in = new Scanner(System.in);
		
		int id = 0;
		
		boolean hasEnteredValidEmployeeID = false;		
		
		System.out.print("Enter Employee ID: " + "\n");
		do
		  {
			try
			
				{	
					id = in.nextInt();
					
					for(WarehouseWorker workerListItem: warehouse.getWorkers()) {
						
						if(workerListItem.getEmployeeID() == id)
						{
							hasEnteredValidEmployeeID = true;
							break;
						}
						
					}
					
					if(hasEnteredValidEmployeeID != true)
					{
						System.out.println("Invalid Id. Please enter valid Employee Id." + "\n" + "\n");
					}
				}
			
				catch (InputMismatchException e)
				{
					{
						System.out.println("You entered invalid input. Please enter valid Employee Id." + "\n" + "\n");
					}
				}
			
			// clears input buffer
			in.nextLine();
			} while(hasEnteredValidEmployeeID != true);
		
			return id;
		/*****************************************EMPLOYEE ID PROMPT LOGIC**********************************************/
	}
	
	public void viewOrders(){
		/*****************************************VIEW ORDER LOGIC**********************************************/
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Customer Order ID to view details, or Enter 0 when finish: ");
		do
		{
			try
			{
				
				input = in.nextInt();		
				
				for (CustomerOrder customerOrderListItem : warehouse.getOrders()) {
					
					if (customerOrderListItem.getCustomerOrderID() == input)
					{
						for (Product productListItem: customerOrderListItem.getProducts())
							
							productListItem.printProduct();
							
							System.out.println("To Continue, Enter Customer Order ID to view details, or Enter 0 when finish: ");
							
							hasEnteredValidCustomerOrderId = true;
							
							break;
					}
					
					else if (input == 0)
					{
						hasEnteredZero = true;
						hasEnteredValidCustomerOrderId = true;
					}
					
					else
						hasEnteredValidCustomerOrderId = false;
				}
				
				if (hasEnteredValidCustomerOrderId == true && hasEnteredZero == true)
				{
					System.out.println(" ");
				}
				
				else if(hasEnteredValidCustomerOrderId == false)
				{
					System.out.println("Customer Order ID not found. Please enter a valid Customer Order. ");
				}
				
				else if (hasEnteredValidCustomerOrderId == true)
				{
					System.out.println(" " + "\n");
				}
				
				
			}
			catch (InputMismatchException e)
				{
					System.out.println("You entered invalid input. Enter Customer Order ID to view details, or Enter 0 when finish: ");
				}
			in.nextLine();
		}
		while (input != 0);
		/*****************************************VIEW ORDER LOGIC**********************************************/
	}
	
	public void orderCheckOut(int employeeIdParam){
/*****************************************ORDER CHECKOUT LOGIC**********************************************/
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Customer Order ID (3 max.) to checkout, or Enter 0 to complete checkout: ");
		
		do
		{
			try 
			{ 
				input = in.nextInt();
				
				for(CustomerOrder customerOrderListItem : warehouse.getOrders()) {
					
					if(customerOrderListItem.getCustomerOrderID() == input) {
						
						// Checks if input is a customer order id that has already been checked out
						if(!warehouse.assignOrderToWorker(warehouse.getOrder(input), warehouse.getWorker(employeeIdParam))){
							System.out.println("You have entered the id of an Customer Order that is already checked out. Please enter a valid Customer Order.");
							
							// Subtracts the order that user tried to checkout
							maxOrders = maxOrders - 1;
							
						}
						else
						// Confirms to user that customer order input is assigned to them
						System.out.println("Employee " + warehouse.getWorker(employeeIdParam).getName() + 
								" is handling customer order "  + warehouse.getOrder(input).getCustomerOrderID());
						
						// Marks that input does correspond to valid customer order id
						hasEnteredValidCustomerOrderId = true;
						
						// Adds order to queue
						maxOrders = maxOrders + 1;
						
						// Assigns order to worker
						warehouse.assignOrderToWorker(warehouse.getOrder(input), warehouse.getWorker(employeeIdParam));
						break;
						
					}
					
					// Since "0" is a condition for completion, code block informs user of checkout completion, and assigns 3 to maxOrders to end the loop
					else if (0 == input)
					{
						System.out.println("Checkout Complete.");
						maxOrders = 3;
						break;
					}
					
				}
				
				// Checks if there was a valid input, and if not, prompts user to input again			
				if (hasEnteredValidCustomerOrderId != true) {
					System.out.println("Customer Order ID not found. Please enter a valid Customer Order.");
				}
				
				// Checks if orders have been checked out, and if so, terminates loop
				if (maxOrders == 3) {
					System.out.println("You have entered 3 orders. Checkout Complete.");
					
				}
				
				// Returns to false, otherwise variable stays at true, and program is unable to determine if input is incorrect customer order id 
				hasEnteredValidCustomerOrderId = false;
				
				System.out.println("\n" + "\n");
				
				
			}
			
			// Catches character input exception
			catch(InputMismatchException e) 
			{
				System.out.println("You entered invalid input. Enter Customer Order ID (3 max.) to checkout, or Enter 0 to complete checkout: ");
				in.nextLine();
			} 
			
		}
		// Warehouse Workers can only checkout 3 orders at a time, to change this number, maxOrders simply needs to be assigned new value for scalability
		while (maxOrders < 3);
		
		
		/*****************************************ORDER CHECKOUT LOGIC**********************************************/
	}
	
	public void menu() {
		System.out.println("***** WAREHOUSE ORDER TRACKING *****" + "\n" + "\n");
		System.out.println("LIST OF ORDERS");
		System.out.println("--------------" + "\n");
		System.out.println("Order ID | " + " Customer ID | " + " Product ID | " + 
		" Quantity | " + " Price | " + " Arrival Date");
		System.out.println("-----------------------------------------------------" +
				"---------------------");
	}
	
	public void printOutOrders() {
		for (CustomerOrder list : warehouse.getOrders()) {
			System.out.println(list.getCustomerOrderID() + "            " + list.getCustomerID() + "          " + list.getProductID() + "          " + list.getQuantity() + "        " + list.getPrice() + "     " + list.getArrivalDate());
			System.out.println("\n" + "\n" + "\n" + "\n" + "\n");
		}
				
	}

}

/* 
 * 	Created 3 while loops since workers can only check out 3 orders at a time, what if they could check out 10 orders?, bad for scalability
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * // Employee checks out first order		
		while (hasEnteredValidCustomerOrderId != true) {
			
			System.out.println("Enter First Customer Order ID to checkout, or Enter 0 to complete checkout: ");
			input = in.nextInt();
			
			for(CustomerOrder customerOrderListItem : Warehouse.getOrders()) {
				if(customerOrderListItem.getCustomerOrderID() == input) {
					System.out.println("Employee " + Warehouse.getWorker(employeeID).getName() + 
							" is handling customer order "  + Warehouse.getOrder(input).getCustomerOrderID());
					
					// Variable becomes true since user has entered valid ID;
					hasEnteredValidCustomerOrderId = true;
					
					// Assigns order to worker
					Warehouse.assignOrderToWorker(Warehouse.getOrder(input), Warehouse.getWorker(employeeID));
					break;
				}
				else if (0 == input)
				{
					System.out.println("Checkout Complete.");
					hasEnteredValidCustomerOrderId = true;
					break;
				}
				else if (customerOrderListItem.getCustomerOrderID() != input)
				{
					errorCount = errorCount + 1;
				}
			}
			
			// Checks if there was a valid input, and if not, prompts user to input again 
			if (errorCount == Warehouse.getOrders().size()){
				System.out.println("Customer Order ID not found. Please enter a valid Customer Order.");
				errorCount = 0;
			}
			
			System.out.println("\n" + "\n");
		}
		
		
		// Variable reset
		hasEnteredValidCustomerOrderId = false;
		
		// Variable reset, minus one, because a customer order has been selected for
		// first checkout, thus the array list size is - 1.
		errorCount = 0 - 1;
		
		
		
		// Employee checks out second order
		while (hasEnteredValidCustomerOrderId != true) {
			
			System.out.println("Enter Second Customer Order ID to checkout, or Enter 0 to complete checkout: ");
			input2 = in.nextInt();
			
			for(CustomerOrder customerOrderListItem : Warehouse.getOrders()) {
			 if (input == input2)
				 continue;
				if(customerOrderListItem.getCustomerOrderID() == input2) {
					System.out.println("Employee " + Warehouse.getWorker(employeeID).getName() + 
							" is handling customer order "  + Warehouse.getOrder(input2).getCustomerOrderID());
					
					// Variable becomes true since user has entered valid ID;
					hasEnteredValidCustomerOrderId = true;
					
					// Assigns order to worker
					Warehouse.assignOrderToWorker(Warehouse.getOrder(input2), Warehouse.getWorker(employeeID));
					break;
				}
				else if (0 == input2)
				{
					System.out.println("Checkout Complete.");
					hasEnteredValidCustomerOrderId = true;
					break;
				}
				else 
				{
					errorCount = errorCount + 1;
				} 
			}
			
			// Checks if there was a valid input, and if not, prompts user to input again 
			if (errorCount == Warehouse.getOrders().size() - 1) {
				System.out.println("Customer Order ID not found. Please enter a valid Customer Order ID.");
				errorCount = 0 - 1;
			}
			
			// Checks if the user input matched the id of a checked out customer order
			if (input == input2){
					System.out.println("You have entered the id of an Customer Order that is already checked out. Please enter a valid Customer Order.");
				}
			
			System.out.println("\n" + "\n");
		}
		
		
		// Variable reset
		hasEnteredValidCustomerOrderId = false;
		
		// Variable reset, minus two, because 2 customer orders has been selected for
		// first checkout and second checkout, thus the array list size is - 2.
		errorCount = 0 - 2;
		
		
		// Employee checks out third order
		while (hasEnteredValidCustomerOrderId != true) {
			
			System.out.println("Enter Third Customer Order ID to checkout, or Enter 0 to complete checkout: ");
			int input3 = in.nextInt();
			
			for(CustomerOrder list : Warehouse.getOrders()) {
			 if (input == input3 || input2 == input3)
				 continue;
				if(list.getCustomerOrderID() == input3) {
					System.out.println("Employee " + Warehouse.getWorker(employeeID).getName() + 
							" is handling customer order "  + Warehouse.getOrder(input3).getCustomerOrderID());
					
					// Variable becomes true since user has entered valid ID;
					hasEnteredValidCustomerOrderId = true;
					
					// Assigns order to worker
					Warehouse.assignOrderToWorker(Warehouse.getOrder(input3), Warehouse.getWorker(employeeID));
					break;
				}
				else if (0 == input3)
				{
					System.out.println("Checkout Complete.");
					hasEnteredValidCustomerOrderId = true;
					break;
				}
				else 
				{
					errorCount = errorCount + 1;
				} 
			}
			 
			// Checks if there was a valid input, and if not, prompts user to input again 
						if (errorCount == Warehouse.getOrders().size() - 2) {
							System.out.println("Customer Order ID not found. Please enter a valid Customer Order ID.");
							errorCount = 0 - 2;
						}
						
						// Checks if the user input matched the id of a checked out customer order
						if (input == input3 || input2 == input3){
								System.out.println("You have entered the id of an Customer Order that is already checked out. Please enter a valid Customer Order.");
							}
						
						System.out.println("\n" + "\n");
		}
 */
