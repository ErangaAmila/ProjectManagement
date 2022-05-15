package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


	
	//*************************read project***********************************************

	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Project Name</th>"
					+ "<th>Project Description</th>"
					+ "<th>Project StartDate</th>"
					+ "<th>Project EndDate</th>"
					+ "<th>Project Budget</th>"
					+ "<th>Project Price</th>"
					+ "<th>Project SponserId</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";

			String query = "select * from project";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String projectID = Integer.toString(rs.getInt("projectId"));
				String projectName = rs.getString("projectName");
				String projectDescription = rs.getString("description");
				String projectStartdate = rs.getString("startDate");
				String projectEnddate = rs.getString("endDate");
				String projectBudget = Integer.toString(rs.getInt("budget"));
				String projectPrice = Integer.toString(rs.getInt("price"));
				String projectSponserId = rs.getString("sponserId");
				
				// Add into the html table
				output += "<tr>"
						+ "<td><input id='hidProjectIDUpdate' name='hidProjectIDUpdate' type='hidden' value='" +projectID+"'>" +projectName +"</td>";
				output += "<td>" + projectDescription + "</td>";
				output += "<td>" + projectStartdate + "</td>";
				output += "<td>" + projectEnddate + "</td>";
				output += "<td>" + projectBudget + "</td>";
				output += "<td>" + projectPrice + "</td>";
				output += "<td>" + projectSponserId + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-projectId='" + projectID + "'></td>"
						+ "</tr>";
			}
			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading Projects detailss.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	//---------------------------------------------insert projects------------------------------
	public String insertProject(String projectName,String description,String startDate,String endDate,String budget,String price,String sponserId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into project(`projectId`, `projectName`, `description`, `startDate`, `endDate`, `budget`,`price`,`sponserId`) values (?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, projectName);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, startDate);
			preparedStmt.setString(5, endDate);
			preparedStmt.setInt(6,Integer.parseInt(budget));
			preparedStmt.setInt(7,Integer.parseInt(price));
			preparedStmt.setString(8, sponserId);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "status:error ,data:Error while inserting the Project details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//------------------------------------update project--------------------------------------------
	public String updateProject(String projectID,String projectName,String description,String startDate,String endDate,String budget,String price,String sponserId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE project SET projectName=?,description=?,startDate=?,endDate=?,budget=?,price=?,sponserId=? WHERE projectId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, projectName);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, startDate);
			preparedStmt.setString(4, endDate);
			preparedStmt.setInt(5, Integer.parseInt(budget));
			preparedStmt.setInt(6, Integer.parseInt(price));
			preparedStmt.setString(7, sponserId);
			preparedStmt.setInt(8, Integer.parseInt(projectID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "status:error data Error while updating the Project details.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	//-----------------------------------delete project------------------------------------
	public String deleteProject(String projectID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from project where projectId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(projectID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "status:error data :Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
