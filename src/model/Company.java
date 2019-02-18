package model;

public class Company {

	private int Id;
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
