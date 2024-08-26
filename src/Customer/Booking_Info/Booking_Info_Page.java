package src.Customer.Booking_Info;
import src.Customer.Customer_Page;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Booking_Info_Page extends JFrame {
    public static String name;
    public static LocalDate current_date = LocalDate.now();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Booking_Info_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Booking_Info_Page(String n) {
        setTitle("Booking Info");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\Image\\hall.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        // Set Panel
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD,25));
        logo_lbl.setForeground(new Color(169,169,169));
        logo_lbl.setBounds(60,20,160,30);
        contentPane.add(logo_lbl);

        //Logo Pic
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

        // Upcoming Label
        JLabel current_event_lbl = new JLabel("Current Event : ");
        current_event_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        current_event_lbl.setBounds(100, 160, 200, 30);
        contentPane.add(current_event_lbl);

        // Current Table
        String[] col_name = {"Book ID", "Hall ID", "Capacity", "Start date", "End date", "Booking Date"};
        Object[][] current_event_data = new Booking_Info().current_data(n, current_date);
        DefaultTableModel current_table_model = new DefaultTableModel(current_event_data, col_name);
        JTable current_table = new JTable(current_table_model);
        JScrollPane current_scrollPane = new JScrollPane(current_table);
        current_scrollPane.setBounds(100, 200, 800, 100);
        contentPane.add(current_scrollPane);

        // Upcoming Label
        JLabel upcome_lbl = new JLabel("Upcoming Event : ");
        upcome_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        upcome_lbl.setBounds(100, 310, 200, 30);
        contentPane.add(upcome_lbl);

        // Upcoming Table
        Object[][] upcome_data = new Booking_Info().upcome_data(n, current_date);
        DefaultTableModel upcome_table_model = new DefaultTableModel(upcome_data, col_name);
        JTable upcome_table = new JTable(upcome_table_model);
        ListSelectionModel list_upcome_model = upcome_table.getSelectionModel();
        list_upcome_model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane upcome_scrollPane = new JScrollPane(upcome_table);
        upcome_scrollPane.setBounds(100, 350, 800, 150);
        contentPane.add(upcome_scrollPane);

        // Past Label
        JLabel past_lbl = new JLabel("Pass Event : ");
        past_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        past_lbl.setBounds(100, 510, 200, 30);
        contentPane.add(past_lbl);

        // Past Table
        Object[][] past_data = new Booking_Info().past_data(n, current_date);
        DefaultTableModel past_table_model = new DefaultTableModel(past_data, col_name);
        JTable past_table = new JTable(past_table_model);
        JScrollPane past_scrollPane = new JScrollPane(past_table);
        past_scrollPane.setBounds(100, 550, 800, 150);
        contentPane.add(past_scrollPane);

        // Cancel Button
        JButton cancel_book_btn = new JButton("Cancel Booking");
        cancel_book_btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        cancel_book_btn.setBounds(700, 710, 200, 30);
        contentPane.add(cancel_book_btn);

        // Upcoming table action listener
        ArrayList<String> selected_data = new ArrayList<>();
        list_upcome_model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] sel;
                Object value;
                selected_data.clear();
                if (!e.getValueIsAdjusting()) {
                    sel = upcome_table.getSelectedRows();
                    if (sel.length > 0) {
                        for (int i = 0; i < col_name.length; i++) {
                            TableModel tm = upcome_table.getModel();
                            value = tm.getValueAt(sel[0], i);
                            selected_data.add(value.toString());
                        }
                    }
                }
            }
        });

        cancel_book_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Confirm cancel?", 
                    "Cancel booking confirmation", 
                    JOptionPane.OK_CANCEL_OPTION);
                if (response == 0) {
                    // go cancel
                    if (new Booking_Info().cancel_book(selected_data.toArray(new String[0]))) {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Booking cancel successfully", 
                            "Booking cancel status", 
                            JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new Booking_Info_Page(n);
                    }
                } else {
                    System.out.println("No cancel booking");
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
