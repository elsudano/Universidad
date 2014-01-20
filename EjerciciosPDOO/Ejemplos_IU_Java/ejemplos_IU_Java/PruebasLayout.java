/*
 * Ejemplo que ilustra algunos de los ipos de Layout asociados a un contenedor:
 * FlowLayout, BorderLayout, GridLayout y BoxLayout. Compararlos
 */
package ejemplos_IU_Java;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author mjfortiz
 */
public class PruebasLayout {
JFrame f ;
Container cont;
JButton boton1;
JButton boton2;
JButton boton3;
JLabel label1;
JLabel label2;
    


public void flow(){
    
cont.setLayout(new FlowLayout());        
cont.add(label1);
cont.add(boton1);
cont.add(boton2);
cont.add(boton3);
cont.add(label2);
f.setSize( 500,300);
f.setVisible( true );
        
    }

public void border(){
    
//El layout por defecto es BorderLayout
    
cont.setLayout(new BorderLayout()); 
cont.add(label1, BorderLayout.EAST);
cont.add(boton1, BorderLayout.NORTH);
cont.add(boton2,BorderLayout.CENTER);
cont.add(boton3, BorderLayout.SOUTH);
cont.add(label2, BorderLayout.WEST);
f.setSize( 500,300);
f.setVisible( true );
        
    }

public void box(){ 
//descomentar una de las dos siguientes y probar la diferencia
    
//cont.setLayout(new BoxLayout(cont,BoxLayout.X_AXIS));
//cont.setLayout(new BoxLayout(cont,BoxLayout.Y_AXIS));    
      
cont.add(label1);
cont.add(boton1);
cont.add(boton2);
cont.add(boton3);
cont.add(label2);
f.setSize( 500,300);
f.setVisible( true );
}

public void grid(){

cont.setLayout(new GridLayout(2,3));
cont.add(label1);
cont.add(boton1);
cont.add(boton2);
cont.add(boton3);
cont.add(label2);
f.setSize( 500,300);
f.setVisible( true );
}
        

PruebasLayout(){
f = new JFrame("Un ejemplo");
cont= f.getContentPane();
label1 = new JLabel("Etiqueta primera");
boton1 = new JButton("uno");
boton2 = new JButton("dos");
boton3 = new JButton("tres");
label2 = new JLabel("Etiqueta segunda");
}

public static void main (String args[]){
  
PruebasLayout pru= new PruebasLayout();

//Descomentar uno cada vez para ver la diferencia
//pru.flow();
//pru.border();
//pru.box();
pru.grid();

//posible mejora: añadir otros layout y probar
}
    
}
