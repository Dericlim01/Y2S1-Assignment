import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;
import javax.swing.table.DefaultTableModel;

public class Manager {

    private DefaultTableModel table_Model;
    private TableRowSorter<DefaultTableModel> sorter;

    public String[] read_user_Information(String n){
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        try(BufferedReader read = new BufferedReader(new FileReader("users.txt"));){
            while((line = read.readLine()) != null){
                // Create array and store data 
                data = line.split(",");
                users.add(data);
            }
            // Search in arrayList
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i)[0].equals(n)){
                    return users.get(i);
                }
            }
   
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    //DefaultTableModel table_Model, TableRowSorter<DefaultTableModel> sorter,
    public void data_filter(String txt_day, String txt_month, String txt_year){
        //1, String txt2, String txt3
        this.table_Model = table_Model;
        this.sorter = sorter;

        List<RowFilter<Object, Object>> filter = new ArrayList<>();


        if(txt_day != null && !txt_day.trim().isEmpty()){

            String regexDay = String.format("^%02d/", Integer.parseInt(txt_day));
            filter.add(RowFilter.regexFilter(regexDay , 4));
            return;
        }
        

        if(txt_month != null && !txt_month.trim().isEmpty()){
            String regexMonth = String.format("/%s/", txt_month);
            filter.add(RowFilter.regexFilter(regexMonth, 4));
            return;
        }

        if(txt_year != null && !txt_year.trim().isEmpty()){
            String regexYear = String.format("/%s$", txt_year);
            filter.add(RowFilter.regexFilter(regexYear, 4));
            return;
        }

        if(filter.isEmpty()){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.orFilter(filter));
        }


        



        // if(txt_day.trim().length() == 0 && txt_month.trim().length() == 0 && txt_year.trim().length() == 0){
        //     sorter.setRowFilter(null);

        // } else{
        //     sorter.setRowFilter(RowFilter.regexFilter(txt_day, 4));
        //     sorter.setRowFilter(RowFilter.regexFilter(txt_month, 4));
        //     sorter.setRowFilter(RowFilter.regexFilter(txt_year, 4));
        // }
    }

    public JScrollPane present_data(String[] colname){
        String line_Sales;
        ArrayList<String[]> rowData = new ArrayList<>();
        
        try(BufferedReader read_Sales = new BufferedReader(new FileReader("Sales.txt"))){
            while((line_Sales = read_Sales.readLine()) != null){
                String[] row = line_Sales.split(",");
                rowData.add(row);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Object[][] row_Data = rowData.toArray(new Object[0][]);

        table_Model = new DefaultTableModel(row_Data, colname);
        JTable view = new JTable(table_Model);
        sorter = new TableRowSorter<>(table_Model);
        view.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(view);

        return scrollPane;

    }

    public JScrollPane view_issues(String[] colname){
        String line_Sales;
        ArrayList<String[]> issuess = new ArrayList<>();
        
        try(BufferedReader read_Sales = new BufferedReader(new FileReader("issues.txt"))){
            while((line_Sales = read_Sales.readLine()) != null){
                String[] row = line_Sales.split(",");
                issuess.add(row);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Object[][] issues = issuess.toArray(new Object[0][]);

        table_Model = new DefaultTableModel(issues, colname);
        JTable view = new JTable(table_Model);
        sorter = new TableRowSorter<>(table_Model);
        view.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(view);

        return scrollPane;

    }



    // public Object[][] present_data(){
    //     String line_Sales;
    //     ArrayList<String[]> rowData = new ArrayList<>();
    //     try(BufferedReader read_Sales = new BufferedReader(new FileReader("Sales.txt"))){
    //         while((line_Sales = read_Sales.readLine()) != null){
    //             String[] row = line_Sales.split(",");
    //             rowData.add(row);
    //         }
    //     }catch(IOException e){
    //         e.printStackTrace();
    //     }

    //     Object[][] row_Data = rowData.toArray(new Object[0][]);

    //     return row_Data;

    // }

    // public void date_based_on_month(JComboBox day,JComboBox month, JComboBox year){
    //     String month_selected = (String) month.getSelectedItem();
    //     String year_selected = (String) year.getSelectedItem();
    //     int year_select = Integer.parseInt(year_selected);

    //     if(month_selected != null){
    //         switch(month_selected){
    //             case "January":
    //             case "March":
    //             case "May":
    //             case "July":
    //             case "August":
    //             case "October":
    //             case "December":
    //                 day.removeAllItems();
    //                 for(int i = 1; i <= 31; i++){
    //                     day.addItem(String.valueOf(i));
    //                 }

    //             case "February":
    //                 if((year_select % 4 == 0 && year_select % 100 != 0) ||year_select % 400 == 0){
    //                     day.removeAllItems();
    //                     for(int i = 1; i <= 29; i++){
    //                     day.addItem(String.valueOf(i));
    //     }
    //                 }else{
    //                     day.removeAllItems();
    //                     for(int i = 1; i <= 28; i++){
    //                     day.addItem(String.valueOf(i));
    //     }
    //                 }

    //             case "April":
    //             case "June":
    //             case "September":
    //             case "November":
    //                 day.removeAllItems();
    //                 for(int i = 1; i <= 30; i++){
    //                     day.addItem(String.valueOf(i));
    //                 }

    //         }
    //     }

    // }

}
