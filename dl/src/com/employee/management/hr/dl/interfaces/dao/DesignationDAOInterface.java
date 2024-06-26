package com.employee.management.hr.dl.interfaces.dao;
import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.dto.*;
import java.util.*;
public interface DesignationDAOInterface {
	public void add(DesignationDTOInterface designationDTO) throws DAOException;
	public void update(DesignationDTOInterface designationDTO) throws DAOException;
	public void delete(int code) throws DAOException;
	public TreeSet<DesignationDTOInterface>getAll() throws DAOException;
	public DesignationDTOInterface getByCode(int code) throws DAOException;
	public DesignationDTOInterface getByTitle(String title) throws DAOException;
	public boolean codeExists(int code) throws DAOException;
	public boolean titleExists(String title) throws DAOException;
	public int getCount() throws DAOException;
}