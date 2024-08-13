package src.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import src.Create_file;

public class Admin_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;

    public Admin_Management (String name){

    }

    //Check the unique adminname
    //Check the unique staffname
        Create_file file = new Create_file();
        public Boolean check_staff (String name) {
        if(file.staffs_file()){
            try (BufferedReader read_staff = new BufferedReader(new FileReader("resources/Database/userss.txt"))){
                while ((line = read_staff.readLine()) != null){
                    String[] data = line.split(",");
                    String staffname = data[0];
                    if(staffname.equals(name)){
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }    

    }
    return true;
    }


    public JScrollPane view_admin(String[] adCol){
    ArrayList<String[]> adData = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if(data[4].equals("admin")){
                    adData.add(data);
                }
                }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] adRow= adData.toArray(new Object[0][]);

        DefaultTableModel admin_table = new DefaultTableModel(adRow,adCol);
        JTable table = new JTable(admin_table);
        sorter = new TableRowSorter<>(admin_table);
        table.setRowSorter(sorter);           
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }


}
