package dao;

import model.Company;
import model.Computer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@Transactional
public class ComputerDAOImpl implements ComputerDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Gets the computer list.
	 *
	 * @return the computer list
	 */
	public List<Computer> getList() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> cQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);

		cQuery.select(criteriaBuilder.construct(Computer.class, computerRoot.get("id"), computerRoot.get("name"), computerRoot.get("dateIntroduced"),
				computerRoot.get("dateDiscontinued"), computerRoot.get("company"))).orderBy(criteriaBuilder.asc(computerRoot.get("id")));

		return entityManager.createQuery(cQuery).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#create(java.lang.Object)
	 */
	@Override
	public void create(Computer comp) {

EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		if (!ObjectUtils.isEmpty(comp) && !entityManager.contains(comp)) {
		   entityManager.persist(comp);
		   entityManager.flush();
		}
		entityManager.getTransaction().commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Computer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Computer.class);
        Root<Computer> computerRoot = criteriaDelete.from(Computer.class);

        criteriaDelete.where(criteriaBuilder.equal(computerRoot.get("id"), id));

        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#deleteByCompany(Company company)
	 */
	@Override
	public boolean deleteByCompany(Company company) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Computer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Computer.class);
        Root<Computer> computerRoot = criteriaDelete.from(Computer.class);

        criteriaDelete.where(criteriaBuilder.equal(computerRoot.get("company"), company));

        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Computer comp) {

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaUpdate<Computer> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Computer.class);
        Root<Computer> computerRoot = criteriaUpdate.from(Computer.class);

        criteriaUpdate.set("name", comp.getName());
        criteriaUpdate.set("dateIntroduced", comp.getDateIntroduced());
        criteriaUpdate.set("dateDiscontinued", comp.getDateDiscontinued());
        criteriaUpdate.set("company", comp.getCompany());
        criteriaUpdate.where(criteriaBuilder.equal(computerRoot.get("id"), comp.getId()));

        Query<?> query = getSession().createQuery(criteriaUpdate);
        query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#find(int)
	 */
	@Override
	public Computer find(int id) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> cQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);

		cQuery.select(criteriaBuilder.construct(Computer.class, computerRoot.get("id"), computerRoot.get("name"), computerRoot.get("dateIntroduced"),
				computerRoot.get("dateDiscontinued"), computerRoot.get("company"))).where(criteriaBuilder.equal(computerRoot.get("id"), id));
		
		return entityManager.createQuery(cQuery).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#getPageByName(int, int, String)
	 */
	@Override
	public List<Computer> getPageByName(int pageNo, int objCount, String name, String orderOption) {

		int minId = pageNo * objCount - objCount;

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> computerRoot = criteriaQuery.from(Computer.class);

		if (orderOption != null) {
			switch (orderOption) {
			case "name":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("name"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			case "nameDesc":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.desc(computerRoot.get("name"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			case "introdate":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("dateIntroduced"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			case "discondate":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("dateDiscontinued"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			case "company":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("company"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			default:
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("id"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
				break;
			}
		} else {
			criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("id"))).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
		}

		Query<Computer> query = session.createQuery(criteriaQuery);

		query.setFirstResult(minId).setMaxResults(minId + objCount);

		return query.getResultList();
	}

	@Override
	public Long getCount(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = criteriaBuilder.createQuery(Long.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);

		cQuery.select(criteriaBuilder.count(computerRoot)).where(criteriaBuilder.like(computerRoot.get("name"), "%"+name+"%"));
		
		Query<Long> query = session.createQuery(cQuery); //TODO .where(criteriaBuilder.like(computerRoot.get("name"), name)
		
		return query.getSingleResult();
	}

}
