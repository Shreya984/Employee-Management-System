import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeDeleteTestCase {
	public static void main(String args[]) {
		String employeeID = args[0];
		try {
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			employeeDAO.delete(employeeID);
			System.out.println("Employee deleted.");
		} catch(DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}