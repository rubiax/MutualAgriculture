package com.geowind.entities;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Article entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.geowind.entities.Article
 * @author MyEclipse Persistence Tools
 */
public class ArticleDAO implements IArticleDAO {
	// property constants
	public static final String CATEGORY = "category";
	public static final String TITLE = "title";
	public static final String URL = "url";
	public static final String HEAD_CONTENT = "headContent";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Article entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ArticleDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Article entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Article entity) {
		EntityManagerHelper.log("saving Article instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Article entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ArticleDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Article entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Article entity) {
		EntityManagerHelper.log("deleting Article instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Article.class,
					entity.getArticleId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Article entity and return it or a copy of it
	 * to the sender. A copy of the Article entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ArticleDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Article entity to update
	 * @return Article the persisted Article entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Article update(Article entity) {
		EntityManagerHelper.log("updating Article instance", Level.INFO, null);
		try {
			Article result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Article findById(Integer id) {
		EntityManagerHelper.log("finding Article instance with id: " + id,
				Level.INFO, null);
		try {
			Article instance = getEntityManager().find(Article.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Article entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Article property to query
	 * @param value
	 *            the property value to match
	 * @return List<Article> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Article> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Article instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Article model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Article> findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List<Article> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Article> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Article> findByHeadContent(Object headContent) {
		return findByProperty(HEAD_CONTENT, headContent);
	}

	/**
	 * Find all Article entities.
	 * 
	 * @return List<Article> all Article entities
	 */
	@SuppressWarnings("unchecked")
	public List<Article> findAll() {
		EntityManagerHelper.log("finding all Article instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Article model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}