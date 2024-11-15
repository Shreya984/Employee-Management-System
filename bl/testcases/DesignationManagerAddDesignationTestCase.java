import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerAddDesignationTestCase {
    public static void main (String args[]) {
        DesignationInterface designation = new Designation();
        designation.setTitle("Clerk");
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            designationManager.addDesignation(designation);
            System.out.println("Designation added with code as: " + designation.getCode());
        }
        catch (BLException blException) {
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