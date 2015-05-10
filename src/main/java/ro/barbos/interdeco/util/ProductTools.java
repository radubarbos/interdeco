package ro.barbos.interdeco.util;

import ro.barbos.interdeco.model.METRIC;
import ro.barbos.interdeco.model.Product;

/**
 * Created by radu on 5/9/2015.
 */
public class ProductTools {

    public static Double getVolume(Product product, METRIC metric) {
        Long volume = product.getWidth() * product.getThick() * product.getLength();
        if(metric == METRIC.METER) {
            return volume/1000000000D;
        }
        return  volume.doubleValue();
    }
}
