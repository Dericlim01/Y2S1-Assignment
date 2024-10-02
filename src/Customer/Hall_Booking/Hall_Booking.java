package src.Customer.Hall_Booking;

import src.shared.Create_file;

import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Hall_Booking {
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String line;

    public ArrayList<String[]> search_hall() {
        // HallID, HallType, Capacity, Price/h
        ArrayList<String[]> halls = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    halls.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls;
    }

    public ArrayList<String> arrange_hall_type() {
        ArrayList<String[]> hall_type = search_hall();
        ArrayList<String> hall_type_list = new ArrayList<>();
        for (int i = 0; i < hall_type.size(); i++) {
            if (!hall_type_list.contains(hall_type.get(i)[1])) {
                hall_type_list.add(hall_type.get(i)[1]);
            }
        }
        return hall_type_list;
    }

    public List<String[]> hall_data() {
        // HallStatusID, HallID, ReservedStartDateTime, ReservedEndDateTime, Status, Remarks, BookingID
        List<String[]> hall_list = new ArrayList<>();
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    hall_list.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hall_list;
    }

    public Object[][] unfiltered_data() {
        // Hall ID, Hall Type, Capacity, Price/h
        ArrayList<String[]> hall_data = search_hall();
        // HallStatusID, HallID, ReservedStartDateTime, ReservedEndDateTime, Status, Remarks, BookingID
        List<String[]> hall_details = hall_data();
        // Hall ID, Hall Type, Capacity, Price per H, Start date, End date, Status, Remarks
        ArrayList<Object[]> hall_data_list = new ArrayList<>();

        for (int hall = 0; hall < hall_data.size(); hall++) {
            for (int i = 0; i < hall_details.size(); i++) {
                // if hall id same
                if (hall_details.get(i)[1].equals(hall_data.get(hall)[0])) {
                    // ReservedStartDateTime, ReservedEndDateTime, Status, Remarks, BookingID
                    ArrayList<String> hall_details_format = new ArrayList<>();
                    for (int ii = 2; ii < hall_details.get(i).length; ii++) {
                        hall_details_format.add(hall_details.get(i)[ii]);
                    }
                    String[] combinedata = new String[hall_data.get(hall).length + hall_details_format.size()];
                    System.arraycopy(hall_data.get(hall), 0, combinedata, 0, hall_data.get(hall).length);
                    System.arraycopy(hall_details_format.toArray(new String[0]), 0, combinedata, hall_data.get(hall).length, hall_details_format.size());
                    hall_data_list.add(combinedata);
                }
            }
        }
        return hall_data_list.toArray(new Object[0][]);
    }

    public Object[][] start_date_filter(LocalDate start_date, String hall_type) {
        List<Object[]> hall_data = new ArrayList<>();
        Object[][] hall_list = unfiltered_data();
        if (start_date == null){
            start_date = LocalDate.now();
            for (int i = 0; i < hall_list.length; i++) {
                if (hall_list[i][6].equals("available")) {
                    LocalDate end = LocalDate.parse(hall_list[i][5].toString(), datePattern);
                    if (!start_date.isAfter(end)||!start_date.equals(end)) {
                        if (hall_type.equals("-1")) {
                            hall_data.add(hall_list[i]);
                        } else if (hall_list[i][1].equals(hall_type)) {
                            hall_data.add(hall_list[i]);
                        }
                    }
                }
            }
        } else{
            for (int i = 0; i < hall_list.length; i++) {
                if (hall_list[i][6].equals("available")) {
                    // Start Date in data
                    LocalDate start = LocalDate.parse(hall_list[i][4].toString(), datePattern);
                    LocalDate end = LocalDate.parse(hall_list[i][5].toString(), datePattern);
                    if ((start.equals(start_date) || start_date.isAfter(start))&& start_date.isBefore(end)) {
                        if (hall_type.equals("-1")) {
                            hall_data.add(hall_list[i]);
                        } else if (hall_list[i][1].equals(hall_type)) {
                            hall_data.add(hall_list[i]);
                        }
                    }
                }
            }
        }
        
        return hall_data.toArray(new Object[0][]);
    }

    public Object[][] end_date_filter(LocalDate start_date, String hall_type, LocalDate end_date) {
        List<Object[]> hall_data = new ArrayList<>();
        Object[][] hall_list = start_date_filter(start_date, hall_type);
        if (end_date == null) {
            return hall_list;
        } else{
            for (int i = 0; i < hall_list.length; i++) {    
                // End date in data
                LocalDate ListEndDate = LocalDate.parse(hall_list[i][5].toString(), datePattern);
                LocalDate ListStartDate = LocalDate.parse(hall_list[i][4].toString(), datePattern);
                if ((ListEndDate.equals(end_date) || end_date.isBefore(ListEndDate))&&(end_date.isAfter(ListStartDate)||end_date.isEqual(ListStartDate))) {
                    if (hall_type.equals("-1")) {
                        hall_data.add(hall_list[i]);
                    } else if (hall_list[i][1].equals(hall_type)) {
                        hall_data.add(hall_list[i]);
                    }
                }
            }
            return hall_data.toArray(new Object[0][]);
        }
    }
}
