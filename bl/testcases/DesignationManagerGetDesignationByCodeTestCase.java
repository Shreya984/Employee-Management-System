import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationByCodeTestCase {
    public static void main(String args[]) {
        int code = Integer.parseInt(args[0]);
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            DesignationInterface designation = designationManager.getDesignationByCode(code);
            System.out.printf("Code: %d, Title: %s\n", designation.getCode(), designation.getTitle());
        }
        catch(BLException blException) {
            List<String> properties = blException.getProperties();
            properties.forEach((property) -> {
                System.out.println(blException.getException(property));
            });
        }
    }
}