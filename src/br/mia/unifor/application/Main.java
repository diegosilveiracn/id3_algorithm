package br.mia.unifor.application;

/**
 *
 *
 *
 */
public class Main {
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }
}