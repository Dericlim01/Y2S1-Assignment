package src.Customer.Raise_Issue;

import src.shared.Create_file;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Raise_Issue {
    public static String name;
    
    public Raise_Issue(String n) {
        name = n;
    }

    String line;

    // Search hall id in booking file booked previously by the user
    public ArrayList<String> search_hall() {
        ArrayList<String> halls = new ArrayList<String>();
        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    // if hall is book by user
                    if (data[9].equals(name)) {
                        // Check if hall id valid
                        if (!halls.contains(data[1])) {
                            halls.add(data[1]);
                        }
                    }
                }
                return halls;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls;
    }

    // Search hall type by id
    public String search_id(String id) {
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (id.equals(data[0])) {
                        return data[1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    // Send issue
    int num_issue;
    public Boolean send_issue(String issue_title, String issue_desc, String hall_id) {
        int nextID = 0;
        String line;
        if (new Create_file().issue_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/issues.txt"))) {
                int max_id = 0;
                while ((line = read.readLine()) != null) {
                    String[] ID_data = line.split(",");
                    int prev_id = Integer.parseInt(ID_data[0].substring(1));
                    if (prev_id > max_id) {
                        max_id = prev_id;
                    }
                }
                // New ID
                nextID = max_id + 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ID (I01)
        String id = String.format("I%02d", nextID);
        if (new Create_file().issue_file()) {
            String hall_type = search_id(hall_id);
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/issues.txt"))) {
                FileWriter issues = new FileWriter("resources/Database/issues.txt", true);
                issues.append(id + "," + issue_title + "," + issue_desc + "," + hall_id + "," + hall_type + "," + name + "\n");
                issues.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
