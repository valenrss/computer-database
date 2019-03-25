package webcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

@Controller
public class ComputerController {

	private static final int DEFAULT_COMPUTER_ID = 0;
	private static final int FIRST_PAGE = 1;
	private static final int PAGE_OFFSET = 2;

	private ComputerServiceImpl computerService;
	private CompanyServiceImpl companyService;

	@Autowired
	public ComputerController(ComputerServiceImpl cmptService, CompanyServiceImpl cpnyService) {

		computerService = cmptService;
		companyService = cpnyService;

	}
	
	@GetMapping("/Dashboard")
	public ModelAndView getPage(@RequestParam(required = false, defaultValue = "1") Integer pageId, 
								@RequestParam(required = false, defaultValue = "10") Integer objPerPage,
								@RequestParam(required = false, defaultValue = "id") String sortOption, 
								@RequestParam(required = false, defaultValue = "") String search) {

		List<ComputerDTO> computersPage = computerService.getPageByName(pageId, objPerPage, search, sortOption);
		List<CompanyDTO> companiesList = companyService.getAll();
		int computerCount = computerService.getCount(search).intValue();
		
		Integer pagesCount = computerCount / objPerPage - 1;

		if (pageId > pagesCount + PAGE_OFFSET) {
			pageId = pagesCount + PAGE_OFFSET;
			computersPage = computerService.getPageByName(pageId, objPerPage, search, sortOption);
		}
		if (pageId < FIRST_PAGE) {
			pageId = FIRST_PAGE;
			computersPage = computerService.getPageByName(pageId, objPerPage, search, sortOption);
		}

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("dashboard");

		modelAndView.addObject("computers", computersPage);
		modelAndView.addObject("cpNumber", computerCount);
		modelAndView.addObject("pageId", pageId);
		modelAndView.addObject("pagesCount", pagesCount);
		modelAndView.addObject("companies", companiesList);
		modelAndView.addObject("sortOption", sortOption);
		modelAndView.addObject("objPerPage", objPerPage);
		modelAndView.addObject("search", search);

		return modelAndView;

	}

	@PostMapping("/AddComputer")
	public ModelAndView addComputer(@RequestParam String computerName, @RequestParam String introduced, @RequestParam String discontinued, @RequestParam Integer companyId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		Date dateInt, dateDis;

		try {
			dateInt = dateFormat.parse(introduced);
		} catch (ParseException e1) {
			dateInt = null;
		}
		try {
			dateDis = dateFormat.parse(discontinued);
		} catch (ParseException e1) {
			dateDis = null;
		}

		try {
			computerService.add(new Computer(DEFAULT_COMPUTER_ID, computerName, dateInt, dateDis, companyService.getById(companyId)));
		} catch (ComputerNameEmptyException | DateOrderException e) {
			modelAndView.addObject("errorMessage", e.getMessage());
			modelAndView.setViewName("500");
			return modelAndView;
		}

		modelAndView.addObject("pageId", 1);
		return modelAndView;

	}

	@GetMapping("/EditComputer")
	public ModelAndView getInfo(@RequestParam Integer cpEditId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editComputer");

		List<CompanyDTO> cpnyList = companyService.getAll();
		modelAndView.addObject("companies", cpnyList);
		modelAndView.addObject("cpEditId", cpEditId);
		modelAndView.addObject("cpEdit", computerService.detail(cpEditId));

		return modelAndView;

	}

	@PostMapping("/EditComputer")
	public ModelAndView doPost(@RequestParam String name, 
								@RequestParam String dateintroduced, 
								@RequestParam String datediscontinued, 
								@RequestParam Integer companyid,
								@RequestParam Integer cpEditId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		Date dateInt, dateDis;

		try {
			dateInt = dateFormat.parse(dateintroduced);
		} catch (ParseException e1) {
			dateInt = null;
		}
		try {
			dateDis = dateFormat.parse(datediscontinued);
		} catch (ParseException e1) {
			dateDis = null;
		}

		try {
			computerService.update(new Computer(cpEditId, name, dateInt, dateDis, companyService.getById(companyid)));
		} catch (DateOrderException | ComputerNameEmptyException formException) {
			modelAndView.addObject("errorMessage", formException.getMessage());
			modelAndView.setViewName("500");
			return modelAndView;
		}

		modelAndView.addObject("pageId", 1);
		return modelAndView;

	}

	@PostMapping("/DeleteComputer")
	public ModelAndView delete(@RequestParam String selection) {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("dashboard");

		String[] tableDelete = selection.split(",");

		for (String id : tableDelete) {
			try {
				computerService.delete(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				modelAndView.addObject("errorMessage", e.getMessage());
				modelAndView.setViewName("500");
				return modelAndView;
			}

		}

		modelAndView.addObject("pageId", 1);
		return modelAndView;
	}

}
