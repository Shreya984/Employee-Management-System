import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeGetByEmployeeIDTestCase {
	public static void main(String args[]) {
		String employeeID = args[0];
		try {
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			EmployeeDTOInterface employeeDTO = employeeDAO.getByEmployeeID(employeeID);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Employee ID: " + employeeDTO.getEmployeeID());
			System.out.println("Name: " + employeeDTO.getName());
			System.out.println("Designation Code: " + employeeDTO.getDesignationCode());
			System.out.println("Date of Birth: " + simpleDateFormat.format(employeeDTO.getDateOfBirth()));
			System.out.println("Gender: " + employeeDTO.getGender());
			System.out.println("Is Indian: " + employeeDTO.getIsIndian());
			System.out.println("Basic Salary: " + employeeDTO.getBasicSalary().toPlainString());
			System.out.println("PAN Number: " + employeeDTO.getPANNumber());
			System.out.println("Aadhar Card Number: " + employeeDTO.getAadharCardNumber());
		} catch(DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}