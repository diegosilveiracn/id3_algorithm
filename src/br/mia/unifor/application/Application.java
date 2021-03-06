/*
 * Application.java
 *
 * Created on September 25, 2007, 11:43 PM
 */

package br.mia.unifor.application;

import br.mia.unifor.id3.CrossValitadion;
import br.mia.unifor.id3.InductiveDecisionTree;
import br.mia.unifor.id3.InductiveDecisionTree.Node;
import br.mia.unifor.id3.InductiveDecisionTree.Node.Type;
import br.mia.unifor.util.Graph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author  diego
 */
public class Application extends javax.swing.JFrame {
    
    /** Creates new form Application */
    public Application() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneFunction = new javax.swing.JTabbedPane();
        jPanelData = new javax.swing.JPanel();
        jScrollPaneData = new javax.swing.JScrollPane();
        jTableData = new javax.swing.JTable();
        jPanelTree = new javax.swing.JPanel();
        jScrollPaneTree = new javax.swing.JScrollPane();
        jTreeDecisionTree = new javax.swing.JTree();
        jPanelValidation = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuData = new javax.swing.JMenu();
        jMenuItemLoad = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuTree = new javax.swing.JMenu();
        jMenuItemGenerate = new javax.swing.JMenuItem();
        jMenuValidation = new javax.swing.JMenu();
        jMenuItemCrossValidation = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAboutSistem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ID3 Algorithm");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("frameMain");
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jPanelData.setLayout(new javax.swing.BoxLayout(jPanelData, javax.swing.BoxLayout.X_AXIS));

        jTableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPaneData.setViewportView(jTableData);

        jPanelData.add(jScrollPaneData);

        jTabbedPaneFunction.addTab("Data", jPanelData);

        jPanelTree.setLayout(new javax.swing.BoxLayout(jPanelTree, javax.swing.BoxLayout.X_AXIS));

        jTreeDecisionTree.setModel(null);
        jScrollPaneTree.setViewportView(jTreeDecisionTree);

        jPanelTree.add(jScrollPaneTree);

        jTabbedPaneFunction.addTab("Tree", jPanelTree);

        jPanelValidation.setLayout(new java.awt.GridLayout(1, 0));

        jPanelValidation.setAutoscrolls(true);
        jTabbedPaneFunction.addTab("Validation", jPanelValidation);

        jPanel1.add(jTabbedPaneFunction);

        getContentPane().add(jPanel1);

