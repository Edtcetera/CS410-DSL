package ast;

import libs.EventObject;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SCHEDULE extends STATEMENT {
    EventObject eventObj = new EventObject();
    Calendar scheduleDay = Calendar.getInstance();
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("SCHEDULE");
        String next = tokenizer.getNext();
        if (next.equals("daily")) {
            //todo daily
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("weekly")) {
            //todo weekly
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("monthly")) {
            //todo monthly
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("annual")) {
            //todo annual
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else { //non-recurring event
            eventObj.setTitle(next);
        }

        next = tokenizer.getNext();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD", Locale.ENGLISH);
        try {
            scheduleDay.setTime(sdf.parse(next));
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse MM/DD");
        }

        next = tokenizer.getNext();
        try {
            //TODO: check regex for correct time format (00:00-11:11), else throw exception
            String[] splitArray = next.split("[-\\s]");
            sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            eventObj.setStart(new Time(sdf.parse(splitArray[0]).getTime()));
            eventObj.setEnd(new Time(sdf.parse(splitArray[1]).getTime()));
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse HH:mm");
        } catch (Exception e) {
            System.out.println("PARSE::SCHEDULE - must have both start and end time");
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }
}
