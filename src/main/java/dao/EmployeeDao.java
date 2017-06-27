package dao;


import model.Employee;

public interface EmployeeDao {

    public Employee findByEmployeeId(Long employeeId);

}
