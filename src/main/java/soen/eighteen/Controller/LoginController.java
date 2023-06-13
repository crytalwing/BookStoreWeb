package soen.eighteen.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.annotations.Check;

import soen.eighteen.Dao.Impl.AccountAbstractImpl;
import soen.eighteen.Dao.Impl.CustomerAbstractImpl;
import soen.eighteen.Dao.Impl.EmployeeAbstractImpl;
import soen.eighteen.Dao.Impl.OrderAbstractImpl;
import soen.eighteen.Dao.Impl.OrderDetailAbstractImpl;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Employee;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -154364869572962728L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher rq = req.getRequestDispatcher("decorators/login.jsp");
		rq.forward(req, resp);
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		String accType = req.getParameter("accType");

		AccountAbstractImpl account = new AccountAbstractImpl();
		CustomerAbstractImpl customerDao = new CustomerAbstractImpl();
		EmployeeAbstractImpl employeeDao = new EmployeeAbstractImpl();

		String mess = "username or password incorrect";

		boolean checkAccount = account.CheckAccount(name, pass);
		List<Customer> customer = customerDao.FindByObject("username", name);
		int isCustomer = 0;
		if (accType == null) {
			mess = "Please check your role!!";
		} else if ((checkAccount == true)) {
			if (customer.size() > 0) {
				if (accType.equals("member")) {
					isCustomer = 2;

				}

			} else {
				if (accType.equals("manager")) {
					isCustomer = 1;

				}
			}
		}

		if (isCustomer == 0) {
			req.setAttribute("mess", mess);
			RequestDispatcher rq = req.getRequestDispatcher("decorators/login.jsp");
			rq.forward(req, resp);

		} else {
			HttpSession session = req.getSession();
			session.setAttribute("checkuser", isCustomer);
			if (isCustomer == 1) {
				String username = req.getParameter("username");

				Employee employee = employeeDao.findById(username);

				Date date = employee.getBirthDate();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strDate = formatter.format(date);
				session.setAttribute("birthdate", strDate);

				session.setAttribute("admin", employee);
				resp.sendRedirect("admin");

			} else {
				resp.sendRedirect("user");
				OrderAbstractImpl orderDao = new OrderAbstractImpl();
				String username = req.getParameter("username");
				if (username != null && username.length() > 0) {
					Customer cus = customerDao.FindByObject("username", username).get(0);
					Order cart = new Order();
					;
					cart = customerDao.getOldCart(username);

					if (cart == null) {
						cart = new Order();
						cart.setStatus(0);
						cart.setTotalPrice(0);
						orderDao.insertWithSet(cus, cart, null);
					}


					List<OrderDetail> detaillist = orderDao.getDetails(cart);
					System.out.println(cart.getOrderID() + " SIZE OF DETAIL LIST : " + detaillist.size());
					cart.setOderDetails(detaillist);
					// System.out.println("SIZE OF DETAIL LIST : "+detaillist.size());
					session.setAttribute("order", cart);
					session.setAttribute("user", cus);
					session.setAttribute("detaillist", detaillist);
				

				}
			}

		}
	}
}
