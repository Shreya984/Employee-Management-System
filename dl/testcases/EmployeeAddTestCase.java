import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import com.employee.management.enums.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeAddTestCase {
	public static void main(String args[]) {
		String name = args[0];
		int designationCode = Integer.parseInt(args[1]);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = null;
		try {
			dateOfBirth = sdf.parse(args[2]);
		} catch(ParseException pe) {
			System.out.println(pe.getMessage());
			return;
		}
		char gender = args[3].charAt(0);
		boolean isIndian = Boolean.parseBoolean(args[4]);
		BigDecimal basicSalary = new BigDecimal(args[5]);
		String panNumber = args[6];
		String aadharCardNumber = args[7];
		try {
			EmployeeDTOInterface employeeDTO = new EmployeeDTO();
			employeeDTO.setName(name);
			employeeDTO.setDesignationCode(designationCode);
			employeeDTO.setDateOfBirth(dateOfBirth);
			if(gender == 'M') employeeDTO.setGender(GENDER.MALE);
			else if(gender == 'F') employeeDTO.setGender(GENDER.FEMALE);
			employeeDTO.setIsIndian(isIndian);
			employeeDTO.setBasicSalary(basicSalary);
			employeeDTO.setPANNumber(panNumber);
			employeeDTO.setAadharCardNumber(aadharCardNumber);
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			employeeDAO.add(employeeDTO);
			System.out.println("Employee added with employeeID as: " + employeeDTO.getEmployeeID());
		} catch(DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}