package src.Scheduler;

import src.shared.DateFormat;

import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;

public class Schedule_Maintainance_Page extends JFrame {
    private static String name, schedule_ID;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static int selectedRow = -1;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Schedule_Maintainance_Page(name).setVisible(true));
    }
    
    public Schedule_Maintainance_Page(String name) {
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
        hall_type_lbl.setBounds(48, 110, 100, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(hall_type_lbl);
    
        // Start Date label
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setBounds(30, 150, 150, 30);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(start_date_lbl);

        // End Date label
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setBounds(44, 190, 150, 30);
        end_date_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(end_date_lbl);

        // Status label
        JLabel status_lbl = new JLabel("Status : ");
        status_lbl.setBounds(355, 150, 150, 30);
        status_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(status_lbl);

        // HallID label
        JLabel hallID_lbl = new JLabel("Hall ID : ");
        hallID_lbl.setBounds(350, 110, 150, 30);
        hallID_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(hallID_lbl);

        // Remark label
        JLabel rmk_lbl = new JLabel("Remark : ");
        rmk_lbl.setBounds(59, 230, 150, 30);
        rmk_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(rmk_lbl);

        // Hall type ComboBox
        ArrayList<String> hallsList = new Schedule_Maintainance().search_hall_type() ;
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_type_cmbbx = new JComboBox<String>(halls);
        hall_type_cmbbx.setBounds(150, 115, 125, 25);
        hall_type_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_type_cmbbx.setSelectedIndex(-1);
        panel.add(hall_type_cmbbx);

        // Status ComboBox
        String status[] = {"available","maintainance"};
        JComboBox<String> status_cmbbx = new JComboBox<String>(status);
        status_cmbbx.setBounds(450, 155, 125, 25);
        status_cmbbx.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        status_cmbbx.setEnabled(false);
        status_cmbbx.setSelectedIndex(-1);
        panel.add(status_cmbbx);

        // Hall ID Combo Box
        // Calling the search_hall_ID page from Schedule_Maintainance.java
        // Get the list of hall ID from hall.txt 
        ArrayList<String> hallIDList = new Schedule_Maintainance().search_hall_ID();
        // Convert it to array , make it able to add in JcomboBox
        String[] hallID = hallIDList.toArray(new String[0]);
        // Add those hallID into comboBox
        JComboBox<String> hallID_cmbbx = new JComboBox<String>(hallID);
        // setup the combobox
        hallID_cmbbx.setBounds(450, 115, 125, 25);
        hallID_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hallID_cmbbx.setSelectedIndex(-1);
        hallID_cmbbx.setEnabled(false);
        panel.add(hallID_cmbbx);

        // Start Date Picker
        // Create a date model to hold the selected date
        UtilDateModel sdateModel = new UtilDateModel();
        // Properties create object to store values in it
        Properties Properties = new Properties();
        Properties.put("text.day", "Day");
        Properties.put("text.month", "Month");
        Properties.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl sDatePanel = new JDatePanelImpl(sdateModel, Properties);
        JDatePickerImpl sDatePicker = new JDatePickerImpl(sDatePanel, new DateFormat());
        // Set the position and size of the date picker and add the datepicker inside UI
        sDatePicker.setBounds(150, 155, 150, 30);
        sDatePicker.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        sDatePicker.getComponent(1).setEnabled(false);
        panel.add(sDatePicker);

        // End Date Picker
        // Create a date model to hold the selected date
        UtilDateModel edateModel = new UtilDateModel();
        // Import Date Panel and Picker
        JDatePanelImpl eDatePanel = new JDatePanelImpl(edateModel, Properties);
        JDatePickerImpl eDatePicker = new JDatePickerImpl(eDatePanel, new DateFormat());
        // Set the position and size of the date picker and add the datepicker inside UI
        eDatePicker.setBounds(150, 195, 150, 30);
        eDatePicker.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        eDatePicker.getComponent(1).setEnabled(false);
        panel.add(eDatePicker);

        // Remark Text Box
        JTextField rmk_txtField = new JTextField();
        rmk_txtField.setBounds(150, 235, 600, 25);
        rmk_txtField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        rmk_txtField.setEditable(false);
        panel.add(rmk_txtField);

        // Add Schedule Button
        JButton addSchdlBtn = new JButton("Add New Schedule");
        addSchdlBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        addSchdlBtn.setBackground(new Color(250,240,230));
        addSchdlBtn.setForeground(new Color(128,128,128));
        addSchdlBtn.setBounds(670,670,230,35);
        panel.add(addSchdlBtn);

        // Delete Schedule Button
        JButton deleteSchdlBtn = new JButton("Delete Schedule");
        deleteSchdlBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        deleteSchdlBtn.setBackground(new Color(250,240,230));
        deleteSchdlBtn.setForeground(new Color(128,128,128));
        deleteSchdlBtn.setBounds(65,670,230,35);
        panel.add(deleteSchdlBtn);

        // Edit Schedule Button
        JButton editSchdlBtn = new JButton("Edit Schedule");
        editSchdlBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        editSchdlBtn.setBackground(new Color(250,240,230));
        editSchdlBtn.setForeground(new Color(128,128,128));
        editSchdlBtn.setBounds(360,670,230,35);
        panel.add(editSchdlBtn);

        // Show All Button
        JButton showAllBtn = new JButton("Show All");
        showAllBtn.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        showAllBtn.setBackground(new Color(250,240,230));
        showAllBtn.setForeground(new Color(128,128,128));
        showAllBtn.setBounds(840,240,120,30);
        panel.add(showAllBtn);

        // Edit Done Button
        JButton editDoneBtn = new JButton("Edit Done");
        editDoneBtn.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        editDoneBtn.setBackground(new Color(250,240,230));
        editDoneBtn.setForeground(new Color(128,128,128));
        editDoneBtn.setBounds(840,240,120,30);
        editDoneBtn.setVisible(false);
        panel.add(editDoneBtn);
        
        // table
        String[] col_name = {"Hall Schedule ID", "Hall ID", "Start Date", "End Date",  "Status", "Comment", "Booking ID"};
        Object[][] data =  new Schedule_Maintainance().search_hall_schedule(hall_type_cmbbx.getSelectedItem());
        DefaultTableModel table = new DefaultTableModel(data, col_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        JTable details = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(details);
        scrollPane.setBounds(30, 290, 930, 350);
        panel.add(scrollPane);

        // Filter table based on hall type combobox choosing
        hall_type_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] newData = new Schedule_Maintainance().search_hall_schedule(hall_type_cmbbx.getSelectedItem());
                // Clear the existing rows in the table model
                table.setRowCount(0);

                // Add new data to the table model
                for (Object[] row : newData) {
                    table.addRow(row);
                }
                // Refresh the table
                table.fireTableDataChanged();
            }
        });

        // Add Schedule
        addSchdlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Add_New_Schedule_Page(name).setVisible(true);
            }
        });

        // Get Selected row content on table
        details.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the selected row index
                selectedRow = details.getSelectedRow();
                
                // If a row is selected, fetch the values
                if (selectedRow != -1) {
                    // Get values from each column in the selected row
                    hallID_cmbbx.setSelectedItem(details.getValueAt(selectedRow, 1).toString());
                    schedule_ID = details.getValueAt(selectedRow, 0).toString();

                    String selectedSDateStr = details.getValueAt(selectedRow, 3).toString();
                    Date selectedSDate = null;
                    try {
                        selectedSDate = dateFormat.parse(selectedSDateStr);
                    } catch (ParseException g) {
                        g.printStackTrace();
                    }
                    // Set the parsed Date value to the date picker
                    if (selectedSDate != null) {
                        sdateModel.setValue(selectedSDate); // Set the Date value to the model
                        sdateModel.setSelected(true); // Mark the date as selected
                    }
                    
                    String selectedEDateStr = details.getValueAt(selectedRow, 4).toString();
                    Date selectedEDate = null;
                    try {
                        selectedEDate = dateFormat.parse(selectedEDateStr);
                    } catch (ParseException g) {
                        g.printStackTrace();
                    }
                    // Set the parsed Date value to the date picker
                    if (selectedEDate != null) {
                        edateModel.setValue(selectedEDate); // Set the Date value to the model
                        edateModel.setSelected(true); // Mark the date as selected
                    }
                    status_cmbbx.setSelectedItem(details.getValueAt(selectedRow, 5).toString());
                    rmk_txtField.setText(details.getValueAt(selectedRow, 6).toString());
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Select A Row",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }   
            }                 
        });

        editSchdlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    editDoneBtn.setVisible(true);
                    showAllBtn.setVisible(false);
                    sDatePicker.getComponent(1).setEnabled(true);
                    eDatePicker.getComponent(1).setEnabled(true);
                    addSchdlBtn.setVisible(false);
                    deleteSchdlBtn.setVisible(false);
                    editSchdlBtn.setVisible(false);
                    status_cmbbx.setEnabled(true);
                    hall_type_cmbbx.setEnabled(false);
                    hallID_cmbbx.setEnabled(true);
                    details.setEnabled(false);
                    rmk_txtField.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Select A Row",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        });

        editDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    editDoneBtn.setVisible(false);
                    showAllBtn.setVisible(true);
                    sDatePicker.getComponent(1).setEnabled(false);
                    eDatePicker.getComponent(1).setEnabled(false);
                    addSchdlBtn.setVisible(true);
                    deleteSchdlBtn.setVisible(true);
                    rmk_txtField.setEditable(false);
                    status_cmbbx.setEnabled(false);
                    editSchdlBtn.setVisible(true);
                    hallID_cmbbx.setEnabled(false);
                    hall_type_cmbbx.setEnabled(true);
                    details.setEnabled(true);

                    Date updatedsDate = (Date) sDatePicker.getModel().getValue(); 
                    Date updatedeDate = (Date) eDatePicker.getModel().getValue();
                    if (new Schedule_Maintainance().check_schedule(schedule_ID, hallID_cmbbx.getSelectedItem().toString(), updatedsDate, updatedeDate)) {
                        if (new Schedule_Maintainance().edit_Schedule(schedule_ID, hallID_cmbbx.getSelectedItem().toString(), updatedsDate,
                        updatedeDate, status_cmbbx.getSelectedItem().toString(), rmk_txtField.getText())) {
                            JOptionPane.showMessageDialog(
                                null,
                                "Edit Successful",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Edit Failed",
                                "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showConfirmDialog(
                            null,
                            "There is an event on that day.\n" +
                            "Checking Schedule,Click 'Yes' and change to 'Schedule Checker Page'\n" +
                            "Click 'No' will stay at this page  ", 
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION); 
                    }
                    hall_type_cmbbx.setSelectedIndex(-1);
                    Object[][] newData = new Schedule_Maintainance().search_hall_schedule(hall_type_cmbbx.getSelectedItem());
                    // Clear the existing rows in the table model
                    table.setRowCount(0);

                    // Add new data to the table model
                    for (Object[] row : newData) {
                        table.addRow(row);
                    }
                    // Table done refresh
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Select A Row",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }   
            }           
        });

        showAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hall_type_cmbbx.setSelectedIndex(-1);
                Object[][] newData = new Schedule_Maintainance().search_hall_schedule(hall_type_cmbbx.getSelectedItem());
                // Clear the existing rows in the table model
                table.setRowCount(0);

                // Add new data to the table model
                for (Object[] row : newData) {
                    table.addRow(row);
                }
                // Table done refresh
            }
        });

        deleteSchdlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    if (new Schedule_Maintainance().delete_Schedule(schedule_ID)) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Delete Successful",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "Delete Failed",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    Object[][] newData = new Schedule_Maintainance().search_hall_schedule(hall_type_cmbbx.getSelectedItem());
                    // Clear the existing rows in the table model
                    table.setRowCount(0);

                    // Add new data to the table model
                    for (Object[] row : newData) {
                        table.addRow(row);
                    }// Table done refresh
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Select A Row",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        });

        // Home Page Label
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
                new Scheduler_Main_Page(name).setVisible(true);
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