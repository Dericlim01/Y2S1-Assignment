package src;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;


public class Staff_Management_Page extends JFrame {
    private JPanel contentPane;
    private static String name;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    Staff_Management_Page sm = new Staff_Management_Page(name);
                    sm.setTitle("Staff Management");
                    sm.setVisible(true);
                    sm.getContentPane().setBackground(new Color(230,220,202));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Staff_Management_Page(String name){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Staff Management Page Label
        JLabel staffman_lbl = new JLabel("Staff Management");
        staffman_lbl.setFont(new Font("Broadway",Font.PLAIN,20));
        staffman_lbl.setBounds(400,100,300,30);
        contentPane.add(staffman_lbl);      

        //Add Staff Button
        JButton add_btn = new JButton("Add Staff");
        add_btn.setBounds(700,600,120,30);
        add_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Staff_Add_Page sad = new Staff_Add_Page(name);
                sad.setTitle("Staff Add");
                sad.setVisible(true);
            }
        });
        contentPane.add(add_btn);


        //Edit Page Button



        //View, Filter and Delete Page Button



             
        //Home Page Button
        JButton home_btn = new JButton();
        try{
            BufferedImage home_pic = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            home_pic = ImageIO.read(new File("C:\\Users\\ARELLAYIM\\Documents\\JAVA\\Assignment Pictures\\logout.png"));
            Image home_icon = home_pic.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            home_btn.setIcon(new ImageIcon(home_icon));
            home_btn.setBounds(920,30,40,40);

        }catch(IOException e){
            e.printStackTrace();
        }
        home_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                Home_back hb = new Home_back();
                String[] users = hb.home_back(name);

                if (users != null && users.length > 4){
                    String role = users[4];
                    if(role.equals("admin")){
                        Admin_Page ad = new Admin_Page(name);
                        //ad.setTitle("Admin");
                        ad.setVisible(true);
                    }
                    else if(role.equals("superadmin")){
                        Suadmin_Page suad = new Suadmin_Page(name);
                        //suad.setTitle("Super Admin");
                        suad.setVisible(true);
                    }
                    else{
                        System.out.println("Error Occured.");
                    }
                }              
               
            }
        });
        contentPane.add(home_btn);
    }
}
