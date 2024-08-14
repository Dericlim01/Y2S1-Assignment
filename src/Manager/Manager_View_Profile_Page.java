package src.Manager;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;

import src.UpdateProfile;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Manager_View_Profile_Page extends JFrame {

    private static String manname;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Manager_View_Profile_Page(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Manager_View_Profile_Page(String n){
        setTitle("Manager View Profile");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_VP = new JPanel(); // JPanel like grouping thing like groupbox
        manager_VP.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_VP);
        manager_VP.setLayout(null);
        UpdateProfile up = new UpdateProfile();
        String[] user = up.search_user(n);


        // Hall icon
        JLabel hall = new JLabel();
        try{
            BufferedImage hallImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            hallImage = ImageIO.read(new File("resources/Image/hall.png"));

            Image hall_Image = hallImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            hall.setIcon(new ImageIcon(hall_Image));
            hall.setBounds(10, 5, 40, 40);
        }catch(IOException e){
            e.printStackTrace();
        }
        manager_VP.add(hall);

        // Hall text label
        JLabel hall_txt_lbl = new JLabel("Symphony Hall");
        hall_txt_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        hall_txt_lbl.setForeground(new Color(169, 169, 169));
        //hall_txt_lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));;
        hall_txt_lbl.setBounds(50, 5, 110, 40);
        manager_VP.add(hall_txt_lbl);

        // Profile Picture 
        JLabel picture = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/little white dog.png"));

            Image image = get_image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            picture.setIcon(new ImageIcon(image));
            picture.setBounds(425, 80, 120, 120);

        } catch(IOException e){
            e.printStackTrace();
        }

        manager_VP.add(picture);

        // Gradient Design
        JLabel gradient = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/gradient.png"));

            Image image = get_image.getScaledInstance(1000, 280, Image.SCALE_SMOOTH);

            gradient.setIcon(new ImageIcon(image));
            gradient.setBounds(0, 0, 1000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_VP.add(gradient);

        // Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        greet_lbl.setBounds(440, 210, 200, 30);
        manager_VP.add(greet_lbl);


        // Username icon
        JLabel user_icon = new JLabel();
        try{
            BufferedImage userIcon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            userIcon = ImageIO.read(new File("resources/Image/head icon.png"));

            Image icon1 = userIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            user_icon.setIcon(new ImageIcon(icon1));
            user_icon.setBounds(100, 50, 40, 40);

        }catch(IOException e){
            e.printStackTrace();
        }

        manager_VP.add(user_icon);

        // Username Label
        JLabel username = new JLabel(user[0]);
        username.setFont(new Font("Serif", Font.PLAIN, 15));
        username.setBounds(450, 240, 200, 30);
        manager_VP.add(username);

        // Password Label (455, 300)
        JLabel pass_lbl = new JLabel("Password:");
        // pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_lbl.setBounds(350, 290, 200, 30);
        manager_VP.add(pass_lbl);

        // Password Show Label
        JLabel pshow_lbl = new JLabel(user[1]);
        pshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pshow_lbl.setBounds(450, 290, 200, 30);
        manager_VP.add(pshow_lbl);

        
        // Mobile Number Label
        JLabel cshow_lbl = new JLabel(user[2]);
        cshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        cshow_lbl.setBounds(450, 340, 200, 30);
        manager_VP.add(cshow_lbl);

        // Email Label
        JLabel email = new JLabel(user[3]);
        email.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        //email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        email.setBounds(450, 390, 200, 50);
        manager_VP.add(email);

        // Password field
        JTextField pfield = new JTextField();
        pfield.setBounds(450, 290, 200, 30);

        // Contact No. Text Field
        JTextField cfield = new JTextField();
        cfield.setBounds(450, 350, 200, 30);

        // Email Text Field
        JTextField efield = new JTextField();
        efield.setBounds(450, 400, 200, 30);

        // Update Button
        JButton update_btn = new JButton("Update");
        update_btn.setBackground(new Color(250, 240, 230));
        update_btn.setForeground(new Color(128, 128, 128));
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String pass = pfield.getText();
                String con = cfield.getText();
                String mail = efield.getText();
                UpdateProfile up = new UpdateProfile();
                if (up.update_user(userName, pass, con, mail)) {
                    JOptionPane.showMessageDialog(null, "Update Successfully", "Plain", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    // UPdate Failed
                    int response = JOptionPane.showConfirmDialog(null, "Update Failed, Do you want to Update Again?", "Question", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (response == 1) {
                        pfield.setText("");
                        cfield.setText("");
                        efield.setText("");
                    }
                }
            }
        });
        update_btn.setBounds(430, 480, 120, 30);
        update_btn.setVisible(false);
        manager_VP.add(update_btn);
        

        // Back button
        JButton back_btn = new JButton();
        try{
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            
            backImage = ImageIO.read(new File("resources/Image/logout.png"));

            Image back_ima = backImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 30, 40, 40);
        }catch(IOException e){
            e.printStackTrace();
        }
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setTitle("Manager Home Page");
                man_HP.setVisible(true);
            }
        });

        manager_VP.add(back_btn);

        // Edit Button
        JButton edit_btn = new JButton("Edit Profile");
        edit_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        edit_btn.setBackground(new Color(250, 240, 230));
        edit_btn.setForeground(new Color(128, 128, 128));
        edit_btn.setBounds(430, 480, 120, 30);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit_btn.setVisible(false);
                update_btn.setVisible(true);
                pfield.setText(pshow_lbl.getText());
                cfield.setText(cshow_lbl.getText());
                efield.setText(email.getText());

                manager_VP.add(pfield);
                manager_VP.add(cfield);
                manager_VP.add(efield);
                manager_VP.add(update_btn);
            }
        });

        manager_VP.add(edit_btn);

    }
}
