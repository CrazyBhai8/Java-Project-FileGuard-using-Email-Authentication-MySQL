package dao;

/* Work of this file
 	getAllFiles(String email):
This method retrieves all the files from the database based on the provided email. It executes a SQL query and stores the result in a list of Data objects.

hideFile(Data file):
This method hides a file by inserting it into the database. It stores the file’s binary data (bin_data) in the database and then deletes the file from the file system to "hide" it.

unhide(int id):
This method retrieves the file (based on its ID) from the database, writes its binary data back to the file system (restores the file), and then deletes the record from the database, effectively "unhiding" the file.

 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.SqlConnection;
import Model.Data;


	// getAllFiles function me sari files ko sql me se show kere ge. by email id.
public class DataDAO {
	public static List<Data> getAllFiles(String email) throws SQLException {
		Connection connection = SqlConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("Select * from data where email = ?"); //insert the sql query
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		List<Data> files = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String path = rs.getString(3);
			files.add(new Data(id, name, path));
		}
		return files;
	}
	
	// we are using this function for hide the files. 
	//hum pahele file ko bin_data formate me sql me save kere ge and after vo file system me se delet kere ge.
	public static int hideFile(Data file)throws SQLException, IOException {
        Connection connection = SqlConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into data(name, path, email, bin_data) values(?,?,?,?)");
        ps.setString(1, file.getFileName());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());
        File f = new File(file.getPath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr, f.length());
        int ans = ps.executeUpdate();
        fr.close();
        if (f.exists()) {
            System.out.println("File exists. Attempting to delete...");
            if (f.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File not found.");
        }
		return ans;
    }


	// unhide function me hume jo file unhide kerni hee uska path SQL me se milega and after we use FileWriter function and write that file in that location. after that we delet that file data from sql.
	public static void unhide(int id) throws SQLException, IOException {
	    Connection connection = SqlConnection.getConnection();
	    PreparedStatement ps = connection.prepareStatement("select path, bin_data from data where id = ?");
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();

	    // Check if the result exists
	    if (rs.next()) {
	        String path = rs.getString("path");
	        Clob c = rs.getClob("bin_data");

	        Reader r = c.getCharacterStream();
	        try (FileWriter fw = new FileWriter(path)) {
	            int i;
	            while ((i = r.read()) != -1) {
	                fw.write((char) i);
	            }
	        }

	        // Delete the record from the database after successfully un-hiding the file
	        ps = connection.prepareStatement("delete from data where id = ?");
	        ps.setInt(1, id);
	        ps.executeUpdate();

	        System.out.println("Successfully Unhidden");
	    } else {
	        System.out.println("No file found with the provided ID.");
	    }
	}
}