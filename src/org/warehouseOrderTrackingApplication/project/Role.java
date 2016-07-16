package org.warehouseOrderTrackingApplication.project;

public class Role {
	
	//role variables
	int roleID;
	String roleName;
	WarehouseWorker person;
	
	//set role for warehouse worker
	public void setRole(String param) {
		this.roleName = param;
	}
	
	//get role for warehouse worker
	public String getRole() {
		return roleName;
	}
}
