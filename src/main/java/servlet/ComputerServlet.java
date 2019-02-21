package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;
import service.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet(name = "ComputerServlet", urlPatterns = {"/ComputerServlet"})
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComputerServiceImpl cmptService;
	//private CompanyServiceImpl cpnyService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
        super();
        cmptService = ComputerServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//int pagesCount = (int)request.getAttribute("page");
			cmptService = ComputerServiceImpl.getInstance();
			//List<Computer> pageC = cmptService.getPage(1, 20);
			List<Computer> allC = cmptService.getAll();
			//pagesCount = allC.size()/20;
			request.setAttribute("computers", allC);
			request.setAttribute("cpNumber", allC.size());
			//request.setAttribute("pgCount", pagesCount);
			
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
