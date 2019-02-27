package dto;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Computer;

public class Mapper {

	private static Mapper mapperInstance;

	public ComputerDTO mapComputer(Computer comp) {
		ComputerDTO compDTO = new ComputerDTO(Integer.toString(comp.getId()), comp.getName(),
				comp.getDateIntroduced().toString(), comp.getDateDiscontinued().toString(),
				Integer.toString(comp.getCompany().getId()), comp.getCompany().getName());

		return compDTO;
	}

	public List<ComputerDTO> mapListComputer(List<Computer> compL) {

		List<ComputerDTO> compDTOList = new ArrayList<>();

		for (Computer comp : compL) {

			String id;
			try {
				id = Integer.toString(comp.getId());
			} catch (NullPointerException e) {
				id = "-";
			}
			String name;
			try {
				name = comp.getName();
			} catch (NullPointerException e) {
				name = "-";
			}
			String dateIntroduced;
			try {
				dateIntroduced = comp.getDateIntroduced().toString();
			} catch (NullPointerException e) {
				dateIntroduced = "-";
			}
			String dateDiscontinued;
			try {
				dateDiscontinued = comp.getDateDiscontinued().toString();
			} catch (NullPointerException e) {
				dateDiscontinued = "-";
			}
			String companyId;
			try {
				companyId = Integer.toString(comp.getCompany().getId());
			} catch (NullPointerException e) {
				companyId = "-";
			}
			String companyName;
			try {
				companyName = comp.getCompany().getName();
			} catch (NullPointerException e) {
				companyName = "-";
			}

			compDTOList.add(new ComputerDTO(id, name, dateIntroduced, dateDiscontinued, companyId, companyName));

		}

		return compDTOList;
	}
	/*
	 * public CompanyDTO mapCompany(Company cny) { return new
	 * CompanyDTO(Integer.toString(cny.getId()),cny.getName()); }
	 */

	public List<CompanyDTO> mapListCompany(List<Company> cnyList) {

		List<CompanyDTO> cnyDTOList = new ArrayList<>();

		for (Company cny : cnyList) {
			cnyDTOList.add(new CompanyDTO(Integer.toString(cny.getId()), cny.getName()));
		}

		return cnyDTOList;
	}

	public static Mapper getInstance() {
		if (mapperInstance == null) {
			mapperInstance = new Mapper();
		}
		return mapperInstance;
	}

}
