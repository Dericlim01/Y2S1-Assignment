package src.Customer.Booking_Info;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Booking_Info_Page extends JFrame {
    public static String name;
    public static LocalDate current_date = LocalDate.now();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Booking_Info_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Booking_Info_Page(String n) {
        setTitle("Booking Info");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set Panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        //Logo Pic
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

        // Upcoming Label
        JLabel upcome_lbl = new JLabel("Upcoming Event : ");
        upcome_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        upcome_lbl.setBounds(getBounds());
        contentPane.add(upcome_lbl);

        // Upcoming Table
        String[] col_name = {"Hall ID", "Hall Type", "Capacity", "Price per hour", "Price per day", "Status"};
        Object[][] upcome_data = new Booking_Info().upcome_data(current_date);
        DefaultTableModel upcome_table = new DefaultTableModel(upcome_data, col_name);
        JTable upcome_details = new JTable(upcome_table);
        JScrollPane upcome_scrollPane = new JScrollPane(upcome_details);
        upcome_scrollPane.setBounds(200, 300, 600, 400);
        contentPane.add(upcome_scrollPane);

        // Past Label
        JLabel past_lbl = new JLabel("Upcoming Event : ");
        past_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        past_lbl.setBounds(getBounds());
        contentPane.add(past_lbl);

        Object[][] past_data = new Booking_Info().past_data(current_date);
        DefaultTableModel past_table = new DefaultTableModel(past_data, col_name);
        JTable past_details = new JTable(past_table);
        JScrollPane past_scrollPane = new JScrollPane(past_details);
        past_scrollPane.setBounds(200, 300, 600, 400);
        contentPane.add(past_scrollPane);

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
