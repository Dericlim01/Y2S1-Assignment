package src.Manager;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


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

import java.io.File;
import java.io.IOException;

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
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/Image/hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        Manager man_task = new Manager();

        JPanel manager_TAS = new JPanel();
        manager_TAS.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TAS);
        manager_TAS.setLayout(null);

        // Title 
        JLabel title = new JLabel("Task Status");
        title.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        title.setBounds(450, 20, 120, 20);
        manager_TAS.add(title);

        String[] col_name = {"Task ID", "Issues ID", "Issues", "Description", "Username", "Halls ID", "Staff ID", "Handled Staff", "Issues Status"};
        Object[][] tasks_status = man_task.task_status();
        
        tm = new DefaultTableModel(tasks_status, col_name);
        view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 50, 970, 600);
        manager_TAS.add(scrollPane);

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

    }
}
