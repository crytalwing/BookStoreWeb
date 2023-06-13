package soen.eighteen.Dao.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;
import soen.eighteen.JPAConfig.JPAConfig;

public class OrderDetailAbstractImpl extends AbstractDao<OrderDetail> {

	public OrderDetailAbstractImpl() {
		super(OrderDetail.class);
	}

	public void insertWithSet(Book b, Order o, OrderDetail d) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			d.setBook(b);
			d.setOrder(o);
			enma.merge(d);

			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}
	
	public List<OrderDetail >findOrderDetailByMonth(String selectedmonth)
	{
		Calendar cal = Calendar.getInstance();
		int month = Integer.parseInt(selectedmonth);

		List<OrderDetail> orderDetailList = this.findAll();
		List<OrderDetail> chosenOrderDetail = new ArrayList<OrderDetail>();
		for (OrderDetail ordDetail : orderDetailList) {
			cal.setTime(ordDetail.getOrder().getOrderDate());
			if (cal.get(Calendar.MONTH) + 1 == month && ordDetail.getOrder().getStatus() != 0 ) {
				chosenOrderDetail.add(ordDetail);
			}
		}
		return chosenOrderDetail;
	}
}
