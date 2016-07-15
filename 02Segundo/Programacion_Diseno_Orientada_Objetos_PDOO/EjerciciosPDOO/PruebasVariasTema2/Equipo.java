/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasVariasTema2;

import java.util.ArrayList;

/**
 *
 * @author aljibe
 */
public class Equipo {
    ArrayList<Persona>miembros= new ArrayList();
    
    public void addMiembro(Persona miembro) {
        miembros.add(miembro);
    }
    public Persona getMiembro(){
        return miembros.get(miembros.size()-1);
    }
    public void verMiembros(){
        for (Persona p: miembros)
        {System.out.println(p.nombre);}
    }
    
    public static void main(String args[]){
        Persona p8= new Persona("p8",10);
//        Persona p9= new Persona ("p9",20);
//        Equipo e= new Equipo();
//        e.addMiembro(p8);
//        e.addMiembro(p9);
//        System.out.println(e.getMiembro().nombre);
//        e.verMiembros();
    }
}
