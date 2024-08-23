package src.Customer.Payment;
import src.Create_file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment {
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String calculate_day(String start_date, String end_date) {
        LocalDate d1 = LocalDate.parse(start_date, datePattern);
        LocalDate d2 = LocalDate.parse(end_date, datePattern);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        String diffDays = String.valueOf(diff.toDays());
        return diffDays;
    }

    private String calculate_price(String days, String price_per_hour) {
        int time_distance = 18 - 8 + 1;
        int total_day_price = Integer.parseInt(days) * time_distance * (int) Double.parseDouble(price_per_hour);
        return String.valueOf(total_day_price);
    }

    public String[] calculate_price_hour(String price_h, String start_date, String end_date) {
        String total_days = calculate_day(start_date, end_date);
        String total_price = calculate_price(total_days, price_h);

        String[] total = {total_days, total_price};
        return total;
    }

    public Integer confirm_booking(String name, String[] data, String price) {
        int nextID = 0;
        String line;
        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/booking.txt"))) {
                int max_id = 0;
                while ((line = read.readLine()) != null) {
                    String[] ID_data = line.split(";");
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
        // ID (B01)
        String id = String.format("B%02d", nextID);
        if (new Create_file().booking_file()) {
            // Write to booking txt
            try {
                FileWriter book_data = new FileWriter("resources/Database/booking.txt", true);
                String[] booking_data = {
                // id, hall id, capacity, start date, end date, book stat, book paid, deposit, username
                    id, data[0], data[2], data[4], data[5], "approved", price, "300", String.valueOf(LocalDate.now()), name
                };
                String data_join = String.join(";", booking_data);
                book_data.append(data_join + "\n");
                book_data.close();
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
