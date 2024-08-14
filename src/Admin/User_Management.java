package src.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class User_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;

    public JScrollPane view_users(String[] userCol){
    ArrayList<String[]> usersData = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                usersData.add(data);
                }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] userRow = usersData.toArray(new Object[0][]);

        DefaultTableModel users_table = new DefaultTableModel(userRow, userCol);
        JTable table = new JTable(users_table);
        sorter = new TableRowSorter<>(users_table);
        table.setRowSorter(sorter);           
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    
}
