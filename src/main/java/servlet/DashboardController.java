package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import dto.CompanyDTO;
import dto.ComputerDTO;
import dto.Mapper;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

@Controller
public class DashboardController {

	private static final int FIRST_PAGE = 1;
	private static final int PAGE_OFFSET = 2;

	@Autowired
	private ComputerServiceImpl cmptService;
	@Autowired
	private CompanyServiceImpl cpnyService;
	@Autowired
	private Mapper mapper;

	@GetMapping("/Dashboard")
	protected ModelAndView doGet(@RequestParam(required=false , defaultValue = "1") Integer pageId, 
								 @RequestParam(required=false , defaultValue = "10") Integer objPerPage,
								 @RequestParam(required=false , defaultValue = "id") String sortOption, 
								 @RequestParam(required=false , defaultValue = "") String search)
			throws ServletException, IOException {

		Integer pagesCount;

		List<ComputerDTO> pageC = mapper
				.mapListComputer(cmptService.getPageByName(pageId, objPerPage, search, sortOption));
		List<ComputerDTO> allC = mapper.mapListComputer(
				cmptService.getPageByName(FIRST_PAGE, cmptService.getAll().size(), search, sortOption));
		pagesCount = allC.size() / objPerPage - 1;

		if (pageId > pagesCount + PAGE_OFFSET) {
			pageId = pagesCount + PAGE_OFFSET;
			pageC = mapper.mapListComputer(cmptService.getPageByName(pageId, objPerPage, search, sortOption));
		}
		if (pageId < FIRST_PAGE) {
			pageId = FIRST_PAGE;
			pageC = mapper.mapListComputer(cmptService.getPageByName(pageId, objPerPage, search, sortOption));
		}

		List<CompanyDTO> cpnyList = mapper.mapListCompany(cpnyService.getAll());

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("dashboard");

		modelAndView.addObject("computers", pageC);
		modelAndView.addObject("cpNumber", allC.size());
		modelAndView.addObject("pageId", pageId);
		modelAndView.addObject("pagesCount", pagesCount);
		modelAndView.addObject("companies", cpnyList);
		modelAndView.addObject("sortOption", sortOption);
		modelAndView.addObject("search", search);

		return modelAndView;

	}

}
