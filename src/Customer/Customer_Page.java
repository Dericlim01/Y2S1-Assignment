package src.Customer;
import src.Login_Page;
import src.Customer.Hall_Booking.Hall_Booking_Page;
import src.Customer.Booking_Info.Booking_Info_Page;
import src.Customer.Update_Profile.Update_Profile;
import src.Customer.Raise_Issue.Raise_Issue_Page;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

public class Customer_Page extends JFrame {
    private static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Customer_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Customer_Page(String n) {
        setTitle("Customer");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248,248,248));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Logo Label
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

        // Profile Pic
        JLabel profile = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);

            get_image = ImageIO.read(new File("resources\\Image\\labixiaoxin(red).png"));

            Image image = get_image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

            profile.setIcon(new ImageIcon(image));
            profile.setBounds(75, 50, 1000, 200);

        } catch(IOException e) {
            e.printStackTrace();
        }
        contentPane.add(profile);

        // Name Label
        JLabel name_lbl = new JLabel(n);
        name_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        name_lbl.setBounds(90, 240, 200, 30);
        contentPane.add(name_lbl);

        // Welcome back
        JLabel welcome_lbl = new JLabel("Welcome Back !");
        welcome_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        welcome_lbl.setBounds(70,210,200,30);
        contentPane.add(welcome_lbl);

        // Setting Pic
        JLabel setting = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\setting.png"));
            Image image = get_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            setting.setIcon(new ImageIcon(image));
            setting.setBounds(40, 290, 30, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(setting);

        // Notification Pic
        JLabel noti= new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\notification.png"));
            Image image = get_image.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
            noti.setIcon(new ImageIcon(image));
            noti.setBounds(100, 285, 38, 38);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(noti);

        // Mail Pic
        JLabel email = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\email.png"));
            Image image = get_image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            email.setIcon(new ImageIcon(image));
            email.setBounds(160, 280, 55, 55);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(email);
        email.setCursor(new Cursor(Cursor.HAND_CURSOR));
        email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                do {
                    JOptionPane.showMessageDialog(
                        null,
                        "You have been hacked! Give us 4.0 to resolve! Thank You!",
                        "Hacked Notification",
                        JOptionPane.OK_CANCEL_OPTION);
                        System.out.println("Prompting continue.");
                } while (true);      
            }
        });
        
        // Side Pic
        JLabel side = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\side.png"));
            Image image = get_image.getScaledInstance(400, 800, Image.SCALE_SMOOTH);
            side.setIcon(new ImageIcon(image));
            side.setBounds(0, 0, 400, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(side);

        // Customer title
        JLabel cus_page_title = new JLabel("Customer Home Page");
        cus_page_title.setFont(new Font("Engravers MT", Font.PLAIN, 15));
        cus_page_title.setBounds(480,200,300,30);
        contentPane.add(cus_page_title);

        // Update Profile button
        JButton update_p_btn = new JButton("Update Profile");
        update_p_btn.setBackground(new Color(250,240,230));
        update_p_btn.setForeground(new Color(128,128,128));
        update_p_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        update_p_btn.setBounds(500,280,200,30);
        update_p_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Update_Profile(n).setVisible(true);
            }
        });
        contentPane.add(update_p_btn);

        // Hall Booking Button
        JButton book_hall_btn = new JButton("Hall Booking");
        book_hall_btn.setBackground(new Color(250,240,230));
        book_hall_btn.setForeground(new Color(128,128,128));
        book_hall_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        book_hall_btn.setBounds(500,320,200,30);
        book_hall_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Hall_Booking_Page(n).setVisible(true);
            }
        });
        contentPane.add(book_hall_btn);

        // Booking Information
        JButton book_info_btn = new JButton("Book Information");
        book_info_btn.setBackground(new Color(250,240,230));
        book_info_btn.setForeground(new Color(128,128,128));
        book_info_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        book_info_btn.setBounds(500,360,200,30);
        book_info_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Booking_Info_Page(n).setVisible(true);
            }
        });
        contentPane.add(book_info_btn);

        // Issue Rasing
        JButton issue_btn = new JButton("Issue Raising");
        issue_btn.setBackground(new Color(250,240,230));
        issue_btn.setForeground(new Color(128,128,128));
        issue_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        issue_btn.setBounds(500,400,200,30);
        issue_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Raise_Issue_Page(n).setVisible(true);
            }
        });
        contentPane.add(issue_btn);

        // Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground(new Color(250,240,230));
        logout_btn.setForeground(new Color(128,128,128));
        logout_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        logout_btn.setBounds(850,30,100,30);
        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        contentPane.add(logout_btn);
    }
}
