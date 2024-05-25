package com.employee.management.hr.dl.dao;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.enums.*;
public class EmployeeDAO implements EmployeeDAOInterface {
	private static final String FILE_NAME = "employee.data";
	public void add(EmployeeDTOInterface employeeDTO) throws DAOException {
		if(employeeDTO == null) throw new DAOException("Name is null");
		String employeeID;
		String name = employeeDTO.getName();
		if(name == null) throw new DAOException("Name is null");
		name = name.trim();
		if(name.length() == 0) throw new DAOException("Length of name is zero.");
		int designationCode = employeeDTO.getDesignationCode();
		if(designationCode <= 0) throw new DAOException("Invalid designation code: " + designationCode);
		DesignationDAOInterface designationDAO = new DesignationDAO();
		if(!designationDAO.codeExists(designationCode)) throw new DAOException("Invalid designation code: " + designationCode);
		Date dateOfBirth = employeeDTO.getDateOfBirth();
		if(dateOfBirth == null) throw new DAOException("Date of birth is null.");
		char gender = employeeDTO.getGender();
		boolean isIndian = employeeDTO.getIsIndian();
		BigDecimal basicSalary = employeeDTO.getBasicSalary();
		if(basicSalary == null) throw new DAOException("Basic salary is null.");
		if(basicSalary.signum() == -1) throw new DAOException("Basic salary is negative.");
		String panNumber = employeeDTO.getPANNumber();
		if(panNumber == null) throw new DAOException("PAN Number is null");
		panNumber = panNumber.trim();
		if(panNumber.length() == 0) throw new DAOException("Length of PAN number is zero.");
		String aadharCardNumber = employeeDTO.getAadharCardNumber();
		if(aadharCardNumber == null) throw new DAOException("Aadhar Card Number is null.");
		aadharCardNumber = aadharCardNumber.trim();
		if(aadharCardNumber.length() == 0) throw new DAOException("Length of Aadhar Card Number is zero.");
		try{
			int lastGeneratedEmployeeID = 10000000;
			String lastGeneratedEmployeeIDString = "";
			int recordCount = 0;
			String recordCountString = "";
			File file = new File(FILE_NAME);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				lastGeneratedEmployeeIDString = String.format("%-10s", "10000000");
				randomAccessFile.writeBytes(lastGeneratedEmployeeIDString + "\n");
				recordCountString = String.format("%-10s", "0");
				randomAccessFile.writeBytes(recordCountString + "\n");
			}
			else {
				lastGeneratedEmployeeID = Integer.parseInt(randomAccessFile.readLine().trim());
				recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
			}
			boolean panNumberExists = false, aadharCardNumberExists = false;
			String fPANNumber, fAadharCardNumber;
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				for(x = 1; x <= 7; x++) randomAccessFile.readLine();
				fPANNumber = randomAccessFile.readLine();
				fAadharCardNumber = randomAccessFile.readLine();
				if(!panNumberExists && fPANNumber.equalsIgnoreCase(panNumber)) panNumberExists = true;
				if(!aadharCardNumberExists && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) aadharCardNumberExists = true;
				if(panNumberExists && aadharCardNumberExists) break;
			}
			if(panNumberExists && aadharCardNumberExists) {
				randomAccessFile.close();
				throw new DAOException("PAN Number (" + panNumber + ") and Aadhar Card Number (" + aadharCardNumber + ") exists.");
			}
			if(panNumberExists) {
				randomAccessFile.close();
				throw new DAOException("PANNumber (" + panNumber + ") exists.");
			}
			if(aadharCardNumberExists) {
				randomAccessFile.close();
				throw new DAOException("Aadhar Card Number (" + aadharCardNumber + ") exists.");
			}
			lastGeneratedEmployeeID++;
			employeeID = "A" + lastGeneratedEmployeeID;
			recordCount++;
			randomAccessFile.writeBytes(employeeID + "\n");
			randomAccessFile.writeBytes(name + "\n"); 
			randomAccessFile.writeBytes(designationCode + "\n");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth) + "\n");
			randomAccessFile.writeBytes(gender + "\n");
			randomAccessFile.writeBytes(isIndian + "\n");
			randomAccessFile.writeBytes(basicSalary.toPlainString() + "\n");
			randomAccessFile.writeBytes(panNumber + "\n");
			randomAccessFile.writeBytes(aadharCardNumber + "\n");
			randomAccessFile.seek(0);
			lastGeneratedEmployeeIDString = String.format("%-10d", lastGeneratedEmployeeID);
			recordCountString = String.format("%-10d", recordCount);
			randomAccessFile.writeBytes(lastGeneratedEmployeeIDString + "\n");
			randomAccessFile.writeBytes(recordCountString + "\n");
			randomAccessFile.close();
			employeeDTO.setEmployeeID(employeeID);
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public void update(EmployeeDTOInterface employeeDTO) throws DAOException {
		if(employeeDTO == null) throw new DAOException("Name is null");
		String employeeID = employeeDTO.getEmployeeID();
		if(employeeID == null) throw new DAOException("Employee ID is null.");
		employeeID = employeeID.trim();
		if(employeeID.length() == 0) throw new DAOException("Length of Employee ID is zero.");
		String name = employeeDTO.getName();
		if(name == null) throw new DAOException("Name is null");
		name = name.trim();
		if(name.length() == 0) throw new DAOException("Length of name is zero.");
		int designationCode = employeeDTO.getDesignationCode();
		if(designationCode <= 0) throw new DAOException("Invalid designation code: " + designationCode);
		DesignationDAOInterface designationDAO = new DesignationDAO();
		if(!designationDAO.codeExists(designationCode)) throw new DAOException("Invalid designation code: " + designationCode);
		Date dateOfBirth = employeeDTO.getDateOfBirth();
		if(dateOfBirth == null) throw new DAOException("Date of birth is null.");
		char gender = employeeDTO.getGender();
		if(gender == ' ') throw new DAOException("Gender is not set to Male/Female.");
		boolean isIndian = employeeDTO.getIsIndian();
		BigDecimal basicSalary = employeeDTO.getBasicSalary();
		if(basicSalary == null) throw new DAOException("Basic salary is null.");
		if(basicSalary.signum() == -1) throw new DAOException("Basic salary is negative.");
		String panNumber = employeeDTO.getPANNumber();
		if(panNumber == null) throw new DAOException("PAN Number is null");
		panNumber = panNumber.trim();
		if(panNumber.length() == 0) throw new DAOException("Length of PAN number is zero.");
		String aadharCardNumber = employeeDTO.getAadharCardNumber();
		if(aadharCardNumber == null) throw new DAOException("Aadhar Card Number is null.");
		aadharCardNumber = aadharCardNumber.trim();
		if(aadharCardNumber.length() == 0) throw new DAOException("Length of Aadhar Card Number is zero.");
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) throw new DAOException("Invalid Employee ID: " + employeeID);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				throw new DAOException("Invalid Employee ID: " + employeeID);
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fEmployeeID;
			String fName;
			int fDesignationCode;
			Date fDateOfBirth;
			char fGender;
			boolean fIsIndian;
			BigDecimal fBasicSalary;
			String fPANNumber;
			String fAadharCardNumber;
			int x;
			boolean employeeIDFound = false;
			boolean panNumberFound = false;
			boolean aadharCardNumberFound = false;
			String panNumberFoundAgainstEmployeeID = "";
			String aadharCardNumberFoundAgainstEmployeeID = "";
			long foundAt = 0;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				if(!employeeIDFound) foundAt = randomAccessFile.getFilePointer();
				fEmployeeID = randomAccessFile.readLine();
				for(x = 1; x <= 6; x++) randomAccessFile.readLine();
				fPANNumber = randomAccessFile.readLine();
				fAadharCardNumber = randomAccessFile.readLine();
				if(!employeeIDFound && fEmployeeID.equalsIgnoreCase(employeeID)) employeeIDFound = true;
				if(!panNumberFound && fPANNumber.equalsIgnoreCase(panNumber)) {
					panNumberFound = true;
					panNumberFoundAgainstEmployeeID = fEmployeeID;
				}
				if(!aadharCardNumberFound && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) {
					aadharCardNumberFound = true;
					aadharCardNumberFoundAgainstEmployeeID = fEmployeeID;
				}
				if(employeeIDFound && panNumberFound && aadharCardNumberFound) break;
			}
			if(!employeeIDFound) {
				randomAccessFile.close();
				throw new DAOException("Invalid Employee ID: " + employeeID);
			}
			boolean panNumberExists = false;
			if(panNumberFound && !panNumberFoundAgainstEmployeeID.equalsIgnoreCase(employeeID)) panNumberExists = true;
			boolean aadharCardNumberExists = false;
			if(aadharCardNumberFound && !aadharCardNumberFoundAgainstEmployeeID.equalsIgnoreCase(employeeID)) aadharCardNumberExists = true;
			if(panNumberExists && aadharCardNumberExists) {
				randomAccessFile.close();
				throw new DAOException("PAN Number (" + panNumber + ") and Aadhar Card Number (" + aadharCardNumber + ") exists.");
			}
			if(panNumberExists) {
				randomAccessFile.close();
				throw new DAOException("PAN Number (" + panNumber + ") exists.");
			}
			if(aadharCardNumberExists) {
				randomAccessFile.close();
				throw new DAOException("Aadhar Card Number (" + aadharCardNumber + ") exists.");
			}
			randomAccessFile.seek(foundAt);
			for(x = 1; x <= 9; x++) randomAccessFile.readLine();
			File tmpFile = new File("tmp.tmp");
			if(tmpFile.exists()) tmpFile.delete();
			RandomAccessFile tmpRandomAccessFile = new RandomAccessFile(tmpFile, "rw");
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) tmpRandomAccessFile.writeBytes(randomAccessFile.readLine() + "\n");
			randomAccessFile.seek(foundAt);
			randomAccessFile.writeBytes(employeeID + "\n");
			randomAccessFile.writeBytes(name + "\n");
			randomAccessFile.writeBytes(designationCode + "\n");
			randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth) + "\n");
			randomAccessFile.writeBytes(gender + "\n");
			randomAccessFile.writeBytes(isIndian + "\n");
			randomAccessFile.writeBytes(basicSalary.toPlainString() + "\n");
			randomAccessFile.writeBytes(panNumber + "\n");
			randomAccessFile.writeBytes(aadharCardNumber + "\n");
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length()) randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
			randomAccessFile.setLength(randomAccessFile.getFilePointer());
			tmpRandomAccessFile.setLength(0);
			randomAccessFile.close();
			tmpRandomAccessFile.close();
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}

	}
	public void delete(String employeeID) throws DAOException {
		if(employeeID == null) throw new DAOException("Employee ID is null.");
		employeeID = employeeID.trim();
		if(employeeID.length() == 0) throw new DAOException("Length of Employee ID is zero.");
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) throw new DAOException("Invalid Employee ID: " + employeeID);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				throw new DAOException("Invalid Employee ID: " + employeeID);
			}
			randomAccessFile.readLine();
			int recordCount = Integer.parseInt(randomAccessFile.readLine());
			String fEmployeeID;
			int x;
			boolean employeeIDFound = false;
			long foundAt = 0;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				if(!employeeIDFound) foundAt = randomAccessFile.getFilePointer();
				fEmployeeID = randomAccessFile.readLine();
				for(x = 1; x <= 8; x++) randomAccessFile.readLine();
				if(fEmployeeID.equalsIgnoreCase(employeeID)) {
					employeeIDFound = true;
					break;
				}
			}
			if(!employeeIDFound) {
				randomAccessFile.close();
				throw new DAOException("Invalid Employee ID: " + employeeID);
			}
			File tmpFile = new File("tmp.tmp");
			if(tmpFile.exists()) tmpFile.delete();
			RandomAccessFile tmpRandomAccessFile = new RandomAccessFile(tmpFile, "rw");
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) tmpRandomAccessFile.writeBytes(randomAccessFile.readLine() + "\n");
			randomAccessFile.seek(foundAt);
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length()) randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
			randomAccessFile.setLength(randomAccessFile.getFilePointer());
			tmpRandomAccessFile.setLength(0);
			recordCount--;
			String recordCountString = String.format("%-10d", recordCount);
			randomAccessFile.seek(0);
			randomAccessFile.readLine(); 
			randomAccessFile.writeBytes(recordCountString + "\n");
			randomAccessFile.close();
			tmpRandomAccessFile.close();
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public Set<EmployeeDTOInterface> getAll() throws DAOException {
		Set <EmployeeDTOInterface> employees = new TreeSet<>();
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return employees;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return employees;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			EmployeeDTOInterface employeeDTO;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			char fGender;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployeeID(randomAccessFile.readLine());
				employeeDTO.setName(randomAccessFile.readLine());
				employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
				try {
					employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
				} catch(ParseException pe) {
					// do nothing
				}
				fGender = randomAccessFile.readLine().charAt(0);
				if(fGender == 'M') employeeDTO.setGender(GENDER.MALE);
				else if (fGender == 'F') employeeDTO.setGender(GENDER.FEMALE);
				employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
				employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
				employeeDTO.setPANNumber(randomAccessFile.readLine());
				employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
				employees.add(employeeDTO);
			}
			randomAccessFile.close();
			return employees;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException {
		if(new DesignationDAO().codeExists(designationCode) == false) throw new DAOException("Invalid designation Code: " + designationCode);
		Set<EmployeeDTOInterface> employees = new TreeSet<>();
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return employees;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return employees;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			EmployeeDTOInterface employeeDTO;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fEmployeeID;
			String fName;
			int fDesignationCode;
			int x;
			char fGender;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				fEmployeeID = randomAccessFile.readLine();
				fName = randomAccessFile.readLine();
				fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
				if(designationCode != fDesignationCode) {
					for(x = 1; x <= 6; x++) randomAccessFile.readLine();
					continue;
				}
				employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployeeID(fEmployeeID);
				employeeDTO.setName(fName);
				employeeDTO.setDesignationCode(fDesignationCode);
				try {
					employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
				} catch(ParseException pe) {
					//do nothing
				}
				fGender = randomAccessFile.readLine().charAt(0);
				if(fGender == 'M') employeeDTO.setGender(GENDER.MALE);
				else if (fGender == 'F') employeeDTO.setGender(GENDER.FEMALE);
				employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
				employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
				employeeDTO.setPANNumber(randomAccessFile.readLine());
				employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
				employees.add(employeeDTO);
			}
			randomAccessFile.close();
			return employees;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public boolean isDesignationAlloted(int designationCode) throws DAOException {
		if(new DesignationDAO().codeExists(designationCode) == false) throw new DAOException("Invalid designation code: " + designationCode);
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return false;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return false;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			int fDesignationCode;
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
				if(fDesignationCode == designationCode) {
					randomAccessFile.close();
					return true;
				}
				for(x = 1; x <= 6; x++) randomAccessFile.readLine();
			}
			randomAccessFile.close();
			return false;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public EmployeeDTOInterface getByEmployeeID(String employeeID) throws DAOException {
		if(employeeID == null) throw new DAOException("Invalid Employee ID: " + employeeID);
		employeeID = employeeID.trim();
		if(employeeID.length() == 0) throw new DAOException("Length of Employee ID is zero.");
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) throw new DAOException("Invalid Employee ID: " + employeeID);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) throw new DAOException("Invalid Employee ID: " + employeeID);
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			EmployeeDTOInterface employeeDTO;
			String fEmployeeID;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				fEmployeeID = randomAccessFile.readLine();
				if(fEmployeeID.equalsIgnoreCase(employeeID)) {
					employeeDTO = new EmployeeDTO();
					employeeDTO.setEmployeeID(fEmployeeID);
					employeeDTO.setName(randomAccessFile.readLine());
					employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
					try {
						employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
					} catch(ParseException pe) {
						//do nothing
					}
					employeeDTO.setGender((randomAccessFile.readLine().charAt(0) == 'M') ? GENDER.MALE : GENDER.FEMALE);
					employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
					employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
					employeeDTO.setPANNumber(randomAccessFile.readLine());
					employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
					randomAccessFile.close();
					return employeeDTO;
				}
				for(x = 1; x <= 8; x++) randomAccessFile.readLine();
			}
			randomAccessFile.close();
			throw new DAOException("Invalid Employee ID: " + employeeID);
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException {
		if(panNumber == null) throw new DAOException("Invalid PAN Number: " + panNumber);
		panNumber = panNumber.trim();
		if(panNumber.length() == 0) throw new DAOException("Invalid PAN Number: PAN Number is of zero length.");
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) throw new DAOException("Invalid PAN Number: " + panNumber);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				throw new DAOException("Invalid PAN Number: " + panNumber);
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			EmployeeDTOInterface employeeDTO;
			String fEmployeeID;
			String fName;
			int fDesignationCode;
			Date fDateOfBirth;
			char fGender;
			boolean fIsIndian;
			BigDecimal fBasicSalary;
			String fPANNumber;
			String fAadharCardNumber;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			int x;
			fDateOfBirth = null;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				fEmployeeID = randomAccessFile.readLine();
				fName = randomAccessFile.readLine();
				fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
				try {
					fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
				} catch(ParseException pe) {
					//do nothing
				}
				fGender = randomAccessFile.readLine().charAt(0);
				fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
				fBasicSalary = new BigDecimal(randomAccessFile.readLine());
				fPANNumber = randomAccessFile.readLine();
				fAadharCardNumber = randomAccessFile.readLine();
				if(fPANNumber.equalsIgnoreCase(panNumber)) {
					employeeDTO = new EmployeeDTO();
					employeeDTO.setEmployeeID(fEmployeeID);
					employeeDTO.setName(fName);
					employeeDTO.setDesignationCode(fDesignationCode);
					employeeDTO.setDateOfBirth(fDateOfBirth);
					employeeDTO.setGender((fGender == 'M') ? GENDER.MALE : GENDER.FEMALE);
					employeeDTO.setIsIndian(fIsIndian);
					employeeDTO.setBasicSalary(fBasicSalary);
					employeeDTO.setPANNumber(fPANNumber);
					employeeDTO.setAadharCardNumber(fAadharCardNumber);
					randomAccessFile.close();
					return employeeDTO;
				}
			}
			randomAccessFile.close();
			throw new DAOException("Invalid PAN Number: " + panNumber);
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException {
		if(aadharCardNumber == null) throw new DAOException("Invalid Aadhar Card Number: " + aadharCardNumber);
		aadharCardNumber = aadharCardNumber.trim();
		if(aadharCardNumber.length() == 0) throw new DAOException("Invalid Aadhar Card Number: Aadhar Card Number is of zero length.");
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) throw new DAOException("Invalid Aadhar Card Number: " + aadharCardNumber);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				throw new DAOException("Invalid Aadhar Card Number: " + aadharCardNumber);
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			EmployeeDTOInterface employeeDTO;
			String fEmployeeID;
			String fName;
			int fDesignationCode;
			Date fDateOfBirth;
			char fGender;
			boolean fIsIndian;
			BigDecimal fBasicSalary;
			String fPANNumber;
			String fAadharCardNumber;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			int x;
			fDateOfBirth = null;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				fEmployeeID = randomAccessFile.readLine();
				fName = randomAccessFile.readLine();
				fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
				try {
					fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
				} catch(ParseException pe) {
					//do nothing
				}
				fGender = randomAccessFile.readLine().charAt(0);
				fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
				fBasicSalary = new BigDecimal(randomAccessFile.readLine());
				fPANNumber = randomAccessFile.readLine();
				fAadharCardNumber = randomAccessFile.readLine();
				if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) {
					employeeDTO = new EmployeeDTO();
					employeeDTO.setEmployeeID(fEmployeeID);
					employeeDTO.setName(fName);
					employeeDTO.setDesignationCode(fDesignationCode);
					employeeDTO.setDateOfBirth(fDateOfBirth);
					employeeDTO.setGender((fGender == 'M') ? GENDER.MALE : GENDER.FEMALE);
					employeeDTO.setIsIndian(fIsIndian);
					employeeDTO.setBasicSalary(fBasicSalary);
					employeeDTO.setPANNumber(fPANNumber);
					employeeDTO.setAadharCardNumber(fAadharCardNumber);
					randomAccessFile.close();
					return employeeDTO;
				}
			}
			randomAccessFile.close();
			throw new DAOException("Invalid Aadhar Card Number: " + aadharCardNumber);
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public boolean employeeIDExists(String employeeID) throws DAOException {
		if(employeeID == null) return false;
		employeeID = employeeID.trim();
		if(employeeID.length() == 0) return false;
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return false;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return false;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			String fEmployeeID;
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				fEmployeeID = randomAccessFile.readLine();
				if(fEmployeeID.equalsIgnoreCase(employeeID)) {
					randomAccessFile.close();
					return true;
				}
				for(x = 1; x <= 8; x++) randomAccessFile.readLine();
			}
			randomAccessFile.close();
			return false;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public boolean panNumberExists(String panNumber) throws DAOException {
		if(panNumber == null) return false;
		panNumber = panNumber.trim();
		if(panNumber.length() == 0) return false;
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return false;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return false;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			String fPANNumber;
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				for(x = 1; x <= 7; x++) randomAccessFile.readLine();
				fPANNumber = randomAccessFile.readLine();
				randomAccessFile.readLine();
				if(fPANNumber.equalsIgnoreCase(panNumber)) {
					randomAccessFile.close();
					return true;
				}
			}
			randomAccessFile.close();
			return false;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException {
		if(aadharCardNumber == null) return false;
		aadharCardNumber = aadharCardNumber.trim();
		if(aadharCardNumber.length() == 0) return false;
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return false;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return false;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			String fAadharCardNumber;
			int x;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				for(x = 1; x <= 7; x++) randomAccessFile.readLine();
				fAadharCardNumber = randomAccessFile.readLine();
				randomAccessFile.readLine();
				if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) {
					randomAccessFile.close();
					return true;
				}
			}
			randomAccessFile.close();
			return false;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public int getCount() throws DAOException {
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) return 0;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return 0;
			}
			randomAccessFile.readLine();
			int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
			randomAccessFile.close();
			return recordCount;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
	public int getCountByDesignation(int designationCode) throws DAOException {
		try {
			if(new DesignationDAO().codeExists(designationCode) == false) throw new DAOException("Invalid Designation Code: " + designationCode);
			File file = new File(FILE_NAME);
			if(!file.exists()) return 0;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			if(randomAccessFile.length() == 0) {
				randomAccessFile.close();
				return 0;
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			int count = 0;
			int x;
			int fDesignationCode;
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
				if(fDesignationCode == designationCode) count++;
				for(x = 1; x <= 6; x++) randomAccessFile.readLine();
			}
			randomAccessFile.close();
			return count;
		} catch(IOException ioException) {
			throw new DAOException(ioException.getMessage());
		}
	}
}