import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Hall_Booking_Page
 */
public class Hall_Booking_Page extends JFrame {
    public static String name;
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

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall type : ");
        hall_type_lbl.setBounds(30, 100, 100, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(hall_type_lbl);

        // Show Hall type label
        JLabel hall_type_show_lbl = new JLabel("Show hall type : ");
        hall_type_show_lbl.setBounds(300, 100, 150, 20);
        hall_type_show_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentPane.add(hall_type_show_lbl);

        // Hall id
        ArrayList<String> hallsList = new Hall_Booking().search_hall();
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_type_cmbbx = new JComboBox<String>(halls);
        hall_type_cmbbx.setBounds(150, 100, 100, 25);
        hall_type_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_type_cmbbx.setSelectedIndex(-1);
        hall_type_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hall_id = String.valueOf(hall_type_cmbbx.getSelectedItem());
                String hall_type = new Raise_Issue(n).search_id(hall_id);
                hall_type_show_lbl.setText(hall_type);
            }
        });
        contentPane.add(hall_type_cmbbx);

        // Start Date
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setBounds(30, 150, 150, 30);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(start_date_lbl);

        // Day Combo Box
        // Set to today's date
        ArrayList<String> start_day = new Hall_Booking().day_list(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());
        String[] start_days = start_day.toArray(new String[0]);
        JComboBox<String> start_day_cmbbx = new JComboBox<>(start_days);
        start_day_cmbbx.setBounds(500, 150, 100, 30);
        start_day_cmbbx.setSelectedIndex(LocalDateTime.now().getDayOfMonth() - 1);
        contentPane.add(start_day_cmbbx);

        // Year Combo Box
        ArrayList<String> start_year = new Hall_Booking().year_list();
        String[] start_years = start_year.toArray(new String[0]);
        JComboBox<String> start_year_cmbbx = new JComboBox<>(start_years);
        start_year_cmbbx.setBounds(200, 150, 100, 30);
        contentPane.add(start_year_cmbbx);

        // Month Combo Box
        ArrayList<String> start_month = new Hall_Booking().month_list();
        String[] start_months = start_month.toArray(new String[0]);
        JComboBox<String> start_month_cmbbx = new JComboBox<>(start_months);
        start_month_cmbbx.setBounds(350, 150, 100, 30);
        start_month_cmbbx.setSelectedIndex(LocalDateTime.now().getMonthValue() - 1);
        start_month_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_day_cmbbx.removeAllItems();
                ArrayList<String> daylist = new Hall_Booking().day_list(Integer.parseInt(start_year_cmbbx.getSelectedItem().toString()), start_month_cmbbx.getSelectedIndex() + 1);
                String[] all_day = daylist.toArray(new String[0]);
                for (int i = 0; i < all_day.length; i++) {
                    start_day_cmbbx.addItem(all_day[i]);
                }
            }
        });
        contentPane.add(start_month_cmbbx);

        // End Date
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setBounds(30, 190, 150, 30);
        end_date_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        contentPane.add(end_date_lbl);

        // Day Combo Box
        ArrayList<String> end_day = new Hall_Booking().day_list(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());
        String[] end_days = end_day.toArray(new String[0]);
        JComboBox<String> end_day_cmbbx = new JComboBox<>(end_days);
        end_day_cmbbx.setBounds(500, 190, 100, 30);
        end_day_cmbbx.setSelectedIndex(LocalDateTime.now().getDayOfMonth() - 1);
        contentPane.add(end_day_cmbbx);

        // Year Combo Box
        ArrayList<String> end_year = new Hall_Booking().year_list();
        String[] end_years = end_year.toArray(new String[0]);
        JComboBox<String> end_year_cmbbx = new JComboBox<>(end_years);
        end_year_cmbbx.setBounds(200, 190, 100, 30);
        contentPane.add(end_year_cmbbx);

        // Month Combo Box
        ArrayList<String> end_month = new Hall_Booking().month_list();
        String[] end_months = end_month.toArray(new String[0]);
        JComboBox<String> end_month_cmbbx = new JComboBox<>(end_months);
        end_month_cmbbx.setBounds(350, 190, 100, 30);
        end_month_cmbbx.setSelectedIndex(LocalDateTime.now().getMonthValue() - 1);
        end_month_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end_day_cmbbx.removeAllItems();
                ArrayList<String> daylist = new Hall_Booking().day_list(Integer.parseInt(end_year_cmbbx.getSelectedItem().toString()), end_month_cmbbx.getSelectedIndex() + 1);
                String[] all_day = daylist.toArray(new String[0]);
                for (int i = 0; i < all_day.length; i++) {
                    end_day_cmbbx.addItem(all_day[i]);
                }
            }
        });
        contentPane.add(end_month_cmbbx);

        // Table
        String[] col_name = {"Hall ID", "Hall Type", "Capacity", "Price per hour", "Price per day"};
        Object[][] data = new Hall_Booking().hall_data();
        DefaultTableModel table = new DefaultTableModel(data, col_name);
        JTable details = new JTable(table);

        JScrollPane scrollPane = new JScrollPane(details);
        scrollPane.setBounds(200, 300, 600, 400);
        contentPane.add(scrollPane);

        // Back button
        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        back_btn.setBounds(400, 630, 100, 30);
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