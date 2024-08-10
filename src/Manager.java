package src;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
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

    // public static List<String[]> data_filter(int day){
    //     //1, String txt2, String txt3
    //     // this.table_Model = table_Model;
    //     // this.sorter = sorter;

    //     // List<RowFilter<Object, Object>> filter = new ArrayList<>();

    //     List<String[]> filter_Data = new ArrayList<>();



    //     // if(txt.trim().length() == 0){
    //     //     sorter.setRowFilter(null);

    //     // } else{
    //     //     sorter.setRowFilter(RowFilter.regexFilter(txt, 4));
           
    //     // }

    // }

    //DefaultTableModel table_Model, TableRowSorter<DefaultTableModel> sorter,
    public void data_filter(String txt_day, String txt_month, String txt_year){
        //1, String txt2, String txt3
        this.table_Model = table_Model;
        this.sorter = sorter;

        String file_data;

        try (BufferedReader br = new BufferedReader(new FileReader("Sales.txt"))) {

            while ((file_data = br.readLine()) != null){
                String[] f_data = file_data.split(",");
                String date_data = f_data[4];
                String[] new_date_data = date_data.split("-");
                if(txt_day.trim().equals(new_date_data[0])) {
                    sorter.setRowFilter(RowFilter.regexFilter(txt_day, 4));

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        

        // List<RowFilter<Object, Object>> filter = new ArrayList<>();

        // if(txt_day.trim().length() == 0){
        //     sorter.setRowFilter(null);
        // }else{
        //     sorter.setRowFilter(RowFilter.regexFilter(txt_day,4));
        // }

        // if(txt_month.trim().length() == 0){
        //     sorter.setRowFilter(null);
        // }else{
        //     sorter.setRowFilter(RowFilter.regexFilter("-" + txt_month + "-", 4));
        // }

        // if(txt_year.trim().length() == 0){
        //     sorter.setRowFilter(null);
        // }else{
        //     sorter.setRowFilter(RowFilter.regexFilter("-" + txt_year + "$", 4));
        // }

        // if(txt.trim().length() == 0){
        //     sorter.setRowFilter(null);

        // } else{
        //     sorter.setRowFilter(RowFilter.regexFilter(txt, 4));
           
        // // // }


    }

    public JScrollPane present_data(String[] colname){
        String line_Sales;
        ArrayList<String[]> rowData = new ArrayList<>();
        
        try(BufferedReader read_Sales = new BufferedReader(new FileReader("Sales.txt"))) {
            while ((line_Sales = read_Sales.readLine()) != null) {
                String[] row = line_Sales.split(",");
                rowData.add(row);
            }
        } catch (IOException e) {
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

    public JscrollPane task_status(){
        String task;
        ArrayList<String[]> taskStatus = new ArrayList<>();

        try (BufferedReader br_task = new BufferedReader(new FileReader("issues.txt"))){
            while((task = br_task.readLine()) != null){
                String[] row = task.split(",");
                taskStatus.add(row);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

         
    }



    public ArrayList<String> day_list(int year, int month){
        ArrayList<String> day_List = new ArrayList<>();
        int day = 0;
        // Leap year

        if (month == 2) {
            // Feb 29
            if (year % 4 == 0) {
                day = 30;
            } else {
                // Feb 28
                day = 29;
            }
        } else if (month < 8 && month % 2 == 1) {
            // Jan, Mar, May, Jul
            day = 32;
        } else if (month > 7 && month % 2 == 0) {
            // Aug, Oct, Dec
            day = 32;
        } else {
            // Feb, Apr, Jun, Sep, Nov
            day = 31;
        }
        // Add to day list and return
        for (int i = 1; i < day; i++) {
            day_List.add(i + "");
        }

        return day_List;
    }

    public ArrayList<String> month_list() {
        ArrayList<String> month_List = new ArrayList<>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length - 1; i++) {
            month_List.add(months[i]);
        }
        return month_List;
        
    }

    public ArrayList<String> year_list() {
        ArrayList<String> year_List = new ArrayList<>();
        for (int year = LocalDateTime.now().getYear(); year <= Calendar.getInstance().get(Calendar.YEAR) + 1; year++) {
            year_List.add(year + "");
        }
        return year_List;

    }



}
