package dto;

import java.util.Objects;

import model.Company;

public class CompanyDTO {

	private String id;
	private String name;

	public CompanyDTO(String id, String name) {

		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Company id : " + id + " name : " + name;
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
		CompanyDTO cpny = (CompanyDTO) o;
		return id == cpny.getId() && Objects.equals(getName(), cpny.getName());
	}

	public int compareTo(Company company) {

		return Integer.valueOf(id).compareTo(company.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}

}
