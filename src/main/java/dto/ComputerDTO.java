package dto;

public class ComputerDTO {
	
	private String Id;
	private String Name;
	private String DateIntroduced;
	private String DateDiscontinued;
	private String CompanyId;

	public ComputerDTO(String id, String name, String dateIntroduced,String dateDiscontinued, String companyId) {
		
		Id = id;
		Name = name;
		DateIntroduced = dateIntroduced;
		DateDiscontinued = dateDiscontinued;
		CompanyId = companyId;
		
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
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

	public String getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}

}
