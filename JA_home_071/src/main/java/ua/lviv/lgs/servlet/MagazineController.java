package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.service.MagazineService;
import ua.lviv.lgs.service.impl.MagazineServiceImpl;

@WebServlet(value = "/magazineServ")
public class MagazineController extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private static Logger LOGGER = Logger.getLogger(UserRegistrationServlet.class);
	private MagazineService magazineService = MagazineServiceImpl.getMagazineService();

	public void init() {
		System.out.println("init servlet magazine");
	}

	// to create resources

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("dopost magazine");

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String isbn = request.getParameter("isbn");

		System.out.println(name);
		System.out.println(isbn);

		if ((name != null)  && (isbn != null)) {
			if (magazineService.readByIsbn(isbn) == null) {
				System.out.println("isbn unique");
				
				Magazine magazine = new Magazine(name, description, priceValidate(price), isbn);
				
				System.out.println(magazine);

				 magazineService.create(magazine);
				 
				 
				 
				 
				  response.setContentType("text");
				  response.setCharacterEncoding("UTF-8");
				  response.getWriter().write("Success");
				 
				 
				/*request.setAttribute("isbn", isbn);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write("Success");
				} catch (IOException e) {
					 LOGGER.error(e);
				}
*/
			}
		}

	}
	
	
	private double priceValidate(String price) {
		if(price == null || price.isEmpty() ) {
			return 0;
		}
		try {
			return Double.parseDouble(price);
		} catch(java.lang.NumberFormatException e) {
			 LOGGER.error(e);
		}
		return 0;
	}
	
	  // to read resources
	  
	  protected void doGet(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException {
	 
	  response.getWriter().append("Served at: ").append(request.getContextPath());
	  }
	  
	  // to update resources
	  
	  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws
	  ServletException, IOException {
	  
	  super.doPut(req, resp); }
	  
	  // to delete resources
	  
	  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	  throws ServletException, IOException {
	  
	  super.doDelete(req, resp); }
	 

}
