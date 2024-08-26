package src.Manager;

import src.DateFormat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.io.*;

import javax.imageio.*;


import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Properties;

import java.text.SimpleDateFormat;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

public class Sales_Dashboard_Page extends JFrame {

    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel tm;
    private List<Object[]> filteredDate;
    private double tp;
    private JLabel paid;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Sales_Dashboard_Page(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Sales_Dashboard_Page(String n) {
        setTitle("Sales Dashboard");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_SD = new JPanel();
        manager_SD.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_SD);
        manager_SD.setLayout(null);
        manager_SD.setBackground(new Color(248, 248, 248));

        Manager man_data = new Manager();

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_SD.add(logo_lbl);

        // Logo Picture
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_SD.add(logo);

        // Title 
        JLabel title = new JLabel("Sales Dashboard");
        title.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        title.setBounds(400, 20, 300, 20);
        manager_SD.add(title);

        // Define the columns names
        String[] col_name = {"Booking ID", "Hall ID", "Num of Guests", "Start Date", "End Date", "Book Status", "Booking Paid", "Deposit Paid", "Booking Date", "Username"};

        // Define the data for the table
        // Object [][] represents whole table
        Object[][] row_Data = man_data.present_data("resources/Database/bookings.txt");

        tm = new DefaultTableModel(row_Data, col_name);
        JTable view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_SD.add(scrollPane);

        // Start Date label
        JLabel day_lbl = new JLabel("Start Date :");
        day_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        day_lbl.setBounds(80, 80, 90, 20);
        manager_SD.add(day_lbl);

        // End Date label
        JLabel end_date = new JLabel("End Date:");
        end_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        end_date.setBounds(400, 80, 90, 20);
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
        start_datePicker.setBounds(170, 80, 170, 30);
        manager_SD.add(start_datePicker);

        // End Date Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        Properties end_p = new Properties();
        end_p.put("text.day", "Day");
        end_p.put("text.month", "Month");
        end_p.put("text.year", "Year");
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, end_p);
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat());
        end_datePicker.setBounds(480, 80, 170, 30);
        manager_SD.add(end_datePicker);

        //Add the total from the program 
        double totalPaid = man_data.calculate_total_paid("resources/Database/bookings.txt");
        tp = totalPaid;

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filteredDate = man_data.date_read(start_datePicker, end_datePicker, "resources/Database/bookings.txt");
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

                Double totalPaid = man_data.paid_total(filteredDate, dateFormat, start_datePicker, end_datePicker);
                if (totalPaid != null) {
                    paid.setText(String.valueOf(totalPaid));
                } else {
                    paid.setText("0.00");
                }

//                tp = man_data.paid_total(filteredDate, dateFormat, start_datePicker, end_datePicker);
            }
        };

        start_datePicker.addActionListener(actionListener);
        end_datePicker.addActionListener(actionListener);

        // Refresh Button
        JButton refresh = new JButton("Refresh");
        refresh.setBackground(new Color(250, 240, 230));
        refresh.setForeground(new Color(128, 128, 128));
        refresh.setBounds(720, 83, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_SD.remove(scrollPane);
                Object[][] row_Data = man_data.present_data("resources/Database/bookings.txt");

                tm.setDataVector(row_Data, col_name);
                JTable table_tm = new JTable(tm);

                scrollPane = new JScrollPane(table_tm);

                scrollPane.setBounds(9, 125, 970, 400);
                manager_SD.add(scrollPane);

                double totalPaid = man_data.calculate_total_paid("resources/Database/bookings.txt");
                paid.setText(String.format("%.2f", totalPaid));

                table_tm.revalidate(); // important, recalculate the layout / resize the panel?
                table_tm.repaint(); // important, rearranging components

            }
        });
        manager_SD.add(refresh);

        // Total Label
        JLabel tt_lbl = new JLabel("Total:");
        tt_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        tt_lbl.setBounds(700, 575, 110, 20);
        manager_SD.add(tt_lbl);

        // Total Show Label
        //Double totalPaid = man_data.paid_total(filteredDate);
        paid = new JLabel(String.valueOf(tp));
        paid.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        paid.setBounds(800, 575, 110, 20);

        manager_SD.add(paid);

        //Back Page Pic
        JLabel back_lbl = new JLabel();
        try {

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/logout.png"));

            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);

        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Manager_Home_Page(n).setVisible(true);         
            }

        });
        manager_SD.add(back_lbl);

        //Design 4 Background Pic
        JLabel des4 = new JLabel();
        try {

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_SD.add(des4);

    }

}
