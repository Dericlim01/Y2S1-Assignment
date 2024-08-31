package src.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.util.List;

import java.io.File;
import java.io.IOException;

public class Reply_Customer extends JFrame{
    private static String manname;
    private static List<String> issues_Row;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Reply_Customer(manname, issues_Row).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Reply_Customer(String n, List<String> issuesRow){
        setTitle("Reply Customer");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_RC = new JPanel();
        manager_RC.setBorder(new EmptyBorder(5, 5, 5, 5));
        manager_RC.setBackground(new Color(248,248,248));
        setContentPane(manager_RC);
        manager_RC.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_RC.add(logo_lbl);

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
        manager_RC.add(logo);


        // Title 
        JLabel title = new JLabel("Reply Customer Page");
        title.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        title.setBounds(350, 20, 400, 20);
        manager_RC.add(title);

        // Issues Label
        JLabel issues_lbl = new JLabel("Issues :");
        issues_lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        issues_lbl.setBounds(50, 120, 100, 20);
        manager_RC.add(issues_lbl);

        // Issues Show Label
        JLabel show_issues = new JLabel(issuesRow.get(1));
        show_issues.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        show_issues.setBounds(120, 120, 400, 20);
        manager_RC.add(show_issues);

        // Issues Hall Type
        JLabel issues_hall = new JLabel("Hall Type :");
        issues_hall.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        issues_hall.setBounds(400, 120, 100, 20);
        manager_RC.add(issues_hall);

        // Issues Hall Type Show
        JLabel show_hall = new JLabel(issuesRow.get(3));
        show_hall.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        show_hall.setBounds(500, 120, 150, 20);
        manager_RC.add(show_hall);

        // Issues Description Label
        JLabel descrip_lbl = new JLabel("Issues Description :");
        descrip_lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        descrip_lbl.setBounds(50, 150, 400, 20);
        manager_RC.add(descrip_lbl);

        // Issues Description Show Label
        JLabel show_descrip = new JLabel(issuesRow.get(2));
        show_descrip.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        show_descrip.setBounds(50, 180, 600, 100);
        manager_RC.add(show_descrip);

        // Reply lbl
        JLabel reply_lbl = new JLabel("Content :");
        reply_lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        reply_lbl.setBounds(50, 300, 100, 20);
        manager_RC.add(reply_lbl);

        // Reply Text Area
        JTextArea reply_text = new JTextArea(10, 50);
        reply_text.setText("Dear " + issuesRow.get(4) + ",\n");
        reply_text.setEditable(true);
        reply_text.setLineWrap(true);
        reply_text.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        reply_text.setBounds(50, 350, 900, 150);
        manager_RC.add(reply_text);

        // Send button
        JButton send = new JButton("Send");
        send.setBackground(new Color(250,240,230));
        send.setForeground(new Color(128,128,128));
        send.setBounds(450, 600, 100, 50);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Send Successfully !", "Message Successul Send Notification", JOptionPane.OK_CANCEL_OPTION);
            }
        });
        manager_RC.add(send);


        //Back Page Pic
        JLabel back_lbl = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/logout.png"));

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
                new Manager_Home_Page(n).setVisible(true);         
            }

        });
        manager_RC.add(back_lbl);


        //Design 4 Background Pic
        JLabel des4 = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        manager_RC.add(des4);
    }
}
