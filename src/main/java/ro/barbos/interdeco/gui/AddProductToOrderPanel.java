package ro.barbos.interdeco.gui;

import ro.barbos.interdeco.config.ConfigLocalManager;
import ro.barbos.interdeco.dao.ProductDAO;
import ro.barbos.interdeco.gui.exswing.SuggestionJComboBox;
import ro.barbos.interdeco.model.Product;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by radu on 5/6/2015.
 */
public class AddProductToOrderPanel extends JPanel {

    NumberFormat formater = NumberFormat.getNumberInstance(ConfigLocalManager.locale);

    SuggestionJComboBox<Product> productComboBox;
    JFormattedTextField lengthInput;

    public AddProductToOrderPanel() {
        formater.setMaximumFractionDigits(1);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(GUIFactory.createLabel("Product:", 100));
        List<Product> products = ProductDAO.getProducts();
        Product[] productModel = new Product[0];
        if(products != null) {
            productModel = products.toArray(productModel);
        }
        productComboBox = new SuggestionJComboBox<>(productModel);
        panel.add(productComboBox);
        add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(GUIFactory.createLabel("Cantitate:", 100));
        lengthInput = GUIFactory.createNumberInput(null, 0L, 1000000L, 80);
        panel.add(lengthInput);
        add(panel);
    }

    public Map validateData() {
        Map data = new HashMap();

        JLabel productLabel = (JLabel)((JPanel)getComponent(0)).getComponent(0);
        Product product = productComboBox.getSelectedItem();
        if(product == null) {
            productLabel.setForeground(Color.red);
        }
        else {
            data.put("PRODUCT", product);
        }

        JLabel cantitateLabel = (JLabel)((JPanel)getComponent(1)).getComponent(0);
        Long cantitate = (Long)lengthInput.getValue();
        if(cantitate == null || cantitate < 0) {
            cantitateLabel.setForeground(Color.red);
        }
        else {
            data.put("CANTITATE", cantitate);
        }
        return data;
    }
}
