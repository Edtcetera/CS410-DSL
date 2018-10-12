package ast;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class UPDATE extends STATEMENT {
    String year;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("UPDATE");
        year = tokenizer.getNext();
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        //todo: place year into SingleSchedule to see year currently working on
        return null;
    }
}
