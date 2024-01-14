/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.danielfreitassc.tanks2d;

import javax.swing.JFrame;

import br.com.danielfreitassc.modelo.Fase;

/**
 *
 * @author andro
 */
public class Container extends JFrame{
    
    public Container(){
        add(new Fase());
        setTitle("Tanks2D");
        setSize(1024,768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
    public static void main(String []args){
        new Container();
    }
}
