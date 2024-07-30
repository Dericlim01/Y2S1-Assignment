import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;

import java.awt.Font;
import java.awt.Image;
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

        // image icon
        JLabel picture = new JLabel();
        try{
            BufferedImage image_icon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            image_icon = ImageIO.read(new File("D:/sem 1/Java/test/Manager/little white dog.png"));

            Image imageIcon = image_icon.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            picture.setIcon(new ImageIcon(imageIcon));
            picture.setBounds(70, 60, 120, 120);

        }catch(IOException e){
            e.printStackTrace();
        }
        manager_HP.add(picture);

        // Name
        JLabel name_lbl = new JLabel("HI");
        name_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        name_lbl.setBounds(110, 180, 100, 30);
        manager_HP.add(name_lbl);

        // Welcome
        JLabel wel_lbl = new JLabel("Welcome Back !");
        wel_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        wel_lbl.setBounds(70, 210, 125, 25);
        manager_HP.add(wel_lbl);

        // Manager Home Page Label
        JLabel hp_lbl = new JLabel("Manager Home Page");
        hp_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hp_lbl.setBounds(400, 100, 200, 25);
        manager_HP.add(hp_lbl);

        // Logout Button
        JButton logout_btn = new JButton();
        try{
            BufferedImage logout_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            logout_image = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));

            Image log_icon = logout_image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            logout_btn.setIcon(new ImageIcon(log_icon));
            logout_btn.setBounds(160, 250, 40, 40);
        }catch(IOException e){
            e.printStackTrace();
        }

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
                Manager_View_Profile man_VP = new Manager_View_Profile(n);
                man_VP.setTitle("Manager View Profile");
                man_VP.setVisible(true);
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
                Sales_Dashboard sales = new Sales_Dashboard(n);
                sales.setTitle("Sales Dashboard");
                sales.setVisible(true);
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
                Booking_Management man_BM = new Booking_Management(n);
                man_BM.setTitle("Booking Management");
                man_BM.setVisible(true);
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
                Task_Assign_and_Status man_TAS = new Task_Assign_and_Status(n);
                man_TAS.setTitle("Task Assign and Status");
            }
        });
        manager_HP.add(task_ass_sta_btn);

        // Design 
        //Character gradient = new Character('linear-gradient(to right, #1f1c17, #dacfb7, #ded7c6)');
        // 

        // 

        // // Design
        // JLabel design_lbl = new JLabel();
        // design_lbl.setBounds(100, 100, 150, 500);

        // Graphics2D g2d = (Graphics2D) 

        // Color[] colors = {startColor, endColor};
        // float[] fractions = {0f, 1f};
        // LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0, design_lbl.getHeight(), fractions, colors);
        // design_lbl.setPaint(paint);

    }

//    private BufferedImage createCircularImage(BufferedImage newImage, int size){
//     int diameter = Math.min(newImage.getWidth(), newImage.getHeight());
//     BufferedImage circle_Image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

//     Graphics2D g2 = circle_Image.createGraphics();
//     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // ini apa?
//     g2.setClip(new Ellipse2D.Double(0, 0, diameter, diameter));

//     int xSet = (diameter - newImage.getWidth()) / 2;
//     int ySet = (diameter - newImage.getHeight()) / 2;
//     g2.drawImage(circle_Image, xSet, ySet, null);
//     g2.dispose();

//     return circle_Image;
//    }
}
