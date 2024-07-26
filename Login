import java.io.*;
import java.util.Arrays;

public class Login {
    public String login(String n, char[] p) {
        String line;

        // Check file
        Create_file file = new Create_file();
        if (file.user_file()) {
            // Read file
            try (BufferedReader read = new BufferedReader(new FileReader("users.txt"))) {
                while ((line = read.readLine()) != null) {
                    // Create array and store data
                    String[] data = line.split(",");
                    String username = data[0];
                    String password = data[1];

                    // If username and password correct
                    if (username.equals(n) && Arrays.equals(password.toCharArray(), p)) {
                        return data[4];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
