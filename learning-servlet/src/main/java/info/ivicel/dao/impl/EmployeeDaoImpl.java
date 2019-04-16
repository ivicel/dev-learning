package info.ivicel.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import info.ivicel.dao.IDao;
import info.ivicel.domain.Employee;
import info.ivicel.serivce.RowMapper;

public class EmployeeDaoImpl implements IDao<Employee> {
    @Override
    public Employee insert(Employee em) {
        String sql = "INSERT INTO `employees` (emp_no, birth_date, first_name, last_name, gender, " +
                "hire_date) VALUES (?, ?, ?, ?, ?, ?)";
        return JdbcTemplate.update(sql, em.getEmpNo(), em.getBirthDate(), em.getFirstName(),
                em.getLastName(), em.getGender(), em.getHireDate()) == 1 ? em : null;
    }

    @Override
    public int delete(int empNo) {
        String sql = "DELETE FROM `employees` WHERE emp_no = ?";
        return JdbcTemplate.update(sql, empNo);
    }

    @Override
    public int update(Employee e) {
        String sql = "UPDATE `employees` SET birth_date = ?, first_name = ?, " +
                "last_name = ?, hire_date = ? WHERE emp_no = ?";
        return JdbcTemplate.update(sql, e.getBirthDate(), e.getFirstName(), e.getLastName(),
                e.getHireDate(), e.getEmpNo());
    }

    @Override
    public List<Employee> findAll() {
        return findAll(0);
    }

    @Override
    public List<Employee> findAll(int start) {
        String sql = "SELECT * FROM `employees` LIMIT " + start + ", 15";
        return JdbcTemplate.findAll(sql, (rs, num) -> getEmployee(rs));
    }

    @Override
    public Employee find(int empNo) {
        String sql = "SELECT * FROM `employees` WHERE emp_no = ?";
        return JdbcTemplate.query(sql, (rs, num) -> getEmployee(rs), empNo);
    }

    private Employee getEmployee(ResultSet rs) throws SQLException {
        Employee em = new Employee();
        em.setEmpNo(rs.getInt("emp_no"));
        em.setBirthDate(rs.getDate("birth_date"));
        em.setFirstName(rs.getString("first_name"));
        em.setLastName(rs.getString("last_name"));
        em.setHireDate(rs.getDate("hire_date"));
        return em;
    }

    @Override
    public Object query(String sql, Class clazz) {
        return JdbcTemplate.query(sql);
    }
}
