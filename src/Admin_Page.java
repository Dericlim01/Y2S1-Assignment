package src;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Admin_Page extends JFrame {
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                try {
                    Admin_Page ad = new Admin_Page(name);
                    ad.setVisible(true);
                    ad.getContentPane().setBackground(new Color(230,220,202));
       
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }

    public Admin_Page(String name){
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);
  

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Home Page Label
        JLabel adhome_lbl = new JLabel("Admin Home Page");
        adhome_lbl.setFont(new Font("Broadway",Font.PLAIN,20));
        adhome_lbl.setBounds(400,100,300,30);
        contentPane.add(adhome_lbl);

        //View Profile Button
        JButton viewpro_btn = new JButton("View Profile");
        viewpro_btn.setBackground(new Color(255,239,222));
        viewpro_btn.setForeground(new Color(88,57,39));
        viewpro_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        viewpro_btn.setBounds(400,200,200,30);
        viewpro_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Admin_Profile ap = new Admin_Profile(name);
                ap.setTitle("Admin Profile");
                ap.setVisible(true);
            }
        });
        contentPane.add(viewpro_btn);

        //Staff Management Button
        JButton staffman_btn = new JButton("Staff Management");
        staffman_btn.setBackground(new Color(255,239,222));
        staffman_btn.setForeground(new Color(88,57,39));
        staffman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        staffman_btn.setBounds(400,240,200,30);
        staffman_btn.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e){
                dispose();
                // String role = "staffs";
                Staff_Management_Page sm = new Staff_Management_Page(name);
                sm.setTitle("Staff Management");
                sm.setVisible(true);
             }
             
        });
        contentPane.add(staffman_btn);

        //User Management Button
        JButton userman_btn = new JButton("User Management");
        userman_btn.setBackground(new Color(255,239,222));
        userman_btn.setForeground(new Color(88,57,39));
        userman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        userman_btn.setBounds(400,280,200,30);
        ////
        contentPane.add(userman_btn);

        //Booking Management Button
        JButton bookman_btn = new JButton("Booking Management");
        bookman_btn.setBackground(new Color(255,239,222));
        bookman_btn.setForeground(new Color(88,57,39));
        bookman_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        bookman_btn.setBounds(400,320,200,30);
        //
        contentPane.add(bookman_btn);


        //Logout Button
        JButton logout_btn = new JButton("Logout");
        logout_btn.setBackground(new Color(255,239,222));
        logout_btn.setForeground(new Color(88,57,39));
        logout_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        logout_btn.setBounds(850,30,100,30);
        logout_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Login_Page log = new Login_Page();
                log.setTitle("Login");
                log.setVisible(true);
            }

        });
        contentPane.add(logout_btn);

    }
}
