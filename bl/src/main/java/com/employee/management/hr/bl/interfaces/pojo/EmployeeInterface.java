package com.employee.management.hr.bl.interfaces.pojo;
import com.employee.management.enums.*;
import java.math.*;
import java.util.*;
public interface EmployeeInterface extends Comparable<EmployeeInterface>, java.io.Serializable {
	public void setEmployeeID(String employeeID);
	public String getEmployeeID();
	public void setName(String name);
	public String getName();
	public void setDesignation(DesignationInterface designation);
	public DesignationInterface getDesignation();
	public void setDateOfBirth(Date dateOfBirth);
	public Date getDateOfBirth();
	public void setGender(GENDER gender);
	public char getGender();
	public void setIsIndian(boolean isIndian);
	public boolean getIsIndian();
	public void setBasicSalary(BigDecimal basicSalary);
	public BigDecimal getBasicSalary();
	public void setPANNumber(String PANNumber);
	public String getPANNumber();
	public void setAadharCardNumber(String aadharCardNumber);
	public String getAadharCardNumber();
}