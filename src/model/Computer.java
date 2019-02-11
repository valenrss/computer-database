package Objects;

import java.time.LocalDate;

public class Computer {
	
	private int Id;
	private String Name;
	private LocalDate DateIntroduced;
	private LocalDate DateDiscontinued;
	private int CompanyId;

	public Computer() {
		
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

	public LocalDate getDateIntroduced() {
		return DateIntroduced;
	}

	public void setDateIntroduced(LocalDate dateIntroduced) {
		DateIntroduced = dateIntroduced;
	}

	public LocalDate getDateDiscontinued() {
		return DateDiscontinued;
	}

	public void setDateDiscontinued(LocalDate dateDiscontinued) {
		DateDiscontinued = dateDiscontinued;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}


}
