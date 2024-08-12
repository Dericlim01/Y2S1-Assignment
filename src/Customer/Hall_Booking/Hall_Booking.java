package src.Customer.Hall_Booking;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import src.Create_file;

public class Hall_Booking {
    String line;
    public ArrayList<String> search_hall() {
        ArrayList<String> halls = new ArrayList<String>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (!halls.contains(data[1])) {
                        halls.add(data[1]);
                    }
                }
                return halls;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls;
    }

    public Object[][] hall_data(String hall_type) {
        List<Object[]> halls_list = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(hall_type) && data[5].equals("available")) {
                        halls_list.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls_list.toArray(new Object[0][]);
    }
}
