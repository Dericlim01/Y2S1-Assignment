package src;
import java.io.*;

public class Login {
    public String login(String n, String p) {
        String line;

        // Check file
        Create_file file = new Create_file();
        if (file.user_file()) {
            // Read file
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
                while ((line = read.readLine()) != null) {
                    // Create array and store data
                    String[] data = line.split(",");
                    String username = data[0];
                    String password = data[1];

                    // If username and password correct
                    if (username.equals(n) && password.equals(p)) {
                        return data[2];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
