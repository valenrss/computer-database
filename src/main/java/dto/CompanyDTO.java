package dto;

public class CompanyDTO {

	private String ID;
	private String Name;

	public CompanyDTO(String id, String name) {

		ID = id;
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
