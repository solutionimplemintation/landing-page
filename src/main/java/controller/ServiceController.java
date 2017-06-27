package controller;

import dao.EmployeeDao;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class ServiceController {

    private static Logger LOG = LogManager.getLogger(ServiceController.class);

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/test")
    public String test(@RequestParam(value="text", defaultValue="default") String text) {
        LOG.trace("text " + text);
        return "Hello! Your text: " + text;
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        LOG.trace("name " + name);
        return "greeting";
    }

    @RequestMapping("/employees")
    public String employees(@RequestParam(value="employee_id") Long employeeId, Model model) {

        Employee employee = employeeDao.findByEmployeeId(employeeId);

        if(employee == null) {
            return "notfound";
        }

        model.addAttribute("EMPLOYEE_ID", employee.getEmployeeId());
        model.addAttribute("FIRST_NAME", employee.getFirstName());
        model.addAttribute("LAST_NAME", employee.getLastName());
        model.addAttribute("EMAIL", employee.getEmail());
        model.addAttribute("PHONE_NUMBER", employee.getPhoneNumber());
        model.addAttribute("HIRE_DATE", employee.getHireDate());
        model.addAttribute("JOB_ID", employee.getJobId());
        model.addAttribute("SALARY", employee.getSalary());
        model.addAttribute("COMMISSION_PCT", employee.getCommisionPct());
        model.addAttribute("MANAGER_ID", employee.getManagerId());
        model.addAttribute("DEPARTMENT_ID", employee.getDepartmentId());

        return "employees";
    }



}
