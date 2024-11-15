import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationDeleteTestCase {
	public static void main(String args[]) {
		int code = Integer.parseInt(args[0]);
		try {
			DesignationDAOInterface designationDAO = new DesignationDAO();
			designationDAO.delete(code);
			System.out.println("Designation deleted");
		} catch (DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}