package src;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Hall_Booking_Page
 */
public class Hall_Booking_Page extends JFrame {
    public static String name;

    // Today's date
    public static Integer current_day = LocalDate.now().getDayOfMonth();
    public static Integer current_month = LocalDate.now().getMonthValue();
    public static String current_month_str = String.valueOf(LocalDate.now().getMonth());
    public static String current_month_str_f = current_month_str.substring(0, 1).toUpperCase() + current_month_str.substring(1).toLowerCase();
    public static Integer current_year = LocalDate.now().getYear();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Hall_Booking_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Hall_Booking_Page(String n) {
        setTitle("Hall Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        
        // Set Panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Hall Booking Label
        JLabel hall_book_lbl = new JLabel("Hall Booking");
        hall_book_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        hall_book_lbl.setBounds(400, 50, 200, 45);
        contentPane.add(hall_book_lbl);

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall type : ");
        hall_type_lbl.setBounds(30, 110, 100, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(hall_type_lbl);
        
        // Hall type
        ArrayList<String> hallsList = new Hall_Booking().search_hall();
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_type_cmbbx = new JComboBox<String>(halls);
        hall_type_cmbbx.setBounds(175, 110, 125, 25);
        hall_type_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_type_cmbbx.setSelectedIndex(-1);
        contentPane.add(hall_type_cmbbx);

        // Start Date
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setBounds(30, 150, 150, 30);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(start_date_lbl);

        // Start Calendar
        UtilDateModel start_date_model = new UtilDateModel();
        // Properties create object to store values in it
        Properties start_date_prop = new Properties();
        start_date_prop.put("text.day", "Day");
        start_date_prop.put("text.month","Month");
        start_date_prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl start_datePanel = new JDatePanelImpl(start_date_model, start_date_prop);
        JDatePickerImpl start_datePicker = new JDatePickerImpl(start_datePanel, new DateFormat());
        start_datePicker.setBounds(175,150,140,30);
        contentPane.add(start_datePicker);

        // End Date
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setBounds(30, 190, 150, 30);
        end_date_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        contentPane.add(end_date_lbl);

        // End Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        // Properties create object to store values in it
        Properties end_date_prop = new Properties();
        end_date_prop.put("text.day", "Day");
        end_date_prop.put("text.month","Month");
        end_date_prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, end_date_prop);
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat());
        end_datePicker.setBounds(175,190,140,30);
        contentPane.add(end_datePicker);

        // Table
        String[] col_name = {"Hall ID", "Hall Type", "Capacity", "Price per hour", "Price per day", "Status"};
        Object[][] data = new Hall_Booking().hall_data(String.valueOf(hall_type_cmbbx.getSelectedItem()));
        DefaultTableModel table = new DefaultTableModel(data, col_name);
        JTable details = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(details);
        scrollPane.setBounds(200, 300, 600, 400);
        contentPane.add(scrollPane);

        hall_type_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hall_type = String.valueOf(hall_type_cmbbx.getSelectedItem());
                Object[][] hall_data = new Hall_Booking().hall_data(hall_type);
                table.setDataVector(hall_data, col_name);
                details.revalidate();
                details.repaint();
            }
        });

        // Search button
        JButton search_btn = new JButton("Search");
        search_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search_btn.setBounds(600, 190, 120, 30);
        contentPane.add(search_btn);

        // Back button
        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        back_btn.setBounds(50, 700, 100, 30);
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Customer_Page(n).setVisible(true);
            }
        });
        contentPane.add(back_btn);
    }
}