import java.io.*;

public class Register {
    private String role;
    
    public Register() {}

    public Register(String r) {
        role = r;
    }
    public Boolean reg_user(String name, char[] pass, String cont_num, String email) {
        try {
            FileWriter user = new FileWriter("users.txt", true);
            user.append(name + "," + pass.toString() + "," + cont_num + "," + email + role + "\n");
            user.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return false;
    }
}
