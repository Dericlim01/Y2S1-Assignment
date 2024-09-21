package src.Manager;

import src.shared.Create_file;

import org.jdatepicker.impl.JDatePickerImpl;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Manager {
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public String[] read_user_Information(String n) {
        ArrayList<String[]> users = new ArrayList<>();
        String line;
        String[] data;
        if (new Create_file().user_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"));) {
                while ((line = read.readLine()) != null) {
                    // Create array and store data 
                    data = line.split(",");
                    users.add(data);
                }
                // Search in arrayList
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i)[0].equals(n) && users.get(i)[6].equals("manager")) {
                        return users.get(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Boolean update_user(String n, String p, String pn, String m, String b, String g, String r) {
        // Create a user list
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        // String dobform = dateFormat.format(d);
        Boolean user_update = false;
        Boolean users_update = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))) {
            while ((line = read.readLine()) != null) {
                // Store data into data and add to users array
                data = line.split(",");
                users.add(data);
                System.out.println(users);
            }
            // Loop through the users
            for (int i = 0; i < users.size(); i++) {
                // If username found
                if (users.get(i)[0].equals(n)) {
                    users.get(i)[1] = p;
                    users.get(i)[2] = pn;
                    users.get(i)[3] = m;
                    users.get(i)[4] = b;
                    users.get(i)[5] = g;
                    users.get(i)[6] = r;
                    user_update = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (user_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/staffs.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[2] + "," + userdata[3] 
                    + "," + userdata[4] + "," + userdata[5] + "," + userdata[6]);
                }
                users_update = true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }

        if (users_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/users.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[6]);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    public List<Object[]> date_read(JDatePickerImpl start_date, JDatePickerImpl end_date, String filename) {
        // Opject startDateOption = start_date.getDate();
        List<Object[]> dateList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (new Create_file().booking_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader(filename))) {
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
                    // for (int i = 6; i < f_data.length; i++) {
                    //     rowData[i] = f_data[i].trim();
                    // }
                    rowData[5] = f_data[6].trim();
                    rowData[6] = f_data[8].trim();
                    rowData[7] = f_data[9].trim();

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
            try (BufferedReader read_Sales = new BufferedReader(new FileReader(fileName))) {
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
    
    public Object[][] view_sales(String fileName) {
        String sales;
        ArrayList<String[]> viewSales = new ArrayList<>();

        if (new Create_file().issue_file()) {
            try (BufferedReader br_sales = new BufferedReader(new FileReader(fileName))) {
                while ((sales = br_sales.readLine()) != null) {
                    String[] row = sales.split(",");
                    viewSales.add(row);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Object[][] view_Sales = new Object[viewSales.size()][8];
        for (int i = 0; i < viewSales.size(); i++) {
            String[] sales_row = viewSales.get(i);
            view_Sales[i][0] = sales_row[0];
            view_Sales[i][1] = sales_row[1];
            view_Sales[i][2] = sales_row[2];
            view_Sales[i][3] = sales_row[3];
            view_Sales[i][4] = sales_row[4];
            view_Sales[i][5] = sales_row[6];
            view_Sales[i][6] = sales_row[8];
            view_Sales[i][7] = sales_row[9];
        }
        return view_Sales;
    }

    public Object[][] task_status() {
        String task;
        ArrayList<String[]> taskStatus = new ArrayList<>();

        if (new Create_file().issue_file()) {
            try (BufferedReader br_task = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
                while ((task = br_task.readLine()) != null) {
                    String[] row = task.split(",");
                    taskStatus.add(row);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Object[][] tasks_status = new Object[taskStatus.size()][8];
        for (int i = 0; i < taskStatus.size(); i++) {
            String[] task_row = taskStatus.get(i);
            tasks_status[i][0] = task_row[0];
            tasks_status[i][1] = task_row[1];
            tasks_status[i][2] = task_row[2];
            tasks_status[i][3] = task_row[3];
            tasks_status[i][4] = task_row[4];
            tasks_status[i][5] = task_row[5];
            tasks_status[i][6] = task_row[6];
            tasks_status[i][7] = task_row[8];
        }
        return tasks_status;
    }

    // 
    public ArrayList<String> task_ID() {
        String task_line;
        ArrayList<String> tasks = new ArrayList<>();

        if (new Create_file().hall_file()) {
            try (BufferedReader task_br = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
                while ((task_line = task_br.readLine()) != null) {
                    String[] row_task = task_line.split(",");
                    if (!tasks.contains(row_task[1])) {
                        tasks.add(row_task[1]);
                    }
                }
                return tasks;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    public Boolean update_status(String taskID, String issuesID, String issues_title, String issues_description, String username, String hallId, String Staff, String s) {
        // Create a user list
        List<String> task = new ArrayList<>();
        String line;
        Boolean status_update = false;

        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
            while ((line = read.readLine()) != null) {
                // Store data into data and add to task array
                String[] data = line.split(",");
                if (data[0].equals(taskID)){
                    data[8] = s;
                    status_update = true;
                }
                task.add(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Loop through the task
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/task.txt"))) {
            for (String taskdata : task) {
                //writer.println(taskdata[0] + "," + taskdata[1] + "," + taskdata[2] + "," + taskdata[3] 
                //+ "," + taskdata[4] + "," + taskdata[5] + "," + taskdata[6] + "," + taskdata[7] + "," + taskdata[8]);
                writer.write(taskdata);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status_update;
    }

    // Customer_Issues_Receive
    public ArrayList<String> hall_id_type() {
        String hall_line;
        ArrayList<String> halls = new ArrayList<>();

        if (new Create_file().hall_file()) {
            try (BufferedReader hall_br = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((hall_line = hall_br.readLine()) != null) {
                    String[] row_hall = hall_line.split(",");
                    if (!halls.contains(row_hall[0])) {
                        halls.add(row_hall[0]);
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

    public Object[][] hallType(String hall_type) {
        List<Object[]> hallList = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/issues.txt"))) {
                String line;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[4].equals(hall_type)) {
                        hallList.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallList.toArray(new Object[0][]);
    }

    public Object[][] hallID(String hall_ID) {
        List<Object[]> hallList = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/issues.txt"))) {
                String line;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[3].equals(hall_ID)) {
                        hallList.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallList.toArray(new Object[0][]);
    }

    public ArrayList<String> assign_staff() {
        String staff_data;
        ArrayList<String> staffList = new ArrayList<>();
        if (new Create_file().staffs_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))) {
                while ((staff_data = read.readLine()) != null) {
                    String[] data = staff_data.split(",");

                    if (!staffList.contains(data[0]) && data[6].equals("scheduler")) {
                        staffList.add(data[0]);
                    }
                }
                return staffList;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return staffList;
    }

    public Boolean keep_task(List<String> issuesRow, String staff_data, String details) {
        // String halls;
        int nextID = 0;
        String ID_data;
        String issues_id = issuesRow.get(0);
        String issues = issuesRow.get(1);
        String description = issuesRow.get(2);
        String hallID = issuesRow.get(3);
        // String hallType = issuesRow.get(4);
        String userName = issuesRow.get(5);

        try (BufferedReader task_br = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
            int maxID = 0;
            while ((ID_data = task_br.readLine()) != null) {
                String[] partsID = ID_data.split(",");
                // Extract ID from "Txx" format
                int old_id = Integer.parseInt(partsID[0].substring(1));
                if (old_id > maxID) {
                    maxID = old_id;
                }
            }
            nextID = maxID + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        String id = String.format("T%02d", nextID);
        // In progress , Done, Closed, Cancelled
        String issues_status = "In progress";
        try (FileWriter task = new FileWriter("resources/Database/task.txt", true)) {
            task.write(id + "," + issues_id + "," + issues + "," + description + "," + userName + "," + hallID  + "," + staff_data + "," + details + "," + issues_status + "\n");
            JOptionPane.showMessageDialog(
                null,
                "Assign Successfull. Please go to the Task Status Page to check. Thank You ^_^",
                "Successful Assign Notification",
                JOptionPane.OK_CANCEL_OPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("null")
    public Double paid_total(List<Object[]> filteredDate, SimpleDateFormat dateFormat, JDatePickerImpl startDate, JDatePickerImpl endDate) {
        double totalPaid = 0.00;
        // Iterate through the filteredDate list, adding up the "Booking Paid" data for each row.
        System.out.println("Filtered Date Entries:");
        for (Object[] data : filteredDate) {
            if (data == null && data.length <= 9) {
                System.err.println("Invalid data entry: " + (data == null ? "null" : "length too short"));
                continue;
            }

            if (data != null) {
                System.out.println(Arrays.toString(data));
            }

            String paidStr = data[5].toString();
            try {
                double price = Double.parseDouble(paidStr);
                totalPaid += price;
            } catch (NumberFormatException e) {
                System.err.println("Error parsing booking paid value: " + data[5]);
            }
        }
        return totalPaid;
    }

    public double calculate_total_paid(String filePath) {
        double totalPaid = 0.00;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the "Booking Paid" amount is at index 8
                double paidAmount = Double.parseDouble(data[6].trim());
                totalPaid += paidAmount;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalPaid;
    }

    public Boolean delete_booking(List<String> book) {
        ArrayList<String> bookData = new ArrayList<>();
        String line;
        boolean delete = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/bookings.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(book.get(0))) {
                    delete = true;
                    // When succefully searching and matching booking id, continue to delete
                } else {
                    bookData.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (delete) {
            // Ensure all line are written back to the file
            try (BufferedWriter writeBook = new BufferedWriter(new FileWriter("resources/Database/bookings.txt"))) {
                for (String booking : bookData) {
                    writeBook.write(booking);
                    writeBook.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return delete;
    }
}
