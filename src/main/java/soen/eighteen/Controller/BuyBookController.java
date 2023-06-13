package soen.eighteen.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import soen.eighteen.Dao.Impl.BookAbstractImpl;
import soen.eighteen.Dao.Impl.CategoryDetailAbstractImpl;
import soen.eighteen.Dao.Impl.OrderAbstractImpl;
import soen.eighteen.Dao.Impl.OrderDetailAbstractImpl;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.CategoryDetail;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;

@WebServlet(urlPatterns = { "/book", "/details", "/add-cart" })
public class BuyBookController extends HttpServlet {
	private static final long serialVersionUID = 3459424452544316296L;

	@Inject
	private BookAbstractImpl bookDaoabstract = new BookAbstractImpl();
	OrderAbstractImpl orderDao = new OrderAbstractImpl();
	CategoryDetailAbstractImpl categoryDetailDao = new CategoryDetailAbstractImpl();
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		session= req.getSession();
		session.removeAttribute("messadd");

		if (url.contains("/detail")) {
			String bookID = req.getParameter("bookId");
			Book b = bookDaoabstract.findById(Integer.parseInt(bookID));
			List<CategoryDetail> lstCategoryDetails = categoryDetailDao.FindCategoryName(Integer.parseInt(bookID));
			if (lstCategoryDetails != null)
				req.setAttribute("lstCate", lstCategoryDetails);
			req.setAttribute("book", b);
			req.getRequestDispatcher("views/details.jsp").forward(req, resp);
		} else {
			List<Book> book = bookDaoabstract.findAll();
			req.setAttribute("listAllBook", book);
			req.getRequestDispatcher("views/book.jsp").forward(req, resp);
		}
	}

	protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = (Order) session.getAttribute("order");
		Customer cus = (Customer) session.getAttribute("user");
		String bookID = req.getParameter("bookId");
		Book b = bookDaoabstract.findById(Integer.parseInt(bookID));
		@SuppressWarnings("unchecked")
		List<OrderDetail> detailList = (List<OrderDetail>) session.getAttribute("detaillist");
		if (order == null) {
			Date current = new Date(System.currentTimeMillis());
			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			order = new Order();
			order.setCustomer(cus);
			order.setOrderDate(current);
			order.setStatus(0);
			order.setOderDetails(detailList);
		} else {
			Date current = new Date(System.currentTimeMillis());
			order.setOrderDate(current);
		}

		OrderDetail item = new OrderDetail();
		item.setBook(b);
		item.setOrder(order);
		OrderDetailAbstractImpl orderdetailDao = new OrderDetailAbstractImpl();
        orderdetailDao.insert(item);
		detailList.add(item);
		order.setOderDetails(detailList);
		
		
		
		session.setAttribute("detaillist", detailList);
		session.setAttribute("order", order);
		req.setAttribute("messadd", "Done!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		if (url.contains("/add-cart")) {
			if ((Customer) session.getAttribute("user") == null) {
				resp.sendRedirect("login");
			} else {
				addCart(req, resp);
				Order order = (Order) session.getAttribute("order");
				System.out.println(order.getOderDetails().size());
				resp.sendRedirect("book");
			}
		}
	}

}