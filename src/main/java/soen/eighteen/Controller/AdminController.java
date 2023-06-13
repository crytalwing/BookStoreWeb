package soen.eighteen.Controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import soen.eighteen.Dao.Impl.AccountAbstractImpl;
import soen.eighteen.Dao.Impl.EmployeeAbstractImpl;
import soen.eighteen.Entity.Employee;

@WebServlet(urlPatterns = { "/admin", "/trangquantri", "/admin/update" })
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = -587132695837461864L;
	EmployeeAbstractImpl employeeDao = new EmployeeAbstractImpl();
	AccountAbstractImpl accountDao = new AccountAbstractImpl();

	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		Employee employee = new Employee();
		
		try {
			session = req.getSession();
			employee = (Employee) session.getAttribute("admin");

			employee.setFullName(req.getParameter("fullName"));
			Date date = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    date = formatter.parse(req.getParameter("birthDate"));

			employee.setBirthDate(date);
			employee.setPhoneNumber(req.getParameter("phoneNumber"));
			
			session.setAttribute("admin", employee);
			employeeDao.update(employee);
			req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
			
		} catch (Exception e) {
		}
	}
}
