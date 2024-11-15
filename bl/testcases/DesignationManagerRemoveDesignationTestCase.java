import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerRemoveDesignationTestCase {
    public static void main(String args[]) {
        int code = Integer.parseInt(args[0]);
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            designationManager.removeDesignation(code);
            System.out.println("Designation removed.\n");
        } catch(BLException blException) {
            if(blException.hasGenericException()) {
                System.out.println(blException.getGenericException());
            }
            List<String> properties = blException.getProperties();
            for(String property: properties) {
                System.out.println(blException.getException(property));
            }
        }
    }
}