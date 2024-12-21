package views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Model.Data;
import dao.DataDAO;

public class UserView {
	private String email;
	UserView(String email){
		this.email = email;
	}
	// The main method to display the user interface and handle user actions
	public void home() {
		do {
			System.out.println("Wlcome "+ this.email);
			System.out.println("Press 1 to show hidden files");
			System.out.println("Press 2 to hide a new file");
			System.out.println("Press 3 to unhide a file");
			System.out.println("Press 9 for go to welcome screen");
			System.out.println("Press 0 to exit the program");
			Scanner sc = new Scanner(System.in);
			int ch = Integer.parseInt(sc.nextLine());
			switch(ch) {
			case 1 : {
				try {
					List<Data> files = DataDAO.getAllFiles(this.email);// Fetch all files for the current user
					System.out.println("ID - File Name");
					for (Data file : files) {  // Displaying each file with its ID and name
						System.out.println(file.getId()+ " - "+ file.getFileName());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
			case 2: { // Hide a file
			    System.out.println("Enter the file path:");
			    String path = sc.nextLine();
			    System.out.println("Path entered: " + path);
			    File f = new File(path);
			    if (!f.exists()) { // Check if file exists at the given path
			        System.out.println("File does not exist. Please check the path.");
			        break;
			    }
			    
			 // Create a Data object for the file to be hidden
			    Data file = new Data(0, f.getName(), path, this.email);
			    try {
			        DataDAO.hideFile(file); // Call the hideFile method to hide the file
			        System.out.println("File hidden successfully.");
			    } catch (SQLException | IOException e) {
			        e.printStackTrace();
			    }
			}
			break;
			case 3: { // Unhide a file
				try {
					List<Data> files = DataDAO.getAllFiles(this.email);
					System.out.println("ID - File Name");
					for (Data file : files) {
						System.out.println(file.getId()+ " - "+ file.getFileName());
					}
				
				System.out.println("Enter the id of file to unhide");
				int id = Integer.parseInt(sc.nextLine()); // Reading file ID from user
				
				// Check if the entered ID is valid
				boolean isVaildID =false;
				for (Data file : files) {
					if (file.getId() == id) {
						isVaildID = true;
						break;
					}
				}
					if (isVaildID) {
						DataDAO.unhide(id); // Call the unhide method to unhide the file
					}else {
						System.out.println("Wrong ID.");
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			break;
			case 9: {
				System.out.println("Exiting to welcome screen...");
				Welcome welcome = new Welcome();
				welcome.welcomeScreen();
				return;
				
			}
			case 0: {
				System.exit(0);	
			}
			}
		}while(true);
	}
}
