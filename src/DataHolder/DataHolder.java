package DataHolder;

import TimeObj.TimeObj;

import java.sql.Time;
import java.util.*;


// Singleton implemented : Probably not amazingly. TODOTODO

public class DataHolder {
    Map<Calendar, ArrayList<TimeObj>> dh = new HashMap();
    private static DataHolder dataHolder = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public void insertTimeObj(Calendar calendar, TimeObj timeObj) {
        ArrayList singleDayData = dh.get(calendar);
        singleDayData.add(timeObj);
        dh.put(calendar, singleDayData);
    }

    public void deleteTimeObj(Calendar calendar, String titleOfTimeObjToDelete) {
        TimeObj edit = new TimeObj();
        ArrayList<TimeObj> singleDayData = dh.get(calendar);
        for (TimeObj t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjToDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        dh.put(calendar, singleDayData);
    }

    public void changeTimeObj(Calendar calendar, Time stime, Time etime, String note, String titleOfTimeObjtoDelete) {
        TimeObj edit = null;
        ArrayList<TimeObj> singleDayData = dh.get(calendar);
        for (TimeObj t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjtoDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        if (edit == null) {
            System.out.println("DataHolder::ChangeTimeObj - Fail to Edit: No Such timeObj.");
            return;
        }
        if (stime != null && etime != null) {
            edit.setStart(stime);
            edit.setEnd(etime);
        }
        if (note != null) {
            edit.setNote(note);
        }
        singleDayData.add(edit);
        dh.put(calendar, singleDayData);
    }
}
