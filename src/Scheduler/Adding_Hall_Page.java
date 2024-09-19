package src.Scheduler;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

/**
 * Adding_Hall_Page
 */
public class Adding_Hall_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Adding_Hall_Page(name).setVisible(true));    
    }

    // Set Panel
    public Adding_Hall_Page(String name) {
        setTitle("Adding New Hall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(248,248,248));
    
        // Title Label
        JLabel titleLabel = new JLabel("Adding New Hall");
        titleLabel.setBounds(360, 10, 350, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.PLAIN,25));
        panel.add(titleLabel);
   
        // Hall Type Label
        JLabel hallType_lbl = new JLabel("Hall Type:");
        hallType_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        hallType_lbl.setBounds(315,230,300,50);
        panel.add(hallType_lbl);

        // Hall Capacity label
        JLabel hallCap_lbl = new JLabel("Hall Capacity:");
        hallCap_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        hallCap_lbl.setBounds(280,350,300,50);
        panel.add(hallCap_lbl);

        // Price per hour label
        JLabel pricePerH_lbl = new JLabel("Price Per Hour:");
        pricePerH_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        pricePerH_lbl.setBounds(270,470,300,50);
        panel.add(pricePerH_lbl);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        panel.add(logo_lbl);

        // JFormattedTextField for Price Per Hour
        // Using Number Format to convert number and display it as Malaysia Ringgit Format
        NumberFormat DoubleFormat = new DecimalFormat("RM #,##0.00");

        // Using a NumberFormatter to restrict what user can input
        NumberFormatter doubleFormatter = new NumberFormatter(DoubleFormat);
        doubleFormatter.setValueClass(Double.class);
        doubleFormatter.setAllowsInvalid(false); // Prevent invalid input
        doubleFormatter.setMinimum(0.0); // Optional: Set minimum value
        doubleFormatter.setMaximum(Double.MAX_VALUE);
        
        JFormattedTextField priceField = new JFormattedTextField(doubleFormatter);
        priceField.setValue(BigDecimal.ZERO);
        priceField.setBounds(450,480,200,40);
        priceField.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        priceField.setEditable(true);
        panel.add(priceField);
    
        // Combo Box for hall type
        String[] hallType = {"Auditorium", "BanquetHall", "MeetingRoom", "Others"};
        JComboBox<String> hallTypeBox = new JComboBox<>(hallType);
        hallTypeBox.setBounds(450,235,200,40);
        hallTypeBox.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        hallTypeBox.setBackground(new Color(250,240,230));
        panel.add(hallTypeBox);

        // Spinner for Capacity
        SpinnerModel value =  
            new SpinnerNumberModel(20, // initial value  
                0, // minimum value  
                1000, // maximum value  
                1); // step  
        JSpinner capacitySpinner = new JSpinner(value);   
        capacitySpinner.setBounds(450,358,200,40);    
        capacitySpinner.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        panel.add(capacitySpinner);

        // Logo Pic
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch(IOException e) {
            e.printStackTrace();
        }
        panel.add(logo);

        // Add Hall Button
        JButton addHall_btn = new JButton("Add");
        addHall_btn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        addHall_btn.setBackground(new Color(250,240,230));
        addHall_btn.setForeground(new Color(128,128,128));
        addHall_btn.setBounds(360,600,250,35);
        addHall_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hallTypeValue = (String) hallTypeBox.getSelectedItem();
                Integer capacityValue = (Integer) capacitySpinner.getValue();
                Double priceValue = ((Number) priceField.getValue()).doubleValue();
            if (new Hall_Management().Add_Hall(hallTypeValue, capacityValue,priceValue)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Hall Added successfully",
                        "Status",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Hall Added failed",
                        "Status",
                        JOptionPane.INFORMATION_MESSAGE);
               }
            }
        });
        panel.add(addHall_btn);

        // Home Page Label
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);

        } catch(IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Hall_Management_Page(name).setVisible(true);
            }
        });
        panel.add(back_lbl);

        // Design 4 Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch(IOException e) {
            e.printStackTrace();
        }
        panel.add(des4);
    }
}
