package ast;

import DataHolder.DataHolder;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EDIT extends STATEMENT {
    private Calendar scheduleDay = Calendar.getInstance();
    private String titleToEdit;
    private String newNote = null;
    private Time sTime = null;
    private Time eTime = null;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("EDIT");
        String next = tokenizer.getNext();
        titleToEdit = next;

        next = tokenizer.getNext();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD", Locale.ENGLISH);
        try {
            scheduleDay.setTime(sdf.parse(next));
        } catch (Exception e) {
            System.out.println("PARSE::EDIT - unable to parse MM/DD");
        }

        next = tokenizer.getNext();
        if (next.matches("([0-9]+:[0-9]+)-([0-9]+:[0-9]+)")) {
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            String[] time = next.split("-");
            try {
                sTime = new Time(stf.parse(time[0]).getTime());
                eTime = new Time(stf.parse(time[1]).getTime());
            } catch (Exception e) {
                System.out.println("PARSE::EDIT - unable to parse HH/MM");
            }
        } else
            newNote = next;
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        DataHolder.getInstance().changeTimeObj(scheduleDay, sTime, eTime, newNote, titleToEdit);
        return null;
    }
}
