package ro.barbos.interdeco.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ro.barbos.interdeco.model.Product;
import ro.barbos.interdeco.config.ConfigLocalManager;

public class AddProductPanel extends JPanel {

	NumberFormat formater = NumberFormat.getNumberInstance(ConfigLocalManager.locale);
	
	public AddProductPanel() {
		formater.setMaximumFractionDigits(1);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(GUIFactory.createLabel("Lungime:", 100));
		JFormattedTextField lengthInput = GUIFactory.createDecimalNumberInput(null, 0D, 1000000D, 80);
		panel.add(lengthInput);
		JComboBox<String> mCombo = new JComboBox<String>(GUIUtil.metric);
		mCombo.setPreferredSize(new Dimension(50, mCombo.getPreferredSize().height));
		panel.add(mCombo);
		add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(GUIFactory.createLabel("Latime:", 100));
		JFormattedTextField widthInput = GUIFactory.createDecimalNumberInput(null, 0D, 1000000D, 80);
		panel.add(widthInput);
		mCombo = new JComboBox<String>(GUIUtil.metric);
		mCombo.setPreferredSize(new Dimension(50, mCombo.getPreferredSize().height));
		panel.add(mCombo);
		add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(GUIFactory.createLabel("Grosime:", 100));
		JFormattedTextField thickInput = GUIFactory.createDecimalNumberInput(null, 0D, 1000000D, 80);
		panel.add(thickInput);
		mCombo = new JComboBox<String>(GUIUtil.metric);
		mCombo.setPreferredSize(new Dimension(50, mCombo.getPreferredSize().height));
		panel.add(mCombo);
		add(panel);
	}
	
	public Product validateData() {
		boolean status = true;
		
		JLabel lenLabel = (JLabel)((JPanel)getComponent(0)).getComponent(0);
		JFormattedTextField lenRadius = (JFormattedTextField)((JPanel)getComponent(0)).getComponent(1);
		JComboBox<String> lenMetric = (JComboBox<String>)((JPanel)getComponent(0)).getComponent(2);
		lenLabel.setForeground(Color.black);
		if(lenRadius.getValue() == null) {
			status = false;
			lenLabel.setForeground(Color.red);
		}
		
		JLabel widthLabel = (JLabel)((JPanel)getComponent(1)).getComponent(0);
		JFormattedTextField widthRadius = (JFormattedTextField)((JPanel)getComponent(1)).getComponent(1);
		JComboBox<String> widthMetric = (JComboBox<String>)((JPanel)getComponent(1)).getComponent(2);
		widthLabel.setForeground(Color.black);
		if(widthRadius.getValue() == null) {
			status = false;
			widthLabel.setForeground(Color.red);
		}
		
		JLabel thickLabel = (JLabel)((JPanel)getComponent(2)).getComponent(0);
		JFormattedTextField thickRadius = (JFormattedTextField)((JPanel)getComponent(2)).getComponent(1);
		JComboBox<String> thickMetric = (JComboBox<String>)((JPanel)getComponent(2)).getComponent(2);
		thickLabel.setForeground(Color.black);
		if(thickRadius.getValue() == null) {
			status = false;
			thickLabel.setForeground(Color.red);
		}
		
		if(!status) {
			return null;
		}
		
		Product product = new Product();
		double lengthValue = getMilimetricNumericValue((Double)lenRadius.getValue(), lenMetric.getSelectedIndex());
		double widthValue = getMilimetricNumericValue((Double)widthRadius.getValue(), widthMetric.getSelectedIndex());
		double thickValue = getMilimetricNumericValue((Double)thickRadius.getValue(), thickMetric.getSelectedIndex());
		product.setLength((long)lengthValue);
		product.setWidth((long)widthValue);
		product.setThick((long)thickValue);
		product.setName(product.getWidth()+"X"+product.getThick()+"X"+formater.format(product.getLength()/1000D)+" m");
			
		return product;
	}
	
	private double getMilimetricNumericValue(double value, int metricIndex) {
		switch(metricIndex) {
		case 0: return value;
		case 1: return value * 10;
		case 2: return value * 100;
		case 3: return value * 1000;
		}
		return value;
	}
}
