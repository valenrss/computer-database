package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompanyDTO;
import dto.ComputerDTO;
import dto.Mapper;
import model.Computer;
import model.SortOptions;
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
	private String sortOption;

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

		try {
			currentPage = Integer.valueOf(request.getParameter("pageId"));
		} catch (NumberFormatException e) {
			// currentPage = 1;
		}
		try {
			objPerPage = Integer.valueOf(request.getParameter("objPerPage"));
		} catch (NumberFormatException e) {
			// objPerPage = 10;
		}try {
			
			sortOption = request.getParameter("sortOption");
			
		}catch (NullPointerException e) {
			
		}
		
		List<Computer> pageComputer = cmptService.getPage(currentPage, objPerPage);
		List<Computer> pageComputerSorted;
		
		
		if (sortOption != null) {
			switch (sortOption) {
			case "name":
				pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.NAME);
				break;
			case "introdate":
				pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.DATEINTRODUCED);
				break;
			case "discondate":
				pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.DATEDISCONTINUED);
				break;
			case "company":
				pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.COMPANY);
				break;
			default:
				pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.ID);
				break;
			}
		}else {
			pageComputerSorted = cmptService.compareBy(pageComputer, SortOptions.ID);
		}
		
		
		List<ComputerDTO> pageC = mapper.mapListComputer(pageComputerSorted);
		List<ComputerDTO> allC = mapper.mapListComputer(cmptService.getAll());
		pagesCount = allC.size() / objPerPage;
		List<CompanyDTO> cpnyList = mapper.mapListCompany(cpnyService.getAll());

		if (currentPage > pagesCount + PAGE_OFFSET) {
			currentPage = pagesCount + PAGE_OFFSET;
			pageC = mapper.mapListComputer(cmptService.getPage(currentPage, objPerPage));
		}
		if (currentPage < FIRST_PAGE) {
			currentPage = FIRST_PAGE;
			pageC = mapper.mapListComputer(cmptService.getPage(currentPage, objPerPage));
		}

		request.setAttribute("computers", pageC);
		request.setAttribute("cpNumber", allC.size());
		request.setAttribute("pageId", currentPage);
		request.setAttribute("pagesCount", pagesCount);
		request.setAttribute("companies", cpnyList);

		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Add a computer

		doGet(request, response);
	}

}
