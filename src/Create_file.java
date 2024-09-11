package src;
import java.io.*;

public class Create_file {
    // User file
    public Boolean user_file() {
        try {
            if (new File("resources/Database/users.txt").createNewFile()) {
                System.out.println("User file created");
            } else {
                System.out.println("User file exist");
            }
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
            return false;
        }
    }

    // Hall file
    public Boolean hall_file() {
        try {
            if (new File("resources/Database/halls.txt").createNewFile()) {
                System.out.println("Hall file created");
            } else {
                System.out.println("Hall file exist");
            }
            return true;
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }

    // Booking file
    public Boolean booking_file() {
        try {
            if (new File("resources/Database/bookings.txt").createNewFile()) {
                System.out.println("Booking file created");
            } else {
                System.out.println("Booking file exist");
            }
            return true;
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }

    // Issue file
    public Boolean issue_file() {
        try {
            if (new File("resources/Database/issues.txt").createNewFile()) {
                System.out.println("Issue file created");
            } else {
                System.out.println("Issue file exist");
            }
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
            return false;
        }
    }

    // Staffs file
    public Boolean staffs_file() {
        try {
            if (new File("resources/Database/staffs.txt").createNewFile()) {
                System.out.println("Staffs file created");
            } else{
                System.out.println("Staffs file exist");
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }

    // Hall Status file
    public Boolean hall_stat_file() {
        try {
            if (new File("resources/Database/hall_status.txt").createNewFile()) {
                System.out.println("Hall Status file created");
            } else {
                System.out.println("Hall Status File exist");
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }

    // Customer file
    public Boolean customer_file() {
        try {
            if (new File("resources/Database/customers.txt").createNewFile()) {
                System.out.println("Customer file created");
            } else {
                System.out.println("Customer File exist");
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }

    // Task file
    public Boolean task_file() {
        try {
            if (new File("resources/Database/task.txt").createNewFile()) {
                System.out.println("Task file created");
            } else {
                System.out.println("Task File exist");
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
            return false;
        }
    }
}
