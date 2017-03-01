package com.geowind.hunong.jpa;

import java.util.Date;
import java.util.List;

/**
 * Interface for AiplanningDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAiplanningDAO {
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
	 * IAiplanningDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Aiplanning entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Aiplanning entity);

	/**
	 * Delete a persistent Aiplanning entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAiplanningDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Aiplanning entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Aiplanning entity);

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
	 * entity = IAiplanningDAO.update(entity);
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
	public Aiplanning update(Aiplanning entity);

	public Aiplanning findById(Integer id);

	/**
	 * Find all Aiplanning entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Aiplanning property to query
	 * @param value
	 *            the property value to match
	 * @return List<Aiplanning> found by query
	 */
	public List<Aiplanning> findByProperty(String propertyName, Object value);

	public List<Aiplanning> findByEvent(Object event);

	public List<Aiplanning> findByBreakpoint(Object breakpoint);

	public List<Aiplanning> findByDays(Object days);

	/**
	 * Find all Aiplanning entities.
	 * 
	 * @return List<Aiplanning> all Aiplanning entities
	 */
	public List<Aiplanning> findAll();
}