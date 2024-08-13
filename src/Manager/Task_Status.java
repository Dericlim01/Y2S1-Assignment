package src.Manager;
import javax.swing.*;
import javax.imageio.*;

import javax.swing.JTable;
import javax.swing.border.*;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;

public class Task_Status extends JFrame {
    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel tm;
    private JTable view;


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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        Manager man_task = new Manager();

        JPanel manager_TAS = new JPanel();
        manager_TAS.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TAS);
        manager_TAS.setLayout(null);


        String[] col_name = {"Task ID", "Issues ID", "Issues", "Description", "Username", "Halls ID", "Staff ID", "Handled Staff", "Issues Status"};
        Object[][] tasks_status = man_task.task_status();
        
        tm = new DefaultTableModel(tasks_status, col_name);
        view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 350, 970, 100);
        manager_TAS.add(scrollPane);

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
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setVisible(true);
            }
        });

        manager_TAS.add(back_btn);

    }
}
