/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3.interfaces;

/**
 *
 * @author Carlos de la Torre
 */
public class Rectangulo extends FiguraGeometrica implements FiguraGrafica{
    float ladoa, ladob;
    public Rectangulo (float la, float lb){
        super(4);
        ladoa =la;
        ladob =lb;
    }
    @Override
    public float perimetro(){ return ((ladoa*2)+(ladob*2)); }
    @Override
    public float area(){return (ladoa*ladob);}
    @Override
    public void pintarBorde(String color){
        System.out.println("El color es: " + color + " y el grosor es: " + FiguraGrafica.grosorBorde);
    }
    @Override
    public void colorear(String color){ }
}

 
