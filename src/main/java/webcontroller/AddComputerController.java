package webcontroller;

import java.io.IOException;
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
public class AddComputerController {

	private static final int DEFAULT_COMPUTER_ID = 0;

	@Autowired
	private ComputerServiceImpl cmptService;
	@Autowired
	private CompanyServiceImpl cpnyService;
	
	@GetMapping("/AddComputer")
	public ModelAndView doGet()
			throws IOException {
		
		ModelAndView modelAndView = new ModelAndView();

		List<Company> cpnyList = cpnyService.getAll();

		modelAndView.setViewName("addComputer");
		modelAndView.addObject("companies", cpnyList);

		return modelAndView;

	}

	@PostMapping("/AddComputer")
	public ModelAndView doPost(@RequestParam String computerName,
			@RequestParam String introduced,
			@RequestParam String discontinued,
			@RequestParam Integer companyId){
		
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
			cmptService.add(new Computer(DEFAULT_COMPUTER_ID, computerName, dateInt, dateDis, cpnyService.getById(companyId)));
		} catch (ComputerNameEmptyException | DateOrderException e) {
			modelAndView.addObject("errorMessage", e.getMessage());
			modelAndView.setViewName("500");
			return modelAndView;
		}

		modelAndView.addObject("pageId", 1);
		return modelAndView;

	}

}
