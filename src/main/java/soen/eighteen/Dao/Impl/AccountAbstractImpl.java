package soen.eighteen.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Account;
import soen.eighteen.Entity.Customer;
import soen.eighteen.JPAConfig.JPAConfig;

public class AccountAbstractImpl extends AbstractDao<Account> {
	public AccountAbstractImpl() {
		super(Account.class);
	}

	public void insertOnetoOne(Account a, Customer c) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			a.setCustomer(c);
			enma.merge(a);
			
			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}
	public void updateOnetoOne(Account a) {

		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			
			a=enma.merge(a);
			
			trans.commit();
		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}
	public boolean CheckAccount(String name, String pass) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			CriteriaBuilder cb = enma.getCriteriaBuilder();

			CriteriaQuery<Account> cq = cb.createQuery(Account.class);
			Root<Account> accRoot = cq.from(Account.class);

			ParameterExpression<String> pe1 = cb.parameter(String.class);
			ParameterExpression<String> pe2 = cb.parameter(String.class);

			cq.select(accRoot)
					.where(cb.and(cb.equal(accRoot.get("username"), pe1), cb.equal(accRoot.get("password"), pe2)));
			TypedQuery<Account> typedQuery = enma.createQuery(cq);
			typedQuery.setParameter(pe1, name);
			typedQuery.setParameter(pe2, pass);
			List<Account> account = typedQuery.getResultList();
			System.out.print(accRoot.get("username"));
			enma.close();
			if (account.size() > 0)
				return true;
			return false;

		} finally {
			enma.close();

		}

	}
}
