package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Computer;

@Repository
public class ComputerRepository {

	public ComputerRepository() {

	}

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	public List<Computer> getList() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> cQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);

		cQuery.select(criteriaBuilder.construct(Computer.class, computerRoot.get("id"), computerRoot.get("name"), computerRoot.get("dateIntroduced"),
				computerRoot.get("dateDiscontinued"), computerRoot.get("company")));

		return entityManager.createQuery(cQuery).getResultList();
	}

	public List<Computer> getPage(int minId, int objCount, String name, String orderOption) {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> computerRoot = criteriaQuery.from(Computer.class);

		if (orderOption != null) {
			switch (orderOption) {
			case "name":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("name")));
				break;
			case "nameDesc":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.desc(computerRoot.get("name")));
				break;
			case "introdate":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("dateIntroduced")));
				break;
			case "discondate":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("dateDiscontinued")));
				break;
			case "company":
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("company")));
				break;
			default:
				criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("id")));
				break;
			}
		} else {
			criteriaQuery.select(computerRoot).orderBy(criteriaBuilder.asc(computerRoot.get("id")));
		}

		Query<Computer> query = session.createQuery(criteriaQuery);

		query.setFirstResult(minId).setMaxResults(minId + objCount);

		return query.getResultList();
	}

}
