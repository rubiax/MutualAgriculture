package com.geowind.hunong.jpa;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Aiplanning entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.geowind.hunong.jpa.Aiplanning
 * @author MyEclipse Persistence Tools
 */
public class AiplanningDAO implements IAiplanningDAO {
	// property constants
	public static final String EVENT = "event";
	public static final String BREAKPOINT = "breakpoint";
	public static final String DAYS = "days";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Aiplanning entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AiplanningDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Aiplanning entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Aiplanning entity) {
		EntityManagerHelper.log("saving Aiplanning instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Aiplanning entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AiplanningDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Aiplanning entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Aiplanning entity) {
		EntityManagerHelper.log("deleting Aiplanning instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Aiplanning.class,
					entity.getAid());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Aiplanning entity and return it or a copy of
	 * it to the sender. A copy of the Aiplanning entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AiplanningDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Aiplanning entity to update
	 * @return Aiplanning the persisted Aiplanning entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Aiplanning update(Aiplanning entity) {
		EntityManagerHelper.log("updating Aiplanning instance", Level.INFO,
				null);
		try {
			Aiplanning result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Aiplanning findById(Integer id) {
		EntityManagerHelper.log("finding Aiplanning instance with id: " + id,
				Level.INFO, null);
		try {
			Aiplanning instance = getEntityManager().find(Aiplanning.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Aiplanning entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Aiplanning property to query
	 * @param value
	 *            the property value to match
	 * @return List<Aiplanning> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Aiplanning> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Aiplanning instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Aiplanning model where model."
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

	public List<Aiplanning> findByEvent(Object event) {
		return findByProperty(EVENT, event);
	}

	public List<Aiplanning> findByBreakpoint(Object breakpoint) {
		return findByProperty(BREAKPOINT, breakpoint);
	}

	public List<Aiplanning> findByDays(Object days) {
		return findByProperty(DAYS, days);
	}

	/**
	 * Find all Aiplanning entities.
	 * 
	 * @return List<Aiplanning> all Aiplanning entities
	 */
	@SuppressWarnings("unchecked")
	public List<Aiplanning> findAll() {
		EntityManagerHelper.log("finding all Aiplanning instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Aiplanning model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}