package src.Customer.Booking_Info;
import src.Create_file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Booking_Info {
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String line;

    // Hall ID, Hall ID, Capacity, Start Date, End Date, Booking Date
    public Object[][] event_data() {
        List<Object[]> all_event_data = new ArrayList<>();
        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    all_event_data.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return all_event_data.toArray(new Object[0][]);
    }

    public Object[][] filter_name(String name) {
        Object[][] all_event_data = event_data();
        List<Object[]> event_data = new ArrayList<>();
        for (Object[] event : all_event_data) {
            if (event[9].equals(name)) {
                event_data.add(event);
            }
        }
        return event_data.toArray(new Object[0][]);
    }

    public Object[][] arrange_data(String name) {
        Object[][] all_event_data = filter_name(name);
        List<Object[]> event_data = new ArrayList<>();
        for (Object[] event : all_event_data) {
            Object[] data = new Object[6];
            for (int i = 0; i < 5; i++) {
                data[i] = event[i];
            }
            data[5] = event[8];
            event_data.add(data);
        }
        return event_data.toArray(new Object[0][]);
    }

    public Object[][] current_data(String name, LocalDate date) {
        Object[][] all_event_data = arrange_data(name);
        List<Object[]> event_data = new ArrayList<>();
        for (int i = 0; i < all_event_data.length; i++) {
            LocalDate data_start_date = LocalDate.parse(all_event_data[i][3].toString(), datePattern);
            LocalDate data_end_date = LocalDate.parse(all_event_data[i][4].toString(), datePattern);
            if ((data_start_date.equals(date) || data_start_date.isAfter(date)) && 
                (data_end_date.isBefore(date) || data_end_date.equals(date))) {
                event_data.add(all_event_data[i]);
            }
        }
        return event_data.toArray(new Object[0][]);
    }

    public Object[][] upcome_data(String name, LocalDate date) {
        Object[][] all_event_data = arrange_data(name);
        List<Object[]> event_data = new ArrayList<>();
        for (int i = 0; i < all_event_data.length; i++) {
            LocalDate data_start_date = LocalDate.parse(all_event_data[i][3].toString(), datePattern);
            // Event start date is after today date
            if (data_start_date.isAfter(date)) {
                event_data.add(all_event_data[i]);
            }
        }
        return event_data.toArray(new Object[0][]);
    }

    public Object[][] past_data(String name, LocalDate date) {
        Object[][] all_event_data = arrange_data(name);
        List<Object[]> event_data = new ArrayList<>();
        for (int i = 0; i < all_event_data.length; i++) {
            LocalDate data_end_date = LocalDate.parse(all_event_data[i][4].toString(), datePattern);
            // Event end date is before todays date
            if (data_end_date.isBefore(date)) {
                event_data.add(all_event_data[i]);
            }
        }
        return event_data.toArray(new Object[0][]);
    }

    public Boolean update_stat(String[] select_data) {
        List<String[]> hall_stat_data = new ArrayList<>();
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    hall_stat_data.add(data);
                }
                for (String[] hall_data : hall_stat_data) {
                    if (hall_data[6].equals(select_data[0])) {
                        hall_data[4] = "available";
                        hall_data[6] = "";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Writing into hall stat file
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/hall_status.txt"))) {
                for (String[] hall_data : hall_stat_data) {
                    writer.println(String.join(",", hall_data));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public Boolean cancel_book(String[] data) {
        Object[][] all_event_data = event_data();
        try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/bookings.txt"))) {
            for (Object[] event : all_event_data) {
                if (!event[0].equals(data[0])) {
                    String[] eventStrings = Arrays.stream(event)
                                                    .map(Object::toString)
                                                    .toArray(String[]::new);
                    writer.println(String.join(",", eventStrings));
                }
            }
            if (update_stat(data)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
