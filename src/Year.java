import java.util.ArrayList;
import java.util.List;

public class Year {
    double       numberAwards;
    double       numberProposals;
    int          yearNumber;
    String       country;
    List<Double> listValue = new ArrayList<Double>();
    List<String> listValueString = new ArrayList<String>();
    boolean isListString = false;

    public boolean isListString() {
        return isListString;
    }

    public void setListString(boolean listString) {
        isListString = listString;
    }

    public List<String> getListValueString() {
        return listValueString;
    }

    public void setListValueString(List<String> listValueString) {
        this.listValueString = listValueString;
    }

    public List<Double> getListValue() {
        return listValue;
    }

    public void setListValue(List<Double> listValue) {
        this.listValue = listValue;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    String comment;

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public Year(int year) {
        this.yearNumber = year;
    }

    public Year(String country,int yearNumber, double numberAwards, double numberProposals ) {
        this.numberAwards = numberAwards;
        this.numberProposals = numberProposals;
        this.yearNumber = yearNumber;
        this.country = country;
    }

    public double getNumberAwards() {
        return numberAwards;
    }

    public void setNumberAwards(double numberAwards) {
        this.numberAwards = numberAwards;
    }

    public double getNumberProposals() {
        return numberProposals;
    }

    public void setNumberProposals(double numberProposals) {
        this.numberProposals = numberProposals;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumberAwards(int numberAwards) {
        this.numberAwards = numberAwards;
    }



    public void setNumberProposals(int numberProposals) {
        this.numberProposals = numberProposals;
    }
}
