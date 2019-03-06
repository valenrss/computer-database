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
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

/**
 * Servlet implementation class SearchComputerServlet
 */
@WebServlet(name = "SearchComputerServlet", urlPatterns = { "/SearchComputerServlet" })
public class SearchComputerServlet extends HttpServlet {

	private static final int FIRST_PAGE = 1;
	private static final int PAGE_OFFSET = 2;
	private static final long serialVersionUID = 1L;

	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;
	private int currentPage = 1;
	private int objPerPage = 10;
	private int pagesCount;
	private String nameSearch = "*";
	private Mapper mapper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchComputerServlet() {
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
		}
		try {
			nameSearch = request.getParameter("search");
		} catch (NullPointerException e) {
			//nameSearch = "*";
		}

		List<ComputerDTO> pageS = mapper.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch));
		List<ComputerDTO> allC = mapper.mapListComputer(cmptService.getPageByName(FIRST_PAGE, pageS.size(), nameSearch));
		pagesCount = allC.size() / objPerPage;
		List<CompanyDTO> cpnyList = mapper.mapListCompany(cpnyService.getAll());

		if (currentPage > pagesCount + PAGE_OFFSET) {
			currentPage = pagesCount + PAGE_OFFSET;
			pageS = mapper.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch));
		}
		if (currentPage < FIRST_PAGE) {
			currentPage = FIRST_PAGE;
			pageS = mapper.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch));
		}

		request.setAttribute("computers", pageS);
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

	}

}
