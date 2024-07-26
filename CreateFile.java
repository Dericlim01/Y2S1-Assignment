import java.io.*;

public class CreateFile{
    public Boolean user_file(){
        try{
            File user_file = new File ("users.txt");
            if (user_file.createNewFile()){
                System.out.println("User file created");
            }else{
                System.out.println("User file exist");
            }
            return true;
        } catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
            return false;
        }
    }
}