package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Computer {

	private int Id;
	private String Name;
	private Timestamp DateIntroduced;
	private Timestamp DateDiscontinued;
	private int CompanyId;

	/**
	 * Instantiates a new computer.
	 *
	 * @param id the id
	 * @param name the name
	 * @param dateIntroduced the date introduced
	 * @param dateDiscontinued the date discontinued
	 * @param companyId the company id
	 */
	public Computer(int id, String name, Timestamp dateIntroduced, Timestamp dateDiscontinued, int companyId) {

		Id = id;
		Name = name;
		DateIntroduced = dateIntroduced;
		DateDiscontinued = dateDiscontinued;
		CompanyId = companyId;

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Gets the date introduced.
	 *
	 * @return the date introduced
	 */
	public Timestamp getDateIntroduced() {
		return DateIntroduced;
	}

	/**
	 * Sets the date introduced.
	 *
	 * @param dateIntroduced the new date introduced
	 */
	public void setDateIntroduced(Timestamp dateIntroduced) {
		DateIntroduced = dateIntroduced;
	}

	/**
	 * Gets the date discontinued.
	 *
	 * @return the date discontinued
	 */
	public Timestamp getDateDiscontinued() {
		return DateDiscontinued;
	}

	/**
	 * Sets the date discontinued.
	 *
	 * @param dateDiscontinued the new date discontinued
	 */
	public void setDateDiscontinued(Timestamp dateDiscontinued) {
		DateDiscontinued = dateDiscontinued;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public int getCompanyId() {
		return CompanyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId the new company id
	 */
	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nComputer Id : " + Id + "\nName : " + Name + "\nDate Introduced : " + DateIntroduced
				+ "\nDate Discontinued : " + DateDiscontinued + "\nCompany Id : " + CompanyId + "\n";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals()
	 */
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Computer)) {
            return false;
        }
        Computer cmpt = (Computer) o;
        return Id == cmpt.getId() &&
                Objects.equals(Name, cmpt.getName()) &&
                Objects.equals(DateIntroduced, cmpt.getDateIntroduced()) &&
                Objects.equals(DateDiscontinued, cmpt.getDateDiscontinued()) &&
                Objects.equals(CompanyId, cmpt.getCompanyId());
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
    @Override
    public int hashCode() {
        return Objects.hash(Id,Name, DateIntroduced, DateDiscontinued,CompanyId);
    }

}