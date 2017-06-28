package dao;


import model.Employee;

public interface EmployeeDao {

    Employee findByEmployeeId(Long employeeId);

    void insertNewEmployee(Employee employee);

}
