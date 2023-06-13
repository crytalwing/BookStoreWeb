package soen.eighteen.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import soen.eighteen.Dao.Impl.OrderDetailAbstractImpl;
import soen.eighteen.Dao.Impl.OrderAbstractImpl;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;

@WebServlet(urlPatterns = { "/admin/salecalc"})
public class SaleController extends HttpServlet {

	private static final long serialVersionUID = -587132695837461864L;
	HttpSession session = null;
	OrderDetailAbstractImpl orderdetailDao = new OrderDetailAbstractImpl();
	OrderAbstractImpl ordDao = new OrderAbstractImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		if(url.contains("salecalc"))
		{
			req.getRequestDispatcher("/views/admin/managesale/salecalculator.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String url = req.getRequestURL().toString();
			//--------------------------SALE CALCULATION
			String selectedmonth;
			if(req.getParameter("selectedmonth") != "")
			{
				selectedmonth = req.getParameter("selectedmonth");
			}
			else {
				selectedmonth = "0";
			}
			if (Integer.parseInt(selectedmonth) >0 &&  Integer.parseInt(selectedmonth) <13)
			{
//				Calendar cal = Calendar.getInstance();
//				int month = Integer.parseInt(selectedmonth);
//
//				List<OrderDetail> orderDetailList = orderdetailDao.findAll();
//				List<OrderDetail> chosenOrderDetail = new ArrayList<OrderDetail>();
//				for (OrderDetail ordDetail : orderDetailList) {
//					cal.setTime(ordDetail.getOrder().getOrderDate());
//					if (cal.get(Calendar.MONTH) + 1 == month && ordDetail.getOrder().getStatus() != 0 ) {
//						chosenOrderDetail.add(ordDetail);
//					}
//				}
				
				List<OrderDetail> chosenOrderDetail = orderdetailDao.findOrderDetailByMonth(selectedmonth);
				req.setAttribute("listofsale", chosenOrderDetail);
		        req.getRequestDispatcher("/views/admin/managesale/salecalculator.jsp").forward(req, resp);
			}
			else
			{
				req.setAttribute("mess", "Month have to be between 1 and 12 ");
				req.getRequestDispatcher("/views/admin/managesale/salecalculator.jsp").forward(req, resp);
			}
			
	}
}
