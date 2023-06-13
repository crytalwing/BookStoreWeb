package soen.eighteen.Controller;

import java.io.IOException;
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

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = -1l;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher rq = req.getRequestDispatcher("decorators/register.jsp");
		rq.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		String fname = req.getParameter("fullName");
		String phone = req.getParameter("phoneNumber");
		String address = req.getParameter("address");

	    CustomerAbstractImpl customerDao = new CustomerAbstractImpl();
		AccountAbstractImpl accountDao = new AccountAbstractImpl();
		List<Customer> customer = customerDao.FindByObject("username",name);
		if (customer.size() > 0) {
			req.setAttribute("mess", "Username Exist!");
			RequestDispatcher rq = req.getRequestDispatcher("decorators/register.jsp");
			rq.forward(req, resp);
		} else
		{
			Customer newcus = new Customer();
		    Account newacc = new Account();
		    //set acc
		    newacc.setUsername(name);
		    newacc.setPassword(pass);
		    //set cus
		    newcus.setFullName(fname);
		    newcus.setAddress(address);
		    newcus.setBalance(0);
		    newcus.setPhoneNumber(phone);
		    newcus.setPoint(0);
		    newcus.setUsername(name);
		    
		    newacc.setCustomer(newcus);
		    newcus.setAccount(newacc);		

		    accountDao.insertOnetoOne(newacc,newcus);
		    //customerDao.insertOnetoOne(newcus, newacc);
		    //customerDao.insert(newcus);
		    req.setAttribute("mess", "Registered!");
			RequestDispatcher rq = req.getRequestDispatcher("decorators/register.jsp");
			rq.forward(req, resp);
//			
			
		}
//		if (isCustomer == 0)
//		{
//			req.setAttribute("mess", "username or password incorrect");
//			RequestDispatcher rq = req.getRequestDispatcher("decorators/login.jsp");
//			rq.forward(req, resp);
//		
//		}
//		else {
//			HttpSession session = req.getSession();
//			session.setAttribute("checkuser", isCustomer);
//			if (isCustomer == 1)
//			{
//				resp.sendRedirect("admin");
//			}
//			else
//				resp.sendRedirect("user");
//		}
	}
}
