import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationCountTestCase {
    public static void main(String args[]) {
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            System.out.println("Number of designations: " + designationManager.getDesignationCount());
        }
        catch(BLException blException) {
            List<String> properties = blException.getProperties();
            properties.forEach((property) -> {
                System.out.println(blException.getException(property));
            });
        }
    }
}