package src.Manager;

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

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.imageio.*;

public class Task_Status extends JFrame {
    private static String manname;
    private static JScrollPane scrollPane;
    private static DefaultTableModel tm;
    private static JTable view;
    private static String issues_title;
    private static String issues_description;
    // private static String taskStatus;
    private static String username;

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
        scrollPane.setBounds(9, 80, 970, 450);
        manager_TAS.add(scrollPane);

        // Back Label
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 15, 35, 35);
        } catch (IOException e) {
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
                scrollPane.setBounds(9, 80, 970, 450);
                manager_TAS.add(scrollPane);
                view.revalidate();
                view.repaint();
            }
        });
        manager_TAS.add(update);

        // Task Id Label
        JLabel task_id = new JLabel("Task ID: ");
        task_id.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        task_id.setBounds(80, 550, 110, 20);
        manager_TAS.add(task_id);

        // Task Id Show Label
        JLabel task_ID_show = new JLabel();
        task_ID_show.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        task_ID_show.setBounds(200, 550, 110, 20);
        manager_TAS.add(task_ID_show);

        // Status label
        JLabel status = new JLabel("Status: ");
        status.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        status.setBounds(600, 550, 110, 20);
        manager_TAS.add(status);

        // Status combo box
        String[] Status = {"In_progress", "Cancel", "Close"};
        JComboBox<String> statusBox = new JComboBox<>(Status);
        statusBox.setBounds(700, 550, 150, 28);
        statusBox.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        statusBox.setBackground(new Color(250,240,230));
        statusBox.setSelectedIndex(-1);
        manager_TAS.add(statusBox);

        // Task Issues Label
        JLabel issues_id = new JLabel("Issues ID: ");
        issues_id.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        issues_id.setBounds(80, 600, 110, 20);
        //issues_id.setVisible(false);
        manager_TAS.add(issues_id);

        // Issue Id Show Label
        JLabel issue_id_show = new JLabel();
        issue_id_show.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        issue_id_show.setBounds(200, 600, 110, 20);
        //issue_id_show.setVisible(false);
        manager_TAS.add(issue_id_show);

        // Task Hall Label
        JLabel hall_id = new JLabel("Hall ID: ");
        hall_id.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        hall_id.setBounds(300, 550, 110, 20);
        //hall_id.setVisible(false);
        manager_TAS.add(hall_id);

        // Task Hall ID Show Label        
        JLabel hall_id_show = new JLabel();
        hall_id_show.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        hall_id_show.setBounds(400, 550, 110, 20);
        //hall_id_show.setVisible(false);
        manager_TAS.add(hall_id_show);

        // Task Staff Label
        JLabel staff = new JLabel("Handle Staff: ");
        staff.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        staff.setBounds(300, 600, 110, 20);
        //hall_id.setVisible(false);
        manager_TAS.add(staff);

        // Task Hall ID Show Label        
        JLabel staff_show = new JLabel();
        staff_show.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        staff_show.setBounds(450, 600, 110, 20);
        // hall_id_show.setVisible(false);
        manager_TAS.add(staff_show);
        
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String taskID;
                String issues_id;
                String hallId;
                String handle_staff;
                String taskStatus;
                int selectedRow = view.getSelectedRow();
            
                if (selectedRow != -1) {
                    // Get value from each column in the selected row 
                    taskID = view.getValueAt(selectedRow, 0).toString();
                    issues_id = view.getValueAt(selectedRow, 1).toString();
                    issues_title = view.getValueAt(selectedRow, 2).toString();
                    issues_description = view.getValueAt(selectedRow, 3).toString();
                    username = view.getValueAt(selectedRow, 4).toString();
                    hallId = view.getValueAt(selectedRow, 5).toString();
                    handle_staff = view.getValueAt(selectedRow, 6).toString();
                    taskStatus = view.getValueAt(selectedRow, 7).toString();
                    
                    task_ID_show.setText(taskID);
                    issue_id_show.setText(issues_id);
                    hall_id_show.setText(hallId);
                    staff_show.setText(handle_staff);
                    statusBox.setSelectedItem(taskStatus);
                }
            }
        });

        // Save Button
        JButton save_btn = new JButton("Save");
        save_btn.setBackground(new Color(250,240,230));
        save_btn.setForeground(new Color(128,128,128));
        save_btn.setBounds(600, 675, 110, 20);
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskID = task_ID_show.getText();
                String issuesID = issue_id_show.getText();
                String hallId = hall_id_show.getText();
                String Staff = staff_show.getText();
                String status = statusBox.getSelectedItem().toString();

                man_task.update_status(taskID, issuesID, issues_title, issues_description, username, hallId, Staff, status);
            }
        });
        manager_TAS.add(save_btn);

        // Design 4 Background Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_TAS.add(des4);
    }
}
