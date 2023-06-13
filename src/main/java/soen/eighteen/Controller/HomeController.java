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

import soen.eighteen.Dao.Impl.BookAbstractImpl;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.Customer;



@WebServlet(urlPatterns = { "/home", "/trangchu" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -587132695837461864L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		BookAbstractImpl bookab = new BookAbstractImpl();
		
		List<Book> lstBestbook = bookab.GetBestBookByRate();
		List<Book> lstLastbook = bookab.GetLastBook();
		List<Book>lstBestbook1= new ArrayList<Book>();
		
		req.setAttribute("listBestPro", lstBestbook);
		req.setAttribute("listLastPro", lstLastbook);
		//lstBestbook1.add(lstBestbook.get(0));
	//	req.setAttribute("listBestPro1", lstBestbook1);
		
	
		
		RequestDispatcher rq = req.getRequestDispatcher("views/home.jsp");
		rq.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
