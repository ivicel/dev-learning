package info.ivicel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.ivicel.domain.Salary;
import info.ivicel.serivce.IService;
import info.ivicel.serivce.impl.EmployeeService;
import info.ivicel.serivce.impl.SalaryService;


@WebServlet("/query/salary")
public class QuerySalaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Salary> salaries;
        IService<Salary> service = new SalaryService();
        try {
            int empNo = Integer.valueOf(req.getParameter("num"));
            salaries = new ArrayList<>(1);
            salaries.add(service.find(empNo));
        } catch (NumberFormatException ignored) {
            salaries = service.findAll();
        }
        req.setAttribute("list", salaries);
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
}
