package soen.eighteen.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import soen.eighteen.Dao.Impl.BookAbstractImpl;
import soen.eighteen.Dao.Impl.CategoryAbstractImpl;
import soen.eighteen.Dao.Impl.CategoryDetailAbstractImpl;
import soen.eighteen.Dao.Impl.PublisherAbstractImpl;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.Category;
import soen.eighteen.Entity.CategoryDetail;
import soen.eighteen.Entity.Publisher;

@WebServlet(urlPatterns = { "/admin/book", "/admin-book/edit","/admin-book/create", "/admin-book/update","/admin-book/delete","/admin-book/reset",
		"/admin-manager/book/update","/admin-book/detail","/admin-book/category"})
public class BookController extends HttpServlet {

	private static final long serialVersionUID = -5875837461864L;
	BookAbstractImpl bookDao = new BookAbstractImpl();
	PublisherAbstractImpl publisherDao = new PublisherAbstractImpl();
	CategoryDetailAbstractImpl categoryDetailDao = new CategoryDetailAbstractImpl();
	CategoryAbstractImpl categoryDao = new CategoryAbstractImpl();
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		

		Book book = null;
		String url = req.getRequestURL().toString();
		if (url.contains("create")) {

			req.getRequestDispatcher("/views/admin/managerbook/managerbook.jsp").forward(req, resp);

		} else if (url.contains("delete")) {

			delete(req, resp);
			book = new Book();

			req.setAttribute("book", book);

		} else if (url.contains("edit")) {

			edit(req, resp);

		} else if (url.contains("reset")) {

			book = new Book();

			req.setAttribute("book", book);

		}else if (url.contains("detail"))
		{
			detail(req, resp);
			req.getRequestDispatcher("/views/admin/managerbook/bookdetail.jsp").forward(req, resp);

		}else if (url.contains("category"))
		{
			String bookID = req.getParameter("id");
			book =new Book();
			book=bookDao.findById(Integer.parseInt(bookID));
			req.setAttribute("book", book);
			List<Category> lstCategories = categoryDao.findAll();
			req.setAttribute("cateList", lstCategories);
			
		}

		FindAll(req, resp);
		List<Publisher> lstPublishers = publisherDao.findAll();
		req.setAttribute("pubList", lstPublishers);
		
		
		req.getRequestDispatcher("/views/admin/managerbook/managerbook.jsp").forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy url

		String url = req.getRequestURL().toString();
		if (url.contains("create")) {

			insert(req, resp);

		} else if (url.contains("update")) {

			update(req, resp);

		} else if (url.contains("delete")) {

			delete(req, resp);

		} else if (url.contains("reset")) {

			req.setAttribute("book", new Book());

		} 
		else if (url.contains("category"))
		{
			
			AddCate(req, resp);
			

			
		}
		FindAll(req, resp);

		req.getRequestDispatcher("/views/admin/managerbook/managerbook.jsp").forward(req, resp);
	}
	
	private void AddCate(HttpServletRequest req, HttpServletResponse resp) {

		try {
			req.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			
			String bookID = req.getParameter("bookID");
			

			Book book =new Book();
			book=bookDao.findById(Integer.parseInt(bookID));
			String cateName = req.getParameter("catename");
	
			Category category = categoryDao.FindByObject("name", cateName).get(0);
			
			
			CategoryDetail categoryDetail = new CategoryDetail();
			categoryDetail.setBook(book);
			categoryDetail.setCategory(category);
			
			categoryDetailDao.insert(categoryDetail);
			req.setAttribute("message","Successfull");
		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}
		
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {

		try {

			String bookID = req.getParameter("id");
			bookDao.delete(Integer.parseInt(bookID));
			req.setAttribute("message", "ĐãDeleted");

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}
		
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) {
		
		
		try {

			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			Book book = new Book();
			BeanUtils.populate(book, req.getParameterMap());
			String pubName = req.getParameter("pubname");
			Publisher publisher = publisherDao.FindByObject("name",pubName).get(0);
		
			book.setPublisher(publisher);
		
			
			bookDao.insertBook(book, publisher);

			req.setAttribute("message", publisher.getName());

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}
		
	}

	protected void detail(HttpServletRequest req, HttpServletResponse reps)

			throws ServletException, IOException {
		
		try {

			req.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			String bookID = req.getParameter("id");
			Book book =new Book();
			book=bookDao.findById(Integer.parseInt(bookID));
			List<CategoryDetail> lstCategoryDetails = categoryDetailDao.FindCategoryName(Integer.parseInt(bookID));
			if (lstCategoryDetails != null)
				req.setAttribute("lstCate", lstCategoryDetails);


			req.setAttribute("book", book);
			req.setAttribute("message", "Successfull");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());

		}
		
		

	}
	
	protected void edit(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {

			String bookID = req.getParameter("id");
			Book book = bookDao.findById(Integer.parseInt(bookID));
			req.setAttribute("book", book);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
	
	protected void FindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			String indexPage = req.getParameter("index");
//			if (indexPage.equals(null))
//			{
//				indexPage ="1";
//			}
//			int index= Integer.parseInt(indexPage);
//			int count = bookDao.count();
//			int endPage = count/3;
//			if (count%3 !=0)
//			{
//				endPage++;
//			}
//			req.setAttribute("endP", endPage);
//					req.setAttribute("tag", index);

			List<Book> lstBooks = bookDao.findAll();
			req.setAttribute("bookList", lstBooks);
		} catch (Exception e) {
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse reps)

			throws ServletException, IOException {
		
		try {

			req.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			String pubName = req.getParameter("pubname");
			Publisher publisher = publisherDao.FindByObject("name",pubName).get(0);
		
			Book book = new Book();
			BeanUtils.populate(book, req.getParameterMap());
			book.setPublisher(publisher);
			bookDao.updateBook(book);

			req.setAttribute("book", book);
			req.setAttribute("message", "Successfull");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
}
