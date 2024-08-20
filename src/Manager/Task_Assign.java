package src.Manager;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Task_Assign extends JFrame{
    private static String manname;
    //private static Object[] row_Data;
    private static List<String> issues_Row;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new Task_Assign(manname, issues_Row).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Assign(String n, List<String> issuesRow){
        setTitle("Task Assign");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_TA = new JPanel();
        manager_TA.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TA);
        manager_TA.setLayout(null);

        Manager man_taskAssign = new Manager();

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_TA.add(logo_lbl);

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
        manager_TA.add(logo);

        // Title 
        JLabel title = new JLabel("Task Assign");
        title.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        title.setBounds(400, 20, 200, 20);
        manager_TA.add(title);

        // Assign staff Label
        JLabel taskAssign_lbl = new JLabel("Task Assign to: ");
        taskAssign_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        taskAssign_lbl.setBounds(50,70, 120, 40);
        manager_TA.add(taskAssign_lbl);

        // Assign staff combo box
        ArrayList<String> assignStaff = man_taskAssign.assign_staff();
        String[] staff_data = assignStaff.toArray(new String[0]);
        JComboBox<String> staff = new JComboBox<>(staff_data);
        staff.setBackground(new Color(250,240,230));
        staff.setForeground(new Color(128,128,128));
        staff.setBounds(160, 83, 120, 20);
        manager_TA.add(staff);

        
        // Assign Text Label
        JLabel text_lbl = new JLabel("Assign Description: ");
        text_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        text_lbl.setBounds(50, 125, 150, 30);
        manager_TA.add(text_lbl);


        // Assign Text Area
        JTextArea assignText = new JTextArea(10, 50);
        assignText.setEditable(true);
        assignText.setLineWrap(true);
        assignText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        assignText.setBounds(50, 155, 800, 150);
        manager_TA.add(assignText);

        // Assign Button
        JButton assign = new JButton("Assign");
        assign.setBackground(new Color(250,240,230));
        assign.setForeground(new Color(128,128,128));
        assign.setBounds(800, 600, 110, 20);
        assign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // issuesRow
                String staff_data = staff.getSelectedItem().toString();
                String details = assignText.getText(); 
                man_taskAssign.keep_task(issuesRow, staff_data, details);
                
            }
        });
        manager_TA.add(assign);
        
        // Back Home page
        JLabel back_lbl = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/logout.png"));

            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 15, 35, 35);

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
        manager_TA.add(back_lbl);

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
        manager_TA.add(des4);
    }
}
