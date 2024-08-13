package src.Manager;
import javax.swing.*;
import javax.swing.border.*;

import javax.imageio.ImageIO;

import src.Login_Page;
import src.UpdateProfile;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.*;




public class Manager_Home_Page extends JFrame{
    private static String manname;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Manager_Home_Page(manname).setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Manager_Home_Page(String n){
        setTitle("Manager Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_HP = new JPanel();
        manager_HP.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_HP);
        manager_HP.setLayout(null);
        manager_HP.setBackground(new Color(248, 248, 248));

        UpdateProfile searchUser = new UpdateProfile();
        String[] user = searchUser.search_user(n);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_HP.add(logo_lbl);

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
        manager_HP.add(logo);

        // image icon
        JLabel picture = new JLabel();
        try{
            BufferedImage image_icon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            image_icon = ImageIO.read(new File("resources/Image/little white dog.png"));

            Image imageIcon = image_icon.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            picture.setIcon(new ImageIcon(imageIcon));
            picture.setBounds(70, 60, 120, 120);

        }catch(IOException e){
            e.printStackTrace();
        }
        manager_HP.add(picture);



        // Name
        JLabel name_lbl = new JLabel(user[0]);
        name_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        name_lbl.setBounds(110, 180, 100, 30);
        manager_HP.add(name_lbl);

        // Welcome
        JLabel wel_lbl = new JLabel("Welcome Back !");
        wel_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        wel_lbl.setBounds(70, 210, 125, 25);
        manager_HP.add(wel_lbl);

        // Setting Picture
        JLabel setting = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/setting.png"));

            Image image = get_image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

            setting.setIcon(new ImageIcon(image));
            setting.setBounds(40, 290, 30, 30);

        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_HP.add(setting);

   
        //Notification Pic
        JLabel noti= new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/notification.png"));

            Image image = get_image.getScaledInstance(43, 43, Image.SCALE_SMOOTH);

            noti.setIcon(new ImageIcon(image));
            noti.setBounds(100, 285, 38, 38);

        } catch(IOException e){
            e.printStackTrace();
        }
        manager_HP.add(noti);


        // Mail Picture
        JLabel email = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/email.png"));
            Image image = get_image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            email.setIcon(new ImageIcon(image));
            email.setBounds(160, 280, 55, 55);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_HP.add(email);

        // Side Picture
        JLabel side = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/side.png"));
            Image image = get_image.getScaledInstance(400, 800, Image.SCALE_SMOOTH);
            side.setIcon(new ImageIcon(image));
            side.setBounds(0, 0, 400, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_HP.add(side);

        // Manager Home Page Label
        JLabel hp_lbl = new JLabel("Manager Home Page");
        hp_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hp_lbl.setBounds(400, 100, 200, 25);
        manager_HP.add(hp_lbl);

        // Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground((new Color(250, 240, 230)));
        logout_btn.setForeground(new Color(128, 128, 128));
        logout_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        logout_btn.setBounds(850, 30, 100, 30);
        // try{
        //     BufferedImage logout_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        //     logout_image = ImageIO.read(new File("resources/Image/logout.png"));

        //     Image log_icon = logout_image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        //     logout_btn.setIcon(new ImageIcon(log_icon));
        //     logout_btn.setBounds(160, 250, 40, 40);
        // }catch(IOException e){
        //     e.printStackTrace();
        // }

        logout_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login_Page logout = new Login_Page();
                logout.setTitle("Login");
                logout.setVisible(true);
            }
        });

        manager_HP.add(logout_btn);

        // View Profile Button
        JButton view_btn = new JButton("View Profile");
        view_btn.setBounds(400, 200, 200, 25);
        view_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new Manager_View_Profile(n).setVisible(true);
                new Manager_View_Profile_Page(n).setVisible(true);
                dispose();
            }
        });
        manager_HP.add(view_btn);

        // Sale Dashboard Button
        JButton sales_btn = new JButton("Sales Dashboard");
        //view_btn.setFont("Comic Sans MS", Font.PLAIN, 15);
        sales_btn.setBounds(400, 250, 200, 25);
        sales_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sales_Dashboard_Page(n).setVisible(true);
                dispose();
            }
        });
        manager_HP.add(sales_btn);

        // Booking Management Button
        JButton book_Man_btn = new JButton("Booking Management");
        //view_btn.setFont("Comic Sans MS", Font.PLAIN, 15);
        book_Man_btn.setBounds(400, 300, 200, 25);
        book_Man_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Booking_Management(n).setVisible(true);
                dispose();
            }
        });
        manager_HP.add(book_Man_btn);

        // Customer Issues Receive Button
        JButton cus_iss_rec_btn = new JButton("Customer Issues Receive");
        cus_iss_rec_btn.setBounds(400, 350, 200, 25);
        cus_iss_rec_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Customer_Issues_Receive(n).setVisible(true);
                
            }
        });
        manager_HP.add(cus_iss_rec_btn);

        // Task Assign and Status Button
        JButton task_ass_sta_btn = new JButton("Task Assign and Status");
        task_ass_sta_btn.setBounds(400, 400, 200, 25);
        task_ass_sta_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Task_Status(n).setVisible(true);
            }
        });
        manager_HP.add(task_ass_sta_btn);


    }

}
