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
 * Servlet implementation class EditComputerServlet
 */
@WebServlet(name = "EditComputerServlet", urlPatterns = {"/editComputer"})
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        cmptService = ComputerServiceImpl.getInstance();
        cpnyService = CompanyServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cpEditId = Integer.parseInt(request.getQueryString());
		
		try {
			List<Company> cpnyList = cpnyService.getAll();
			request.setAttribute("companies", cpnyList);
			
			request.setAttribute("cpEditId", cpEditId);
			request.setAttribute("cpEdit", cmptService.detail(cpEditId));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);

		//System.out.println("editing pc : "+cpEditId);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String computerName = request.getParameter("name");
		 String tss1 = request.getParameter("dateintroduced");
		 String tss2 = request.getParameter("datediscontinued");
		 int cmpnyID = Integer.parseInt(request.getParameter("companyid"));
		 int computerId = Integer.parseInt(request.getParameter("cpEditId"));
		 
		 
		 
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
			cmptService.update(new Computer(computerId,computerName,d1,d2,cpnyService.getById(cmpnyID)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		this.getServletContext().getRequestDispatcher("/Dashboard").forward(request, response);

	}

}
