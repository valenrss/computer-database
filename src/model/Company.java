package model;

public class Company {
	
	private int Id;
	private String Name;

	public Company(int id,String name) {
		Id = id;
		Name = name;
	}
	
	
	@Override
	public String toString() {
		
		return "Company id : "+Id+" name : "+Name;
	}

}
