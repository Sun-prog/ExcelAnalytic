import java.util.List;

public class Research {
    String       name;
    List<Double> dataCountry;
    List<String> dataCountryString;

    public Research(String name, List<Double> dataCountry) {
        this.name = name;
        this.dataCountry = dataCountry;
    }

    public Research( List<String> dataCountryString) {

        this.dataCountryString = dataCountryString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getDataCountry() {
        return dataCountry;
    }

    public void setDataCountry(List<Double> dataCountry) {
        this.dataCountry = dataCountry;
    }


}
