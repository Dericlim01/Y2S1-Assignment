package src.Customer.Payment;

import src.shared.Create_file;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
// import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;



public class Payment {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<Integer> findBiggest = new ArrayList<Integer>();
    private Integer newIDNum;
    private String calculate_day(String start_date, String end_date) {
        LocalDate d1 = LocalDate.parse(start_date, datePattern);
        LocalDate d2 = LocalDate.parse(end_date, datePattern);
        //Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        //Integer diffDays = Integer.parseInt(diff.toString()) + 1;
        long diffDays = ChronoUnit.DAYS.between(d1, d2) + 1;
        String diffDays_str = String.valueOf(diffDays);
        return diffDays_str;
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

    public Boolean change_book_stat(String[] select_data, String id, String Sdate, String Edate) {
        ArrayList<String[]> hall_data = new ArrayList<>();
        Boolean update = false;
        String[] modifiedHall = null;
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                hall_data.add(data);
                String[] hallstat_ID = data[0].split("_");
                    int statID =  Integer.parseInt(hallstat_ID[1]);
                    // Add the status ID to the list for comparison
                    findBiggest.add(statID);  
                }  
                // Determine the new ID number
                if (findBiggest.isEmpty()) {
                    newIDNum = 1;  // No valid lines in file, start from 1
                } else {
                    // Otherwise, find the maximum ID and increment it by 1 for the new entry
                    newIDNum = Collections.max(findBiggest) + 1;
                }
            
            for (String[] hall : hall_data) {
                // hall id and start date same
                if (hall[1].equals(select_data[0]) && hall[2].equals(select_data[4])) {
                    hall[2] = Sdate;
                    hall[3] = Edate;
                    hall[4] = "booked";
                    hall[6] = id;
                    modifiedHall = hall.clone();  // Save a copy of the modified hall
                }
            }
            update = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        if (update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/hall_status.txt"))) {
                for (String[] data : hall_data) {
                    writer.println(String.join(",", data));
                }
                if (modifiedHall != null) {
                    Boolean addedline1 = false;
                    Date originalsDate = dateFormat.parse(select_data[4]);
                    Date splitSdate = dateFormat.parse(Sdate);
                    Date originaleDate = dateFormat.parse(select_data[5]);
                    Date splitEdate = dateFormat.parse(Edate);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(splitSdate);
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    Date newESDate = calendar.getTime();
                    if(originalsDate.before(newESDate)){
                        String[] extraLine1 = modifiedHall.clone();
                        extraLine1[6] = null;  // Example modification
                        extraLine1[4] = "available";
                        extraLine1[0] = "HS_"+newIDNum;
                        extraLine1[2] = select_data[4];  // Example modification
                        extraLine1[3] = dateFormat.format(newESDate);      // Example modification
                        writer.println(String.join(",", extraLine1));
                        addedline1 = true;
                    }


                    calendar.setTime(splitEdate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    Date newEEDate = calendar.getTime();
                    if(originaleDate.after(newEEDate)){
                        String[] extraLine2 = modifiedHall.clone();
                        extraLine2[6] = null;  // Example modification
                        extraLine2[4] = "available";
                        if (addedline1){
                            extraLine2[0] = "HS_"+(newIDNum+1);
                        } else{
                            extraLine2[0] = "HS_"+newIDNum;
                        }
                        extraLine2[2] = dateFormat.format(newEEDate);  // Example modification
                        extraLine2[3] = select_data[5];      // Example modification
                        writer.println(String.join(",", extraLine2));
                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public Integer confirm_booking(String name, String[] data, String price, LocalDate bookdate, String Sdate, String Edate) {
        int nextID = 0;
        String line;
        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
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
        // ID (B01)
        String id = String.format("B%02d", nextID);
        if (new Create_file().booking_file()) {
            // Write to booking txt
            try {
                FileWriter book_data = new FileWriter("resources/Database/bookings.txt", true);
                String[] booking_data = {
                // id, hall id, capacity, start date, end date, book stat, book paid, deposit, booking date, username
                    id, data[0], data[2], Sdate, Edate, "approved", price, "300", String.valueOf(datePattern.format(bookdate)), name
                };
                String data_join = String.join(",", booking_data);
                book_data.append(data_join + "\n");
                book_data.close();
                if (change_book_stat(data, id,  Sdate,  Edate)) {
                    return 1;
                }
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }
}
