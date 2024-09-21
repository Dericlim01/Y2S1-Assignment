package src.Customer.Payment;

import src.Customer.Hall_Booking.Hall_Booking_Page;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Payment_Page extends JFrame {
    public static String name;
    public static String[] data;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Payment_Page(name, data).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Payment_Page(String n, String[] selected_data) {
        setTitle("Payment");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Create a panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248,248,255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        // Logo Pic
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(logo);

        // Payment Details
        JLabel pymt_det_lbl = new JLabel("Payment Details");
        pymt_det_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        pymt_det_lbl.setBounds(375, 75, 250, 45);
        contentPane.add(pymt_det_lbl);

        // Hall Type
        JLabel hall_type_lbl = new JLabel("Hall Type : " + selected_data[1]);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hall_type_lbl.setBounds(100, 200, 250, 30);
        contentPane.add(hall_type_lbl);

        // Number of guest
        JLabel guest_num_lbl = new JLabel("Number of guest : " + selected_data[2]);
        guest_num_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        guest_num_lbl.setBounds(100, 300, 250, 30);
        contentPane.add(guest_num_lbl);

        // Price per day
        JLabel price_per_day_lbl = new JLabel("Price per hour : " + selected_data[3]);
        price_per_day_lbl.setBounds(400, 300, 50, 30);

        // Start Date
        JLabel start_date_lbl = new JLabel("Start Date : " + selected_data[4]);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        start_date_lbl.setBounds(400, 200, 250, 30);
        contentPane.add(start_date_lbl);

        // End Date
        JLabel end_date_lbl = new JLabel("End Date : " + selected_data[5]);
        end_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        end_date_lbl.setBounds(650, 200, 250, 30);
        contentPane.add(end_date_lbl);

        // Rent days
        String hour_price[] = new Payment().calculate_price_hour(selected_data[3], selected_data[4], selected_data[5]);
        JLabel rent_days_lbl = new JLabel("Total Rent Days : " + hour_price[0]);
        rent_days_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        rent_days_lbl.setBounds(600, 400, 250, 30);
        contentPane.add(rent_days_lbl);

        // Total price
        JLabel total_price_lbl = new JLabel("Total Price : RM " + hour_price[1]);
        total_price_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        total_price_lbl.setBounds(600, 500, 250, 30);
        contentPane.add(total_price_lbl);

        // Pay button
        JButton pay_btn = new JButton("Pay");
        pay_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        pay_btn.setBounds(200, 700, 100, 30);
        pay_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Confirm Payment?", 
                    "Payment", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null);
                if (response == 0) {
                    // Write to booking, proceed to payment
                    LocalDate date = LocalDate.now();
                    int status = new Payment().confirm_booking(n, selected_data, hour_price[1], date);
                    if (status == 1) {
                        // Booked succussfully
                        JOptionPane.showMessageDialog(
                            null, 
                            "Book Successfully", 
                            "Book Status", 
                            JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        new Receipt_Page(n, selected_data, date).setVisible(true);
                    } else {
                        // Booked failed
                        JOptionPane.showMessageDialog(
                            null, 
                            "Book Failed", 
                            "Book Status", 
                            JOptionPane.PLAIN_MESSAGE);
                        new Hall_Booking_Page(n).setVisible(true);
                    }
                } else {
                    // Close Confirm Dialog and remain
                    System.out.println("Payment not confirm");
                }
            }
        });
        contentPane.add(pay_btn);

        // Back Page Pic
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
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
                new Hall_Booking_Page(n).setVisible(true);
            }

        });
        contentPane.add(back_lbl);

        // Design 4 Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des4);
    }
}
