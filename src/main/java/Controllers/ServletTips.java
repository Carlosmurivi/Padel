package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Config.HibernateDBTips;
import Model.Tip;


/**
 * Servlet implementation class ServletTips
 */
@WebServlet("/ServletTips")
public class ServletTips extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HibernateDBTips hibernateDBTips = new HibernateDBTips();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTips() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "listTips":
			int categoria_id = Integer.parseInt(request.getParameter("categoria_id"));
			request.setAttribute("categoria_id", categoria_id);
			
			List<Tip> tips = hibernateDBTips.leerTips();
			request.setAttribute("lista", tips);
			
			request.getRequestDispatcher("jsp/tips/tipsListByCategory.jsp").forward(request, response);
			break;
		case "add":
			
			break;
		case "delete":
			
			break;
		case "edit":
			
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
