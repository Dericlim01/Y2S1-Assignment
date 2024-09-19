package src.Scheduler;

import src.Customer.Update_Profile.UpdateProfile;
import src.Scheduler.Scheduler_Update_Profile_Page;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

public class Scheduler_Update_Profile_Page extends JFrame {
    private static String name;
    private static String[] user ;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Scheduler_Update_Profile_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Scheduler_Update_Profile_Page(String name) {
        setTitle("Scheduler View Profile");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        user = new Scheduler_Update_Profile().read_user_Information(name);

        // Panel
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        // Hall icon
        JLabel hall = new JLabel();
        try {
            BufferedImage hallImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            hallImage = ImageIO.read(new File("resources/Image/hall (1).png"));
            Image hall_Image = hallImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            hall.setIcon(new ImageIcon(hall_Image));
            hall.setBounds(10, 5, 40, 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(hall);

        // Hall text label
        JLabel hall_txt_lbl = new JLabel("Symphony Hall");
        hall_txt_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        hall_txt_lbl.setForeground(new Color(169, 169, 169));
        // hall_txt_lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));;
        hall_txt_lbl.setBounds(50, 5, 180, 40);
        panel.add(hall_txt_lbl);

        // Profile Picture 
        JLabel picture = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/fengjian.png"));
            Image image = get_image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            picture.setIcon(new ImageIcon(image));
            picture.setBounds(425, 80, 120, 120);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(picture);
       
        // Greeting Label
        JLabel greet_lbl = new JLabel("Welcome Back!");
        greet_lbl.setFont(new Font("Serif", Font.PLAIN, 15));
        greet_lbl.setBounds(440, 210, 200, 30);
        panel.add(greet_lbl);

        // Username Label
        JLabel username = new JLabel(user[0]);
        username.setFont(new Font("Serif", Font.PLAIN, 15));
        username.setBounds(475, 240, 200, 30);
        panel.add(username);

        // Password Label
        JLabel pass_lbl = new JLabel("Password:");
        // pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pass_lbl.setBounds(380, 290, 200, 30);
        panel.add(pass_lbl);

        // Password Show Label
        JLabel pshow_lbl = new JLabel(user[1]);
        pshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        pshow_lbl.setBounds(480, 290, 200, 30);
        panel.add(pshow_lbl);

        // Mobile Number Label
        JLabel c_lbl = new JLabel("Phone Number:");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        c_lbl.setBounds(380, 340, 200, 30);
        panel.add(c_lbl);

        // Mobile Number Show Label
        JLabel cshow_lbl = new JLabel(user[2]);
        cshow_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        cshow_lbl.setBounds(480, 340, 200, 30);
        panel.add(cshow_lbl);

        // Email Label
        JLabel e_lbl = new JLabel("Email:");
        pass_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        e_lbl.setBounds(380, 390, 200, 30);
        panel.add(e_lbl);

        // Email Show Label
        JLabel email = new JLabel(user[3]);
        email.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        //email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        email.setBounds(480, 380, 200, 50);
        panel.add(email);

        // Birth Label
        JLabel b_lbl = new JLabel("Birthday:");
        b_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        b_lbl.setBounds(380, 440, 200, 30);
        panel.add(b_lbl);

        // Birth Show Label
        JLabel birth = new JLabel(user[4]);
        birth.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        // email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        birth.setBounds(480, 433, 200, 50);
        panel.add(birth);

        // Gender Label
        JLabel g_lbl = new JLabel("Gender:");
        g_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        g_lbl.setBounds(380, 490, 200, 30);
        panel.add(g_lbl);

        // Gender Show Label
        JLabel gender = new JLabel(user[5]);
        gender.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        // email.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        gender.setBounds(480, 480, 200, 50);
        panel.add(gender);

        // Password field
        JTextField pfield = new JTextField();
        pfield.setBounds(480, 290, 200, 30);

        // Contact No. Text Field
        JTextField cfield = new JTextField();
        cfield.setBounds(480, 350, 200, 30);

        // Email Text Field
        JTextField efield = new JTextField();
        efield.setBounds(480, 400, 200, 30);

        // Update Button
        JButton update_btn = new JButton("Update");
        update_btn.setBackground(new Color(250, 240, 230));
        update_btn.setForeground(new Color(128, 128, 128));
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String pass = pfield.getText();
                UpdateProfile up = new UpdateProfile();
                if (up.update_user(userName, pass)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Update Successfully",
                        "Plain",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // UPdate Failed
                    int response = JOptionPane.showConfirmDialog(
                        null,
                        "Update Failed, Do you want to Update Again?",
                        "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                    if (response == 1) {
                        new Scheduler_Update_Profile_Page(name).setVisible(true);
                    }
                }
            }
        });
        update_btn.setBounds(430, 550, 120, 30);
        update_btn.setVisible(false);
        panel.add(update_btn);
        
        // Back button
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 15, 35, 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Scheduler_Main_Page(name).setVisible(true);         
            }
        });
        panel.add(back_lbl);

        // Edit Button
        JButton edit_btn = new JButton("Edit Profile");
        edit_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        edit_btn.setBackground(new Color(250, 240, 230));
        edit_btn.setForeground(new Color(128, 128, 128));
        edit_btn.setBounds(430, 550, 120, 30);
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit_btn.setVisible(false);
                update_btn.setVisible(true);
                pfield.setText(pshow_lbl.getText());
                cfield.setText(cshow_lbl.getText());
                efield.setText(email.getText());
                panel.add(pfield);
                panel.add(cfield);
                panel.add(efield);
                panel.add(update_btn);
            }
        });
        panel.add(edit_btn);

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
        panel.add(gradient);
    }
}
