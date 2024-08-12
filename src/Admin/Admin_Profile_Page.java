package src.Admin;

import src.UpdateProfile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public Admin_Profile_Page(String n) {
        setTitle("Admin Profile Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(230,220,202));
        UpdateProfile search = new UpdateProfile();
        String[] user = search.search_user(n);

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

        //Password label
        JLabel pass_lbl = new JLabel("Password:");
        pass_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pass_lbl.setBounds(400,290,200,30);
        contentPane.add(pass_lbl);

        //Password Show Label
        JLabel pshow_lbl = new JLabel(user[1]);
        pshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        pshow_lbl.setBounds(500,290,200,30);
        contentPane.add(pshow_lbl);

        //Contact No. Label
        JLabel con_lbl = new JLabel("Contact Number:");
        con_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        con_lbl.setBounds(350,340,200,30);
        contentPane.add(con_lbl);

        //Contact No. Show Label
        JLabel cshow_lbl = new JLabel(user[2]);
        cshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        cshow_lbl.setBounds(500,340,200,30);
        contentPane.add(cshow_lbl);

        //Email Label
        JLabel email_lbl = new JLabel("Email:");
        email_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        email_lbl.setBounds(425,390,200,30);
        contentPane.add(email_lbl);

        //Email Show Label
        JLabel eshow_lbl = new JLabel(user[3]);
        eshow_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        eshow_lbl.setBounds(500,390,200,30);
        contentPane.add(eshow_lbl);

        //Password field
        JTextField pfield = new JTextField();
        pfield.setBounds(500,290,200,30);

        //Contact No. Text Field
        JTextField cfield = new JTextField();
        cfield.setBounds(500,340,200,30);

        //Email Text Field
        JTextField efield = new JTextField();
        efield.setBounds(500,390,200,30);

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
        home_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_Page ad = new Admin_Page(name);
                ad.setTitle("Admin");
                ad.setVisible(true);
                ad.getContentPane().setBackground(new Color(230,220,202));
            }
        });
        contentPane.add(home_btn);


         //Update Button
         JButton up_btn = new JButton("Update");
         up_btn.setBounds(450,480,100,30);
         up_btn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String username = user_lbl.getText();
                 String pass = pfield.getText();
                 String con= cfield.getText();
                 String mail = efield.getText();
                 UpdateProfile up = new UpdateProfile();          
                 if(up.update_user(username, pass, con, mail)){
                     JOptionPane.showMessageDialog(null,"Update Successfully","Plain",JOptionPane.INFORMATION_MESSAGE);
                 }
                 else{
                     //Update Failed
                     int response = JOptionPane.showConfirmDialog(null,"Update Failed, Do you want to Update Again?","Question",JOptionPane.YES_NO_CANCEL_OPTION);
                     if(response == 1){
                         pfield.setText("");
                         cfield.setText("");
                         efield.setText("");       
                     }
                 }
             }
         });

      //Edit Button
      JButton edit_btn = new JButton("Edit");
      edit_btn.setBounds(450,480,100,30);
      edit_btn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pfield.setText(pshow_lbl.getText());
              cfield.setText(cshow_lbl.getText());
              efield.setText(eshow_lbl.getText());
              contentPane.add(pfield);
              contentPane.add(cfield);
              contentPane.add(efield);
              contentPane.add(up_btn);
          }
      });
      contentPane.add(edit_btn);
    }
      
}
