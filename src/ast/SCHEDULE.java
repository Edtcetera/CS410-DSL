package ast;

import libs.EventObject;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SCHEDULE extends STATEMENT {
    EventObject obj;
    Calendar scheduleDay = Calendar.getInstance();
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("SCHEDULE");
        String next = tokenizer.getNext();
        if (next.equals("daily")) {
            //todo daily
            next = tokenizer.getNext();
            obj.setTitle(next);
        } else if (next.equals("weekly")) {
            //todo weekly
            next = tokenizer.getNext();
            obj.setTitle(next);
        } else if (next.equals("monthly")) {
            //todo monthly
            next = tokenizer.getNext();
            obj.setTitle(next);
        } else if (next.equals("annual")) {
            //todo annual
            next = tokenizer.getNext();
            obj.setTitle(next);
        } else { //non-recurring event
            obj.setTitle(next);
        }

        next = tokenizer.getNext();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD", Locale.ENGLISH);
        try {
            scheduleDay.setTime(sdf.parse(next));
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse MM/DD");
        }

        next = tokenizer.getNext();
        sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            obj.setStart(sdf.parse(next));
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse HH:mm");
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }
}
