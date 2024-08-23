package src.Customer.Hall_Booking;
import src.DateFormat;
import src.Customer.Customer_Page;
import src.Customer.Payment.Payment_Page;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Hall_Booking_Page
 */
public class Hall_Booking_Page extends JFrame {
    public static String name;

    // Today's date
    public static LocalDate current_date = LocalDate.now();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Hall_Booking_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Hall_Booking_Page(String n) {
        setTitle("Hall Booking");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        
        // Set Panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248,248,255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

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
        contentPane.add(logo);

        // Hall Booking Label
        JLabel hall_book_lbl = new JLabel("Hall Booking");
        hall_book_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        hall_book_lbl.setBounds(400, 50, 200, 45);
        contentPane.add(hall_book_lbl);

        // Hall type label
        JLabel hall_type_lbl = new JLabel("Hall type : ");
        hall_type_lbl.setBounds(30, 110, 100, 30);
        hall_type_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(hall_type_lbl);
        
        // Hall type
        ArrayList<String> hallsList = new Hall_Booking().arrange_hall_type();
        String[] halls = hallsList.toArray(new String[0]);
        JComboBox<String> hall_type_cmbbx = new JComboBox<String>(halls);
        hall_type_cmbbx.setBounds(175, 110, 125, 25);
        hall_type_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        hall_type_cmbbx.setSelectedIndex(-1);
        contentPane.add(hall_type_cmbbx);

        // Start Date
        JLabel start_date_lbl = new JLabel("Start Date : ");
        start_date_lbl.setBounds(30, 150, 150, 30);
        start_date_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(start_date_lbl);

        // End Date
        JLabel end_date_lbl = new JLabel("End Date : ");
        end_date_lbl.setBounds(30, 190, 150, 30);
        end_date_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        contentPane.add(end_date_lbl);

        // Remarks label
        JLabel remark_lbl = new JLabel("Remarks : ");
        remark_lbl.setBounds(30, 230, 150,30);
        remark_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(remark_lbl);

        // Remarks text area
        JTextField remark_txtarea = new JTextField();
        remark_txtarea.setBounds(175, 230, 400, 30);
        remark_txtarea.setForeground(new Color(169, 169, 169));
        remark_txtarea.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentPane.add(remark_txtarea);

        // Table
        String[] col_name = {"Hall ID", "Hall Type", "Capacity", "Price per H", "Start date", "End date", "Status", "Remarks"};
        Object[][] data = new Hall_Booking().start_date_filter(current_date, String.valueOf(hall_type_cmbbx.getSelectedIndex()));
        DefaultTableModel table_model = new DefaultTableModel(data, col_name);
        JTable table = new JTable(table_model);
        ListSelectionModel list_model = table.getSelectionModel();
        list_model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 300, 800, 400);
        contentPane.add(scrollPane);

        ArrayList<String> selected_data = new ArrayList<>();
        list_model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] sel;
                Object value;
                selected_data.clear();
                
                if (!e.getValueIsAdjusting()) {
                    sel = table.getSelectedRows();

                    if (sel.length > 0) {
                        for (int i = 0; i < col_name.length; i++) {
                            TableModel tm = table.getModel();
                            value = tm.getValueAt(sel[0], i);
                            selected_data.add(value.toString());
                        }
                    }
                }
            }
        });

        // Start Calendar
        UtilDateModel start_date_model = new UtilDateModel();
        // Properties create object to store values in it
        Properties start_date_prop = new Properties();
        start_date_prop.put("text.day", "Day");
        start_date_prop.put("text.month","Month");
        start_date_prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl start_datePanel = new JDatePanelImpl(start_date_model, start_date_prop);
        JDatePickerImpl start_datePicker = new JDatePickerImpl(start_datePanel, new DateFormat());
        start_datePicker.setBounds(175,150,140,30);
        contentPane.add(start_datePicker);

        // End Calendar
        UtilDateModel end_date_model = new UtilDateModel();
        // Properties create object to store values in it
        Properties end_date_prop = new Properties();
        end_date_prop.put("text.day", "Day");
        end_date_prop.put("text.month","Month");
        end_date_prop.put("text.year", "Year");
        // Import Date Panel and Picker
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_date_model, end_date_prop);
        JDatePickerImpl end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormat());
        end_datePicker.setBounds(175,190,140,30);
        contentPane.add(end_datePicker);

        // Hall type combobox action listener
        hall_type_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hall_type = String.valueOf(hall_type_cmbbx.getSelectedItem());
                Object[][] hall_data = new Hall_Booking().start_date_filter(current_date, hall_type);
                table_model.setDataVector(hall_data, col_name);
                table.revalidate();
                table.repaint();
            }
        });

        // Start Calander action listener
        start_datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selected_start_date = (Date) start_datePicker.getModel().getValue();
                if (selected_start_date != null) {
                    LocalDate start_date = selected_start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String halltype;
                    if (String.valueOf(hall_type_cmbbx.getSelectedIndex()).equals("-1")) {
                        halltype = "-1";
                    } else {
                        halltype = String.valueOf(hall_type_cmbbx.getSelectedItem());
                    }
                    Date selected_end_date = (Date) end_datePicker.getModel().getValue();
                    if (selected_end_date != null) {
                        LocalDate end_date = selected_end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        Object[][] start_end_date = new Hall_Booking().end_date_filter(start_date, halltype, end_date);
                        table_model.setDataVector(start_end_date, col_name);
                    } else {
                        Object[][] start_date_data = new Hall_Booking().start_date_filter(start_date, halltype);
                        table_model.setDataVector(start_date_data, col_name);
                    }
                    table.revalidate();
                    table.repaint();
                }
            }
        });
        
        // End Calander action listener
        end_datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selected_end_date = (Date) end_datePicker.getModel().getValue();
                Date selected_start_date = (Date) start_datePicker.getModel().getValue();
                LocalDate start_date = current_date;
                if (selected_start_date != null) {
                    start_date = selected_start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
                if (selected_end_date != null) {
                    // End date
                    LocalDate end_date = selected_end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // Hall type
                    String halltype;
                    if (String.valueOf(hall_type_cmbbx.getSelectedIndex()).equals("-1")) {
                        halltype = "-1";
                    } else {
                        halltype = String.valueOf(hall_type_cmbbx.getSelectedItem());
                    }
                    Object[][] end_date_data = new Hall_Booking().end_date_filter(start_date, halltype, end_date);
                    table_model.setDataVector(end_date_data, col_name);
                    table.revalidate();
                    table.repaint();
                }
            }
        });

        // Next button
        JButton search_btn = new JButton("Proceed");
        search_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search_btn.setBounds(780, 230, 120, 30);
        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected_data.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "No row is selected, pls select one", 
                        "Row selected status", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    dispose();
                    new Payment_Page(n, selected_data.toArray(new String[0])).setVisible(true);
                }
            }
        });
        contentPane.add(search_btn);

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
                new Customer_Page(n).setVisible(true);
            }
        });
        contentPane.add(back_lbl);

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
        contentPane.add(des4);
    }
}