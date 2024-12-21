package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.SqlConnection;
import Model.User;

public class UserDAO { // DAO Full Form:- Data Access Object 
/*	isExists(String email):

		Purpose: Checks if a user with the specified email exists in the database.
		Parameters: String email - the email to be checked.
		Returns: boolean - true if the email exists, otherwise false.
 */

	public static boolean isExists(String email) throws SQLException {
		Connection connection = SqlConnection.getConnection();	//Establishes a connection to the database.
		PreparedStatement ps = connection.prepareStatement("select email from users"); //Prepares and executes a SQL query to select all email addresses from the users table.
		ResultSet rs = ps.executeQuery(); // query execute ker ke jo out puy aa ae ga vo rs me store ho ga.
		
		while (rs.next()) { 
			String e = rs.getString(1); //Iterates through the result set to check if any email matches the provided email.
			if (e.equals(email)) { 
				return true; //Returns true if a match is found, otherwise false.
			}
		}
		return false;
	}
/*
	saveUser(User user):

		Purpose: Saves a new user to the database.
		Parameters: User user - the user object to be saved, containing the user's name and email.
		Returns: int - the number of rows affected by the SQL INSERT operation.		
*/
	public static int saveUser(User user) throws SQLException {
		Connection connection = SqlConnection.getConnection(); //Establishes a connection to the database.
		PreparedStatement ps = connection.prepareStatement("Insert into users values(default, ?, ?)"); //Prepares a SQL INSERT statement to insert a new user into the users table.
		ps.setString(1, user.getName()); //Sets the user's name and email in the prepared statement.
		ps.setString(2, user.getEmail());
		return ps.executeUpdate(); //Executes the update and returns the result.
	}
}


	