package src.Scheduler;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Customer.Hall_Booking.Hall_Booking;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Schedule_Maintainance_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Schedule_Maintainance_Page(name).setVisible(true));
    }
    
    public Schedule_Maintainance_Page(String n){
        setTitle("Hall Schedule Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(248,248,248));
   

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        panel.add(logo_lbl);


    // Logo Pic
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(logo);


        // Title Label
        JLabel titleLabel = new JLabel("Schedule Checker");
        titleLabel.setBounds(340, -80, 370, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.PLAIN,25));
        panel.add(titleLabel);

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall type : ");
        hall_type_lbl.setBounds(30, 110, 100, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(hall_type_lbl);
    
        // Start Date label
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setBounds(30, 150, 150, 30);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(start_date_lbl);

        // End Date label
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setBounds(30, 190, 150, 30);
        end_date_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(end_date_lbl);

        // Hall type ComboBox
        ArrayList<String> hallsList = new Hall_Booking().search_hall();
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_type_cmbbx = new JComboBox<String>(halls);
        hall_type_cmbbx.setBounds(175, 110, 125, 25);
        hall_type_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_type_cmbbx.setSelectedIndex(-1);
        panel.add(hall_type_cmbbx);

        // Button
        JButton addSchdlBtn = new JButton("Add New Schedule");
        addSchdlBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        addSchdlBtn.setBackground(new Color(250,240,230));
        addSchdlBtn.setForeground(new Color(128,128,128));
        addSchdlBtn.setBounds(360,670,300,35);
        panel.add(addSchdlBtn);

        // table
        String[] col_name = {"Hall ID", "Start Date", "End Date", "Start Time", "End Time", "Hall Type", "Status"};
        Object[][] data = new Hall_Booking().hall_data(String.valueOf(hall_type_cmbbx.getSelectedIndex()));
        DefaultTableModel table = new DefaultTableModel(data, col_name);
        JTable details = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(details);
        scrollPane.setBounds(30, 290, 930, 350);
        panel.add(scrollPane);

        
        

    
    }
}
