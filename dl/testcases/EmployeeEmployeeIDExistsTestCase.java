import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dao.*;
public class EmployeeEmployeeIDExistsTestCase {
	public static void main(String args[]) {
		String employeeID = args[0];
		try {
			System.out.println("Employee ID: " + employeeID + " exists: " + new EmployeeDAO().employeeIDExists(employeeID));
		} catch(DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}