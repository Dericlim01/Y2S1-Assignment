package src.Manager;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
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

public class Task_Status extends JFrame {
    private static String manname;
    private static JScrollPane scrollPane;
    private static DefaultTableModel tm;
    private static JTable view;
    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Task_Status(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Status(String n) {
        setTitle("Task Assign and Status");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        Manager man_task = new Manager();

        JPanel manager_TAS = new JPanel();
        manager_TAS.setBorder(new EmptyBorder(5, 5, 5, 5));
        manager_TAS.setBackground(new Color(248,248,248));
        setContentPane(manager_TAS);
        manager_TAS.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_TAS.add(logo_lbl);

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
        manager_TAS.add(logo);
        
        // Title 
        JLabel title = new JLabel("Task Status");
        title.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        title.setBounds(400, 20, 200, 20);
        manager_TAS.add(title);

        String[] col_name = {"Task ID", "Issues ID", "Issues", "Description", "Username", "Halls ID", "Handled Staff", "Issues Status"};
        Object[][] tasks_status = man_task.task_status();
        
        tm = new DefaultTableModel(tasks_status, col_name);
        view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 80, 970, 550);
        manager_TAS.add(scrollPane);

        // Back Label
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
        manager_TAS.add(back_lbl);

        // Update button
        JButton update = new JButton("Update");
        update.setBackground(new Color(250,240,230));
        update.setForeground(new Color(128,128,128));
        update.setBounds(800, 675, 110, 20);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] taskStatus = man_task.task_status();
                tm.setDataVector(taskStatus, col_name);
                view = new JTable(tm);
                
                scrollPane = new JScrollPane(view);
                scrollPane.setBounds(9, 80, 970, 550);

                manager_TAS.add(scrollPane);
                
                view.revalidate();
                view.repaint();

            }
        });
        manager_TAS.add(update);

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
        manager_TAS.add(des4);

    }
}
