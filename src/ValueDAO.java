import java.util.ArrayList;
import java.util.List;

public class ValueDAO {
    static List<ArrayList<Double>> twoArrayList = new ArrayList<ArrayList<Double>>();
    static List<String> disc = new ArrayList<String>();

    public static List<ArrayList<Double>> listtwoArray() {
        return twoArrayList;
    }
    public static List<String> listdisc() {
        return disc;
    }
}
