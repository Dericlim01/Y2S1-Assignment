package src;
import java.util.*;
import java.io.*;

public class UpdateProfile {
    public String[] search_user(String name) {
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/users.txt"))) {
            while ((line = read.readLine()) != null) {
                data = line.split(",");
                users.add(data);
            }
            // Search in arraylist
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i)[0].equals(name)) {
                    return users.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.get(0);
    }

    public Boolean update_user(String n, String p, String cn, String em) {
        // Create a user list
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        Boolean user_update = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/users.txt"))) {
            while ((line = read.readLine()) != null) {
                // Store data into data and add to users array
                data = line.split(",");
                users.add(data);
                System.out.println(users);
            }
            // Loop through the users
            for (int i = 0; i < users.size(); i++) {
                // If username found
                if (users.get(i)[0].equals(n)) {
                    users.get(i)[1] = p;
                    users.get(i)[2] = cn;
                    users.get(i)[3] = em;
                    user_update = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (user_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/users.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[2] + "," + userdata[3] + "," + userdata[4]);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }
    }
}
