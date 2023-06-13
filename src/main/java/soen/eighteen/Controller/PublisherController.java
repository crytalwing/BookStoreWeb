package soen.eighteen.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import soen.eighteen.Dao.Impl.PublisherAbstractImpl;
import soen.eighteen.Entity.Publisher;

@WebServlet(urlPatterns = {"/admin/publisher","/admin-publisher/reset","/admin-publisher/edit", "/admin-publisher/create", "/admin-publisher/update" ,"/admin-publisher/delete"})

public class PublisherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PublisherAbstractImpl publisherDao = new PublisherAbstractImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();

		Publisher pub = null;

		if (url.contains("create")) {

			req.getRequestDispatcher("/views/admin/publisher/publisher.jsp").forward(req, resp);

		} else if (url.contains("delete")) {

			delete(req, resp);
			pub = new Publisher();

			req.setAttribute("pub", pub);

		} else if (url.contains("edit")) {

			edit(req, resp);

		} else if (url.contains("reset")) {

			pub = new Publisher();

			req.setAttribute("pub", pub);

		}
		
		FindAll(req, resp);

		req.getRequestDispatcher("/views/admin/publisher/publisher.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURL().toString();
		if (url.contains("create")) {

			insert(req, resp);

		} else if (url.contains("update")) {

			update(req, resp);

		} else if (url.contains("delete")) {

			delete(req, resp);

		} else if (url.contains("reset")) {

			req.setAttribute("pub", new Publisher());

		}
		FindAll(req, resp);

		req.getRequestDispatcher("/views/admin/publisher/publisher.jsp").forward(req, resp);
	}

	protected void FindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Publisher> lstpubs = publisherDao.findAll();
			req.setAttribute("pubList", lstpubs);
		} catch (Exception e) {
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {

			String pubID = req.getParameter("id");
			Publisher pub = publisherDao.findById(Integer.parseInt(pubID));
			req.setAttribute("pub", pub);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) {

		try {

			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			Publisher pub = new Publisher();
			BeanUtils.populate(pub, req.getParameterMap());
			publisherDao.insert(pub);

			req.setAttribute("message", "Đã Added Successful!");

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) {

		try {

			String pubID = req.getParameter("id");
			publisherDao.delete(Integer.parseInt(pubID));
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

		 Publisher pub = new Publisher();
			BeanUtils.populate(pub, req.getParameterMap());
			publisherDao.update(pub);

			req.setAttribute("pub", pub);
			req.setAttribute("message", "Success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}
}
