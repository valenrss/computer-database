package webcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Company;
import service.CompanyServiceImpl;

@Controller
public class CompanyController {

	private CompanyServiceImpl companyService;

	@Autowired
	public CompanyController(CompanyServiceImpl cpnyService) {

		companyService = cpnyService;

	}

	@GetMapping("/AddComputer")
	public ModelAndView getCompanies() throws IOException {

		ModelAndView modelAndView = new ModelAndView();

		List<Company> cpnyList = companyService.getAll();

		modelAndView.setViewName("addComputer");
		modelAndView.addObject("companies", cpnyList);

		return modelAndView;

	}

}
