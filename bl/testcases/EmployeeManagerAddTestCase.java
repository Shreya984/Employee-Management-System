import com.employee.management.hr.bl.managers.*;
import com.employee.management.enums.*;
import com.employee.management.hr.bl.interfaces.managers.*;
import com.employee.management.hr.bl.interfaces.pojo.*;
import com.employee.management.hr.bl.pojo.*; 
import com.employee.management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
class EmployeeManagerAddTestCase {
    public static void main(String args[]) {
        try {
            String name="Sameer Gupta";
            DesignationInterface designation=new Designation();
            designation.setCode(2);
            Date dateOfBirth=new Date();
            boolean isIndian=false;
            BigDecimal basicSalary=new BigDecimal("30000");
            String panNumber="A12345";
            String aadharCardNumber="U12345";
            EmployeeInterface employee;
            employee = new Employee();
            employee.setName(name);
            employee.setDesignation(designation);
            employee.setDateOfBirth(dateOfBirth);
            employee.setGender(GENDER.MALE);
            employee.setIsIndian(isIndian);
            employee.setBasicSalary(basicSalary);
            employee.setPANNumber(panNumber);
            employee.setAadharCardNumber(aadharCardNumber);
            EmployeeManagerInterface employeeManager;
            employeeManager = EmployeeManager.getEmployeeManager();
            employeeManager.addEmployee (employee);
            System.out.printf("Employee added with employee Id. %s\n", employee.getEmployeeID());
        }
        catch(BLException blException) {
            if(blException.hasGenericException()) System.out.println(blException.getGenericException());
            List<String> properties = blException.getProperties(); 
            for(String property: properties) {
                System.out.println(blException.getException(property));
            }
        }
    }
}