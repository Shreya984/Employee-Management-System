package com.employee.management.hr.bl.interfaces.managers;
import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import java.util.*;
public interface EmployeeManagerInterface {
    public void addEmployee(EmployeeInterface employee) throws BLException;
    public void updateEmployee(EmployeeInterface employee) throws BLException;
    public void removeEmployee(String employeeID) throws BLException;
    public EmployeeInterface getEmployeeByEmployeeId(String  employeeID) throws BLException;
    public EmployeeInterface getEmployeeByPANNumber(String PANNumber) throws BLException;
    public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException;
    public Set<EmployeeInterface> getEmployees();
    public Set<EmployeeInterface> getEmployeesByDesignationCode(int designationCode) throws BLException;
    public int getEmployeeCount();
    public int getEmployeeCountByDesignationCode(int designationCode) throws BLException;
    public boolean designationAlloted(int designationCode) throws BLException;
    public boolean employeeIDExists(String employeeID);
    public boolean employeePANNumberExists(String PANNumber);
    public boolean employeeAadharCardNumberExists(String aadharCardNumber);
}