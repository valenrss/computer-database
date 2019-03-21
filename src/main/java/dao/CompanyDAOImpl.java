package dao;

import model.Company;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class CompanyDAOImpl implements CompanyDAO {


	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;


	/**
	 * Gets the company list.
	 *
	 * @return the company list
	 */
	@Override
	public List<Company> getList() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> cQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> companyRoot = cQuery.from(Company.class);

		cQuery.select(criteriaBuilder.construct(Company.class, companyRoot.get("id"), companyRoot.get("name")));

		return entityManager.createQuery(cQuery).getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.CompanyDAO#getPage(int, int)
	 */
	@Override
	public List<Company> getPage(int pageNo, int objCount) {
		
		int minId = pageNo * objCount - objCount;

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> companyRoot = criteriaQuery.from(Company.class);
		
		criteriaQuery.select(companyRoot).orderBy(criteriaBuilder.asc(companyRoot.get("id")));
		
		Query<Company> query = session.createQuery(criteriaQuery);

		query.setFirstResult(minId).setMaxResults(minId + objCount);

		return query.getResultList();
	}

	@Override
	public Company getById(int id) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> cQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> companyRoot = cQuery.from(Company.class);

		cQuery.select(criteriaBuilder.construct(Company.class, companyRoot.get("id"), companyRoot.get("name"))).where(criteriaBuilder.equal(companyRoot.get("id"), id));
		
		return entityManager.createQuery(cQuery).getSingleResult();

	}
	
	@Override
	public boolean deleteById(Company company) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Company> criteriaDelete = criteriaBuilder.createCriteriaDelete(Company.class);
        Root<Company> companyRoot = criteriaDelete.from(Company.class);

        criteriaDelete.where(criteriaBuilder.equal(companyRoot.get("id"), company.getId()));

        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
		
	}

}
