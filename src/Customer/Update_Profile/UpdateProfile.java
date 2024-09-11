package src.Customer.Update_Profile;
import java.util.Date;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class UpdateProfile {
    String line;

    // search user and return to update for display
    public String[] search_user(String name) {
        // Create an user arraylist
        ArrayList<String[]> users = new ArrayList<String[]>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            // read every line and add to arraylist
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                users.add(data);
            }
            // Search in arraylist
            for (int i = 0; i < users.size(); i++) {
                // if username found
                if (users.get(i)[0].equals(name)) {
                    return users.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.get(0);
    }

    // search user and return to update for display
    public String[] search_users(String name) {
        // Create an user arraylist
        ArrayList<String[]> users = new ArrayList<String[]>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/customers.txt"))) {
            // read every line and add to arraylist
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                users.add(data);
            }
            // Search in arraylist
            for (int i = 0; i < users.size(); i++) {
                // if username found
                if (users.get(i)[0].equals(name)) {
                    return users.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.get(0);
    }

    // update user to user txt
    public Boolean update_user(String n, String p) {
        // Create a user list
        ArrayList<String[]> users = new ArrayList<String[]>();
        Boolean user_update = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            // read every line and add to arraylist
            while ((line = read.readLine()) != null) {
                // Store data into data and add to users array
                String[] data = line.split(",");
                users.add(data);
                System.out.println(users);
            }
            // Loop through the users
            for (int i = 0; i < users.size(); i++) {
                // If username found
                if (users.get(i)[0].equals(n)) {
                    users.get(i)[1] = p;
                    user_update = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (user_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/users.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[2]);
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

    public Boolean update_users(String n, String cn, String em, Date dob, String gender) {
        // Create a user list
        ArrayList<String[]> users = new ArrayList<String[]>();
        Boolean user_update = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/customers.txt"))) {
            while ((line = read.readLine()) != null) {
                // Store data into data and add to users array
                String[] data = line.split(",");
                users.add(data);
                System.out.println(users);
            }
            // Loop through the users
            for (int i = 0; i < users.size(); i++) {
                // If username found
                if (users.get(i)[0].equals(n)) {
                    users.get(i)[1] = cn;
                    users.get(i)[2] = em;
                    users.get(i)[3] = dob.toString();
                    users.get(i)[4] = gender;
                    user_update = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (user_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/customers.txt"))) {
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
