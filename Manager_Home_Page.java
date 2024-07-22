import javax.swing.*;

import java.awt.Font;
import java.awt.EventQueue;

import java.io.*;
import javax.imageio.*;


public class Manager_Home_Page extends JFrame {
   private JFrame manager_HP;
   private static String manname;
   public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable(){
        @Override
        public void run() {
            try{
                Manager_Home_Page man_HP = new Manager_Home_Page(manname);
                man_HP.setTitle("Manager");
                man_HP.setVisible(true);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
       });
       
   }

   public Manager_Home_Page(String name){
       manager_HP = new JFrame("Manager_HomePage");
       manager_HP.setLayout(null);
       
       JLabel man_name = new JLabel(name);
       man_name.setFont(new Font("Calibri", Font.PLAIN, 16));


       JLabel welcome = new JLabel("Welcome Back!");
       welcome.setBounds(35,150,120,80);
       //welcome.setForeground(Color.PINK);
       Font wel_font = new Font("Calibri",Font.BOLD,16);
       welcome.setFont(wel_font);
       //welcome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
       //welcome.setSize(400,500);

       JLabel title = new JLabel("Manager Home Page");
       JButton view_profile = new JButton("View Profile");
       JButton sales_dash = new JButton("Sales DashBoard   ");
       JButton cus_issues = new JButton("Customer Issues");

       
       
       manager_HP.add(welcome);
       manager_HP.add(man_name);
       manager_HP.setSize(900,600);
       manager_HP.setVisible(true);
       manager_HP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

}
