import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerDesignationTitleExistsTestCase {
    public static void main(String args[]) {
        String title = args[0];
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            System.out.println(title + " exists: " + designationManager.designationTitleExists(title.toUpperCase()));
        }
        catch(BLException blException) {
            List<String> properties = blException.getProperties();
            properties.forEach((property) -> {
                System.out.println(blException.getException(property));
            });
        }
    }
}