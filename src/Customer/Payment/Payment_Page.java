package src.Customer.Payment;

import src.Customer.Hall_Booking.Hall_Booking_Page;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Payment_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Payment_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Payment_Page(String n) {
        setTitle("Payment");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Create a panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
        JLabel hall_type_lbl = new JLabel("Hall Type : ");
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hall_type_lbl.setBounds(100, 200, 150, 30);
        contentPane.add(hall_type_lbl);

        // Number of guest
        JLabel guest_num_lbl = new JLabel("Number of guest : ");
        guest_num_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        guest_num_lbl.setBounds(100, 300, 200, 30);
        contentPane.add(guest_num_lbl);

        // Price per day
        JLabel price_per_day_lbl = new JLabel("Price per day : ");
        price_per_day_lbl.setBounds(getBounds());

        // Start Date
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        start_date_lbl.setBounds(400, 200, 150, 30);
        contentPane.add(start_date_lbl);

        // End Date
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        end_date_lbl.setBounds(600, 200, 150, 30);
        contentPane.add(end_date_lbl);

        // Rent days
        JLabel rent_days_lbl = new JLabel("Rent Days : ");
        rent_days_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        rent_days_lbl.setBounds(600, 400, 150, 30);
        contentPane.add(rent_days_lbl);

        // Total price
        JLabel total_price_lbl = new JLabel("Total Price : ");
        total_price_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        total_price_lbl.setBounds(600, 500, 150, 30);
        contentPane.add(total_price_lbl);

        // Pay button
        JButton pay_btn = new JButton("Pay");
        pay_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        pay_btn.setBounds(200, 700, 100, 30);
        pay_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
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
                new Hall_Booking_Page(name).setVisible(true);
            }

        });
        contentPane.add(back_lbl);
    }
}
