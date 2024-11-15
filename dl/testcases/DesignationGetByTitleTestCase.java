import com.employee.management.hr.dl.exceptions.*;
import com.employee.management.hr.dl.interfaces.dto.*;
import com.employee.management.hr.dl.interfaces.dao.*;
import com.employee.management.hr.dl.dto.*;
import com.employee.management.hr.dl.dao.*;
public class DesignationGetByTitleTestCase {
	public static void main(String args[]) {
		String title = args[0];
		try {
			DesignationDTOInterface designationDTO;
			DesignationDAOInterface designationDAO = new DesignationDAO();
			designationDTO = designationDAO.getByTitle(title);
			System.out.printf("Code: %d, Title: %s\n", designationDTO.getCode(), designationDTO.getTitle());
		} catch (DAOException daoException) {
			System.out.println(daoException.getMessage());
		}
	}
}