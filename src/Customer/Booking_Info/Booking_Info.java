package src.Customer.Booking_Info;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.Create_file;

public class Booking_Info {
    String line;
    public Object[][] upcome_data(LocalDate current_date) {
        List<Object[]> halls_list = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    LocalDate date = LocalDate.parse(data[5]);
                    if (date.isBefore(current_date) && data[5].equals("available")) {
                        halls_list.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls_list.toArray(new Object[0][]);
    }

    public Object[][] past_data(LocalDate current_date) {
        List<Object[]> halls_list = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    LocalDate date = LocalDate.parse(data[5]);
                    if (date.isAfter(current_date) && data[5].equals("available")) {
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
