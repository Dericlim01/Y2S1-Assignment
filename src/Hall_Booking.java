import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class Hall_Booking {
    String line;
    public ArrayList<String> search_hall() {
        ArrayList<String> halls = new ArrayList<String>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    for (int i = 0; i < halls.size(); i++) {
                        // if valid, break
                        if (data[1].equals(halls.get(i))) {
                            break;
                        } else {
                            // else, add
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

    public ArrayList<String> day_list(int year, String months, int days) {
        int month = 0;
        String[] monthList = new DateFormatSymbols().getMonths();
        for (int i = 0; i < monthList.length; i++) {
            if (months.equals(monthList[i])) {
                month = i + 1;
                break;
            }
        }
        ArrayList<String> dayList = new ArrayList<>();
        int day = 0;
        // Leap year
        if (month == 2) {
            // Feb 29
            if (year % 4 == 0) {
                day = 30;
            } else {
                // Feb 28
                day = 29;
            }
        } else if (month < 8 && month % 2 == 1) {
            // Jan, Mar, May, Jul
            day = 32;
        } else if (month > 7 && month % 2 == 0) {
            // Aug, Oct, Dec
            day = 32;
        } else {
            // Feb, Apr, Jun, Sep, Nov
            day = 31;
        }
        // Add to day list and return
        for (int i = 1; i < day; i++) {
            if (i > days - 1) {
                dayList.add(i + "");
            }
        }
        return dayList;
    }

    public ArrayList<String> month_list(int month) {
        ArrayList<String> monthList = new ArrayList<>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length - 1; i++) {
            if (i > month - 2) {
                monthList.add(months[i]);
            }
        }
        return monthList;
    }

    public ArrayList<String> year_list(int year) {
        ArrayList<String> yearList = new ArrayList<>();
        for (int years = year; years <= Calendar.getInstance().get(Calendar.YEAR) + 1; years++) {
            yearList.add(years + "");
        }
        return yearList;
    }

    public Object[][] hall_data(String hall_type) {
        Object[][] halls_list = {
            {"H01", "Auditorium", 200, 60, 2000, "Available"},
            {"H02", "Meeting Room", 600, 100, 4000, "Available"}
        };
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    for (int i = 0; i < data.length; i++) {
                        if (data[1].equals(hall_type)) {
                            halls_list[i] = data;
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return halls_list;
    }
}
