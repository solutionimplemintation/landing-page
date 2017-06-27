package dao;


import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Employee findByEmployeeId(Long employeeId) {

        List<Employee> employees = jdbcTemplate.query("select * from employees where employee_id = ?",
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
}
