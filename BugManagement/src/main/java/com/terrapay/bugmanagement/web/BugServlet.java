package com.terrapay.bugmanagement.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terrapay.bugmanagement.dao.BugDao;
import com.terrapay.bugmanagement.model.Bug;

/**
 * Servlet implementation class BugServlet
 */
@WebServlet("/")
public class BugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BugDao bugDao;
	
	public void init() {
		bugDao=new BugDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBug(request, response);
                    break;
                case "/delete":
                    deleteBug(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBug(request, response);
                    break;
                default:
                    listBug(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("bug-form.jsp");
			        dispatcher.forward(request, response);
			    }
	 
	 
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        Bug existingBug = bugDao.selectBug(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("bug-form.jsp");
			        request.setAttribute("bug", existingBug);
			        dispatcher.forward(request, response);

			    }
	 
	 
	 
	 private void insertBug(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
		    String email = request.getParameter("email");
	        String priority = request.getParameter("priority");
	        String status = request.getParameter("status");
	        String projectOwner = request.getParameter("projectOwner");
	        String creationDate = request.getParameter("creationDate");
	        String completionDate = request.getParameter("completionDate");
	        String description=request.getParameter("description");
			Bug newBug = new Bug(email, priority, status, projectOwner, creationDate, completionDate, description);
			bugDao.insertBug(newBug);
			response.sendRedirect("list");
		}
	 
	 private void listBug (HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Bug> listBug = bugDao.selectAllBugs();
			request.setAttribute("listBug", listBug);
			RequestDispatcher dispatcher = request.getRequestDispatcher("bug-list.jsp");
			dispatcher.forward(request, response);
		}

	 
	 
	 private void updateBug(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			 String email = request.getParameter("email");
		        String priority = request.getParameter("priority");
		        String status = request.getParameter("status");
		        String projectOwner = request.getParameter("projectOwner");
		        String creationDate = request.getParameter("creationDate");
		        String completionDate = request.getParameter("completionDate");
		        String description=request.getParameter("description");

			Bug bug = new Bug(id, email, priority, status, projectOwner, creationDate, completionDate, description);
			bugDao.updateBug(bug);
			response.sendRedirect("list");
		}
	 
	 
	 private void deleteBug(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			bugDao.deleteBug(id);
			response.sendRedirect("list");

		}

}
