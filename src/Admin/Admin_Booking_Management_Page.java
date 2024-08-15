package src.Admin;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Admin_Booking_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Admin_Booking_Management_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
    }

    public Admin_Booking_Management_Page(String name){
        setTitle("Booking Management Page");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,248));

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

        //Booking Management Label
        JLabel bookman_lbl = new JLabel("Booking Management");
        bookman_lbl.setFont(new Font("Engravers MT",Font.PLAIN,15));
        bookman_lbl.setBounds(390,100,300,30);
        contentPane.add(bookman_lbl);

        //Booking Data Table Showing
        Admin_Booking_Management bookman = new Admin_Booking_Management();
        String[] booking_table = {"Booking ID", "Hall ID", "Num of Guests", "Start Date","End Date", "Start Time", "End Time","Book Status","Booking Paid","Deposit Paid","Username"};
        scrollPane = bookman.view_booking(booking_table);
        scrollPane.setBounds(85,150,850,380);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(bookman.view_booking(booking_table));
        scrollPane.setPreferredSize(new Dimension(700,400));
        
        ////Filter Label
        JLabel filter_lbl = new JLabel("Filter by: ");
        filter_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        filter_lbl.setBounds(100,570,300,20);
        contentPane.add(filter_lbl);

        //Booking filter option combo box
        String[] filbook = {"Past Bookings", "Upcoming Bookings"};
        JComboBox<String> filbookOp = new JComboBox<>(filbook);
        filbookOp.setBounds(200,573,150,20);
        filbookOp.setBackground(new Color(250,240,230));
        contentPane.add(filbookOp);

        //Filter Button
        JButton filter_btn = new JButton("~Click Me To Filter by Booking~");
        filter_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
        filter_btn.setBounds(550,570,300,20);
        filter_btn.setBackground(new Color(250,240,230));
        filter_btn.setForeground(new Color(128,128,128));
        filter_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        contentPane.add(filter_btn);

        //Refresh Pic
        JLabel refresh = new JLabel();
        try{
 
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\refresh.png"));

            Image image = get_image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            refresh.setIcon(new ImageIcon(image));
            refresh.setBounds(360, 575, 20, 20);

        } catch(IOException e){
            e.printStackTrace();
        }
        refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scrollPane.setViewportView(bookman.view_booking(booking_table));
            }
        });
        contentPane.add(refresh);



        //Home Page Label
        JLabel back_lbl = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));

            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);

        } catch(IOException e){
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Home_back hb = new Home_back();
                String[] users = hb.home_back(name);

                if (users != null && users.length > 2){
                    String role = users[2];
                    if(role.equals("admin")){
                        dispose();
                        Admin_Page ad = new Admin_Page(name);
                        ad.setTitle("Admin Home Page");
                        ad.setVisible(true);
                    }
                    else if(role.equals("superadmin")){
                        dispose();
                        Suadmin_Page suad = new Suadmin_Page(name);
                        suad.setTitle("Super Admin Home Page");
                        suad.setVisible(true);
                    }
                    else{
                        System.out.println("Error Occured.");
                    }
                }              
               
            }
        });
        contentPane.add(back_lbl);

        //Design 4 Background Pic
        JLabel des4 = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(des4);
    }
    
}
