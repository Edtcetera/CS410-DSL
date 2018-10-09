package DataHolder;

import TimeObj.TimeObj;

import java.sql.Time;
import java.util.*;

public class DataHolder {
    Map <Calendar, ArrayList<TimeObj>> dataholder = new HashMap();


    public void DataHolder(Map dataholder){
        this.dataholder = dataholder;
    }

    public void insertTimeObj(Calendar calendar, TimeObj timeObj){
        ArrayList singleDayData = dataholder.get(calendar);
        singleDayData.add(timeObj);
        dataholder.put(calendar, singleDayData);
    }

    public void deleteTimeObj(Calendar calendar, String titleOfTimeObjToDelete){
        TimeObj edit = new TimeObj();
        ArrayList<TimeObj> singleDayData = dataholder.get(calendar);
        for (TimeObj t: singleDayData){
            if (t.getTitle().equals(titleOfTimeObjToDelete)){
                edit = t;
                singleDayData.remove(t);
            }
        }
        dataholder.put(calendar, singleDayData);
    }

    public void changeTimeObj(Calendar calendar, Time stime, Time etime, String note, String titleOfTimeObjtoDelete){
        TimeObj edit = new TimeObj();
        ArrayList<TimeObj> singleDayData = dataholder.get(calendar);
        for (TimeObj t: singleDayData){
            if (t.getTitle().equals(titleOfTimeObjtoDelete)){
               edit = t;
               singleDayData.remove(t);
            }
        }
        if (stime != null && etime != null){
            edit.setStart(stime);
            edit.setEnd(etime);
        }
        if (note != null){
            edit.setNote(note);
        }
        singleDayData.add(edit);
        dataholder.put(calendar, singleDayData);
    }
}
