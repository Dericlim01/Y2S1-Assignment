package src.Manager;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;

public class Task_Assign extends JFrame{
    private static String manname;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new Task_Assign(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Assign(String n){
        setTitle("Task Assign");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_TA = new JPanel();
        manager_TA.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TA);
        manager_TA.setLayout(null);

        Manager man_taskAssign = new Manager();

        // Assign staff Label
        JLabel taskAssign_lbl = new JLabel("Task Assign to: ");
        taskAssign_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        taskAssign_lbl.setBounds(50,70, 120, 40);
        manager_TA.add(taskAssign_lbl);

        // Assign staff combo box
        ArrayList<String> assignStaff = man_taskAssign.assign_staff();
        String[] staff_data = assignStaff.toArray(new String[0]);
        JComboBox<String> staff = new JComboBox<>(staff_data);
        staff.setBounds(160, 83, 120, 20);
        staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        manager_TA.add(staff);

        
        // Assign Text Label
        JLabel text_lbl = new JLabel("Assign Description: ");
        text_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        text_lbl.setBounds(50, 125, 150, 30);
        manager_TA.add(text_lbl);


        // Assign Text field
        JTextArea assignText = new JTextArea(10, 50);
        assignText.setEditable(true);
        assignText.setLineWrap(true);
        assignText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        assignText.setBounds(50, 155, 800, 150);
        manager_TA.add(assignText);
        
        // Back Home page
        JButton back_btn = new JButton();
        try {
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("resources/Database/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);

        } catch (Exception e) {
            e.printStackTrace();
        }

        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Manager_Home_Page(n).setVisible(true);;
            }
        });

        manager_TA.add(back_btn);
    }
}
