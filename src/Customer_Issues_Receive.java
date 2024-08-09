package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.*;

//import java.awt.Font;
//import java.awt.Color;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


import java.io.File;
import java.io.IOException;


public class Customer_Issues_Receive extends JFrame {
    private static String manname;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Customer_Issues_Receive(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Customer_Issues_Receive(String n){
        setTitle("Customer Issues Receive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_CIR = new JPanel();
        manager_CIR.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_CIR);
        manager_CIR.setLayout(null);

        Manager man_issues = new Manager();

        String[] col_name = {"Username", "Hall Type", "Issues", "Issues Description", "Date"};

        scrollPane = man_issues.view_issues(col_name);
        scrollPane.setBounds(70, 70, 850, 400);

        manager_CIR.add(scrollPane);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane = man_issues.view_issues(col_name);
                manager_CIR.remove(scrollPane);
                manager_CIR.add(scrollPane);
                manager_CIR.revalidate();
                manager_CIR.repaint();
            }
        });
        manager_CIR.add(refresh);

        JButton back_btn = new JButton();
        try{
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);

        }catch(IOException e){
            e.printStackTrace();
        }

        


        manager_CIR.add(back_btn);







        
    }
}
