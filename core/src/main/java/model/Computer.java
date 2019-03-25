package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "computer")
public class Computer  {

	
	@Id 
	private int id;
	@Column
	private String name;
	@Column(name="introduced")
	private Date dateIntroduced;
	@Column(name="discontinued")
	private Date dateDiscontinued;
	@OneToOne
	@JoinColumn(name = "company_id") 
	private Company company;
	
	public Computer() {
		
	}


	/**
	 * Instantiates a new computer.
	 *
	 * @param id               the id
	 * @param name             the name
	 * @param dateIntroduced   the date introduced
	 * @param dateDiscontinued the date discontinued
	 * @param companyId        the company id
	 */
	public Computer(int id, String name, Date dateIntroduced, Date dateDiscontinued, Company company) {

		this.id = id;
		this.name = name;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
		this.company = company;

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the date introduced.
	 *
	 * @return the date introduced
	 */
	public Date getDateIntroduced() {
		return dateIntroduced;
	}

	/**
	 * Sets the date introduced.
	 *
	 * @param dateIntroduced the new date introduced
	 */
	public void setDateIntroduced(Timestamp dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	/**
	 * Gets the date discontinued.
	 *
	 * @return the date discontinued
	 */
	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}

	/**
	 * Sets the date discontinued.
	 *
	 * @param dateDiscontinued the new date discontinued
	 */
	public void setDateDiscontinued(Timestamp dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId the new company id
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nComputer Id : " + id + "\nName : " + name + "\nDate Introduced : " + dateIntroduced
				+ "\nDate Discontinued : " + dateDiscontinued + "\nCompany Id : " + company.getId() + "\nCompany Name : "+ company.getName() + "\n";
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
		Computer cmpt = (Computer) o;
		return Objects.equals(name, cmpt.getName()) && Objects.equals(dateIntroduced, cmpt.getDateIntroduced())
				&& Objects.equals(dateDiscontinued, cmpt.getDateDiscontinued())
				&& Objects.equals(company, cmpt.getCompany());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, dateIntroduced, dateDiscontinued, company.getId());
	}

	
	

}
