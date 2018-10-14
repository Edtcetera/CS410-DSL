package ast;

import libs.SingleSchedule;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class UPDATE extends STATEMENT {
    int year;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("UPDATE");
        year = Integer.parseInt(tokenizer.getNext());
        SingleSchedule.getInstance().setCurrentWorkingYear(year);
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        //todo: place year into SingleSchedule to see year currently working on
        return null;
    }
}
