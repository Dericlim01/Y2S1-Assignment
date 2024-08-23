package src.Customer.Payment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment {
    private DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public String calculate_day(String start_date, String end_date) {
        LocalDate d1 = LocalDate.parse(start_date, datePattern);
        LocalDate d2 = LocalDate.parse(end_date, datePattern);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        String diffDays = String.valueOf(diff.toDays());
        return diffDays;
    }

    public String calculate_price(String days, String price_per_hour) {
        int time_distance = 18 - 8;
        int total_day_price = Integer.parseInt(days) * time_distance * (int) Double.parseDouble(price_per_hour);
        return String.valueOf(total_day_price);
    }

    public String[] calculate_price_hour(String price_h, String start_date, String end_date) {
        String total_days = calculate_day(start_date, end_date);
        String total_price = calculate_price(total_days, price_h);

        String[] total = {total_days, total_price};
        return total;
    }
}
