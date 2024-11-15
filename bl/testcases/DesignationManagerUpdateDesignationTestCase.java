import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.managers.*;
import com.employee.management.hr.bl.pojo.*;
import java.util.*;
class DesignationManagerUpdateDesignationTestCase {
    public static void main(String args[]) {
        DesignationInterface designation = new Designation();
        designation.setCode(1);
        designation.setTitle("MANAGER");
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            designationManager.updateDesignation(designation);
            System.out.println("Designation updated.\n");
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