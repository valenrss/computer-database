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

import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

@Controller
public class EditComputerController {

	@Autowired
	private ComputerServiceImpl cmptService;
	@Autowired
	private CompanyServiceImpl cpnyService;
	
	@GetMapping("/EditComputer")
	public ModelAndView doGet(@RequestParam Integer cpEditId){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editComputer");

		List<Company> cpnyList = cpnyService.getAll();
		modelAndView.addObject("companies",cpnyList);
		modelAndView.addObject("cpEditId",cpEditId);
		modelAndView.addObject("cpEdit",cmptService.detail(cpEditId));

		return modelAndView;

	}

	@PostMapping("/EditComputer")
	public ModelAndView doPost(@RequestParam String name, 
						@RequestParam String dateintroduced, 
						@RequestParam String datediscontinued, 
						@RequestParam Integer companyid, 
						@RequestParam Integer cpEditId){
		
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
			cmptService.update(new Computer(cpEditId, name, dateInt, dateDis, cpnyService.getById(companyid)));
		} catch (DateOrderException  | ComputerNameEmptyException formException) {
			modelAndView.addObject("errorMessage", formException.getMessage());
			modelAndView.setViewName("500");
			return modelAndView;
		} 
			

		modelAndView.addObject("pageId", 1);
		return modelAndView;

	}

}
