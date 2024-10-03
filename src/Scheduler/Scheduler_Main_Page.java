package src.Scheduler;

import src.shared.Login_Page;
import src.Customer.Update_Profile.UpdateProfile;

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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

public class Scheduler_Main_Page extends JFrame {
    public static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Scheduler_Main_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Scheduler_Main_Page(String name) {
        setTitle("Scheduler Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(name);

        // Label
        JLabel titleLabel = new JLabel("Scheduler Main Page");
        titleLabel.setBounds(450, -20, 450, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.PLAIN,20));
        panel.add(titleLabel);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        panel.add(logo_lbl);

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
        panel.add(logo);

        // Profile Pic
        JLabel profile = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
            get_image = ImageIO.read(new File("resources\\Image\\fengjian.png"));
            Image image = get_image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            profile.setIcon(new ImageIcon(image));
            profile.setBounds(75, 50, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(profile);

        // Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        greet_lbl.setBounds(70,210,200,30);
        panel.add(greet_lbl);

        // Username Label
        JLabel user_lbl = new JLabel(user[0]);
        user_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        user_lbl.setBounds(90,240,200,30);
        panel.add(user_lbl);

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
        panel.add(setting);

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
        panel.add(noti);

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
        panel.add(email);
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
        panel.add(side);

        // Button
        JButton hallMngBtn = new JButton("Hall Management");
        hallMngBtn.setBounds(500, 200, 220, 45);
        hallMngBtn.setBackground(new Color(250,240,230));
        hallMngBtn.setForeground(new Color(128,128,128));
        hallMngBtn.setFont(new Font("Comic Sans MS",Font.PLAIN,17));
        panel.add(hallMngBtn);
        hallMngBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Hall_Management_Page(name).setVisible(true);
            }
        });

        JButton hallSchBtn = new JButton("Hall Scheduling");
        hallSchBtn.setBounds(500, 300, 220, 45);
        hallSchBtn.setBackground(new Color(250,240,230));
        hallSchBtn.setForeground(new Color(128,128,128));
        hallSchBtn.setFont(new Font("Comic Sans MS",Font.PLAIN,17));
        panel.add(hallSchBtn);
        hallSchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Schedule_Maintainance_Page(name).setVisible(true);
            }
        });

        JButton viewTaskButton = new JButton("View Task");
        viewTaskButton.setBounds(500, 400, 220, 45);
        viewTaskButton.setBackground(new Color(250,240,230));
        viewTaskButton.setForeground(new Color(128,128,128));
        viewTaskButton.setFont(new Font("Comic Sans MS",Font.PLAIN,17));
        panel.add(viewTaskButton);
        viewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Task_Checking_Page(name).setVisible(true);
            }
        });

        // View Profile Button
        JButton viewpro_btn = new JButton("View Profile");
        viewpro_btn.setBackground(new Color(250,240,230));
        viewpro_btn.setForeground(new Color(128,128,128));
        viewpro_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,17));
        viewpro_btn.setBounds(500,500,220,45);
        viewpro_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Scheduler_Update_Profile_Page(name).setVisible(true);
            }
        });
        panel.add(viewpro_btn);

        // Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground(new Color(250,240,230));
        logout_btn.setForeground(new Color(128,128,128));
        logout_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,17));
        logout_btn.setBounds(850,30,100,30);
        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_Page().setVisible(true);
            }
        });
        panel.add(logout_btn);
    }
}
