package src.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Admin_Booking_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;

    public JScrollPane view_booking(String[] bookCol){
        ArrayList<String[]> bookData = new ArrayList<>();
        try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
            while((line = read.readLine()) != null){
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

    

}
