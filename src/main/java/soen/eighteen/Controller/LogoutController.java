package soen.eighteen.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import soen.eighteen.Dao.Impl.CustomerAbstractImpl;
import soen.eighteen.Dao.Impl.OrderAbstractImpl;
import soen.eighteen.Dao.Impl.OrderDetailAbstractImpl;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = -2521978547590044906L;
	CustomerAbstractImpl customerDao = new CustomerAbstractImpl();
	OrderAbstractImpl orderDao = new OrderAbstractImpl();
	OrderDetailAbstractImpl orderdetailDao = new OrderDetailAbstractImpl();
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		if (!session.getAttribute("checkuser").toString().equals("1"))
			updateCart(req, resp);
		session.removeAttribute("checkuser");
		session.removeAttribute("user");
		session.removeAttribute("order");
		session.removeAttribute("detaillist");
		session.removeAttribute("mess");
		session.removeAttribute("message");


		resp.sendRedirect("./home");
	}

	protected void updateCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = (Order) session.getAttribute("order");
		Customer customer = (Customer) session.getAttribute("user");
		@SuppressWarnings("unchecked")
		List<OrderDetail> detailList = (List<OrderDetail>) session.getAttribute("detaillist");
		order.setOderDetails(detailList);
		List<Order> orderList = new ArrayList<Order>();


		for (Order ord : orderDao.findAll()) {
			if (ord.getCustomer().getUsername().equals(customer.getUsername())) {
				orderList.add(ord);
			}
		}
		if (orderDao.findById(order.getOrderID()) != null) {
			orderList.add(order);
			orderDao.updateRela(order);
			List<OrderDetail> checkdetailList = orderDao.getDetails(order);
			if (checkdetailList.size() != detailList.size()) 
			for (OrderDetail detail : detailList) {
				detail.setOrder(order);
				orderdetailDao.insertWithSet(detail.getBook(), order, detail);
			}
		} else {
			for (Order ord : orderList) {
				if (ord.getOrderID() == order.getOrderID()) {
					orderList.set(orderList.indexOf(ord), order);
					orderDao.updateRela(ord);
					List<OrderDetail> checkdetailList = orderDao.getDetails(ord);
					if (checkdetailList.size() != detailList.size()) {
						for (OrderDetail detail : detailList) {
							detail.setOrder(ord);
							orderdetailDao.insertWithSet(detail.getBook(), ord, detail);
						}
					}
				}
			}
		}

		customer.setOrders(orderList);
		customerDao.update(customer);
	}
}
