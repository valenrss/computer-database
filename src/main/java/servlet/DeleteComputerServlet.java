package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ComputerServiceImpl;

/**
 * Servlet implementation class DeleteComputerServlet
 */
@WebServlet(name = "DeleteComputerServlet", urlPatterns = { "/deleteComputer" })
public class DeleteComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerServiceImpl cmptService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerServlet() {
		super();
		cmptService = ComputerServiceImpl.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String deleteSelection = request.getParameter("selection");
		String[] tableDelete = deleteSelection.split(",");

		for (String id : tableDelete) {
			try {
				cmptService.delete(Integer.parseInt(id));
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.getServletContext().getRequestDispatcher("/Dashboard").forward(request, response);

	}

}
