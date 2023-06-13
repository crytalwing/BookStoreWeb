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

import soen.eighteen.Dao.Impl.BookAbstractImpl;
import soen.eighteen.Dao.Impl.CustomerAbstractImpl;
import soen.eighteen.Dao.Impl.OrderAbstractImpl;
import soen.eighteen.Dao.Impl.OrderDetailAbstractImpl;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;

@WebServlet(urlPatterns = { "/cart", "/cart-delete", "/cart-checkout" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = -4156966524867864919L;
	HttpSession session = null;
	private OrderDetailAbstractImpl odab = new OrderDetailAbstractImpl();
	private OrderAbstractImpl orderDao = new OrderAbstractImpl();
	private BookAbstractImpl book = new BookAbstractImpl();
	private CustomerAbstractImpl customerDao = new CustomerAbstractImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		session = req.getSession();

		String url = req.getRequestURL().toString();

		if (url.contains("delete")) {

			delete(req, resp);

		} else if (url.contains("checkout")) {
			checkout(req, resp);
		}

		String order = req.getParameter("id");
		@SuppressWarnings("unchecked")
		List<OrderDetail> odt = (List<OrderDetail>) session.getAttribute("detaillist");

		req.setAttribute("detaillist", odt);
		Order od = (Order) session.getAttribute("order");

		RequestDispatcher rq = req.getRequestDispatcher("views/user/cart.jsp");
		rq.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		RequestDispatcher rq = req.getRequestDispatcher("views/user/cart.jsp");
		rq.forward(req, resp);
	}

	private void checkout(HttpServletRequest req, HttpServletResponse resp) {

		try {
			Customer currentUser = (Customer) session.getAttribute("user");
			Order currentOrder = (Order) session.getAttribute("order");
			int totalPrice = 0;
			@SuppressWarnings("unchecked")
			List<OrderDetail> ordDetailList = (List<OrderDetail>) session.getAttribute("detaillist");
			if (ordDetailList.size() > 0) {
				for (OrderDetail ordDe : ordDetailList) {
					totalPrice = totalPrice + ordDe.getBook().getPrice();
				}
				// this is the current order
				currentOrder.setTotalPrice(totalPrice);
				if (totalPrice <= currentUser.getBalance())
				{
					//calc wallet
					currentOrder.setStatus(1);
					orderDao.updateRela(currentOrder);
					int newBalance = currentUser.getBalance() - totalPrice;
					currentUser.setBalance(newBalance);
					customerDao.updateOnetoOne(currentUser);
					//create new cart
					ordDetailList.clear();
					Order newOrder =new Order();
					newOrder.setStatus(0);
					newOrder.setTotalPrice(0);
					newOrder.setOderDetails(ordDetailList);
					orderDao.insertWithSet(currentUser, newOrder, ordDetailList);
					
					
					session.setAttribute("user", currentUser);
					
					session.setAttribute("order", newOrder);
					req.setAttribute("message", "ĐãPurchase success!");
					
				}
				session.setAttribute("detaillist", ordDetailList);

				req.setAttribute("detaillist", ordDetailList);
				
			}else
				req.setAttribute("error", "Not enough balance!");
			

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {

		try {

			String detailID = req.getParameter("id");

			odab.delete(Integer.parseInt(detailID));
			Order ord = (Order) session.getAttribute("order");
			List<OrderDetail> odt = orderDao.getDetails(ord);
			session.setAttribute("detaillist", odt);
			req.setAttribute("detaillist", odt);
			req.setAttribute("message", "Đã Deleted");
			req.setAttribute("count", odt.size());

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

}
