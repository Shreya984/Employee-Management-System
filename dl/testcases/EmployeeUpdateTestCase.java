import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import com.employee.management.enums.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeUpdateTestCase {
	public static void main(String args[]) {
		String employeeID = args[0];
		String name = args[1];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int designationCode = Integer.parseInt(args[2]);
		Date dateOfBirth = null;
		try {
			dateOfBirth = simpleDateFormat.parse(args[3]);
		} catch(ParseException pe) {
			System.out.println(pe.getMessage());
			return;
		}
		char gender = args[4].charAt(0);
		boolean isIndian = Boolean.parseBoolean(args[5]);
		BigDecimal basicSalary = new BigDecimal(args[6]);
		String panNumber = args[7];
		String aadharCardNumber = args[8];
		try {
			EmployeeDTOInterface employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeID(employeeID);
			employeeDTO.setName(name);
			employeeDTO.setDesignationCode(designationCode);
			employeeDTO.setDateOfBirth(dateOfBirth);
			if(gender == 'M') employeeDTO.setGender(GENDER.MALE);
			if(gender == 'F') employeeDTO.setGender(GENDER.FEMALE);
			employeeDTO.setIsIndian(isIndian);
			employeeDTO.setBasicSalary(basicSalary);
			employeeDTO.setPANNumber(panNumber);
			employeeDTO.setAadharCardNumber(aadharCardNumber);
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			employeeDAO.update(employeeDTO);
			System.out.println("Employee Updated successfully.");
		} catch(DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}