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

public class Task_Assign_and_Status extends JFrame {
    private static String manname;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Task_Assign_and_Status(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Assign_and_Status(String n){
        setTitle("Task Assign and Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_TAS = new JPanel();
        manager_TAS.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TAS);
        manager_TAS.setLayout(null);
    }
}
