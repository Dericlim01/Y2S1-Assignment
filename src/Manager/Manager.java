package src.Manager;
import java.io.*;
import java.util.*;

import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;

import src.Create_file;

public class Manager {

    private DefaultTableModel table_Model;
    private TableRowSorter<DefaultTableModel> sorter;

    public String[] read_user_Information(String n){
        ArrayList<String[]> users = new ArrayList<>();
        String line;
        String[] data;
        if (new Create_file().user_file()) {
            try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"));){
                while((line = read.readLine()) != null){
                    // Create array and store data 
                    data = line.split(",");
                    users.add(data);
                }
                // Search in arrayList
                for (int i = 0; i < users.size(); i++) {
                    if(users.get(i)[0].equals(n)) {
                        return users.get(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    

    public List<Object[]> date_read(JDatePickerImpl start_date, JDatePickerImpl end_date, String filename){
        //Opject startDateOption = start_date.getDate();

        List<Object[]> dateList = new ArrayList<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader(filename))){
                String line;
                while ((line = read.readLine()) != null) {
                    String[] f_data = line.split(",");
                    String enddate = f_data[3].trim();
                    String startdate = f_data[4].trim();

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

        List<Object[]> filteredDates = new ArrayList<>();
        for (Object[] dateData : dateList) {

            Date Startdate = (Date) dateData[3];
            Date Enddate = (Date) dateData[4];

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, -1);
            Date decrease_one_date = cal.getTime();

            if (!Startdate.before(decrease_one_date) && !Enddate.after(endDate)) {
                filteredDates.add(dateData);

            
            }

        }

        return filteredDates;
    }

    public Object[][] present_data(String fileName) {
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
        return rowData.toArray(new Object[0][]);
    }

   public Object[][] task_status(){
       String task;
       ArrayList<String[]> taskStatus = new ArrayList<>();

       if(new Create_file().issue_file()){
        try (BufferedReader br_task = new BufferedReader(new FileReader("resources/Database/issues.txt"))){
            while((task = br_task.readLine()) != null){
                String[] row = task.split(",");
                taskStatus.add(row);
            }
 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       }

       Object[][] tasks_status = taskStatus.toArray(new Object[0][]);


       return tasks_status;
        
   }


   // Customer_Issues_Receive
    public ArrayList<String> hall_type() {
        String hall_line;
        ArrayList<String>  halls = new ArrayList<>();

        if (new Create_file().hall_file()) {
            try (BufferedReader hall_br = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((hall_line = hall_br.readLine()) != null) {
                    String[] row_hall = hall_line.split(",");
                    if(!halls.contains(row_hall[1])) {
                        halls.add(row_hall[1]);
                    }
                }
                return halls;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return halls;

        
    }

    public Object[][] hallData(String hall_type) {
        List<Object[]> hallList = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/issues.txt"))) {
                String line;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(hall_type)) {
                        hallList.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallList.toArray(new Object[0][]);
    }

    public ArrayList<String> assign_staff(){
        String staff_data;
        ArrayList<String> staffList = new ArrayList<>();
        if(new Create_file().staffs_file()) {
            try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))){
                while((staff_data = read.readLine()) != null){
                    String[] data = staff_data.split(",");
                    if(!staffList.contains(data[0])){
                        staffList.add(data[0]);
                    }

                }
                return staffList;

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return staffList;
    }

    int task_Id;
    public Boolean keep_task() {
        int f_id = 1;
        
        try(BufferedReader id_read = new BufferedReader(new FileReader("resources/Database/issues.txt"))){
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }





}
