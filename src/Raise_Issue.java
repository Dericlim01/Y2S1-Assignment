package src;
import java.io.*;

import java.util.ArrayList;

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
            try (BufferedReader read = new BufferedReader(new FileReader("bookings.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    // if hall is book by user
                    if (data[10].equals(name)) {
                        // Check if hall id valid
                        for (int i = 0; i < halls.size(); i++) {
                            // if valid, break
                            if (data[0].equals(halls.get(i))) {
                                break;
                            } else {
                                // else, add
                                halls.add(data[0]);
                            }
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
            try (BufferedReader read = new BufferedReader(new FileReader("halls.txt"))) {
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
    public Boolean send_issue(String issue_title, String issue_desc) {
        if (new Create_file().issue_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("issues.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    int num = data.length;
                    num_issue = num + 1;
                }
                FileWriter issues = new FileWriter("issues.txt", true);
                issues.append("I" + num_issue + "," + issue_title + "," + issue_desc + "," + name);
                issues.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
