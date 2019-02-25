package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class AddComputerServlet
 */
@WebServlet(name = "AddComputerServlet", urlPatterns = {"/addComputer"})
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_COMPUTER_ID = 0;
	
	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public AddComputerServlet() {
        super();
        cmptService = ComputerServiceImpl.getInstance();
        cpnyService = CompanyServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Company> cpnyList = cpnyService.getAll();
			request.setAttribute("companies", cpnyList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String computerName = request.getParameter("computerName");
		 String tss1 = request.getParameter("introduced");
		 String tss2 = request.getParameter("discontinued");
		 int cmpnyID = Integer.parseInt(request.getParameter("companyId"));
		 
		 
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		 
		 Date d1,d2;
		 
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
			cmptService.add(new Computer(DEFAULT_COMPUTER_ID,computerName,d1,d2,cmpnyID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		this.getServletContext().getRequestDispatcher("/Dashboard").forward(request, response);

	}

}
