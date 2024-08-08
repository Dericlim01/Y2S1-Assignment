import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;


public class DateFormat extends JFormattedTextField.AbstractFormatter {
    //create custom date format
    private String dateform = "dd-MM-yyyy";
    //import library Simple date format
    private SimpleDateFormat dateFormat = new SimpleDateFormat(dateform);
    @Override
    public Object stringToValue(String text) throws ParseException {
        // TODO Auto-generated method stub
        return dateFormat.parseObject(text);
    }
    @Override
    public String valueToString(Object value) throws ParseException {
        // TODO Auto-generated method stub
        if(value != null){           
            Calendar cal = (Calendar) value;
            return dateFormat.format(cal.getTime());
        }
        return "";
    }
}
