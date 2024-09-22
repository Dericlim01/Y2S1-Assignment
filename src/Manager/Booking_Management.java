package src.Manager;

import src.shared.DateFormat;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.imageio.*;

public class Booking_Management extends JFrame {

    private static String manname;
    private static JScrollPane scrollPane;
    private DefaultTableModel tm;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Booking_Management(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Booking_Management(String n) {
        setTitle("Booking Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_BM = new JPanel();
        manager_BM.setBorder(new EmptyBorder(5, 5, 5, 5));
        manager_BM.setBackground(new Color(248,248,248));
        setContentPane(manager_BM);
        manager_BM.setLayout(null);

        Manager man_booking = new Manager();

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_BM.add(logo_lbl);

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
        manager_BM.add(logo);

        // Title 
        JLabel title = new JLabel("Booking Management");
        title.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        title.setBounds(350, 20, 400, 20);
        manager_BM.add(title);

        String[] col_name = {"Booking ID", "Hall ID", "Num of Guests", "Start Date", "End Date", "Book Status", "Booking Paid", "Deposit Paid", "Booking Date", "Username"};

        Object[][] row_data = man_booking.present_data("resources/Database/bookings.txt");

        tm = new DefaultTableModel(row_data, col_name);
        JTable view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_BM.add(scrollPane);

        // Start date label
        JLabel start_date = new JLabel("Start Date:");
        start_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        start_date.setBounds(80, 80, 90, 20);
        manager_BM.add(start_date);

        // End date label
        JLabel end_date = new JLabel("End Date:");
        end_date.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        end_date.setBounds(400, 80, 90, 20);
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
        start_datePicker.setBounds(170, 80, 170, 30);
        manager_BM.add(start_datePicker);

        // End Date Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        Properties end_p = new Properties();
        end_p.put("text.day", "Day");
        end_p.put("text.month", "Month");
        end_p.put("text.year", "Year");
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, start_p);
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat());
        end_datePicker.setBounds(480, 80, 170, 30);
        manager_BM.add(end_datePicker);

        // Do a filter data action according date selected 
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> filteredDate = man_booking.date_book_read(start_datePicker, end_datePicker, "resources/Database/bookings.txt");
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
        refresh.setBackground(new Color(250, 240, 230));
        refresh.setForeground(new Color(128, 128, 128));
        refresh.setBounds(720, 83, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] row_data = man_booking.present_data("resources/Database/bookings.txt");

                System.out.println("Refreshed data rows: " + row_data.length);
                for (Object[] row : row_data) {
                    System.out.println(Arrays.toString(row));
                }

                tm.setDataVector(row_data, col_name);
                //(Come from chatgpt) 
                tm.fireTableDataChanged();
                view.revalidate();
                view.repaint();
            }
        });

        manager_BM.add(refresh);

        // Cancel Booking
        List<String> book = new ArrayList<>();
        JButton cancel = new JButton("Cancel Booking");
        cancel.setBackground(new Color(250, 240, 230));
        cancel.setForeground(new Color(128, 128, 128));
        cancel.setBounds(700, 600, 150, 20);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();

                if (selectedRow >= 0) {
                    int col_count = view.getColumnCount();
                    if (col_count >= 1) {

                        for (int i = 0; i < col_count; i++) {
                            book.add(view.getValueAt(selectedRow, i).toString());
                        }

                        man_booking.delete_booking(book);

                        tm.removeRow(selectedRow);

                        book.clear();

                        view.revalidate();
                        view.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid row data");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select the row");
                }
            }
        });
        manager_BM.add(cancel);

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
        manager_BM.add(back_lbl);

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
        manager_BM.add(des4);

    }
}
