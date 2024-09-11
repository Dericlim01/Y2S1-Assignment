package src.Scheduler;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;


public class Hall_Management_Page extends JFrame {
  public static String name;
  private static int selectedRow = -1;
  private static String hallID;
  private static String halltype;
  private static int capacity;
  private static double price;
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Hall_Management_Page(name).setVisible(true));
    }

    public Hall_Management_Page(String name){
        setTitle("Hall Info Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);


        // Title Label
        JLabel titleLabel = new JLabel("Hall Management");
        titleLabel.setBounds(340, -80, 370, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.PLAIN,25));
        panel.add(titleLabel);

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall type : ");
        hall_type_lbl.setBounds(562, 110, 150, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(hall_type_lbl);

        // HallID label
        JLabel hallID_lbl = new JLabel("Hall ID : ");
        hallID_lbl.setBounds(152, 110, 100, 30);
        hallID_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(hallID_lbl);

        // HallID Content label
        JLabel hallID_ctn_lbl = new JLabel("");
        hallID_ctn_lbl.setBounds(250, 110, 100, 30);
        hallID_ctn_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
        panel.add(hallID_ctn_lbl);

        // Hall Capacity label
        JLabel hallCap_lbl = new JLabel("Hall Capacity:");
        hallCap_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        hallCap_lbl.setBounds(110,180,150,50);
        panel.add(hallCap_lbl);

        // Price per hour label
        JLabel pricePerH_lbl = new JLabel("Price Per Hour:");
        pricePerH_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        pricePerH_lbl.setBounds(520, 180,150,50);
        panel.add(pricePerH_lbl);

        // Filter label
        JLabel filterlbl = new JLabel("Filter By Hall Type:");
        filterlbl.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        filterlbl.setBounds(40,550,300,50);
        panel.add(filterlbl);



        // Hall type  ComboBox
        String[] hallType = {"Auditorium", "BanquetHall", "MeetingRoom", "Others"};
        JComboBox<String> hallTypeBox = new JComboBox<>(hallType);
        hallTypeBox.setBounds(670,115,150,28);
        hallTypeBox.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
        hallTypeBox.setBackground(new Color(250,240,230));
        hallTypeBox.setSelectedIndex(-1);
        hallTypeBox.setEnabled(false);
        panel.add(hallTypeBox);

        // Hall type Filter ComboBox
        JComboBox<String> hallTypeFilterBox = new JComboBox<>(hallType);
        hallTypeFilterBox .setBounds(220,565,150,25);
        hallTypeFilterBox .setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        hallTypeFilterBox .setBackground(new Color(250,240,230));
        hallTypeFilterBox .setSelectedIndex(-1);
        panel.add(hallTypeFilterBox );


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
        priceField.setBounds(670,190,150,30);
        priceField.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        priceField.setEditable(false);
        panel.add(priceField);

        // Spinner for Capacity
         SpinnerModel value =  
             new SpinnerNumberModel(20, //initial value  
                0, //minimum value  
                1000, //maximum value  
                1); //step  
        JSpinner capacitySpinner = new JSpinner(value);   
        capacitySpinner.setBounds(250,190,150,30);    
        capacitySpinner.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        capacitySpinner.setEnabled(false);
        panel.add(capacitySpinner); 
      
        // table
        String[] col_name = {"Hall ID", "Hall Type", "Capacity", "Price per Hour"};
        Object[][] data =  new Hall_Management().search_hall_data(hallTypeBox.getSelectedItem());;
        DefaultTableModel table = new DefaultTableModel(data, col_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        
        JTable details = new JTable(table);
        JScrollPane scrollPane = new JScrollPane(details);
        details.setEditingColumn(ABORT);
        details.setEditingRow(ABORT);
        details.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        scrollPane.setBounds(30, 290, 930, 270);
        panel.add(scrollPane);
        
        //button
        JButton addHallBtn = new JButton("Add New Hall");
        addHallBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        addHallBtn.setBackground(new Color(250,240,230));
        addHallBtn.setForeground(new Color(128,128,128));
        addHallBtn.setBounds(655,670,230,35);
        panel.add(addHallBtn);
        addHallBtn.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Scheduler_Main_Page smp = new Scheduler_Main_Page(name);
                smp.setTitle("Scheduler Main Page");
                smp.setVisible(true);
        }
        });


        // Show All Button
        JButton showAllBtn = new JButton("Show All");
        showAllBtn.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        showAllBtn.setBackground(new Color(250,240,230));
        showAllBtn.setForeground(new Color(128,128,128));
        showAllBtn.setBounds(840,240,120,30);
        panel.add(showAllBtn);

         // Delete Schedule Button
         JButton deleteHallBtn = new JButton("Delete Hall");
         deleteHallBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
         deleteHallBtn.setBackground(new Color(250,240,230));
         deleteHallBtn.setForeground(new Color(128,128,128));
         deleteHallBtn.setBounds(65,670,230,35);
         panel.add(deleteHallBtn);
 
         // Edit Schedule Button
         JButton editHallBtn = new JButton("Edit Hall");
         editHallBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
         editHallBtn.setBackground(new Color(250,240,230));
         editHallBtn.setForeground(new Color(128,128,128));
         editHallBtn.setBounds(360,670,230,35);
         panel.add(editHallBtn);

         // Edit Schedule Button
         JButton editDoneBtn = new JButton("Edit Done");
         editDoneBtn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
         editDoneBtn.setBackground(new Color(250,240,230));
         editDoneBtn.setForeground(new Color(128,128,128));
         editDoneBtn.setBounds(655,670,230,35);
         editDoneBtn.setVisible(false);
         panel.add(editDoneBtn);

         // Get Selected row content on table
        details.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the selected row index
                selectedRow = details.getSelectedRow();
                
                // If a row is selected, fetch the values
                if (selectedRow != -1) {
                    // Get values from each column in the selected row
                    hallID =details.getValueAt(selectedRow, 0).toString();
                    halltype = details.getValueAt(selectedRow, 1).toString();
                    capacity = Integer.parseInt(details.getValueAt(selectedRow, 2).toString());
                    price = Double.parseDouble(details.getValueAt(selectedRow, 3).toString());


                    // put values to the swing components
                    hallID_ctn_lbl.setText(hallID);
                    hallTypeBox.setSelectedItem(halltype);
                    capacitySpinner.setValue(capacity);
                    priceField.setValue(price);

                }
            }
        });

        editHallBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    editDoneBtn.setVisible(true);
                    editHallBtn.setVisible(false);
                    hallTypeBox.setEnabled(true);
                    hallTypeFilterBox.setEnabled(false);
                    deleteHallBtn.setVisible(false);
                    addHallBtn.setVisible(false);
                    capacitySpinner.setEnabled(true);
                    priceField.setEditable(true);
                    showAllBtn.setVisible(false);
                    details.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null,"Please Select A Row", "Error",JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        });

        editDoneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    editDoneBtn.setVisible(false);
                    editHallBtn.setVisible(true);
                    deleteHallBtn.setVisible(true);
                    hallTypeBox.setEnabled(false);
                    hallTypeFilterBox.setEnabled(true);
                    addHallBtn.setVisible(true);
                    capacitySpinner.setEnabled(false);
                    priceField.setEditable(false);
                    showAllBtn.setVisible(true);
                    details.setEnabled(true);

                    if(new Hall_Management().edit_Hall_Info(hallID_ctn_lbl.getText(),
                    hallTypeBox.getSelectedItem().toString(),
                    (int) capacitySpinner.getValue(),
                    (Double) priceField.getValue()))
                    {
                        JOptionPane.showMessageDialog(null,"Edit Successful", "Success",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"Edit Failed", "Error",JOptionPane.INFORMATION_MESSAGE);
                    }

                    Object[][] newData = new Hall_Management().search_hall_data(hallTypeFilterBox.getSelectedItem());
                    // Clear the existing rows in the table model
                    table.setRowCount(0);

                    // Add new data to the table model
                    for (Object[] row : newData) {
                        table.addRow(row);
                    }
                    // Table done refresh
                        
                } else {
                    JOptionPane.showMessageDialog(null,"Please Select A Row", "Error",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteHallBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    if (new Hall_Management().delete_Hall(hallID)){
                        JOptionPane.showMessageDialog(null,"Delete Successful", "Success",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"Delete Failed", "Error",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Object[][] newData = new Schedule_Maintainance().search_hall_schedule(hallTypeFilterBox.getSelectedItem());
                    // Clear the existing rows in the table model
                    table.setRowCount(0);

                    // Add new data to the table model
                    for (Object[] row : newData) {
                        table.addRow(row);
                    }// Table done refresh
                } else {
                    JOptionPane.showMessageDialog(null,"Please Select A Row", "Error",JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        });

        showAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hallTypeFilterBox.setSelectedIndex(-1);
                Object[][] newData = new Hall_Management().search_hall_data(hallTypeFilterBox.getSelectedItem());
                // Clear the existing rows in the table model
                table.setRowCount(0);

                // Add new data to the table model
                for (Object[] row : newData) {
                    table.addRow(row);
                }
                // Table done refresh
            }
        });

        // Filter table based on hall type combobox choosing
        hallTypeFilterBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] newData = new Hall_Management().search_hall_data(hallTypeFilterBox.getSelectedItem());
                // Clear the existing rows in the table model
                table.setRowCount(0);
                // Add new data to the table model
                for (Object[] row : newData) {
                    table.addRow(row);
                }
                // Table done refresh
            }
        });

        //Home Page Label
        JLabel back_lbl = new JLabel();
        try{
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);

        } catch(IOException e){
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Scheduler_Main_Page mp = new Scheduler_Main_Page(name);
                mp.setTitle("Scheduler Main Page");
                mp.setVisible(true);
            }
        });
        panel.add(back_lbl);


        //Design 4 Pic
        JLabel des4 = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        panel.add(des4);
}}

