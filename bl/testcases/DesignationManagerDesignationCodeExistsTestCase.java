import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.exceptions.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.pojo.*;
import com.employee.management.hr.bl.managers.*;
import java.util.*;
class DesignationManagerDesignationCodeExistsTestCase {
    public static void main(String args[]) {
        int code = Integer.parseInt(args[0]);
        try {
            DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
            System.out.println(code + " exists: " + designationManager.designationCodeExists(code));
        }
        catch(BLException blException) {
            List<String> properties = blException.getProperties();
            properties.forEach((property) -> {
                System.out.println(blException.getException(property));
            });
        }
    }
}