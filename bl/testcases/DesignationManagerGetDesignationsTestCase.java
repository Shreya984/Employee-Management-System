import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationsTestCase {
    public static void main(String args[]) {
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            Set<DesignationInterface> designations = designationManager.getDesignations();
            designations.forEach((designation) -> {
                System.out.printf("Code: %d, Title: %s\n", designation.getCode(), designation.getTitle());
            });
        }
        catch(BLException blException) {
            List<String> properties = blException.getProperties();
            properties.forEach((property) -> {
                System.out.println(blException.getException(property));
            });
        }
    }
}