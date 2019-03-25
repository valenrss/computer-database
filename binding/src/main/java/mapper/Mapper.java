package mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import dto.ComputerDTO;
import model.Company;
import model.Computer;

@Component
public class Mapper {


	/**
	 * Map computer.
	 *
	 * @param comp the comp
	 * @return the computer DTO
	 */
	public ComputerDTO mapComputer(Computer comp) {
		
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
		
		return new ComputerDTO(Integer.toString(comp.getId()), comp.getName(),
				dateIntroduced, dateDiscontinued,
				Integer.toString(comp.getCompany().getId()), comp.getCompany().getName());

	}

	/**
	 * Map list computer.
	 *
	 * @param compL the comp L
	 * @return the list
	 */
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

	/**
	 * Map company.
	 *
	 * @param cny the cny
	 * @return the company DTO
	 */
	public CompanyDTO mapCompany(Company cny) {
		return new CompanyDTO(Integer.toString(cny.getId()), cny.getName());
	}

	/**
	 * Map list company.
	 *
	 * @param cnyList the cny list
	 * @return the list
	 */
	public List<CompanyDTO> mapListCompany(List<Company> cnyList) {

		List<CompanyDTO> cnyDTOList = new ArrayList<>();

		for (Company cny : cnyList) {
			cnyDTOList.add(new CompanyDTO(Integer.toString(cny.getId()), cny.getName()));
		}

		return cnyDTOList;
	}

}
