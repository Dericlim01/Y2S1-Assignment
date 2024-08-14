package src.Admin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.*;


public class Admin_Profile_Page extends JFrame {
    private JPanel contentPane;
    private static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new Admin_Profile_Page(name).setVisible(true);;               
       
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Admin_Profile_Page(String name) {
        setTitle("Admin Profile Page");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(248,248,255));
        Admin_Management search = new Admin_Management(name);
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
            profile.setBounds(450, 50, 1000, 200);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(profile);

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
                Admin_Page ad = new Admin_Page(name);
                ad.setTitle("Admin");
                ad.setVisible(true);
            }
        });
        contentPane.add(back_lbl);

        //Gradient Design
        JLabel gradient = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\gradient.png"));

            Image image = get_image.getScaledInstance(1000, 280, Image.SCALE_SMOOTH);

            gradient.setIcon(new ImageIcon(image));
            gradient.setBounds(0, 0, 1000, 200);

        } catch(IOException e){
            e.printStackTrace();
        }
        contentPane.add(gradient);

        //Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        greet_lbl.setBounds(450,210,200,30);
        contentPane.add(greet_lbl);

        //Username Label
        JLabel user_lbl = new JLabel(user[0]);
        user_lbl.setFont(new Font("Serif",Font.PLAIN,15));
        user_lbl.setBounds(470,240,200,30);
        contentPane.add(user_lbl);

        //Role label
        JLabel role_lbl = new JLabel("Your Role:");
        role_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        role_lbl.setBounds(360,300,200,30);
        contentPane.add(role_lbl);

        //Role Show Label
        JLabel rshow_lbl = new JLabel("Admin");
        rshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        rshow_lbl.setBounds(480,300,200,30);
        contentPane.add(rshow_lbl);

        //Password label
        JLabel pass_lbl = new JLabel("Password:");
        pass_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pass_lbl.setBounds(360,350,200,30);
        contentPane.add(pass_lbl);

        //Password Show Label
        JLabel pshow_lbl = new JLabel(user[1]);
        pshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pshow_lbl.setBounds(480,350,200,30);
        contentPane.add(pshow_lbl);

        //Password field
        JTextField pfield = new JTextField();
        pfield.setBounds(460,350,200,30);
   
        //Update Button
        JButton up_btn = new JButton("Update");
        up_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        up_btn.setBackground(new Color(250,240,230));
        up_btn.setForeground(new Color(128,128,128));
        up_btn.setBounds(430,480,150,30);
        up_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user_lbl.getText();
                String pass = pfield.getText();
                Admin_Management adman = new Admin_Management(name);          
                if(adman.update_user(username, pass)){
                    JOptionPane.showMessageDialog(null,"Update Successfully","Plain",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    //Update Failed
                    int response = JOptionPane.showConfirmDialog(null,"Update Failed, Do you want to Update Again?","Question",JOptionPane.YES_NO_CANCEL_OPTION);
                    if(response == 1){
                        new Admin_Profile_Page(name).setVisible(true);
                    }
                }
            }
        });
        contentPane.add(up_btn);
        up_btn.setVisible(false);

        //Edit Button
        JButton edit_btn = new JButton("Edit Password");
        edit_btn.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        edit_btn.setBackground(new Color(250,240,230));
        edit_btn.setForeground(new Color(128,128,128));
        edit_btn.setBounds(430,480,150,30);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pfield.setText(pshow_lbl.getText());
                contentPane.add(pfield);
                pshow_lbl.setVisible(false);
                up_btn.setVisible(true);

            }
        });
        contentPane.add(edit_btn);
        }
      
}
