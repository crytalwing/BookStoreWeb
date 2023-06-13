package soen.eighteen.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import soen.eighteen.Dao.Impl.AccountAbstractImpl;
import soen.eighteen.Dao.Impl.CustomerAbstractImpl;
import soen.eighteen.Entity.Account;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;

@WebServlet(urlPatterns = { "/user", "/me" })

public class UserController extends HttpServlet {

	private static final long serialVersionUID = -57461864L;
	CustomerAbstractImpl customerDao = new CustomerAbstractImpl();
	AccountAbstractImpl accountDao = new AccountAbstractImpl();
	Customer currentuser = new Customer();
	HttpSession session = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		session = req.getSession();
		//String current = session.getAttribute("username").toString();
		//currentuser = customerDao.FindByObject("username", current).get(0);
		currentuser = (Customer) session.getAttribute("user");
		setFields(req,resp);
		RequestDispatcher rq = req.getRequestDispatcher("views/user/home.jsp");
		rq.forward(req, resp);

	}
	protected void setFields(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session.setAttribute("userName", currentuser.getUsername());
		session.setAttribute("fullName", currentuser.getFullName());
		session.setAttribute("phoneNumber", currentuser.getPhoneNumber());
		session.setAttribute("address", currentuser.getAddress());
		session.setAttribute("password", currentuser.getAccount().getPassword());
		session.setAttribute("balance", currentuser.getBalance());
		session.setAttribute("userN", currentuser.getUsername());
	}
	protected void getFields(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer tmp = new Customer();
		tmp.setAddress(req.getParameter("address"));
		tmp.setFullName(req.getParameter("fullName"));
		tmp.setPhoneNumber(req.getParameter("phoneNumber"));
		tmp.setBalance(Integer.parseInt(req.getParameter("balance")));
		tmp.setUsername(req.getParameter("userN"));
		tmp.setPhoneNumber(req.getParameter("phoneNumber"));
		Account acc = accountDao.FindByObject("username", req.getParameter("userN")).get(0);
		tmp.setAccount(acc);
		tmp.getAccount().setPassword(req.getParameter("password"));;
		session.setAttribute("user", tmp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		session = req.getSession();
		getFields(req,resp);
		currentuser = (Customer) session.getAttribute("user");
		customerDao.updateOnetoOne(currentuser);
		session.setAttribute("user", currentuser);
		//List<Customer> tmp = (List<Customer>) session.getAttribute("userlist");
		req.setAttribute("message", "Updated ");
		setFields(req,resp);
		
		RequestDispatcher rq = req.getRequestDispatcher("views/user/home.jsp");
		rq.forward(req, resp);
	}

}
