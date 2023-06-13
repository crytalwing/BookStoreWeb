package soen.eighteen.Dao;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

import soen.eighteen.JPAConfig.JPAConfig;

public abstract class AbstractDao<T> {
	private Class<T> entityClass;

	public AbstractDao(Class<T> cls) {
		this.entityClass = cls;
	}
	
	public List<T> FindByObject(String object,String name) {
		EntityManager enma = JPAConfig.getEntityManager();
		List<T> entity = null;

		try {
			CriteriaBuilder cb = enma.getCriteriaBuilder();

			CriteriaQuery<T> cq = cb.createQuery(entityClass);
			Root<T> accRoot = cq.from(entityClass);
			ParameterExpression<String> pe = cb.parameter(String.class);
			cq.select(accRoot).where(cb.equal(accRoot.get(object), pe));

			TypedQuery<T> typedQuery = enma.createQuery(cq);

			typedQuery.setParameter(pe, name);
			entity = typedQuery.getResultList();
			return entity;

		} catch (Exception e) {

			e.printStackTrace();


			throw e;
			
		} finally {
			enma.close();

		}
	}

	public void insert(T entity) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(entity);
			

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}

	}
	public void delete(Object id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			T entity = enma.find(entityClass, id);

			if (entity != null) {
				enma.remove(entity);
			} else {

				throw new Exception("Not found");

			}

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	public T findById(Object id) {

		EntityManager enma = JPAConfig.getEntityManager();

		T entity = enma.find(entityClass, id);

		return entity;

	}

	public List<T> findAll() {

		EntityManager enma = JPAConfig.getEntityManager();
		try {
			CriteriaQuery cQuery = enma.getCriteriaBuilder().createQuery();
			cQuery.select(cQuery.from(entityClass));
			return enma.createQuery(cQuery).getResultList();
		} finally {
			enma.close();
		}
	}

	public int count() {

		EntityManager enma = JPAConfig.getEntityManager();
		try {
			CriteriaQuery cq = enma.getCriteriaBuilder().createQuery();
			Root<T> rt = cq.from(entityClass);
			cq.select(enma.getCriteriaBuilder().count(rt));
			Query q = enma.createQuery(cq);
			return (int) q.getSingleResult();
		} finally {
			enma.close();

		}

	}

	public List<T> findAll( int firstResult, int maxResult) {

		EntityManager enma = JPAConfig.getEntityManager();

		try {
			CriteriaQuery cq = enma.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = enma.createQuery(cq);
			
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);

			return q.getResultList();

		} finally {
			enma.close();

		}

	}
	public void update(T entity) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(entity);

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
