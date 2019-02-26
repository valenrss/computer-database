package model;

import java.util.Objects;

public class Company {

	private int Id;
	private String Name;

	/**
	 * Instantiates a new company.
	 *
	 * @param id   the id
	 * @param name the name
	 */
	public Company(int id, String name) {
		setId(id);
		setName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Company id : " + getId() + " name : " + getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Company)) {
			return false;
		}
		Company cpny = (Company) o;
		return getId() == cpny.getId() && Objects.equals(getName(), cpny.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
