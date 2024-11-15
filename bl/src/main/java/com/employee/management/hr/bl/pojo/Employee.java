package com.employee.management.hr.bl.pojo;
import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.enums.*;
import java.util.*;
import java.math.*;
public class Employee implements EmployeeInterface {
	private String employeeID;
	private String name;
	private DesignationInterface designation;
	private Date dateOfBirth;
	private char gender;
	private boolean isIndian;
	private BigDecimal basicSalary;
	private String panNumber;
	private String aadharCardNumber;
	public Employee() {
		this.employeeID = "";
		this.name = "";
		this.dateOfBirth = null;
		this.gender = '\0';
		this.isIndian = false;
		this.basicSalary = null;
		this.panNumber = "";
		this.aadharCardNumber = "";
		this.designation = null;
	}
	public void setEmployeeID(java.lang.String employeeID) {
		this.employeeID = employeeID;	
	}
	public java.lang.String getEmployeeID() {
		return this.employeeID;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getName() {
		return this.name;
	}
	public void setDateOfBirth(java.util.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public java.util.Date getDateOfBirth(){
		return this.dateOfBirth;
	}
	public void setGender(GENDER gender) {
		if(gender == GENDER.MALE) this.gender = 'M';
		else this.gender = 'F';
	}
	public char getGender() {
		return this.gender;
	}
	public void setIsIndian(boolean isIndian) {
		this.isIndian = isIndian;
	}
	public boolean getIsIndian() {
		return this.isIndian;
	}
	public void setBasicSalary(java.math.BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}
	public java.math.BigDecimal getBasicSalary() {
		return this.basicSalary;
	}
	public void setPANNumber(java.lang.String panNumber) {
		this.panNumber = panNumber;
	}
	public java.lang.String getPANNumber() {
		return this.panNumber;
	}
	public void setAadharCardNumber(java.lang.String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}
	public java.lang.String getAadharCardNumber() {
		return this.aadharCardNumber;
	}
	public void setDesignation(DesignationInterface designation) {
		this.designation = designation;
	}
	public DesignationInterface getDesignation() {
		return this.designation;
	}
	public boolean equals(Object other) {
		if(!(other instanceof EmployeeInterface)) return false;
		EmployeeInterface employee = (EmployeeInterface)other;
		return this.employeeID.equalsIgnoreCase(employee.getEmployeeID());
	}
	public int compareTo(EmployeeInterface other) {
		return this.employeeID.compareToIgnoreCase(other.getEmployeeID());
	}
	public int hashCode() {
		return this.employeeID.toUpperCase().hashCode();
	}
}