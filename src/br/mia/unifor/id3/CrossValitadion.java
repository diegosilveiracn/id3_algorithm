/*
 * CrossValitadion.java
 *
 * Created on September 27, 2007, 2:02 PM
 */
package br.mia.unifor.id3;

import br.mia.unifor.id3.InductiveDecisionTree.Node;
import br.mia.unifor.id3.InductiveDecisionTree.Node.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for validation
 *
 * @author Diego Silveira
 */
public class CrossValitadion {
    
    public CrossValitadion() {
        
    }
    
    public List<Map> validation(String[] column, String[][] data,  int fold, List<Node> listNodeFold){
        
        List<Map> list = new ArrayList<Map>();
        int linha = 1;
        while(linha + fold <= data.length){
            
            Node node = new InductiveDecisionTree().makeNodeTree(column,this.getLearingSet(data, linha-1, ((linha -1) + (fold - 1))));
            String[][] testing = this.getFoldSet(data, linha-1, ((linha -1) + (fold - 1)));
            
            list.add(this.count(linha+"-"+(linha + fold-1),  node, column, testing));
            
            linha += fold;
            listNodeFold.add(node);
        }
        return list;
    }
    
    private Map<String, Double> count(String foldName,Node node,String[] column, String[][] testing){
        
        Map<String, Double> out = new HashMap<String,Double>();
        
        double count = 0;
        for(int line = 0; line < testing.length; line++ ){
            int indexColumn = this.getColumnIndex(node.getName(), column);
            if (! this.exploreTree(node, column, testing, line, indexColumn))
                count++;
        }
        out.put(foldName, new Double(count/testing.length));
        
        return out;
    }
    
    private boolean exploreTree(Node node, String[] column, String[][] testing, int line,int numberColumn){
        
        boolean out = Boolean.FALSE;
        
        if ( node.type == Type.DECISION ) {
            String atributeValue = testing[line][numberColumn];
            
            for(Map map: node.getBranch()){
                for(Object branch: map.keySet()){
                    if(atributeValue.equals(branch)){
                        int newColumn = this.getColumnIndex( ((Node) map.get(branch)).getName() ,column);
                        out = this.exploreTree((Node)map.get(branch), column,testing, line, newColumn);
                    }
                    break;
                }
            }
            
        }else {
            if (node.getName().equals( testing[line][column.length -1]))
                return Boolean.TRUE;
        }
        
        return out;
    }
    
    private int getColumnIndex(String element, String[] column){
        
        for(int index =0; index < column.length; index++)
            if (column[index].equals(element))
                return index;
        
        return -1;
    }
    
    private String[][] getFoldSet(String [][] matrix, int top, int down){
        
        int numberLine = (down - top) + 1;
        int numberColumn = matrix[0].length;
        
        String[][] out = new String[numberLine][numberColumn];
        
        for(int line= top, lineAux =0; line <= down; line++, lineAux++ ) {
            for(int column = 0, columnAux = 0; column < numberColumn; column++, columnAux++){
                out[lineAux][columnAux] = matrix[line][column];
            }
        }
        
        return out;
    }
    
    private String[][] getLearingSet(String[][] matrix, int top, int down) {
        
        int numberLine = matrix.length - ((down - top) + 1 );
        int numberColumn = matrix[0].length;
        
        String[][] out = new String[numberLine][numberColumn];
        
        for(int line = 0, lineAux = 0; line < matrix.length; line++){
            for(int column =0; column < numberColumn; column++ ){
                if(line >= top && line <= down)
                    break;
                out[lineAux][column] = matrix[line][column];
            }
            if(line >= top && line <= down)
                continue;
            lineAux++;
        }
        
        return out;
    }
}