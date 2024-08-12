package src;
import java.io.*;

public class Register {
    private String role;

    public Register(String r) {
        role = r;
    }

    // Check if username valid
    String line;
    Create_file file = new Create_file();
    public Boolean chk_user(String name) {
        if (file.user_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    String username = data[0];
                    // If username exist
                    if (username.equals(name)) {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    // Register User
    public Boolean reg_user(String name, String pass, String cont_num, String email) {
        try {
            FileWriter user = new FileWriter("resources/Database/users.txt", true);
            user.append(name + "," + pass + "," + cont_num + "," + email + "," + role + "\n");
            user.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return false;
    }
}
