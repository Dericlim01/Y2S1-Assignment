import java.io.*;

public class Create_file {
    public Boolean user_file() {
        try {
            if (new File("users.txt").createNewFile()) {
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

    public Boolean hall_file() {
        try {
            if (new File("halls.txt").createNewFile()) {
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

    public Boolean issue_file() {
        try {
            if (new File("issues.txt").createNewFile()) {
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
}
