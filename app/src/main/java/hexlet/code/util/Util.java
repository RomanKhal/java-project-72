package hexlet.code.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static  <T> String asString(T param) {
        if (param != null) {
            if(param instanceof Timestamp){
                Date date = new Date(((Timestamp) param).getTime());
                return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
            }
            if (param instanceof Number) {
                return String.valueOf(param);
            }
        }
        return "";
    }
}
