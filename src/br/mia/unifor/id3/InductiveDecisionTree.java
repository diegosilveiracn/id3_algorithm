package br.mia.unifor.id3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.mia.unifor.id3.InductiveDecisionTree.Node.Type;

public class InductiveDecisionTree {
    
    public InductiveDecisionTree() {
        
    }
    
    public Node makeNodeTree(String[] column, String[][] data) {
        
        Node node = null;
        
        Map<Integer, Double> map = this.calcEntropya(data);
        
        Integer auxColumn = null;
        for (Integer numberColumn : map.keySet()) {
            if (auxColumn == null) {
                auxColumn = numberColumn;
            } else {
                if (map.get(numberColumn).compareTo(map.get(auxColumn)) > 0)
                    auxColumn = numberColumn;
            }
        }
        
        if (map.size() > 2 && map.get(auxColumn).doubleValue() != 0) {
            node = new Node(column[auxColumn], Type.DECISION);
            Set<String> branch = this.columnElements(data, auxColumn);
            
            for (String elementBranch : branch) {
                Map<String, Node> auxBranch = new HashMap<String, Node>();
                Node subNode = this.makeNodeTree(this.getSubColumn(column,
                        auxColumn), this.getSubMatrix(data, auxColumn,
                        elementBranch));
                auxBranch.put(elementBranch, subNode);
                node.setBranch(auxBranch);
            }
        } else {
            node = new Node(column[auxColumn], Type.DECISION);
            
            Set<String> branch = this.columnElements(data, auxColumn);
            
            for (String elementBranch : branch) {
                Map<String, Node> auxBranch = new HashMap<String, Node>();
                Node subNode = new Node(this.getClasse(data, auxColumn,
                        elementBranch), Type.CLASS);
                auxBranch.put(elementBranch, subNode);
                node.setBranch(auxBranch);
            }
        }
        
        return node;
    }
    
    private Map<Integer, Double> calcEntropya(String[][] data) {
        
                /*
                 * Return a Map object, where the key represents number of column and
                 * value entropy value
                 */
        Map<Integer, Double> out = new HashMap<Integer, Double>();
        
        // Check all columns and calculate entropy value
        for (int columnNumber = 0; columnNumber < data[0].length - 1; columnNumber++) {
            
            Set<String> column = this.columnElements(data, columnNumber);
            Set<String> classe = this.columnElements(data, data[0].length - 1);
            
            double entropyValue = 0; // Save the entropy value of column
            for (String columnElement : column) {
                
                double calcLog = 0;
                for (String classElement : classe) {
                    
                    int columnCass = 0;
                    // Check all lines of data
                    for (int lineNumber = 0; lineNumber < data.length; lineNumber++) {
                        if (data[lineNumber][columnNumber]
                                .equals(columnElement)
                                && data[lineNumber][data[0].length - 1]
                                .equals(classElement))
                            columnCass += 1;
                    }
                    
                    if (columnCass != 0) {
                        int quantityClass = this.quantityColumnElements(data,
                                data[0].length - 1, classElement);
                        double aux = (double) columnCass / quantityClass;
                        calcLog += (-aux * (Math.log10(aux) / Math.log10(2)));
                    }
                }
                
                int quantityElement = quantityColumnElements(data,
                        columnNumber, columnElement);
                entropyValue += ((double) quantityElement / data.length)
                * calcLog;
            }
            
            // Map whitin number of column and value entropy
            out.put(new Integer(columnNumber), new Double(entropyValue));
        }
        
        return out;
    }
    
    public static class Node {
        
        public static enum Type {
            DECISION, CLASS
        };
        
        private String name;
        
        public Type type;
        
        private List<Map> branch;
        
        public Node(String name, Type type) {
            this.name = name;
            this.type = type;
            this.branch = new ArrayList<Map>();
        }
        
        public Node(String node, Type type, List<Map> list) {
            this.name = node;
            this.branch = list;
            this.type = type;
        }
        
        public boolean isType(Type type) {
            return this.type == type;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public List<Map> getBranch() {
            return branch;
        }
        
        public void setBranch(List<Map> branch) {
            this.branch = branch;
        }
        
        public void setBranch(Map<String, Node> map) {
            this.branch.add(map);
        }
        
    }
    
    private Set<String> columnElements(String[][] data, int indexColumn) {
        
        Set<String> set = new HashSet<String>();
        
        for (int i = 0; i < data.length; i++) {
            String element = data[i][indexColumn];
            if (!set.contains(element)) {
                set.add(element);
            }
        }
        return set;
    }
    
    private int quantityColumnElements(String[][] data, int indexColumn,
            String labelElment) {
        
        int value = 0;
        
        for (int i = 0; i < data.length; i++) {
            if (data[i][indexColumn].equals(labelElment)) {
                value += 1;
            }
        }
        
        return value;
    }
    
    private String[][] getSubMatrix(String[][] data, int removeColumn,
            String element) {
        
        int numberLine = this.quantityColumnElements(data, removeColumn,
                element);
        int numberColumn = data[0].length - 1;
        
        String[][] out = new String[numberLine][numberColumn];
        
        // Make new matrix
        for (int line = 0, newLine = 0; line < data.length; line++) {
            if (!data[line][removeColumn].equals(element))
                continue;
            for (int column = 0, newColumn = 0; column < data[0].length; column++) {
                if (column == removeColumn)
                    continue;
                out[newLine][newColumn] = data[line][column];
                newColumn++;
            }
            newLine++;
        }
        
        return out;
    }
    
    private String[] getSubColumn(String[] columns, int removeColumn) {
        
        String[] out = new String[columns.length - 1];
        
        for (int column = 0, newColumn = 0; column < columns.length; column++) {
            if (column == removeColumn)
                continue;
            out[newColumn] = columns[column];
            newColumn++;
        }
        
        return out;
    }
    
    private String getClasse(String[][] data, int column, String element) {
        
        String out = null;
        for (int linha = 0; linha < data.length; linha++) {
            if (data[linha][column].equals(element)) {
                out = data[linha][data[0].length - 1];
                break;
            }
        }
        return out;
    }
}