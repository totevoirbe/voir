/*
 * MainPage.java
 *
 * Created on __DATE__, __TIME__
 */

package be.panidel.pos.gui;

/**
 * 
 * @author __USER__
 */
public class GenericXMLDataEditorWithError extends javax.swing.JFrame {

	// /**
	// *
	// */
	private static final long serialVersionUID = -1983737024486108991L;
	// private DAO currentDAO;
	//
	// private static final Logger log =
	// Logger.getLogger(GenericXMLDataEditorWithError.class);
	//
	// /** Creates new form MainPage */
	// public GenericXMLDataEditorWithError() {
	// super();
	// initData();
	// initComponents();
	// }
	//
	// private void initData() {
	// }
	//
	// /**
	// * This method is called from within the constructor to initialize the
	// form.
	// * WARNING: Do NOT modify this code. The content of this method is always
	// * regenerated by the Form Editor.
	// */
	// // GEN-BEGIN:initComponents
	// // <editor-fold defaultstate="collapsed" desc="Generated Code">
	// private void initComponents() {
	//
	// jScrollPane1 = new javax.swing.JScrollPane();
	// jList1 = new javax.swing.JList();
	// jScrollPane2 = new javax.swing.JScrollPane();
	// jTable1 = new javax.swing.JTable();
	// jButton1 = new javax.swing.JButton();
	// jButton2 = new javax.swing.JButton();
	// jButton3 = new javax.swing.JButton();
	// jButton4 = new javax.swing.JButton();
	// menuBar = new javax.swing.JMenuBar();
	// fileMenu = new javax.swing.JMenu();
	// openMenuItem = new javax.swing.JMenuItem();
	// saveMenuItem = new javax.swing.JMenuItem();
	// saveAsMenuItem = new javax.swing.JMenuItem();
	// exitMenuItem = new javax.swing.JMenuItem();
	// editMenu = new javax.swing.JMenu();
	// cutMenuItem = new javax.swing.JMenuItem();
	// copyMenuItem = new javax.swing.JMenuItem();
	// pasteMenuItem = new javax.swing.JMenuItem();
	// deleteMenuItem = new javax.swing.JMenuItem();
	// jMenu2 = new javax.swing.JMenu();
	// jMenuItem1 = new javax.swing.JMenuItem();
	// jMenuItem2 = new javax.swing.JMenuItem();
	// jMenuItem3 = new javax.swing.JMenuItem();
	// jMenuItem4 = new javax.swing.JMenuItem();
	// helpMenu = new javax.swing.JMenu();
	// contentsMenuItem = new javax.swing.JMenuItem();
	// aboutMenuItem = new javax.swing.JMenuItem();
	//
	// jList1.setModel(new javax.swing.AbstractListModel() {
	// /**
	// *
	// */
	// private static final long serialVersionUID = 6397096660377626107L;
	// String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
	// "Item 5" };
	//
	// public int getSize() {
	// return strings.length;
	// }
	//
	// public Object getElementAt(int i) {
	// return strings[i];
	// }
	// });
	// jScrollPane1.setViewportView(jList1);
	//
	// setDefaultCloseOperation(3);
	//
	// jTable1.setModel(new javax.swing.table.DefaultTableModel(
	// new Object[][] { { null, null, null, null },
	// { null, null, null, null }, { null, null, null, null },
	// { null, null, null, null } }, new String[] { "Title 1",
	// "Title 2", "Title 3", "Title 4" }));
	// jScrollPane2.setViewportView(jTable1);
	//
	// jButton1.setText("Enregistrer");
	// jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
	// public void mouseClicked(java.awt.event.MouseEvent evt) {
	// save(evt);
	// }
	// });
	//
	// jButton2.setText("Annuler");
	// jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
	// public void mouseClicked(java.awt.event.MouseEvent evt) {
	// cancel(evt);
	// }
	// });
	//
	// jButton3.setText("Nouvelle ligne");
	// jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
	// public void mouseClicked(java.awt.event.MouseEvent evt) {
	// newRow(evt);
	// }
	// });
	//
	// jButton4.setText("Fermer");
	// jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
	// public void keyReleased(java.awt.event.KeyEvent evt) {
	// hidePageKeyReleased(evt);
	// }
	// });
	// jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
	// public void mouseClicked(java.awt.event.MouseEvent evt) {
	// hidePageMouseClicked(evt);
	// }
	// });
	//
	// fileMenu.setText("File");
	//
	// openMenuItem.setText("Ouvrir un fichier");
	// fileMenu.add(openMenuItem);
	//
	// saveMenuItem.setText("Save");
	// fileMenu.add(saveMenuItem);
	//
	// saveAsMenuItem.setText("Save As ...");
	// fileMenu.add(saveAsMenuItem);
	//
	// exitMenuItem.setText("Exit");
	// exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent evt) {
	// exitMenuItemActionPerformed(evt);
	// }
	// });
	// fileMenu.add(exitMenuItem);
	//
	// // menuBar.add(fileMenu);
	//
	// editMenu.setText("Edit");
	//
	// cutMenuItem.setText("Cut");
	// editMenu.add(cutMenuItem);
	//
	// copyMenuItem.setText("Copy");
	// editMenu.add(copyMenuItem);
	//
	// pasteMenuItem.setText("Paste");
	// editMenu.add(pasteMenuItem);
	//
	// deleteMenuItem.setText("Delete");
	// editMenu.add(deleteMenuItem);
	//
	// // menuBar.add(editMenu);
	//
	// jMenu2.setText("File s\u00e9lection");
	//
	// jMenuItem1.setText("Product");
	// jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent evt) {
	// loadProducts(evt);
	// }
	// });
	// jMenu2.add(jMenuItem1);
	//
	// jMenuItem2.setText("employee");
	// jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent evt) {
	// loadEmployees(evt);
	// }
	// });
	// jMenu2.add(jMenuItem2);
	//
	// jMenuItem3.setText("payement");
	// jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent evt) {
	// loadPayements(evt);
	// }
	// });
	// jMenu2.add(jMenuItem3);
	//
	// jMenuItem4.setText("groupe");
	// jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
	// public void actionPerformed(java.awt.event.ActionEvent evt) {
	// loadGroups(evt);
	// }
	// });
	// jMenu2.add(jMenuItem4);
	//
	// menuBar.add(jMenu2);
	//
	// helpMenu.setText("Help");
	//
	// contentsMenuItem.setText("Contents");
	// helpMenu.add(contentsMenuItem);
	//
	// aboutMenuItem.setText("About");
	// helpMenu.add(aboutMenuItem);
	//
	// // menuBar.add(helpMenu);
	//
	// setJMenuBar(menuBar);
	//
	// GroupLayout layout = new GroupLayout(getContentPane());
	// getContentPane().setLayout(layout);
	// layout
	// .setHorizontalGroup(layout
	// .createParallelGroup(GroupLayout.Alignment.LEADING)
	// .addGroup(
	// layout
	// .createSequentialGroup()
	// .addContainerGap()
	// .addGroup(
	// layout
	// .createParallelGroup(
	// GroupLayout.Alignment.LEADING)
	// .addGroup(
	// layout
	// .createSequentialGroup()
	// .addComponent(
	// jScrollPane2,
	// GroupLayout.DEFAULT_SIZE,
	// 633,
	// Short.MAX_VALUE)
	// .addContainerGap())
	// .addGroup(
	// GroupLayout.Alignment.TRAILING,
	// layout
	// .createSequentialGroup()
	// .addComponent(
	// jButton3)
	// .addGap(
	// 18,
	// 18,
	// 18)
	// .addComponent(
	// jButton1)
	// .addGap(
	// 18,
	// 18,
	// 18)
	// .addComponent(
	// jButton2)
	// .addGap(
	// 21,
	// 21,
	// 21))))
	// .addGroup(
	// layout.createSequentialGroup().addGap(249, 249,
	// 249).addComponent(jButton4)
	// .addContainerGap(337, Short.MAX_VALUE)));
	// layout.setVerticalGroup(layout.createParallelGroup(
	// GroupLayout.Alignment.LEADING).addGroup(
	// layout.createSequentialGroup().addContainerGap().addComponent(
	// jScrollPane2, GroupLayout.PREFERRED_SIZE, 337,
	// GroupLayout.PREFERRED_SIZE).addPreferredGap(
	// LayoutStyle.ComponentPlacement.RELATED).addGroup(
	// layout.createParallelGroup(
	// GroupLayout.Alignment.BASELINE).addComponent(
	// jButton1).addComponent(jButton2).addComponent(
	// jButton3)).addPreferredGap(
	// LayoutStyle.ComponentPlacement.RELATED).addComponent(
	// jButton4).addContainerGap(218, Short.MAX_VALUE)));
	//
	// pack();
	// }// </editor-fold>
	//
	// // GEN-END:initComponents
	//
	// // GEN-FIRST:event_hidePageKeyReleased
	// private void hidePageKeyReleased(java.awt.event.KeyEvent evt) {
	// this.setVisible(false);
	// }// GEN-LAST:event_hidePageKeyReleased
	//
	// // GEN-FIRST:event_jButton1MouseClicked
	// private void hidePageMouseClicked(java.awt.event.MouseEvent evt) {
	// this.setVisible(false);
	// }// GEN-LAST:event_jButton1MouseClicked
	//
	// private void newRow(java.awt.event.MouseEvent evt) {
	// try {
	// jTable1.setModel(addRow(jTable1));
	// } catch (IllegalAccessException e) {
	// log.error("", e);
	// } catch (InvocationTargetException e) {
	// log.error("", e);
	// } catch (ParserConfigurationException e) {
	// log.error("", e);
	// } catch (SAXException e) {
	// log.error("", e);
	// } catch (IOException e) {
	// log.error("", e);
	// }
	// }
	//
	// private void save(java.awt.event.MouseEvent evt) {
	// try {
	// saveTableModel(jTable1.getModel());
	// } catch (ParserConfigurationException e) {
	// log.error("", e);
	// } catch (SAXException e) {
	// log.error("", e);
	// } catch (IOException e) {
	// log.error("", e);
	// } catch (IllegalAccessException e) {
	// log.error("", e);
	// } catch (InvocationTargetException e) {
	// log.error("", e);
	// } catch (TransformerException e) {
	// log.error("", e);
	// }
	// }
	//
	// private void cancel(java.awt.event.MouseEvent evt) {
	// jTable1.setModel(loadTableModel());
	// }
	//
	// private void loadGroups(java.awt.event.ActionEvent evt) {
	// load(FacadeDAO.instance().getGroupsDAO());
	// }
	//
	// private void loadPayements(java.awt.event.ActionEvent evt) {
	// load(FacadeDAO.instance().getPayementModesDAO());
	// }
	//
	// private void loadEmployees(java.awt.event.ActionEvent evt) {
	// load(FacadeDAO.instance().getEmployeesDAO());
	// }
	//
	// private void loadProducts(java.awt.event.ActionEvent evt) {
	// load(FacadeDAO.instance().getProductsDAO());
	// }
	//
	// private void load(DAO dao) {
	// currentDAO = dao;
	// jTable1.setModel(loadTableModel());
	// }
	//
	// private TableModel loadTableModel() {
	// TableModel tableModel = null;
	// try {
	// tableModel = initTableModel();
	// } catch (IllegalArgumentException e) {
	// log.error(currentDAO.getNodeName(), e);
	// } catch (ParserConfigurationException e) {
	// log.error(currentDAO.getNodeName(), e);
	// } catch (SAXException e) {
	// log.error(currentDAO.getNodeName(), e);
	// } catch (IOException e) {
	// log.error(currentDAO.getNodeName(), e);
	// } catch (IllegalAccessException e) {
	// log.error(currentDAO.getNodeName(), e);
	// } catch (InvocationTargetException e) {
	// log.error(currentDAO.getNodeName(), e);
	// }
	// return tableModel;
	// }
	//
	// private TableModel initTableModel() throws ParserConfigurationException,
	// SAXException, IOException, IllegalArgumentException,
	// IllegalAccessException, InvocationTargetException {
	// TableModel dtm = null;
	// List<Identification> list = currentDAO.getList();
	//
	// if (!Tools.isNullOrEmpty(list)) {
	//
	// int row = 0;
	// int rows = list.size();
	// int columns = currentDAO.getElementAttributeNameList().size();
	// Object[][] docArray = new Object[rows][columns];
	//
	// for (Iterator<Identification> iterator = list.iterator(); iterator
	// .hasNext();) {
	// Identification identification = iterator.next();
	// fulfillRow(row, docArray, identification);
	// row++;
	// }
	// dtm = new RefTableModel(docArray, currentDAO
	// .getElementAttributeNameList());
	// }
	// return dtm;
	// }
	//
	// private void fulfillRow(int row, Object[][] docArray,
	// Identification identification) throws IllegalAccessException,
	// InvocationTargetException {
	// int column = 0;
	// for (String attributeName : currentDAO.getElementAttributeNameList()) {
	// Method getter = currentDAO.getElementGetter(attributeName);
	// Object data = getter.invoke(identification,
	// new Object[] {});
	// if (data instanceof Identification) {
	// Identification new_name = (Identification) data;
	// docArray[row][column++] = new_name.getId();
	// } else {
	// docArray[row][column++] = data;
	// }
	// }
	// }
	//
	// private Identification fulfillElementFromRow(TableModel tableModel, int
	// row)
	// throws IllegalAccessException, InvocationTargetException {
	// int column = 0;
	// Identification element = currentDAO.getEmpty();
	// for (String attributeName : currentDAO.getElementAttributeNameList()) {
	// Method setter = currentDAO.getElementSetter(attributeName);
	// Object data = tableModel.getValueAt(row, column++);
	// setter.invoke(element, data);
	// }
	// return element;
	// }
	//
	// private TableModel addRow(javax.swing.JTable table)
	// throws IllegalAccessException, InvocationTargetException,
	// ParserConfigurationException, SAXException, IOException {
	//
	// TableModel dtm = null;
	// TableModel tableModel = table.getModel();
	// int columnSize = tableModel.getColumnCount();
	// int oldRowSize = tableModel.getRowCount();
	//
	// Object[][] docArray = new Object[oldRowSize+1][columnSize];
	//
	// // copy existing data
	// for (int rowIndex = 0; rowIndex < oldRowSize; rowIndex++) {
	// for (int colIndex = 0; colIndex < columnSize; colIndex++) {
	// docArray[rowIndex][colIndex] = tableModel.getValueAt(rowIndex,
	// colIndex);
	// }
	// }
	//		
	// Identification newElement = currentDAO.getNew();
	//
	// // new id
	// int newIndex = 1;
	// String newIndexAsString = null;
	// do {
	// newIndexAsString = Integer.toString(newIndex++);
	// int rowIndex = 0;
	// for (; rowIndex < oldRowSize; rowIndex++) {
	// if (newIndexAsString.equals(((String) docArray[rowIndex][0])
	// .trim())) {
	// break;
	// }
	// }
	// if (rowIndex == oldRowSize && !currentDAO.isIdExisting(newIndexAsString))
	// {
	// docArray[oldRowSize][0] = newIndexAsString;
	// }
	// } while (docArray[oldRowSize][0] == null);
	// fulfillRow(oldRowSize, docArray, newElement);
	//
	// dtm = new RefTableModel(docArray,
	// currentDAO.getElementAttributeNameList());
	//
	// return dtm;
	// }
	//
	// private void saveTableModel(TableModel tableModel)
	// throws ParserConfigurationException, SAXException, IOException,
	// IllegalAccessException, InvocationTargetException,
	// TransformerException {
	// int rowSize = tableModel.getRowCount();
	// Element node = currentDAO.deleteAll();
	// for (int row = 0; row < rowSize; row++) {
	// Identification identification = fulfillElementFromRow(tableModel, row);
	// currentDAO.newElement(node, identification);
	// }
	// currentDAO.saveTable();
	// }
	//
	// private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt)
	// {// GEN-FIRST:event_exitMenuItemActionPerformed
	// System.exit(0);
	// }// GEN-LAST:event_exitMenuItemActionPerformed
	//
	// // /**
	// // * @param args
	// // * the command line arguments
	// // */
	// // public static void main(String args[]) {
	// // java.awt.EventQueue.invokeLater(new Runnable() {
	// // public void run() {
	// // new XMLDataEditor().setVisible(true);
	// // }
	// // });
	// // }
	//
	// private static JFrame instance;
	// private static Object lock = new Object();
	//
	// public static void createAndShowGUI() {
	// if (instance == null) {
	// synchronized (lock) {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// instance = new GenericXMLDataEditorWithError();
	// instance.setVisible(true);
	// }
	// });
	// }
	// } else {
	// instance.setVisible(true);
	// }
	// }
	//
	// public static void main(String[] args) {
	// createAndShowGUI();
	// }
	//
	// // GEN-BEGIN:variables
	// // Variables declaration - do not modify
	// private javax.swing.JMenuItem aboutMenuItem;
	// private javax.swing.JMenuItem contentsMenuItem;
	// private javax.swing.JMenuItem copyMenuItem;
	// private javax.swing.JMenuItem cutMenuItem;
	// private javax.swing.JMenuItem deleteMenuItem;
	// private javax.swing.JMenu editMenu;
	// private javax.swing.JMenuItem exitMenuItem;
	// private javax.swing.JMenu fileMenu;
	// private javax.swing.JMenu helpMenu;
	// private javax.swing.JButton jButton1;
	// private javax.swing.JButton jButton2;
	// private javax.swing.JButton jButton3;
	// private javax.swing.JButton jButton4;
	// private javax.swing.JList jList1;
	// private javax.swing.JMenu jMenu2;
	// private javax.swing.JMenuItem jMenuItem1;
	// private javax.swing.JMenuItem jMenuItem2;
	// private javax.swing.JMenuItem jMenuItem3;
	// private javax.swing.JMenuItem jMenuItem4;
	// private javax.swing.JScrollPane jScrollPane1;
	// private javax.swing.JScrollPane jScrollPane2;
	// private javax.swing.JTable jTable1;
	// private javax.swing.JMenuBar menuBar;
	// private javax.swing.JMenuItem openMenuItem;
	// private javax.swing.JMenuItem pasteMenuItem;
	// private javax.swing.JMenuItem saveAsMenuItem;
	// private javax.swing.JMenuItem saveMenuItem;
	// // End of variables declaration//GEN-END:variables

}