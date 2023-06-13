package soen.eighteen.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.hibernate.Session;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Account;
import soen.eighteen.Entity.Customer;
import soen.eighteen.Entity.Order;
import soen.eighteen.JPAConfig.JPAConfig;

public class CustomerAbstractImpl extends AbstractDao<Customer> {
	public CustomerAbstractImpl() {
		super(Customer.class);
	}

	public void insertOnetoOne(Customer c, Account a) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			c.setAccount(a);
			enma.merge(c);

			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Transactional
	public Order getOldCart(String username) {
		OrderAbstractImpl orderDao = new OrderAbstractImpl();
		if (orderDao.findAll() != null) {
			for (Order ord : orderDao.findAll()) {
				if (ord.getCustomer().getUsername().equals(username)) {
					if (ord.getStatus() == 0) {
						return ord;
					}

				}
			}
		}
		else {
			Order newOrder = new Order();
			return newOrder;
		}
		return null;
	}

	public void updateOnetoOne(Customer c) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.detach(c);
			enma.merge(c);
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
}
