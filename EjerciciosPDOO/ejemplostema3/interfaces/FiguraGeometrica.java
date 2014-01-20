/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3.interfaces;

/**
 *
 * @author ana
 */
public abstract class FiguraGeometrica{
  private int numLados;
  public FiguraGeometrica(int lados){
        this.setNumLados(lados);
  }
  public abstract float perimetro();
  public abstract float area();
  public void setNumLados(int numL){numLados = numL;}
  public int getNumLados(){return numLados;}
  
}
