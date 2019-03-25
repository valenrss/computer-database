package webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.ComputerServiceImpl;

@Controller
public class DeleteComputerController {

	@Autowired
	private ComputerServiceImpl cmptService;

	@PostMapping("/DeleteComputer")
	public ModelAndView doPost(@RequestParam String selection){
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("dashboard");

		String[] tableDelete = selection.split(",");

		for (String id : tableDelete) {
			try {
				cmptService.delete(Integer.parseInt(id));
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
