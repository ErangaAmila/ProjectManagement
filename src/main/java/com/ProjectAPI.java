package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProjectAPI")
public class ProjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Project projectObj = new Project();
	
    public ProjectAPI() {
        super();
    }

 // Convert request parameters to a Map
 	private static Map getParasMap(HttpServletRequest request) {
 		Map<String, String> map = new HashMap<String, String>();
 		try {
 			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
 			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
 			scanner.close();
 			String[] params = queryString.split("&");
 			for (String param : params) {
 				String[] p = param.split("=");
 				map.put(p[0], p[1]);
 			}
 		} catch (Exception e) {
 		}
 		return map;
 	}
     
     
     
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		response.getWriter().append("Served at: ").append(request.getContextPath());
 	}

 	
 	
 	
 	
 	
 	
 	
 	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String output = projectObj.insertProject(
 				request.getParameter("projectName"),
 				request.getParameter("description"),
 				request.getParameter("startDate"),
 				request.getParameter("endDate"),
 				request.getParameter("budget"),
 				request.getParameter("price"),
 				request.getParameter("sponserId"));
 				response.getWriter().write(output);
 				
 				System.out.println(request.getParameter("projectName"));
 	}

 	
 	
 	
 	
 	
 	
 	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Map paras = getParasMap(request);
 		String output = projectObj.updateProject(
 		paras.get("hidProjectIDSave").toString(),
 		paras.get("projectName").toString(),
 		paras.get("projectDescription").toString(),
 		paras.get("projectStartdate").toString(),
 		paras.get("projectEnddate").toString(),
 		paras.get("projectBudget").toString(),
 		paras.get("projectPrice").toString(),
 		paras.get("projectSponserId").toString());
 		response.getWriter().write(output);
 		
 		
 	}

 	
 	
 	
 	
 	
 	
 	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Map paras = getParasMap(request);
 		String output = projectObj.deleteProject(paras.get("projectID").toString());
 		response.getWriter().write(output);
 	}

}
