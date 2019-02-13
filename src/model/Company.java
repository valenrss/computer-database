package model;

/**
 * The Class Company.
 */
public class Company {

	/** The Id. */
	private int Id;
	
	/** The Name. */
	private String Name;

	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Company(int id, String name) {
		Id = id;
		Name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Company id : " + Id + " name : " + Name;
	}

}
