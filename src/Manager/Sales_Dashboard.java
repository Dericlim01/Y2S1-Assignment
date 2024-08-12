package src.Manager;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import src.DateFormat;

import java.util.List;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class Sales_Dashboard extends JFrame {

    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel tm;
    //private List<LocalDate> data;

    // private static Integer now_day = LocalDate.now().getDayOfMonth();
    // private static Integer now_month = LocalDate.now().getMonthValue();
    // private static String now_month_str = String.valueOf(LocalDate.now().getMonth());
    // private static String now_month_str_f = now_month_str.substring(0, 1).toUpperCase() + now_month_str.substring(1).toLowerCase();
    // private static Integer now_year = LocalDate.now().getYear();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new Sales_Dashboard(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Sales_Dashboard(String n) {
        setTitle("Sales Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_SD = new JPanel();
        manager_SD.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_SD);
        manager_SD.setLayout(null);

        Manager man_data = new Manager();

        // Define the columns names
        String[] col_name = {"Booking ID", "Hall ID", "Num of Guests", "Start Date", "End Date", "Start Time", "End Time", "Book Status", "Booking Paid", "Deposit Paid", "Username"};

        // Define the data for the table
        // Object [][] represents whole table

        Object[][] row_Data = man_data.present_data("resources/bookings.txt");

        tm = new DefaultTableModel(row_Data, col_name);
        JTable view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_SD.add(scrollPane);

        // Start Date label
        JLabel day_lbl = new JLabel("Start Date :");
        day_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        day_lbl.setBounds(80, 26, 90, 20);
        manager_SD.add(day_lbl);

        // End Date label
        JLabel end_date = new JLabel("End Date:");
        end_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        end_date.setBounds(400, 26, 90, 20);
        manager_SD.add(end_date);

        // Calendar
        // Setup date picker model
        // Start date Calendar
        UtilDateModel start_date_model = new UtilDateModel();
        Properties start_p = new Properties();
        start_p.put("text.day", "Day");
        start_p.put("text.month", "Month");
        start_p.put("text.year", "Year");
        JDatePanelImpl start_datePanel = new JDatePanelImpl(start_date_model, start_p);
        JDatePickerImpl start_datePicker = new JDatePickerImpl(start_datePanel, new DateFormat());
        start_datePicker.setBounds(170, 23, 170, 30);
        manager_SD.add(start_datePicker);

        // End Date Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        Properties end_p = new Properties();
        end_p.put("text.day", "Day");
        end_p.put("text.month", "Month");
        end_p.put("text.year", "Year");
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, end_p);
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat());
        end_datePicker.setBounds(480, 23, 170, 30);
        manager_SD.add(end_datePicker);
        

       ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Object[]> filteredDate = man_data.date_read(start_datePicker, end_datePicker, "resources/bookings.txt");
            tm.setRowCount(0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            for (Object[] dates : filteredDate) {
                Object[] formated = new Object[dates.length];

                for (int i = 0; i < dates.length; i++) {
                    if (i == 3 || i == 4) {
                        formated[i] = dateFormat.format(dates[i]);
                    } else {
                        formated[i] = dates[i];
                    }
                }

                tm.addRow(formated);
            }

            view.revalidate();
            view.repaint();


        }
       };

       start_datePicker.addActionListener(actionListener);
       end_datePicker.addActionListener(actionListener);

       JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 43, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_SD.remove(scrollPane);
                Object[][] row_Data = man_data.present_data("resources/bookings.txt");

                DefaultTableModel tm = new DefaultTableModel();
                tm.setDataVector(row_Data, col_name);
                JTable table_tm = new JTable(tm);



                JScrollPane scrollPane = new JScrollPane(table_tm);

                scrollPane.setBounds(9, 125, 970, 400);
                manager_SD.add(scrollPane);
                
                table_tm.revalidate(); // important, recalculate the layout / resize the panel?
                table_tm.repaint(); // important, rearranging components
               
                
                
            }
        });
        manager_SD.add(refresh);

        JButton back_btn = new JButton();
        try {
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setTitle("Manager Home Page");
                man_HP.setVisible(true);
            }
        });

        manager_SD.add(back_btn);
        
    }

}
