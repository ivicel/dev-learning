package info.ivicel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.ivicel.dao.impl.JdbcTemplate;
import info.ivicel.domain.Employee;
import info.ivicel.serivce.IService;
import info.ivicel.serivce.impl.EmployeeService;


@WebServlet("/query/list")
public class QueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        IService<Employee> service = new EmployeeService();
        long total = JdbcTemplate.query("SELECT count(*) FROM `employees`");
        int page = 1;
        try {
            page = Integer.valueOf(req.getParameter("page"));
        } catch (NumberFormatException ignored) {}
        List<Employee> employees = service.findAll((page - 1) * 15);
        PageResult<List<Employee>> result = new PageResult<>();
        result.setTotalPage(total);
        result.setCurrentPage(page);
        result.setT(employees);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
}
