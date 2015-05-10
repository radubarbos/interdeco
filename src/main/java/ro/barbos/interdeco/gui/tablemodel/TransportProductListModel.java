package ro.barbos.interdeco.gui.tablemodel;

import ro.barbos.interdeco.config.ConfigLocalManager;
import ro.barbos.interdeco.model.OrderItemProduct;
import ro.barbos.interdeco.model.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 5/6/2015.
 */
public class TransportProductListModel  extends GeneralAbstractTableModel {

    String[] columns = {"Nr","Produs","Cantitate", "Cubaj", "Greutate"};


    private List<OrderItemProduct> records = new ArrayList<>();

    NumberFormat formater = NumberFormat.getNumberInstance(ConfigLocalManager.locale);
    NumberFormat cubajFormatter = NumberFormat.getNumberInstance(ConfigLocalManager.locale);

    public TransportProductListModel() {
        formater.setMaximumFractionDigits(2);
        cubajFormatter.setMaximumFractionDigits(4);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return records.size();
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return super.getColumnClass(col);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int col) {
        OrderItemProduct record = records.get(row);
        if(col == 0) {
            return row+1;
        }
        else if(col == 1) {
            return record.getProduct().getName();
        }
       /* else if(col == 2) {
            return formater.format(record.getLength()/1000D);
        }*/
        else if(col == 2) {
            return formater.format(record.getQuantity());
        }
        else if(col == 3) {
            return cubajFormatter.format(record.getVolume()) + " m. cub";
        }
        return null;
    }

   /* public void setProducts(List<Product> products) {
        if(products != null) {
            records.clear();
            records.addAll(products);
            refreshOnDataChange();
        }
    }*/

    public void addProduct(OrderItemProduct product) {
        records.add(product);
        refreshOnDataChange();
    }

    public void deleteProduct(int row) {
        records.remove(row);
        refreshOnDataChange();
    }

    public OrderItemProduct getProduct(int row) {
        if(row >= 0 || row < records.size()) {
            return records.get(row);
        }
        return null;
    }
}
