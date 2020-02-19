package net.maritimeconnectivity.mrnservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Result {
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    private boolean result;

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    private String mrn;

    public Result(){
        this.mrn = "";
    }

    public Result(String mrn, String regex){
        super();
        this.mrn = mrn;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mrn);
        this.result = m.matches();
    }
}