        jMenuData.setText("Data");
        jMenuItemLoad.setText("Load");
        jMenuItemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoadActionPerformed(evt);
            }
        });

        jMenuData.add(jMenuItemLoad);

        jMenuData.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });

        jMenuData.add(jMenuItemExit);

        jMenuBar1.add(jMenuData);

        jMenuTree.setText("Tree");
        jMenuItemGenerate.setText("Generete");
        jMenuItemGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGenerateActionPerformed(evt);
            }
        });

        jMenuTree.add(jMenuItemGenerate);
        jMenuItemGenerate.getAccessibleContext().setAccessibleName("Generate");

        jMenuBar1.add(jMenuTree);

        jMenuValidation.setText("Validation");
        jMenuItemCrossValidation.setText("Cross Validation");
        jMenuItemCrossValidation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCrossValidationActionPerformed(evt);
            }
        });

        jMenuValidation.add(jMenuItemCrossValidation);

        jMenuBar1.add(jMenuValidation);

        jMenuHelp.setText("Help");
        jMenuItemAboutSistem.setText("About System");
        jMenuItemAboutSistem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutSistemActionPerformed(evt);
            }
        });

        jMenuHelp.add(jMenuItemAboutSistem);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-638)/2, (screenSize.height-539)/2, 638, 539);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenuItemCrossValidationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrossValidationActionPerformed
        
        String input = JOptionPane.showInputDialog(this,"Informe size fold:","10");
        int fold = 0;
        try{
            fold = Integer.parseInt(input);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid number", "", JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(this,"Informe size fold:","10");
        }
        List<Node> listNodeFold = new ArrayList<Node>();
        CrossValitadion cross = new CrossValitadion();
        List<Map> list = cross.validation(this.getColumn(),this.getData(), fold, listNodeFold);
        this.jPanelValidation.add(Graph.getGrafico(list));
        
        /*int i = 1;
        for(Node node: listNodeFold){
         
            DefaultMutableTreeNode treeNodenew = new DefaultMutableTreeNode("Fold "+i);
         
            JTree jTree = new JTree(treeNodenew);
            //scrollValidationTree.setViewportView(jTree);
            //jPanelValidation.add(jTree);
            JPanel panel = new JPanel();
            panel.add(jTree);
            jPanelFoldTree.add(panel);
            treeNodenew.add(this.createTree("", node));
            i++;
        }*/
        
    }//GEN-LAST:event_jMenuItemCrossValidationActionPerformed
    
    private void jMenuItemAboutSistemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutSistemActionPerformed
        
        JOptionPane
                .showMessageDialog(
                this,
                "Iduticve Descision Tree - ID3\n\n Author: Diego Silveira\n\n University of Fortaleza - Unifor\nMaster Course in Applied Informatics - MIA",
                "Help ID3", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemAboutSistemActionPerformed
    
    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed
    
    /**
     * Method to make decision tree
     *
     * @param evt Objetct ActionEvent
     */
    private void jMenuItemGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGenerateActionPerformed
        
        InductiveDecisionTree.Node rootNode = new InductiveDecisionTree()
        .makeNodeTree(this.getColumn(), this.getData());
        
        DefaultMutableTreeNode treeNodenew = new DefaultMutableTreeNode("ROOT");
        
        this.jTreeDecisionTree = new JTree(treeNodenew);
        this.jScrollPaneTree.setViewportView(this.jTreeDecisionTree);
        
        treeNodenew.add(this.createTree("", rootNode));
    }//GEN-LAST:event_jMenuItemGenerateActionPerformed
    
    private DefaultMutableTreeNode createTree(String branch, Node node) {
        
        String nodeName = "".equals(branch) ? node.getName() : "[" + branch
                + "] " + node.getName();
        DefaultMutableTreeNode treeNodenew = new DefaultMutableTreeNode(
                nodeName);
        
        if (node.isType(Type.DECISION)) {
            
            List<Map> list = node.getBranch();
            for (Map<String, Node> map : list) {
                for (String key : map.keySet()) {
                    treeNodenew.add(this.createTree(key, map.get(key)));
                }
            }
        }
        
        return treeNodenew;
    }
    
    private String[] getColumn(){
        
        int numberColumn = this.jTableData.getColumnCount();
        
        String[] nameColumn = new String[numberColumn];
        
        for (int column = 0; column < numberColumn; column++)
            
            nameColumn[column] = this.jTableData.getColumnName(column);
        
        return nameColumn;
    }
    
    private String[][] getData(){
        
        int numberLine = this.jTableData.getRowCount();
        int numberColumn = this.jTableData.getColumnCount();
        
        String[][] data = new String[numberLine][numberColumn];
        
        for (int line = 0; line < numberLine; line++)
            for (int column = 0; column < numberColumn; column++)
                data[line][column] = (String) this.jTableData.getValueAt(line,
                        column);
        
        return data;
    }
    
    private void jMenuItemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadActionPerformed
        
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        
        fileChooser.setFileFilter(new FileFilter() {
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(".base");
            }
            
            public String getDescription() {
                return "Base file";
            }
        });
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.CANCEL_OPTION)
            return;
        
        File file = fileChooser.getSelectedFile();
        if (file == null || file.getName().equals(""))
            JOptionPane.showMessageDialog(this, "Inavalid file name",
                    "Inavalid file name", JOptionPane.ERROR_MESSAGE);
        else {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(fileInputStream));
                
                Vector<Vector> data = new Vector<Vector>();
                Vector<String> columns = new Vector<String>();
                
                boolean firstLine = Boolean.TRUE;
                while (Boolean.TRUE) {
                    
                    String line = bufferedReader.readLine();
                    if (line == null)
                        break;
                    
                    // Make columns ---------------
                    if (firstLine) {
                        StringTokenizer tokenColumns = new StringTokenizer(
                                line, ",");
                        while (tokenColumns.hasMoreTokens()) {
                            columns.add(tokenColumns.nextToken());
                        }
                        firstLine = !firstLine;
                        continue;
                    }
                    
                    // Make data ------------------
                    StringTokenizer tokenLines = new StringTokenizer(line, ",");
                    Vector<String> elements = new Vector<String>();
                    while (tokenLines.hasMoreTokens())
                        elements.add(tokenLines.nextToken());
                    data.add(elements);
                }
                
                TableModel jTableDataModel = new DefaultTableModel(data,
                        columns);
                jTableData = new JTable();
                jScrollPaneData.setViewportView(jTableData);
                jTableData.setModel(jTableDataModel);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemLoadActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuData;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAboutSistem;
    private javax.swing.JMenuItem jMenuItemCrossValidation;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemGenerate;
    private javax.swing.JMenuItem jMenuItemLoad;
    private javax.swing.JMenu jMenuTree;
    private javax.swing.JMenu jMenuValidation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelTree;
    private javax.swing.JPanel jPanelValidation;
    private javax.swing.JScrollPane jScrollPaneData;
    private javax.swing.JScrollPane jScrollPaneTree;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPaneFunction;
    private javax.swing.JTable jTableData;
    private javax.swing.JTree jTreeDecisionTree;
    // End of variables declaration//GEN-END:variables
    
}
