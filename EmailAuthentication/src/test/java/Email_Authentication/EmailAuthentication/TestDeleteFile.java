package Email_Authentication.EmailAuthentication;

import java.io.File;

public class TestDeleteFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\modik\\Desktop\\hide.txt"; // Update this path
        File file = new File(filePath);
        
        if (file.exists()) {
            System.out.println("File exists. Attempting to delete...");
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File not found..");
        }
    }
}
