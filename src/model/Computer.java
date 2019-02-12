package model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Computer {
	
	private int Id;
	private String Name;
	private Timestamp DateIntroduced;
	private Timestamp DateDiscontinued;
	private int CompanyId;

	public Computer(int id, String name, Timestamp dateIntroduced, Timestamp dateDiscontinued, int companyId) {
		
		Id = id;
		Name = name;
		DateIntroduced = dateIntroduced;
		DateDiscontinued = dateDiscontinued;
		CompanyId = companyId;
		
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

	public Timestamp getDateIntroduced() {
		return DateIntroduced;
	}

	public void setDateIntroduced(Timestamp dateIntroduced) {
		DateIntroduced = dateIntroduced;
	}

	public Timestamp getDateDiscontinued() {
		return DateDiscontinued;
	}

	public void setDateDiscontinued(Timestamp dateDiscontinued) {
		DateDiscontinued = dateDiscontinued;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}
	
	@Override
	public String toString() {
		return  "\nComputer Id : "+Id+ 
				"\nName : "+Name+ 
				"\nDate Introduced : "+DateIntroduced+
				"\nDate Discontinued : "+DateDiscontinued+ 
				"\nCompany Id : "+CompanyId+"\n";
	}


}
