package soen.eighteen.Controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soen.eighteen.Dao.Impl.BookAbstractImpl;
import soen.eighteen.Entity.Book;

@WebServlet(urlPatterns = { "/search", "/search/details" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 3459424452544316296L;

	@Inject
	private BookAbstractImpl bookDaoabstract = new BookAbstractImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		String bookID = req.getParameter("bookID");
		if (url.contains("/search/detail")) {
			Book b = bookDaoabstract.findById(Integer.parseInt(bookID));
			req.setAttribute("book", b);
			req.getRequestDispatcher("views/details.jsp").forward(req, resp);
		} else {
			
			req.getRequestDispatcher("views/search.jsp").forward(req, resp);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String bookName = req.getParameter("txtsearch");
		List<Book> book = bookDaoabstract.FindBookByName(bookName);
		if (book.size() > 0) {
			req.setAttribute("searchlist", book);
			req.getRequestDispatcher("views/search.jsp").forward(req, resp);
		} else {
			req.setAttribute("found", "Not Found");
			req.getRequestDispatcher("views/search.jsp").forward(req, resp);
		}
	}

}