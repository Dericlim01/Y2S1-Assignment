package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Register {
    private String role;

    public Register(String r) {
        role = r;
    }

    // Check if username valid
    String line;
    Create_file file = new Create_file();
    public Boolean chk_user(String name) {
        if (file.user_file() && file.customer_file()) {
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

    // Register User to user txt
    public Boolean reg_user(String username, String pass) {
        try {
            FileWriter user = new FileWriter("resources/Database/users.txt", true);
            user.append(username + "," + pass + "," + role + "\n");
            user.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return false;
    }

    // Register User to customer txt
    public Boolean reg_users(String username, String cont_num, String email, Date dob, String gender) {
        try {
            FileWriter users = new FileWriter("resources/Database/customers.txt", true);
            users.append(username + "," + cont_num + "," + email + "," + dob + "," + gender + "\n");
            users.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return false;
    }
}
