package ro.barbos.interdeco.model;

/**
 * Created by radu on 5/9/2015.
 */
public enum METRIC {
    MILIMETER, CENTIMETER, DECIMETER, METER;

    public static METRIC getById(int id) {
        for(METRIC metric: METRIC.values()) {
            if(metric.ordinal() == (id-1)) {
                return metric;
            }
        }
        return null;
    }
}
