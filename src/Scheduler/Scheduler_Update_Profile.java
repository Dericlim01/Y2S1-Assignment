package src.Scheduler;
import src.shared.Create_file;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * Scheduler_Update_Profile
 */
public class Scheduler_Update_Profile {

    public String[] read_user_Information(String n) {
        ArrayList<String[]> users = new ArrayList<>();
        String line;
        String[] data;
        if (new Create_file().user_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"));) {
                while ((line = read.readLine()) != null) {
                    // Create array and store data 
                    data = line.split(",");
                    users.add(data);
                }
                // Search in arrayList
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i)[0].equals(n) && users.get(i)[6].equals("scheduler")) {
                        return users.get(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
