import java.util.ArrayList;
import java.util.List;

public class Country {
    String name;
    int year;
    boolean add;
    List<Double> listValue = new ArrayList<Double>();
    List<String> listValueString = new ArrayList<String>();

    public List<String> getListValueString() {
        return listValueString;
    }

    public void setListValueString(List<String> listValueString) {
        this.listValueString = listValueString;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }



    public List<Double> getListValue() {
        return listValue;
    }

    public void setListValue(List<Double> listValue) {
        this.listValue = listValue;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Country(String name, int year) {
        this.name = name;
        this.year = year;
    }
}