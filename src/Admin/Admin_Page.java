package src.Admin;

import src.Login_Page;
import src.UpdateProfile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Admin_Page extends JFrame {
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                try {
                    new Admin_Page(name).setVisible(true);
       
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Admin_Page(String name) {
        setTitle("Admin");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,248));
        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(name);

        //Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        //Logo Pic
        JLabel logo = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));

            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(logo);

        //Profile Pic
        JLabel profile = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);

            get_image = ImageIO.read(new File("resources\\Image\\meiya.png"));

            Image image = get_image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

            profile.setIcon(new ImageIcon(image));
            profile.setBounds(75, 50, 1000, 200);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(profile);

        //Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        greet_lbl.setBounds(70,210,200,30);
        contentPane.add(greet_lbl);

        //Username Label
        JLabel user_lbl = new JLabel(user[0]);
        user_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        user_lbl.setBounds(90,240,200,30);
        contentPane.add(user_lbl);

        //Setting Pic
        JLabel setting = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\setting.png"));

            Image image = get_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

            setting.setIcon(new ImageIcon(image));
            setting.setBounds(40, 290, 30, 30);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(setting);

        //Notification Pic
        JLabel noti= new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\notification.png"));

            Image image = get_image.getScaledInstance(43, 43, Image.SCALE_SMOOTH);

            noti.setIcon(new ImageIcon(image));
            noti.setBounds(100, 285, 38, 38);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(noti);

        //Mail Pic
        JLabel email = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\email.png"));

            Image image = get_image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);

            email.setIcon(new ImageIcon(image));
            email.setBounds(160, 280, 55, 55);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(email);
        email.setCursor(new Cursor(Cursor.HAND_CURSOR));
        email.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                do {
                    JOptionPane.showMessageDialog(null,"You have been hacked! Give us 4.0 to resolve! Thank You!","Hacked Notification",JOptionPane.OK_CANCEL_OPTION);
                        System.out.println("Prompting continue.");
                } while (true);      
            }
        });
        
        //Side Pic
        JLabel side = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\side.png"));

            Image image = get_image.getScaledInstance(400, 800, Image.SCALE_SMOOTH);

            side.setIcon(new ImageIcon(image));
            side.setBounds(0, 0, 400, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(side);

        //Home Page Label
        JLabel adhome_lbl = new JLabel("Admin Home Page");
        adhome_lbl.setFont(new Font("Engravers MT",Font.PLAIN,20));
        adhome_lbl.setBounds(480,200,300,30);
        contentPane.add(adhome_lbl);

        //View Profile Button
        JButton viewpro_btn = new JButton("View Profile");
        viewpro_btn.setBackground(new Color(250,240,230));
        viewpro_btn.setForeground(new Color(128,128,128));
        viewpro_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        viewpro_btn.setBounds(500,280,200,30);
        viewpro_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_Profile_Page ap = new Admin_Profile_Page(name);
                ap.setTitle("Admin Profile");
                ap.setVisible(true);
            }
        });
        contentPane.add(viewpro_btn);

        //Staff Management Button
        JButton staffman_btn = new JButton("Staff Management");
        staffman_btn.setBackground(new Color(250,240,230));
        staffman_btn.setForeground(new Color(128,128,128));
        staffman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffman_btn.setBounds(500,320,200,30);
        staffman_btn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                dispose();
                Staff_Management_Page sm = new Staff_Management_Page(name);
                sm.setTitle("Staff Management");
                sm.setVisible(true);
             }
             
        });
        contentPane.add(staffman_btn);

        //User Management Button
        JButton userman_btn = new JButton("User Management");
        userman_btn.setBackground(new Color(250,240,230));
        userman_btn.setForeground(new Color(128,128,128));
        userman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        userman_btn.setBounds(500,360,200,30);
        ////
        contentPane.add(userman_btn);

        //Booking Management Button
        JButton bookman_btn = new JButton("Booking Management");
        bookman_btn.setBackground(new Color(250,240,230));
        bookman_btn.setForeground(new Color(128,128,128));
        bookman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        bookman_btn.setBounds(500,400,200,30);
        //
        contentPane.add(bookman_btn);


        //Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground(new Color(250,240,230));
        logout_btn.setForeground(new Color(128,128,128));
        logout_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        logout_btn.setBounds(850,30,100,30);
        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login_Page log = new Login_Page();
                log.setTitle("Login");
                log.setVisible(true);
            }

        });
        contentPane.add(logout_btn);

    }
}
