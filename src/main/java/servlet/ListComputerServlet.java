package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet(name = "ListComputerServlet", urlPatterns = {"/listComputerServlet"})
public class ListComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;
	private int currentPage = 1;
	private int objPerPage = 10;
	private int pagesCount;
	//private CompanyServiceImpl cpnyService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListComputerServlet() {
        super();
        cmptService = ComputerServiceImpl.getInstance();
        cpnyService = CompanyServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			try {
				currentPage = Integer.valueOf(request.getParameter("pageId"));
			}catch (NumberFormatException e) {
				//currentPage = 1;
			}
			try {
				objPerPage = Integer.valueOf(request.getParameter("objPerPage"));
			}catch (NumberFormatException e) {
				//objPerPage = 10;
			}
			
			
			cmptService = ComputerServiceImpl.getInstance();
			List<Computer> pageC = cmptService.getPage(currentPage,objPerPage );
			List<Computer> allC = cmptService.getAll();
			pagesCount = allC.size()/objPerPage;		
			List<Company> cpnyList = cpnyService.getAll();
			
			
			if (currentPage  > pagesCount+ 2) {
				currentPage = pagesCount + 2;
				pageC = cmptService.getPage(currentPage,objPerPage );
			}
			if (currentPage < 1) {
				currentPage = 1;
				pageC = cmptService.getPage(currentPage,objPerPage );
			}
			
			request.setAttribute("computers", pageC);
			request.setAttribute("cpNumber", allC.size());
			request.setAttribute("pageId", currentPage);
			request.setAttribute("pagesCount",pagesCount);
			request.setAttribute("companies", cpnyList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Add a computer

		doGet(request, response);
	}

}
