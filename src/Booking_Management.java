package src;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;


import java.awt.Font;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Booking_Management extends JFrame {
    private static String manname;
    private static JScrollPane scrollPane;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Booking_Management(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Booking_Management(String n){
        setTitle("Booking Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_BM = new JPanel();
        manager_BM.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_BM);
        manager_BM.setLayout(null);

        JButton refresh = new JButton("refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_BM.remove(scrollPane);
            }
        });

    }
}
