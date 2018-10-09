package DataHolder;

import TimeObj.TimeObj;

import java.sql.Time;
import java.util.*;

public class DataHolder {
    Map <Calendar, ArrayList<TimeObj>> dataholder = new HashMap();


    public void DataHolder(Map dataholder){
        this.dataholder = dataholder;
    }

    public void insertTimeObj(Calendar d, TimeObj t){
        ArrayList al = dataholder.get(d);
        al.add(t);
        dataholder.put(d, al);
    }

    public void deleteTimeObj(Calendar d, String search){
        TimeObj edit = new TimeObj();
        ArrayList<TimeObj> al = dataholder.get(d);
        for (TimeObj t: al){
            if (t.getTitle().equals(search)){
                edit = t;
                al.remove(t);
            }
        }
        dataholder.put(d, al);
    }

    public void changeTimeObj(Calendar d, Time stime, Time etime, String s, String search){
        TimeObj edit = new TimeObj();
        ArrayList<TimeObj> al = dataholder.get(d);
        for (TimeObj t: al){
            if (t.getTitle().equals(search)){
               edit = t;
               al.remove(t);
            }
        }
        if (stime != null && etime != null){
            edit.setStart(stime);
            edit.setEnd(etime);
        }
        if (s != null){
            edit.setNote(s);
        }
        al.add(edit);
        dataholder.put(d, al);
    }
}
