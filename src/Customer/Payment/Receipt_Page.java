package src.Customer.Payment;
import src.Customer.Hall_Booking.Hall_Booking_Page;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
import javax.swing.border.EmptyBorder;

public class Receipt_Page extends JFrame {
    public static String name;
    public static String[] select_data;
    public static LocalDate date;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Receipt_Page(name, select_data, date).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Receipt_Page(String n, String[] selected_data, LocalDate date) {
        setTitle("Receipt");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set Panel
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

        // Receipt Label
        JLabel receipt_lbl = new JLabel("Receipt");
        receipt_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        receipt_lbl.setBounds(400, 75, 250, 45);
        contentPane.add(receipt_lbl);

        // Name Label
        JLabel name_lbl = new JLabel("Name : " + n);
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        name_lbl.setBounds(100, 200, 200, 30);
        contentPane.add(name_lbl);

        // Book Hall ID Label
        JLabel hall_id_label = new JLabel("Hall ID : " + selected_data[0]);
        hall_id_label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hall_id_label.setBounds(100, 250, 200, 30);
        contentPane.add(hall_id_label);

        // Book Hall Type Label
        JLabel hall_type_lbl = new JLabel("Hall Type : " + selected_data[1]);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hall_type_lbl.setBounds(100, 300, 200, 30);
        contentPane.add(hall_type_lbl);

        // Number of hall Label
        JLabel hall_capacity_lbl = new JLabel("Capacity : " + selected_data[2]);
        hall_capacity_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hall_capacity_lbl.setBounds(100, 350, 200, 30);
        contentPane.add(hall_capacity_lbl);

        // Start Date Label
        JLabel start_date_lbl = new JLabel("Start Date : " + selected_data[4]);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        start_date_lbl.setBounds(100, 400, 250, 30);
        contentPane.add(start_date_lbl);

        // End Date Label
        JLabel end_date_lbl = new JLabel("End Date : " + selected_data[5]);
        end_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        end_date_lbl.setBounds(100, 450, 250, 20);
        contentPane.add(end_date_lbl);

        // Book Time Label
        JLabel book_time_lbl = new JLabel("Book time : " + date);
        book_time_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        book_time_lbl.setBounds(100, 500, 250, 20);
        contentPane.add(book_time_lbl);

        // Done Button
        JButton done_btn = new JButton("Done");
        done_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        done_btn.setBounds(100, 550, 200, 20);
        contentPane.add(done_btn);

        done_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Hall_Booking_Page(n).setVisible(true);
            }
        });
    }
}
