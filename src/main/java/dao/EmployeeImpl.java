package dao;


import controller.ServiceController;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeImpl implements EmployeeDao {

    @Autowired
    @Qualifier("jdbc/nc")
    JdbcTemplate ncJdbcTemplate;

    private final String MERGE_EMPLOYEE = "merge into test_table e_src " +
            "  using (select ? employee_id, sysdate persistdate, ? first_name, ? last_name  from dual) e_tg " +
            "     on (e_src.employee_id = e_tg.employee_id) " +
            "  when matched then " +
            "    update " +
            "     set e_src.persistdate = sysdate, e_src.first_name = e_tg.first_name, e_src.last_name = e_tg.last_name " +
            "  when not matched then " +
            "     insert (e_src.employee_id, e_src.persistdate, e_src.first_name, e_src.last_name) " +
            "      values (e_tg.employee_id, sysdate, e_tg.first_name, e_tg.last_name)";


    @Override
    public Employee findByEmployeeId(Long employeeId) {

        List<Employee> employees = ncJdbcTemplate.query("select * from employees where employee_id = ?",
                new Object[]{employeeId},
                new RowMapper<Employee>() {
                    @Override
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Employee emp = new Employee();
                        emp.setEmployeeId(rs.getLong("EMPLOYEE_ID"));
                        emp.setFirstName(rs.getString("FIRST_NAME"));
                        emp.setLastName(rs.getString("LAST_NAME"));
                        emp.setEmail(rs.getString("EMAIL"));
                        emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                        emp.setHireDate(rs.getDate("HIRE_DATE"));
                        emp.setJobId(rs.getString("JOB_ID"));
                        emp.setSalary(rs.getLong("SALARY"));
                        emp.setCommisionPct(rs.getLong("COMMISSION_PCT"));
                        emp.setManagerId(rs.getLong("MANAGER_ID"));
                        emp.setDepartmentId(rs.getLong("DEPARTMENT_ID"));
                        return emp;
                    }
                });

        return employees.size() == 0 ? null : employees.get(0);

    }

    @Override
    @Transactional
    public void insertNewEmployee(Employee employee) {

        if(employee == null) {
            return;
        } else {
            ncJdbcTemplate.update(MERGE_EMPLOYEE,
                    new Object[]{employee.getEmployeeId(), employee.getFirstName(), employee.getLastName()});
        }

    }
}
