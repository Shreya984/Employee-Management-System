package com.employee.management.hr.dl.interfaces.dao;
import java.util.*;
import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
public interface EmployeeDAOInterface {
	public void add(EmployeeDTOInterface employeeDTO) throws DAOException;
	public void update(EmployeeDTOInterface employeeDTO) throws DAOException;
	public void delete(String employeeID) throws DAOException;
	public Set<EmployeeDTOInterface> getAll() throws DAOException;
	public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException;
	public boolean isDesignationAlloted(int designationCode) throws DAOException;
	public EmployeeDTOInterface getByEmployeeID(String employeeID) throws DAOException;
	public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException;
	public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;
	public boolean employeeIDExists(String employeeID) throws DAOException;
	public boolean panNumberExists(String panNumber) throws DAOException;
	public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException;
	public int getCount() throws DAOException;
	public int getCountByDesignation(int designationCode) throws DAOException;
}