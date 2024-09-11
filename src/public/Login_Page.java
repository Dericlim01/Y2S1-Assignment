import src.Admin.Admin_Page;
import src.Admin.Suadmin_Page;
import src.Customer.Customer_Page;
import src.Manager.Manager_Home_Page;
import src.Scheduler.Scheduler_Main_Page;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Login_Page
 */
public class Login_Page extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login_Page().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login_Page() {
        setTitle("Login");
        // Set the app Icon
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

        // Advertisement Label
        JLabel ad_lbl = new JLabel("Find Your");
        ad_lbl.setFont(new Font("MV Boli",Font.BOLD,25));
        ad_lbl.setBounds(60,110,160,30);
        contentPane.add(ad_lbl);

        // Advertisement Label 2
        JLabel ad2_lbl = new JLabel("Best Hall Today!");
        ad2_lbl.setFont(new Font("MV Boli",Font.BOLD,25));
        ad2_lbl.setBounds(140,170,250,30);
        contentPane.add(ad2_lbl);

        // Graphic Hall Pic
        JLabel graphicHall = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\graphichall.png"));
            Image image = get_image.getScaledInstance(300, 350, Image.SCALE_SMOOTH);
            graphicHall.setIcon(new ImageIcon(image));
            graphicHall.setBounds(60, 230, 300, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(graphicHall);

        // Graphic Border Pic
        JLabel graphicBorder = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\graphicBorder.png"));
            Image image = get_image.getScaledInstance(400, 800, Image.SCALE_SMOOTH);
            graphicBorder.setIcon(new ImageIcon(image));
            graphicBorder.setBounds(10, 0, 400, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(graphicBorder);

        // Login Label
        JLabel login_lbl = new JLabel("Login to Your Account");
        login_lbl.setFont(new Font("Engravers MT", Font.PLAIN, 15));
        login_lbl.setBounds(560, 180, 400, 30);
        contentPane.add(login_lbl);

        // Name Label
        JLabel name_lbl = new JLabel("Name : ");
        name_lbl.setBackground(new Color(255, 255, 0));
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_lbl.setBounds(480, 250, 100, 30);
        contentPane.add(name_lbl);

        // Name Text Field
        JTextField name_txt_f = new JTextField();
        name_txt_f.setBounds(570, 250, 250, 30);
        contentPane.add(name_txt_f);

        // Password Label
        JLabel pass_lbl = new JLabel("Password : ");
        pass_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pass_lbl.setBounds(480, 310, 100, 30);
        contentPane.add(pass_lbl);

        // Password Text Field
        JPasswordField pass_txt_f = new JPasswordField();
        pass_txt_f.setBounds(570, 310, 250, 30);
        pass_txt_f.setEchoChar('*');
        contentPane.add(pass_txt_f);

        name_txt_f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = name_txt_f.getText();
                    String pass = String.valueOf(pass_txt_f.getPassword());
                    page_nav(name, pass);
                }
            }
        });

        pass_txt_f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = name_txt_f.getText();
                    String pass = String.valueOf(pass_txt_f.getPassword());
                    page_nav(name, pass);
                }
            }
        });

        // Show password check box
        JCheckBox show_pass = new JCheckBox();
        show_pass.setBounds(830, 315, 20, 20);
        show_pass.setSelected(false);
        show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show_pass.isSelected()) {
                    pass_txt_f.setEchoChar((char) 0);
                } else {
                    pass_txt_f.setEchoChar('*');
                }
            }
        });
        contentPane.add(show_pass);

        // Register Label
        JLabel reg_lbl = new JLabel("<html><u>Haven't have an account? Click here to register!</u></html>");
        reg_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        reg_lbl.setForeground(new Color(128,128,128));
        reg_lbl.setBounds(550, 460, 400, 30);
        reg_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reg_lbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new Register_Page().setVisible(true);
            };
            
        });
        contentPane.add(reg_lbl);

        // Login Button
        JButton login_btn = new JButton("Login");
        login_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        login_btn.setBackground(new Color(250,240,230));
        login_btn.setForeground(new Color(128,128,128));
        login_btn.setBounds(640, 380, 100, 30);
        login_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = name_txt_f.getText();
                String pass = String.valueOf(pass_txt_f.getPassword());
                page_nav(name, pass);
            }
        });
        contentPane.add(login_btn);

        login_btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = name_txt_f.getText();
                    String pass = String.valueOf(pass_txt_f.getPassword());
                    page_nav(name, pass);
                }
            }
        });

        // Design Pic 1
        JLabel des1 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design1.png"));
            Image image = get_image.getScaledInstance(300, 280, Image.SCALE_SMOOTH);
            des1.setIcon(new ImageIcon(image));
            des1.setBounds(780, 0, 250, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des1);

        // Design Pic 2
        JLabel des2 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design2.png"));
            Image image = get_image.getScaledInstance(300, 280, Image.SCALE_SMOOTH);
            des2.setIcon(new ImageIcon(image));
            des2.setBounds(310,580, 250, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(des2);
    }

    private void page_nav(String name, String pass) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "Username or password cannot be empty", 
                "Login status", 
                JOptionPane.INFORMATION_MESSAGE);
        } else if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "Username or password cannot be empty", 
                "Login status", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            String role = new Login().login(name, pass);
            JFrame nextPage = null;
            switch (role) {
                case "customer":
                    nextPage = new Customer_Page(name);
                    break;
                case "superadmin":
                    nextPage = new Suadmin_Page(name);
                    break;
                case "admin":
                    nextPage = new Admin_Page(name);
                    break;
                case "manager":
                    nextPage = new Manager_Home_Page(name);
                    break;
                case "scheduler":
                    nextPage = new Scheduler_Main_Page(name);
                    break;
                default:
                    // Login failed, show message box
                    JOptionPane.showMessageDialog(
                        null,
                        "Login Failed",
                        "Login Status",
                        JOptionPane.PLAIN_MESSAGE);
                    return;
            }
            dispose();
            if (nextPage != null) {
                nextPage.setVisible(true);
            }
        }
    }
}