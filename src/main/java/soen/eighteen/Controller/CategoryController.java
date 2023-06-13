package soen.eighteen.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import soen.eighteen.Dao.Impl.CategoryAbstractImpl;
import soen.eighteen.Entity.Category;

@WebServlet(urlPatterns = {"/admin/category","/admin-category/reset","/admin-category/edit", "/admin-category/create", "/admin-category/update" ,"/admin-category/delete"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 2517967239784287018L;
	CategoryAbstractImpl categoryDao = new CategoryAbstractImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();

		Category cate = null;

		if (url.contains("create")) {

			req.getRequestDispatcher("/views/admin/category/category.jsp").forward(req, resp);

		} else if (url.contains("delete")) {

			delete(req, resp);
			cate = new Category();

			req.setAttribute("category", cate);

		} else if (url.contains("edit")) {

			edit(req, resp);

		} else if (url.contains("reset")) {

			cate = new Category();

			req.setAttribute("category", cate);

		}
		
		FindAll(req, resp);

		req.getRequestDispatcher("/views/admin/category/category.jsp").forward(req, resp);

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

			req.setAttribute("category", new Category());

		}
		FindAll(req, resp);

		req.getRequestDispatcher("/views/admin/category/category.jsp").forward(req, resp);
	}

	protected void FindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Category> lstCates = categoryDao.findAll();
			req.setAttribute("categoryList", lstCates);
		} catch (Exception e) {
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {

			String cateID = req.getParameter("id");
			Category cate = categoryDao.findById(Integer.parseInt(cateID));
			req.setAttribute("category", cate);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) {

		try {

			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			Category cate = new Category();
			BeanUtils.populate(cate, req.getParameterMap());
			categoryDao.insert(cate);

			req.setAttribute("message", "Đã Added!");

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) {

		try {

			String cateID = req.getParameter("id");
			categoryDao.delete(Integer.parseInt(cateID));
			req.setAttribute("message", "Đã Deleted!");

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

	protected void update(HttpServletRequest req, HttpServletResponse reps)

			throws ServletException, IOException {
		
		try {

			req.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			Category cate = new Category();
			BeanUtils.populate(cate, req.getParameterMap());
			categoryDao.update(cate);

			req.setAttribute("category", cate);
			req.setAttribute("message", "Successfully Updated!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
}
