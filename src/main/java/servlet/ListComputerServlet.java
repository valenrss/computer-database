package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CompanyDTO;
import dto.ComputerDTO;
import dto.Mapper;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet(name = "ListComputerServlet", urlPatterns = { "/listComputerServlet" })
public class ListComputerServlet extends HttpServlet {

	private static final int FIRST_PAGE = 1;
	private static final int PAGE_OFFSET = 2;
	private static final long serialVersionUID = 1L;

	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;
	private int currentPage = 1;
	private int objPerPage = 10;
	private int pagesCount;
	private Mapper mapper;
	private String sortOption = "id";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListComputerServlet() {
		super();
		cmptService = ComputerServiceImpl.getInstance();
		cpnyService = CompanyServiceImpl.getInstance();
		mapper = Mapper.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 //HttpSession session = request.getSession();
		 
		try {
			currentPage = Integer.valueOf(request.getParameter("pageId")); // TODO Faire autrement
		} catch (NumberFormatException e) {
			// currentPage = 1;
		}
		try {
			objPerPage = Integer.valueOf(request.getParameter("objPerPage"));
		} catch (NumberFormatException e) {
			// objPerPage = 10;
		}
		try {
			sortOption = request.getParameter("sortOption");
		} catch (NullPointerException e) {

		}
		
		List<ComputerDTO> pageC = mapper.mapListComputer(cmptService.getPage(currentPage, objPerPage));
		List<ComputerDTO> allC = mapper.mapListComputer(cmptService.getAll());
		pagesCount = allC.size() / objPerPage;
		

		if (currentPage > pagesCount + PAGE_OFFSET) {
			currentPage = pagesCount + PAGE_OFFSET;
			pageC = mapper.mapListComputer(cmptService.getPage(currentPage, objPerPage));
		}
		if (currentPage < FIRST_PAGE) {
			currentPage = FIRST_PAGE;
			pageC = mapper.mapListComputer(cmptService.getPage(currentPage, objPerPage));
		}
		
		
		if (sortOption != null) {
			switch (sortOption) {
			case "name":
				pageC.sort((ComputerDTO p1, ComputerDTO p2) -> p1.getName().compareTo(p2.getName()));
				break;
			case "introdate":
				pageC.sort((ComputerDTO p1, ComputerDTO p2) ->  p1.getDateIntroduced().compareTo(p2.getDateIntroduced()));
				break;
			case "discondate":
				pageC.sort((ComputerDTO p1, ComputerDTO p2) ->  p1.getDateDiscontinued().compareTo(p2.getDateDiscontinued()));
				break;
			case "company":
				pageC.sort((ComputerDTO p1, ComputerDTO p2) -> p1.getCompanyName().compareTo(p2.getCompanyName()));
				break;
			default:
				pageC.sort((ComputerDTO p1, ComputerDTO p2) -> p1.getId().compareTo(p2.getId()));
				break;
			}
		}else {
			pageC.sort((ComputerDTO p1, ComputerDTO p2) -> p1.getId().compareTo(p2.getId()));
		}
		
		List<CompanyDTO> cpnyList = mapper.mapListCompany(cpnyService.getAll());

		request.setAttribute("computers", pageC);
		request.setAttribute("cpNumber", allC.size());
		request.setAttribute("pageId", currentPage);
		request.setAttribute("pagesCount", pagesCount);
		request.setAttribute("companies", cpnyList);
		request.setAttribute("sortOption", sortOption);

		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
