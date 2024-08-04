import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;


import java.awt.Font;
import java.awt.Image;
//import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Manager_View_Profile extends JFrame {

    private static String manname;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Manager_View_Profile(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Manager_View_Profile(String n){
        setTitle("Manager View Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_VP = new JPanel(); // JPanel like grouping thing like groupbox
        manager_VP.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_VP);
        manager_VP.setLayout(null);

        Manager search = new Manager();
        String[] user = search.read_user_Information(n);


        // Hall icon
        JLabel hall = new JLabel();
        try{
            BufferedImage hallImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            hallImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/hall.png"));

            Image hall_Image = hallImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            hall.setIcon(new ImageIcon(hall_Image));
            hall.setBounds(10, 5, 40, 40);
        }catch(IOException e){
            e.printStackTrace();
        }
        manager_VP.add(hall);

        // Hall text label
        JLabel hall_txt_lbl = new JLabel("Symphony Hall");
        hall_txt_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        //hall_txt_lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));;
        hall_txt_lbl.setBounds(50, 5, 110, 40);
        manager_VP.add(hall_txt_lbl);

        // Profile Picture 
        JLabel picture = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("D:/sem 1/Java/test/Manager/little white dog.png"));

            Image image = get_image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            picture.setIcon(new ImageIcon(image));
            picture.setBounds(425, 100, 120, 120);

        } catch(IOException e){
            e.printStackTrace();
        }

        manager_VP.add(picture);

        // Username icon
        JLabel user_icon = new JLabel();
        try{
            BufferedImage userIcon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            userIcon = ImageIO.read(new File("D:/ sem 1/Java/test/Manager/head icon.png"));

            Image icon1 = userIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            user_icon.setIcon(new ImageIcon(icon1));
            user_icon.setBounds(50, 50, 40, 40);

        }catch(IOException e){
            e.printStackTrace();
        }

        manager_VP.add(user_icon);

        // Username Label
        JLabel username = new JLabel(user[0]);
        username.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        username.setBounds(455, 250, 100, 50);
        manager_VP.add(username);

        // Bithday Label (455, 300)
        JLabel birth = new JLabel("Happy birhtday");
        birth.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        birth.setBounds(432, 300, 120, 50);
        manager_VP.add(birth);

        
        // Mobile Number Label
        JLabel phone_num = new JLabel(user[2]);
        phone_num.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        phone_num.setBounds(437, 350, 100, 50);
        manager_VP.add(phone_num);

        // Email Label
        JLabel email = new JLabel(user[3]);
        email.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        //email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        email.setBounds(420, 400, 160, 50);
        manager_VP.add(email);

        // Address Label
        JLabel address = new JLabel( "<html>Paya Rumput 1111111111111111111<html>");

        address.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        address.setBounds(420, 450, 160, 50);
        manager_VP.add(address);

        // Update Button
        JButton update_btn = new JButton("Update");
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update_Profile update_pro = new Update_Profile(user[0]);
                update_pro.setTitle("Update Profile");
                update_pro.setVisible(true);
                dispose();
            }
        });
        update_btn.setBounds(440, 550, 100, 50);
        manager_VP.add(update_btn);



        // Back button
        JButton back_btn = new JButton();
        try{
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            
            backImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));

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





    }
}
