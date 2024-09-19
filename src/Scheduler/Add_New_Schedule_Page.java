package src.Scheduler;

import src.shared.DateFormat;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

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
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class Add_New_Schedule_Page extends JFrame {
    public static String name;
    ArrayList<String> hallData;
    String status;
    String remark;

    // The creation and display of Add_New_Schedule_Page window 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Add_New_Schedule_Page(name).setVisible(true));
    }

    
    public Add_New_Schedule_Page(String name) {
        //  Set up the page 
        setTitle("Adding New Schedule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set  Panel
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(248,248,248));

        // Title Label
        JLabel titleLabel = new JLabel("Add New Schedule");
        titleLabel.setBounds(330, -55, 350, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.BOLD,25));
        panel.add(titleLabel);

        // Hall ID label
        JLabel pricePerH_lbl = new JLabel("Hall ID:");
        pricePerH_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        pricePerH_lbl.setBounds(120,230,300,50);
        panel.add(pricePerH_lbl);
   
        // Hall Type Label
        JLabel hallType_lbl = new JLabel("Hall Type: ");
        hallType_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        hallType_lbl.setBounds(400,230,300,50);
        panel.add(hallType_lbl);

        // Hall Capacity label
        JLabel hallCap_lbl = new JLabel("Hall Capacity: " );
        hallCap_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        hallCap_lbl.setBounds(680,230,300,50);
        panel.add(hallCap_lbl);

        // Start Date label
        JLabel startDate_lbl = new JLabel("Start Date:");
        startDate_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        startDate_lbl.setBounds(340,350,300,50);
        panel.add(startDate_lbl);

        // End Date label
        JLabel endDate_lbl = new JLabel("End Date:");
        endDate_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        endDate_lbl.setBounds(357,470,300,50);
        panel.add(endDate_lbl);
        
        // Remark label
        JLabel remark_lbl = new JLabel("Remark:");
        remark_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        remark_lbl.setBounds(120,590,300,50);
        panel.add(remark_lbl);

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

        // Hall ID Combo Box
        // Calling the search_hall_ID page from Schedule_Maintainance.java
        // Get the list of hall ID from hall.txt 
        ArrayList<String> hallsList = new Schedule_Maintainance().search_hall_ID();
        // Convert it to array , make it able to add in JcomboBox
        String[] halls = hallsList.toArray(new String[0]);
        // Add those hallID into comboBox
        JComboBox<String> hallID_cmbbx = new JComboBox<String>(halls);
        // setup the combobox
        hallID_cmbbx.setBounds(220, 245, 125, 25);
        hallID_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hallID_cmbbx.setSelectedIndex(-1);
        panel.add(hallID_cmbbx);
        // Add Action Listener and Action Performed to comboBox
        hallID_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check the selected ID and display the info of that ID on hall type label and hall capacity label
                hallData = new Schedule_Maintainance().match_hall_data(hallID_cmbbx.getSelectedItem().toString());
                if (!hallData.isEmpty()) {
                    // Access the data
                    String hallType = hallData.get(1); // Hall Type
                    String hallCapacity = hallData.get(2); // Hall Capacity
                    hallType_lbl.setText("Hall Type: " + hallType);
                    hallCap_lbl.setText("Hall Capacity: " + hallCapacity);
                }
            }
        });

        // Remark Text Field
        JTextField remarkField = new JTextField();
        remarkField.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        remarkField.setBounds(220,600,700,30);
        panel.add(remarkField);

        // Radio Button Maintainance Schedule
        JRadioButton maintainanceButton , bookingButton; 
        maintainanceButton = new JRadioButton("Maintainance Schedule");
        maintainanceButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        maintainanceButton.setBounds(220,150,250,30);
        maintainanceButton.setBackground(new Color(248,248,248));
        panel.add(maintainanceButton);

        maintainanceButton.addActionListener(new ActionListener() {
            // if maintainance button is selected, display remark label and change status to maintainance
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maintainanceButton.isSelected()){
                    remark_lbl.setVisible(true);
                    remarkField.setVisible(true);
                    status = "maintainance";
                }
                }
        });

        // Radio Button Booking Slot
        bookingButton = new JRadioButton("New Booking Slot");
        bookingButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        bookingButton.setBounds(550,150,250,30);
        bookingButton.setBackground(new Color(248,248,248));
        panel.add(bookingButton);
        bookingButton.addActionListener(new ActionListener(){
            @Override
            // if booking button is selected, hide remark label and change status to booking
            public void actionPerformed(ActionEvent e) {
                if (bookingButton.isSelected()){
                    remark_lbl.setVisible(false);
                    remarkField.setVisible(false);
                    status = "available";
                }
                }

        });

        // Group Radio Button
        ButtonGroup bg=new ButtonGroup();    
        bg.add(maintainanceButton);bg.add(bookingButton);  
        
        // Start Date Picker
        // Create a date model to hold the selected date
        UtilDateModel sdateModel = new UtilDateModel();
        // Set today's date 
        Calendar calendar = Calendar.getInstance();
        sdateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        sdateModel.setSelected(true); 
        // Properties create object to store values in it
        Properties Properties = new Properties();
        Properties.put("text.day", "Day");
        Properties.put("text.month", "Month");
        Properties.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl sDatePanel = new JDatePanelImpl(sdateModel, Properties);
        JDatePickerImpl sDatePicker = new JDatePickerImpl(sDatePanel, new DateFormat());
        // Set the position and size of the date picker and add the datepicker inside UI
        sDatePicker.setBounds(470, 363, 140, 30);
        panel.add(sDatePicker);
        
        // End Date Picker
        // Create a date model to hold the selected date
        UtilDateModel edateModel = new UtilDateModel();
        // Set today's date 
        edateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        edateModel.setSelected(true); 
        // Import Date Panel and Picker
        JDatePanelImpl eDatePanel = new JDatePanelImpl(edateModel, Properties);
        JDatePickerImpl eDatePicker = new JDatePickerImpl(eDatePanel, new DateFormat());
        // Set the position and size of the date picker and add the datepicker inside UI
        eDatePicker.setBounds(470, 485, 140, 30);
        panel.add(eDatePicker);

        sDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get Date value from DatePicker
                Date sDate = (Date) sDatePicker.getModel().getValue();
                // Make start Date must before End Date
                if (sDate.before((Date) eDatePicker.getModel().getValue()) || sDate.equals((Date) eDatePicker.getModel().getValue())) {
                    System.out.println("Start Date Selected: " + sDate);
                } else {
                    System.out.println("Start Date Selected: " + sDate);
                    JOptionPane.showMessageDialog(null,"Cannot choose invalid date",status,JOptionPane.INFORMATION_MESSAGE);
                    sdateModel.setValue((Date) eDatePicker.getModel().getValue());
                }
            }
        });

        eDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get Date value from DatePicker
                Date eDate = (Date) eDatePicker.getModel().getValue();

                // Make End Date must after Start Date
                if (eDate.after((Date) sDatePicker.getModel().getValue()) || eDate.equals((Date) sDatePicker.getModel().getValue())) {
                    System.out.println("This is able" );
                    System.out.println("End Date Selected: " + eDate);
                } else {
                    System.out.println("Unable");
                    System.out.println("End Date Selected: " + eDate);
                    JOptionPane.showMessageDialog(
                        null,
                        "Cannot choose invalid date",
                        status,
                        JOptionPane.INFORMATION_MESSAGE);
                    edateModel.setValue((Date) sDatePicker.getModel().getValue());
                }
            }
        });

        // Add Button
        JButton add_btn = new JButton("Add");
        add_btn.setBounds(420,675,150,30);
        add_btn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        panel.add(add_btn);
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the value from the Date Picker when the button is pressed
                Date sDate = (Date) sDatePicker.getModel().getValue();
                Date eDate = (Date) eDatePicker.getModel().getValue();
                
                // Create Calendar instances 
                Calendar sdateCal = Calendar.getInstance();
                Calendar edateCal = Calendar.getInstance();

                // Set the calendar dates and times to the selected times
                sdateCal.setTime(sDate);
                edateCal.setTime(eDate);
                sdateCal.set(Calendar.HOUR_OF_DAY, 0);
                edateCal.set(Calendar.HOUR_OF_DAY, 0);
                sdateCal.set(Calendar.MINUTE, 0);
                edateCal.set(Calendar.MINUTE, 0);
                sdateCal.set(Calendar.SECOND, 0);
                edateCal.set(Calendar.SECOND, 0); 
                sdateCal.set(Calendar.MILLISECOND, 0);
                edateCal.set(Calendar.MILLISECOND, 0);

                // Get the final updated start and end dates with times
                Date updatedsDate = sdateCal.getTime();
                Date updatedeDate = edateCal.getTime();

                // Determine the remark based on the selected schedule type
                if (bookingButton.isSelected()) {
                    remark = null;
                } else if (maintainanceButton.isSelected()) {
                    remark = remarkField.getText();
                } else if (!bookingButton.isSelected() & !maintainanceButton.isSelected()) {
                    // Show a message if no schedule type is selected
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Choose a Type to Add: Maintainance or Booking",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                 // Check if hall data is selected and a booking or maintenance type is chosen
                if (hallData != null && (bookingButton.isSelected() || maintainanceButton.isSelected())) {
                    // Check for scheduling conflicts
                    if (new Schedule_Maintainance().check_schedule(" ",hallData.get(0), updatedsDate, updatedeDate)) {
                        // If no conflict, add the schedule
                        if (new Schedule_Maintainance().Add_Schedule(hallData.get(0), hallData.get(1), updatedsDate,updatedeDate,status,remark)) {
                             // Show success message
                            JOptionPane.showMessageDialog(
                            null,
                            "Hall Added successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                        } else {
                        // Show failure message if adding schedule fails
                        JOptionPane.showMessageDialog(
                        null,
                        "Hall Added failed",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                         // Show a confirmation dialog if there's a scheduling conflict
                        int choice = JOptionPane.showConfirmDialog(
                            null,
                            "There is an event on that day.\nChecking Schedule,Click 'Yes' and change to 'Schedule Checker Page'\nClick 'No' will stay at this page  ", 
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION); 

                            // Check the user's choice and display a corresponding message 
                            if (choice == JOptionPane.YES_OPTION) { 
                                // If the user chose 'Yes', it will change the page to the schedule checker page
                                dispose();
                                new Schedule_Maintainance_Page(name).setVisible(true);
                            } 
                    }
                    }
                else if (hallData == null) {
                    // Show a message if no hall is selected
                    JOptionPane.showMessageDialog(
                        null,
                        "Please choose a hall",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        // Back Page Pic
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Schedule_Maintainance_Page(name).setVisible(true);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(des4);
    }
}
