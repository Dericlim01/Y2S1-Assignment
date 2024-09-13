package src.Admin;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class Admin_Booking_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;
    //Creates a formatter using the specified pattern
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public JScrollPane view_booking(String[] bookCol) {
        ArrayList<String[]> bookData = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                bookData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] bookRow = bookData.toArray(new Object[0][]);
        DefaultTableModel booking_table = new DefaultTableModel(bookRow, bookCol);
        JTable table = new JTable(booking_table);
        sorter = new TableRowSorter<>(booking_table);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    public JScrollPane filter_booking(String[] bookCol, String filOp) {
        ArrayList<String[]> bookData = new ArrayList<>();
        //setting a Local Date to help identify the past or upcoming bookings
        LocalDate localDate = LocalDate.now();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                //Variable to read and store the start and end date
                LocalDate bookEnd = LocalDate.parse(data[4].trim(),dateFormat);
                LocalDate bookStart = LocalDate.parse(data[3].trim(),dateFormat);
                //Filtering pastbooking
                //Checks if this date is before the specified date, check with end date before localdate
                if (filOp.equals("Past Bookings") && bookEnd.isBefore(localDate)) {
                    bookData.add(data);
                }
                //Filtering Upcoming Bookings
                //Checks if this date is after the specified date, check with start and end date after localdate
                else if (filOp.equals("Upcoming Bookings") && (bookStart.isAfter(localDate)) && (bookEnd.isAfter(localDate))) {
                    bookData.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] bookRow = bookData.toArray(new Object[0][]);
        DefaultTableModel booking_table = new DefaultTableModel(bookRow, bookCol);
        JTable table = new JTable(booking_table);
        sorter = new TableRowSorter<>(booking_table);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
}
