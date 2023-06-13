package soen.eighteen.Dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Account;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.Entity.OrderDetail;
import soen.eighteen.JPAConfig.JPAConfig;

public class OrderAbstractImpl extends AbstractDao<Order> {
	public OrderAbstractImpl() {
		super(Order.class);
	}

	public void updateStatus(Order o) {
		o.setStatus(1);
	}

	public void updateRela(Order o) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.detach(o);
			Date current = new Date(System.currentTimeMillis());
			o.setOrderDate(current);
			enma.merge(o);
			enma.flush();
			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	public void insertWithSet(Customer c, Order o, List<OrderDetail> d) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Date current = new Date(System.currentTimeMillis());
			enma.detach(o);
			o.setOrderDate(current);
			o.setCustomer(c);
			o.setOderDetails(d);
			enma.persist(o);

			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	public List<OrderDetail> getDetails(Order order) {
		OrderDetailAbstractImpl detailDao = new OrderDetailAbstractImpl();
		List<OrderDetail> itemlist = new ArrayList<OrderDetail>();
		if (detailDao.findAll().size() > 0 && order != null) {
			for (OrderDetail detail : detailDao.findAll()) {
				if (detail.getOrder().getOrderID() == order.getOrderID()) {
					itemlist.add(detail);
				}
			}
		}
		return itemlist;
	}
}
