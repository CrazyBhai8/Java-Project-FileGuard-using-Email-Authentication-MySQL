package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import Model.User;
import dao.UserDAO;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final Scanner sc = new Scanner(System.in);

	// This is my welcome screen.
	public void welcomeScreen() {
		System.out.println("Welcome to the app");
		System.out.println("Press 1 to login.");
		System.out.println("Press 2 to signup.");
		System.out.println("Press 0 to exit.");
		int choice = 0;
		try {
			choice = Integer.parseInt(br.readLine());	
		switch (choice) {
		case 1:
			login();
			break;
		case 2:
			signUp();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("Enter valid input.");
		}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Invalid input. Please try again.");
			welcomeScreen();
		}
	}

	private void login() {
		System.out.println("Enter your email:");
		String email = sc.nextLine().trim();
		try {
			if (UserDAO.isExists(email)) {
				
				// Generate and send OTP
				String genOTP = GenerateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter the OTP:");
				String otp = sc.nextLine().trim();
				if (otp.equals(genOTP)) {
					new UserView(email).home();
				} else {
					System.out.println("Wrong OTP.");
				}
			} else {
				System.out.println("User not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void signUp() {
		System.out.println("Enter Name:");
		String name = sc.nextLine().trim();
		System.out.println("Enter email:");
		String email = sc.nextLine().trim();
		try {
			if (UserDAO.isExists(email)) {
				System.out.println("Email already exists.");
			} else {
				String genOTP = GenerateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter the OTP:");
				String otp = sc.nextLine().trim();
				if (otp.equals(genOTP)) {
					User user = new User(name, email);
					int response = UserService.saveUser(user);
					switch (response) {
					case 0:
						System.out.println("User already exists.");
						break;
					case 1:
						System.out.println("User registered successfully.");
						break;
					default:
						System.out.println("An unknown error occurred.");
					}
				} else {
					System.out.println("Wrong OTP. Registration failed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
