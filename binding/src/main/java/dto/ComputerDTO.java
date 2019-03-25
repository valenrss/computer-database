package dto;

import java.util.Objects;

import model.Computer;

public class ComputerDTO {

	private String id;
	private String name;
	private String dateIntroduced;
	private String dateDiscontinued;
	private String companyId;
	private String companyName;

	public ComputerDTO(String id, String name, String dateIntroduced, String dateDiscontinued, String companyId,
			String companyName) {

		this.id = id;
		this.name = name;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
		this.companyId = companyId;
		this.companyName = companyName;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateIntroduced() {
		return dateIntroduced;
	}

	public void setDateIntroduced(String dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	public String getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(String dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nComputer Id : " + id + "\nName : " + name + "\nDate Introduced : " + dateIntroduced
				+ "\nDate Discontinued : " + dateDiscontinued + "\nCompany Id : " + companyId + "\nCompany Name : "+ companyName + "\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object o) {

		if (o == this || o == null)
			return true;
		if (!(o instanceof Computer)) {
			return false;
		}
		ComputerDTO cmpt = (ComputerDTO) o;
		return Objects.equals(name, cmpt.getName()) && Objects.equals(dateIntroduced, cmpt.getDateIntroduced())
				&& Objects.equals(dateDiscontinued, cmpt.getDateDiscontinued());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, dateIntroduced, dateDiscontinued, companyId);
	}


}
