import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    static List<Country> list = new ArrayList<Country>();

    public static List<Country> listCountry() {
        return list;
    }
}
