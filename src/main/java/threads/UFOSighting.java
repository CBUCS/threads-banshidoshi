package threads;

import java.util.Arrays;
import java.util.List;

public class UFOSighting {
    //creating getter and setter for date time, strCity, strState and strCountry
    private String strDateTime;
    private String strCity;
    private String strState;
    private String strCountry;

    public UFOSighting(List<String> s) {
        strDateTime = s.get(0);
        strCity = s.get(1);
        strState = s.get(2);
        strCountry = s.get(3);
    }

    public String getStrCity() {
        return strCity;
    }

    public void setStrCity(String strCity) {
        this.strCity = strCity;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public void setStrCountry(String strCountry) {
        this.strCountry = strCountry;
    }

    public String getStrDateTime() {
        return strDateTime;
    }

    public void setStrDateTime(String strDateTime) {
        this.strDateTime = strDateTime;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        if (strState.equals(""))
            this.strState = "Not A State";
        else
            this.strState = strState;
    }

    public int getYear() {
        List<String> split = Arrays.asList(strDateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(0).split("/"));
        return Integer.parseInt(split2.get(2));
    }

    public int getMonth() {
        List<String> split = Arrays.asList(strDateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(0).split("/"));
        return Integer.parseInt(split2.get(0));
    }

    public int getHour() {
        List<String> split = Arrays.asList(strDateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(1).split(":"));
        return Integer.parseInt(split2.get(0));
    }

    public int getMin() {
        List<String> split = Arrays.asList(strDateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(1).split(":"));
        return Integer.parseInt(split2.get(1));
    }
}
