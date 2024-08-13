package src.Customer.Hall_Booking;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import src.Create_file;

public class Hall_Booking {
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                    if (hall_type.equals("-1") && data[5].equals("available")) {
                        halls_list.add(data);
                    } else if (data[1].equals(hall_type) && data[5].equals("available")) {
                        halls_list.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls_list.toArray(new Object[0][]);
    }

    // Filter start date
    public Object[][] start_filter(LocalDate date) {
        // Format date
        LocalDate start_date = LocalDate.parse(datePattern.format(date));
        List<Object[]> hallList = new ArrayList<>();
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    // Format date
                    LocalDate start_date_f = LocalDate.parse(data[2]);
                    if (start_date_f.isAfter(start_date) && data[6].equals("available")) {
                        hallList.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallList.toArray(new Object[0][]);
    }

    // Filter end date
    public Object[][] end_filter(LocalDate start_date, LocalDate end_date) {
        // Format date
        LocalDate start_date_f = LocalDate.parse(datePattern.format(start_date));
        LocalDate end_date_f = LocalDate.parse(datePattern.format(end_date));

        List<Object[]> hallList = new ArrayList<>();
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    // Format date
                    LocalDate start_date_data_f = LocalDate.parse(data[2]);
                    LocalDate end_date_data_f = LocalDate.parse(data[3]);
                    if (start_date_f != null) {
                        if ((start_date_data_f.isAfter(start_date_f) || start_date_data_f.equals(start_date_f)) &&
                            (end_date_data_f.isBefore(end_date_f) || end_date_data_f.equals(end_date_f)) &&
                            data[6].equals("available")) {
                            hallList.add(data);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallList.toArray(new Object[0][]);
    }
}
 