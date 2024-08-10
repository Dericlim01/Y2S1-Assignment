package src;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;

public class Manager {

    private DefaultTableModel table_Model;
    private TableRowSorter<DefaultTableModel> sorter;

    public String[] read_user_Information(String n){
        ArrayList<String[]> users = new ArrayList<>();
        String line;
        String[] data;
        if (new Create_file().user_file()) {
            try(BufferedReader read = new BufferedReader(new FileReader("resources/users.txt"));){
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
        }

        return null;
    }

    public Object[][] data_filter(String txt_day, String txt_month, String txt_year) {
        List<String[]> filteredData = new ArrayList<>();
        String file_data;
        try (BufferedReader br = new BufferedReader(new FileReader("resources/bookings.txt"))) {
            while ((file_data = br.readLine()) != null) {
                String[] f_data = file_data.split(",");
                String date_data = f_data[4].trim();
                String[] new_date_data = date_data.split("-");

                String day = new_date_data[0];
                String month = new_date_data[1];
                String year = new_date_data[2];

                boolean dayMatch = txt_day.trim().equals(day) || txt_day.trim().equals(String.valueOf(Integer.parseInt(day)));
                boolean monthMatch = txt_month.trim().equals(month);
                boolean yearMatch = txt_year.trim().equals(year);

                // if (dayMatch && monthMatch && yearMatch) {
                //     filteredData.add(f_data);
                // }
                // if (dayMatch) {
                //     filteredData.add(f_data);
                // } 

                // if (monthMatch) {
                //     filteredData.add(f_data);
                // }

                // if (yearMatch) {
                //     filteredData.add(f_data);
                // }

                if (dayMatch && monthMatch && yearMatch) {
                    filteredData.add(f_data);
                }
                
    
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] row_data = filteredData.toArray(new Object[0][]);
        return row_data;

        

         // Update table model with filtered data
        // String[] col_name = {"ID", "Name", "Age", "Department", "Date"};
        // table_Model.setDataVector(filteredData.toArray(new Object[0][]), col_name);
        // sorter = new TableRowSorter<>(table_Model);

        
    }

    public List<Object[]> date_read(JDatePickerImpl start_date, JDatePickerImpl end_date){
        //Opject startDateOption = start_date.getDate();

        List<Object[]> dateList = new ArrayList<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/bookings.txt"))){
                String line;
                while ((line = read.readLine()) != null) {
                    String[] f_data = line.split(",");
                    String enddate = f_data[3].trim();
                    String startdate = f_data[4].trim();
                    // String[] new_date_data = date_data.split("-");

                //     String day = new_date_data[0];
                //     String month = new_date_data[1];
                //     String year = new_date_data[2];
                    Date e_date = dateFormat.parse(enddate);
                    Date s_date = dateFormat.parse(startdate);

                    // Create an object array to hold the data
                    Object[] rowData = new Object[f_data.length];
                    
                    // Fill other data first
                    for (int i = 0; i < 3; i++) {
                        rowData[i] = f_data[i].trim();
                    }

                    // Insert Start Date and End Date
                    rowData[3] = e_date;
                    rowData[4] = s_date;

                    // Fill remaining data
                    for (int i = 5; i < f_data.length; i++) {
                        rowData[i] = f_data[i].trim();
                    }

                    dateList.add(rowData);

                }

                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Date startDate = (Date) start_date.getModel().getValue();
        Date endDate = (Date) end_date.getModel().getValue();

        try {
            if (startDate == null || endDate == null) {
                JOptionPane.showMessageDialog(null, "Both start date and end date must be porvided.");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Object[]> filteredDates = new ArrayList<>();
        for (Object[] dateData : dateList) {

            Date Startdate = (Date) dateData[3];
            Date Enddate = (Date) dateData[4];

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, 1);
            Date plus_one_date = cal.getTime();

            if (!Startdate.before(plus_one_date) && !Enddate.after(endDate)) {
                filteredDates.add(dateData);

            
            }

            // if (Startdate.compareTo(startDate) >= 0 && Enddate.compareTo(endDate) <= 0){
            //     filteredDates.add(dateData);
            // }


        }

        return filteredDates;
    }

    // public void data_filter(String txt_day, String txt_month, String txt_year) {
    //     List<String[]> filteredData = new ArrayList<>();
    //     String file_data;
    //     if (new Create_file().booking_file()) {
    //         try (BufferedReader br = new BufferedReader(new FileReader("resources/bookings.txt"))) {
    //             while ((file_data = br.readLine()) != null) {
    //                 String[] f_data = file_data.split(",");
    //                 String date_data = f_data[4].trim();
    //                 String[] new_date_data = date_data.split("-");
    
    //                 String day = new_date_data[0];
    //                 String month = new_date_data[1];
    //                 String year = new_date_data[2];
    
    //                 boolean dayMatch = txt_day.trim().equals(day) || txt_day.trim().equals(String.valueOf(Integer.parseInt(day)));
    //                 boolean monthMatch = txt_month.trim().equals(month);
    //                 boolean yearMatch = txt_year.trim().equals(year);
    
    //                 // if (dayMatch && monthMatch && yearMatch) {
    //                 //     filteredData.add(f_data);
    //                 // }
    //                 if (dayMatch) {
    //                     filteredData.add(f_data);
    //                 } 
                    
                    
    
    //                 // if (dayMatch && monthMatch && yearMatch) {
    //                 //     filteredData.add(f_data);
    //                 // } else if (dayMatch && monthMatch) {
    //                 //     filteredData.add(f_data);
    //                 // } else if (monthMatch && yearMatch) {
    //                 //     filteredData.add(f_data);
    //                 // } else if (dayMatch) {
    //                 //     filteredData.add(f_data);
    //                 // } else if (monthMatch) {
    //                 //     filteredData.add(f_data);
    //                 // } else if (yearMatch) {
    //                 //     filteredData.add(f_data);
    //                 // }
        
                    
    //             }
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }

        // Update table model with filtered data
    //     String[] col_name = {"ID", "Name", "Age", "Department", "Date"};
    //     table_Model.setDataVector(filteredData.toArray(new Object[0][]), col_name);
    //     sorter = new TableRowSorter<>(table_Model);
    // }

    public Object[][] present_data(String fileName){
        String line_Sales;
        ArrayList<String[]> rowData = new ArrayList<>();
        
        if (new Create_file().booking_file()) {
            try(BufferedReader read_Sales = new BufferedReader(new FileReader(fileName))) {
                while ((line_Sales = read_Sales.readLine()) != null) {
                    String[] row = line_Sales.split(",");
                    rowData.add(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        

        Object[][] row_Data = rowData.toArray(new Object[0][]);

        return row_Data;

    }

    // public Object[][] view_issues(String[] colname){
    //     String line_Sales;
    //     ArrayList<String[]> issuess = new ArrayList<>();
        
    //     try(BufferedReader read_Sales = new BufferedReader(new FileReader("resources/issues.txt"))){
    //         while((line_Sales = read_Sales.readLine()) != null){
    //             String[] row = line_Sales.split(",");
    //             issuess.add(row);
    //         }
    //     }catch(IOException e){
    //         e.printStackTrace();
    //     }

    //     Object[][] issues = issuess.toArray(new Object[0][]);

    //     // table_Model = new DefaultTableModel(issues, colname);
    //     // JTable view = new JTable(table_Model);
    //     // sorter = new TableRowSorter<>(table_Model);
    //     // view.setRowSorter(sorter);

    //     // JScrollPane scrollPane = new JScrollPane(view);

    //     return issues;

    // }

   public JScrollPane task_status(String[] colname){
       String task;
       ArrayList<String[]> taskStatus = new ArrayList<>();

       try (BufferedReader br_task = new BufferedReader(new FileReader("resources/issues.txt"))){
           while((task = br_task.readLine()) != null){
               String[] row = task.split(",");
               taskStatus.add(row);
           }

       } catch (IOException ex) {
           ex.printStackTrace();
       }

       Object[][] tasks_status = taskStatus.toArray(new Object[0][]);

       table_Model = new DefaultTableModel(tasks_status, colname);
       JTable view = new JTable(table_Model);
       sorter = new TableRowSorter<>(table_Model);
       view.setRowSorter(sorter);

       JScrollPane scrollPane = new JScrollPane(view);

       return scrollPane;
        
   }



    public ArrayList<String> day_list(int year, String months, int days){
        int month = 0;
        String[] monthList = new DateFormatSymbols().getMonths();
        for (int i = 0; i < monthList.length; i++) {
            if (months.equals(monthList[i])) {
                month = i + 1;
                break;
            }
        }
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

    public ArrayList<String> month_list(int month) {
        ArrayList<String> month_List = new ArrayList<>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length - 1; i++) {
            month_List.add(months[i]);
        }
        return month_List;
        
    }

    public ArrayList<String> year_list(int year) {
        ArrayList<String> year_List = new ArrayList<>();
        for (int years = year; years <= Calendar.getInstance().get(Calendar.YEAR) + 1; years++) {
            year_List.add(years + "");
        }
        return year_List;

    }



}
