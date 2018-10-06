package ast;

import libs.Node;

public  abstract class STATEMENT extends Node {
    public static STATEMENT getSubStatement(){
        if (tokenizer.checkToken("UPDATE")) {
            return new UPDATE();
        }
        if (tokenizer.checkToken("SCHEDULE")) {
            return new SCHEDULE();
        }
        if (tokenizer.checkToken("ADD")) {
            return new ADD();
        }
        if (tokenizer.checkToken("EDIT")) {
            return new EDIT();
        }
        if (tokenizer.checkToken("DELETE")) {
            return new DELETE();
        }
        if (tokenizer.checkToken("VIEW")) {
            return new VIEW();
        }
        else return null;
    }
}
