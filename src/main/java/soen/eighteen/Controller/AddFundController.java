package soen.eighteen.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import soen.eighteen.Dao.Impl.AccountAbstractImpl;
import soen.eighteen.Dao.Impl.CustomerAbstractImpl;
import soen.eighteen.Entity.Customer;

@WebServlet(urlPatterns = "/funds")
public class AddFundController extends HttpServlet {
	private static final long serialVersionUID = -1l;
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
		currentuser = (Customer) session.getAttribute("user");
		RequestDispatcher rq = req.getRequestDispatcher("decorators/funds.jsp");
		rq.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int amount = Integer.parseInt(req.getParameter("amount"));
		if (amount > 0) {
			currentuser = (Customer) session.getAttribute("user");
			currentuser.setBalance(currentuser.getBalance() + amount);
			customerDao.updateOnetoOne(currentuser);
			session.setAttribute("user",currentuser);
			resp.sendRedirect("user");
		} else {
			req.setAttribute("mess", "Amount can not be negative or zero!");
			RequestDispatcher rq = req.getRequestDispatcher("decorators/funds.jsp");
			rq.forward(req, resp);
		}
	}
}
