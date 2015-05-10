package ro.barbos.interdeco.gui;

import ro.barbos.interdeco.config.ConfigLocalManager;
import ro.barbos.interdeco.dao.ProductDAO;
import ro.barbos.interdeco.gui.renderer.GeneralTableRenderer;
import ro.barbos.interdeco.gui.tablemodel.ProductsModel;
import ro.barbos.interdeco.gui.tablemodel.TransportProductListModel;
import ro.barbos.interdeco.model.METRIC;
import ro.barbos.interdeco.model.OrderItemProduct;
import ro.barbos.interdeco.model.Product;
import ro.barbos.interdeco.util.ProductTools;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.Map;

public class CubajFrame extends GeneralFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
    TransportProductListModel orderModel;
	JTable productsTable;

    NumberFormat formater = NumberFormat.getNumberInstance(ConfigLocalManager.locale);

	private AddProductToOrderPanel addProductPanel;

    private JLabel totalCubaj;
    private double cubaj = 0 ;

	public CubajFrame() {
		super();

		setTitle("Comanda Transport");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JButton but = new JButton("Adauga", new ImageIcon(
				GUITools.getImage("/images/add24.png")));
		but.setVerticalTextPosition(SwingConstants.BOTTOM);
		but.setHorizontalTextPosition(SwingConstants.CENTER);
		but.setFocusPainted(false);
		but.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		but.setToolTipText("Adauga produs nou");
		but.setActionCommand("ADD_PRODUCT");
		but.addActionListener(this);
		toolbar.add(but);

		but = new JButton("Sterge", new ImageIcon(
				GUITools.getImage("/images/delete24.png")));
		but.setVerticalTextPosition(SwingConstants.BOTTOM);
		 but.setHorizontalTextPosition(SwingConstants.CENTER);
		but.setFocusPainted(false);
		but.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		but.setToolTipText("Sterge produs");
		but.setActionCommand("DELETE_PRODUS");
		but.addActionListener(this);
		toolbar.add(but);
		
		JButton csvExport = new JButton("Exporta", new ImageIcon(
				GUITools.getImage("/images/csv24.png")));
		csvExport.setToolTipText("Exporta tabelul in fisier csv");
		csvExport.setVerticalTextPosition(SwingConstants.BOTTOM);
		csvExport.setHorizontalTextPosition(SwingConstants.CENTER);
		csvExport.setActionCommand("CSV");
		csvExport.addActionListener(this);
		csvExport.setFocusPainted(false);
		csvExport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolbar.add(csvExport);

		JButton print = new JButton("Print", new ImageIcon(
				GUITools.getImage("/images/printb24.png")));
		print.setVerticalTextPosition(SwingConstants.BOTTOM);
		print.setHorizontalTextPosition(SwingConstants.CENTER);
		print.setToolTipText("Printeaza tabel");
		print.setActionCommand("PRINT");
		print.addActionListener(this);
		print.setFocusPainted(false);
		print.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolbar.add(print);

		orderModel = new TransportProductListModel();
		productsTable = new JTable(orderModel);

		TableColumn col1 = productsTable.getColumnModel().getColumn(0);
		col1.setMinWidth(10);
		col1.setPreferredWidth(20);
		col1.setWidth(20);
		GeneralTableRenderer renderer = new GeneralTableRenderer();
		for (int i = 0; i < productsTable.getColumnModel().getColumnCount(); i++) {
			productsTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

        totalCubaj = new JLabel("Total m cub: ");
        totalCubaj.setFont(new Font("sans-serif", Font.BOLD, 24));
        JPanel north = new JPanel(new BorderLayout());
        north.add(toolbar, BorderLayout.NORTH);
        north.add(totalCubaj, BorderLayout.SOUTH);
        getContentPane().add(north, BorderLayout.NORTH);
		//getContentPane().add(toolbar, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(productsTable), BorderLayout.CENTER);

	}


	public void actionPerformed(ActionEvent eve) {
		String command = eve.getActionCommand();
		if (command.equals("ADD_PRODUCT")) {
			addProductPanel = new AddProductToOrderPanel();
			JButton buttonOk = new JButton("Salveaza");
			buttonOk.setActionCommand("SAVE_ADD");
			buttonOk.addActionListener(this);
			JButton buttonCancel = new JButton("Anuleaza");
			buttonCancel.setActionCommand("CANCEL_ADD");
			buttonCancel.addActionListener(this);
			JOptionPane.showOptionDialog(GUIUtil.container, addProductPanel,
					"Adaugare produs", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new JButton[] {
							buttonOk, buttonCancel }, buttonCancel);
		} else if (command.equals("CANCEL_ADD")) {
			Window w = SwingUtilities.getWindowAncestor((JButton) eve
					.getSource());
			if (w != null)
				w.dispose();
		} else if (command.equals("SAVE_ADD")) {
			Map data = addProductPanel.validateData();
			if(data.size() == 2) {
                OrderItemProduct item = new OrderItemProduct();
                Product product = (Product)data.get("PRODUCT");
                item.setProduct(product);
                item.setQuantity(((Long) data.get("CANTITATE")).doubleValue());
                item.setVolume(item.getQuantity() * ProductTools.getVolume(product, METRIC.METER));
                cubaj += item.getVolume();
                totalCubaj.setText("Total m cub: " + formater.format(cubaj));
                orderModel.addProduct(item);
				Window w = SwingUtilities.getWindowAncestor((JButton) eve
						.getSource());
				if (w != null)
					w.dispose();
			}
		} else if (command.equals("DELETE_PRODUS")) {
			int row = productsTable.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(GUIUtil.container,
						"Selectati un produs");
			} else {
                OrderItemProduct item = orderModel.getProduct(row);
				int ras = JOptionPane.showConfirmDialog(GUIUtil.container,
						"Doriti sa stergeti produsul: " + item.getProduct().getName() + " Cantitate: " + item.getQuantity(),
						"Confirmati stergere", JOptionPane.YES_NO_OPTION);
				if (ras == JOptionPane.YES_OPTION) {
                    orderModel.deleteProduct(row);
                    cubaj -= item.getVolume();
                    totalCubaj.setText("Total m cub: " + formater.format(cubaj));
				}
			}
			
		}
		else if(command.equals("CSV")) {
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showSaveDialog(GUIUtil.container);  
			if(option == JFileChooser.APPROVE_OPTION){  
				if(chooser.getSelectedFile()!=null){  
				  File theFileToSave = chooser.getSelectedFile(); 
				  orderModel.toCsv(theFileToSave);
				}
			}
		}
		else if (command.equals("PRINT")) {
			try {
				productsTable.print();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public String getFrameCode() {
		return GUIUtil.CUBAJ_KEY;
	}

	@Override
	public ImageIcon getFrameIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageIcon getIconifiedIcon() {
		Image image = GUITools
				.getImage("/images/chartb32.png");
		return new ImageIcon(image);
	}

}
