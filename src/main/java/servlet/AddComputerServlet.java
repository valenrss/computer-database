package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet(name = "AddComputerServlet", urlPatterns = { "/addComputer" })
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_COMPUTER_ID = 0;

	@Autowired
	private ComputerServiceImpl cmptService;
	@Autowired
	private CompanyServiceImpl cpnyService;
	

	public AddComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Company> cpnyList = cpnyService.getAll();
		request.setAttribute("companies", cpnyList);

		this.getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerName = request.getParameter("computerName");
		String tss1 = request.getParameter("introduced");
		String tss2 = request.getParameter("discontinued");
		int cmpnyID = Integer.parseInt(request.getParameter("companyId"));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		Date d1, d2;

		try {
			d1 = dateFormat.parse(tss1);
		} catch (ParseException e1) {
			d1 = null;
		}
		try {
			d2 = dateFormat.parse(tss2);
		} catch (ParseException e1) {
			d2 = null;
		}

		try {
			cmptService.add(new Computer(DEFAULT_COMPUTER_ID, computerName, d1, d2, cpnyService.getById(cmpnyID)));
		} catch (DateOrderException e) {
			request.setAttribute("errorMessage", e);
			this.getServletContext().getRequestDispatcher("/views/500.jsp").forward(request, response);
		} catch (ComputerNameEmptyException e) {
			request.setAttribute("errorMessage", e);
			this.getServletContext().getRequestDispatcher("/views/500.jsp").forward(request, response);
		}

		this.getServletContext().getRequestDispatcher("/Dashboard").forward(request, response);

	}

}
