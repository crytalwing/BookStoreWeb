package soen.eighteen.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.Publisher;
import soen.eighteen.JPAConfig.JPAConfig;

public class BookAbstractImpl extends AbstractDao<Book>{
	public BookAbstractImpl() {
		super(Book.class);
	}
	public void updateBook(Book b) {

		EntityManager enma = JPAConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.detach(b);
		    enma.merge(b);
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

	public void insertBook(Book b, Publisher pub) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(b);
			b.setPublisher(pub);
			enma.merge(b);
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
	public List<Book> FindBookByName(String bookName) {
		EntityManager enma = JPAConfig.getEntityManager();
		List<Book> entity = null;

		try {
			CriteriaBuilder cb = enma.getCriteriaBuilder();

			CriteriaQuery<Book> cq = cb.createQuery(Book.class);
			Root<Book> rt = cq.from(Book.class);
			cq.select(rt);
			if (!bookName.isEmpty()) {
				Predicate p = cb.like(rt.get("name").as(String.class), String.format("%%%s%%", bookName));
				cq = cq.where(p);
			}
			TypedQuery<Book> typedQuery = enma.createQuery(cq);
			entity = typedQuery.getResultList();
			return entity;
		} finally {
			enma.close();

		}
	}
	public List<Book> GetBestBookByRate() {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			CriteriaQuery cQuery = enma.getCriteriaBuilder().createQuery();
			cQuery.select(cQuery.from(Book.class));
			return enma.createQuery(cQuery).setMaxResults(4).getResultList();
		} finally {
			enma.close();
		}
	}

	public List<Book> GetLastBook() {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			CriteriaQuery cQuery = enma.getCriteriaBuilder().createQuery();
			cQuery.select(cQuery.from(Book.class));
			return enma.createQuery(cQuery).setMaxResults(4).getResultList();
		} finally {
			enma.close();
		}
	}
	
}
