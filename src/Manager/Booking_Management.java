package src.Manager;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


import java.util.Properties;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.text.SimpleDateFormat;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class Booking_Management extends JFrame {
    private static String manname;
    private static JScrollPane scrollPane;
    private DefaultTableModel tm;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Booking_Management(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Booking_Management(String n){
        setTitle("Booking Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_BM = new JPanel();
        manager_BM.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_BM);
        manager_BM.setLayout(null);

        Manager man_booking = new Manager();
        String[] col_name = {"Booking ID", "Hall ID", "Num of Guests", "Start Date", "End Date", "Start Time", "End Time", "Book Status", "Booking Paid", "Deposit Paid", "Username"};

        Object[][] row_data = man_booking.present_data("resources/bookings.txt");

        tm = new DefaultTableModel(row_data, col_name);
        JTable view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_BM.add(scrollPane);

        // Start date label
        JLabel start_date = new JLabel("Start Date:");
        start_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        start_date.setBounds(80, 26, 90, 20);
        manager_BM.add(start_date);

        // End date label
        JLabel end_date = new JLabel("End Date:");
        end_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        end_date.setBounds(400, 26, 90, 20);
        manager_BM.add(end_date);

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
        manager_BM.add(start_datePicker);

        // End Date Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        Properties end_p = new Properties();
        end_p.put("text.day", "Day");
        end_p.put("text.month", "Month");
        end_p.put("text.year", "Year");
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, start_p);   
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat()); 
        end_datePicker.setBounds(480, 23, 170, 30);
        manager_BM.add(end_datePicker);

        // Do a filter data action according date selected 
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> filteredDate = man_booking.date_read(start_datePicker, end_datePicker, "resources/bookings.txt");
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

        // Refresh button to get whole data(new / old) again
        JButton refresh = new JButton("refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_BM.remove(scrollPane);
                Object[][] row_data = man_booking.present_data("resources/bookings.txt");
                tm = new DefaultTableModel();
                tm.setDataVector(row_data, col_name);
                JTable view = new JTable(tm);

                scrollPane = new JScrollPane(view);
                scrollPane.setBounds(9, 125, 970, 400);
                manager_BM.add(scrollPane);

                view.revalidate();
                view.repaint();

            }
        });
        manager_BM.add(refresh);
        
        // Back button back to Home Page
        JButton back_btn = new JButton();
        try {
            BufferedImage backImage = new BufferedImage(
                50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setVisible(true);
            }
        });

        manager_BM.add(back_btn);


        

    }
}
