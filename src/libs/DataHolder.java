package libs;

import java.sql.Time;
import java.util.*;


// Singleton implemented : Probably not amazingly. TODOTODO

public class DataHolder {
    Map<Calendar, ArrayList<EventObject>> dh = new HashMap();
    private static DataHolder dataHolder = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public void insertTimeObj(Calendar calendar, EventObject eventObject) {
        ArrayList singleDayData = dh.get(calendar);
        singleDayData.add(eventObject);
        dh.put(calendar, singleDayData);
    }

    public void deleteTimeObj(Calendar calendar, String titleOfTimeObjToDelete) {
        EventObject edit = new EventObject();
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjToDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        dh.put(calendar, singleDayData);
    }

    public void changeTimeObj(Calendar calendar, Time stime, Time etime, String note, String titleOfTimeObjtoDelete) {
        EventObject edit = new EventObject();
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjtoDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
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
