package model;

import java.time.LocalDate;

public class Computer {
	
	private int Id;
	private String Name;
	private String DateIntroduced;
	private String DateDiscontinued;
	private int CompanyId;

	public Computer(int id, String name, String dateIntroduced, String dateDiscontinued, int companyId) {
		
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

	public String getDateIntroduced() {
		return DateIntroduced;
	}

	public void setDateIntroduced(String dateIntroduced) {
		DateIntroduced = dateIntroduced;
	}

	public String getDateDiscontinued() {
		return DateDiscontinued;
	}

	public void setDateDiscontinued(String dateDiscontinued) {
		DateDiscontinued = dateDiscontinued;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}


}
