package soen.eighteen.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import soen.eighteen.Dao.AbstractDao;
import soen.eighteen.Entity.Book;
import soen.eighteen.Entity.CategoryDetail;
import soen.eighteen.JPAConfig.JPAConfig;

public class CategoryDetailAbstractImpl  extends AbstractDao<CategoryDetail>{
	public CategoryDetailAbstractImpl() {
		super(CategoryDetail.class);
	}
	public List<CategoryDetail> FindCategoryName( int id)
	{
		EntityManager enma = JPAConfig.getEntityManager();
		List<CategoryDetail> categoryDetails = null;
		Book book = enma.find(Book.class,id);
		System.out.print(book.getCategoryDetails());
		categoryDetails = book.getCategoryDetails();
		enma.close();
		return categoryDetails;
	}
}
