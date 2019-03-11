package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	private ComputerServiceImpl cmptService;
	@Autowired
	private CompanyServiceImpl cpnyService;
	private int currentPage = 1;
	private int objPerPage = 10;
	private int pagesCount;
	@Autowired
	private Mapper mapper;
	private String sortOption = "id";
	private String nameSearch = "";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSession session = request.getSession();

		try {
			currentPage = Integer.valueOf(request.getParameter("pageId"));
		} catch (NumberFormatException e) {
			logger.info("kept same page " + currentPage);
		}
		try {
			objPerPage = Integer.valueOf(request.getParameter("objPerPage"));
		} catch (NumberFormatException e) {
			logger.info("kept same computer display count " + objPerPage);
		}
		try {
			sortOption = request.getParameter("sortOption");
		} catch (NullPointerException e) {
			logger.info("kept same sorting option " + sortOption);
		}
		try {
			nameSearch = request.getParameter("search");
		} catch (NullPointerException e) {
			logger.info("kept same search option " + nameSearch);
		}
		logger.info("namesearch = " + nameSearch);
		if (nameSearch == null) {
			nameSearch = "";
		}

		List<ComputerDTO> pageC = mapper
				.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch, sortOption));
		List<ComputerDTO> allC = mapper.mapListComputer(
				cmptService.getPageByName(FIRST_PAGE, cmptService.getAll().size(), nameSearch, sortOption));
		pagesCount = allC.size() / objPerPage - 1;

		if (currentPage > pagesCount + PAGE_OFFSET) {
			currentPage = pagesCount + PAGE_OFFSET;
			pageC = mapper.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch, sortOption));
		}
		if (currentPage < FIRST_PAGE) {
			currentPage = FIRST_PAGE;
			pageC = mapper.mapListComputer(cmptService.getPageByName(currentPage, objPerPage, nameSearch, sortOption));
		}

		List<CompanyDTO> cpnyList = mapper.mapListCompany(cpnyService.getAll());

		HttpSession session = request.getSession();

		session.setAttribute("computers", pageC);
		session.setAttribute("cpNumber", allC.size());
		session.setAttribute("pageId", currentPage);
		session.setAttribute("pagesCount", pagesCount);
		session.setAttribute("companies", cpnyList);
		session.setAttribute("sortOption", sortOption);
		session.setAttribute("search", nameSearch);

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
