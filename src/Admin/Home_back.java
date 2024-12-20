package src.Admin;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class Home_back {

    public String[] home_back(String name) {
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while ((line = read.readLine()) != null) {
                data = line.split(",");
                users.add(data);
            }
            // Searching users
            for (String[] user:users) {
                if (user.length > 0 && user[0].equals(name)) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // User not found
        return null;
    }
}
