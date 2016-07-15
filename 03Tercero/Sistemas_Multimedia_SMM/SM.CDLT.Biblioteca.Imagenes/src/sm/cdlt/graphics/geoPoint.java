package sm.cdlt.graphics;

/**
 * Clase que pretende modelar un punto en un entorno virtual 3D.
 *
 * For contact with me visit https://www.sudano.net or send me a email
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 11-abr-2016
 */
public class geoPoint {

    /**
     * Posición en el eje X del área de trabajo.
     */
    private double posX;
    /**
     * Posición en el eje Y del área de trabajo.
     */
    private double posY;
    /**
     * Posición en el eje Z del área de trabajo.
     */
    private double posZ;

    /**
     * Constructor sin parámetros, pone un punto en el centro del área de
     * trabajo.
     */
    public geoPoint() {
        this.posX = 0.0;
        this.posY = 0.0;
        this.posZ = 0.0;
    }

    /**
     * Constructor solo con 2 parámetros para trabajar en 2D.
     *
     * @param X [in] posición en el eje X
     * @param Y [in] posición en el eje Y
     */
    public geoPoint(int X, int Y) {
        this.posX = X;
        this.posY = Y;
        this.posZ = 0.0;
    }

    /**
     * Constructor solo con 2 parámetros para trabajar en 2D.
     *
     * @param X [in] posición en el eje X
     * @param Y [in] posición en el eje Y
     */
    public geoPoint(double X, double Y) {
        this.posX = X;
        this.posY = Y;
        this.posZ = 0.0;
    }

    /**
     * Constructor con 3 parámetros para trabajar en 3D.
     *
     * @param X [in] posición en el eje X
     * @param Y [in] posición en el eje Y
     * @param Z [in] posición en el eje Z
     */
    public geoPoint(int X, int Y, int Z) {
        this.posX = X;
        this.posY = Y;
        this.posZ = Z;
    }

    /**
     * Constructor con 3 parámetros para trabajar en 3D.
     *
     * @param X [in] posición en el eje X
     * @param Y [in] posición en el eje Y
     * @param Z [in] posición en el eje Z
     */
    public geoPoint(double X, double Y, double Z) {
        this.posX = X;
        this.posY = Y;
        this.posZ = Z;
    }

    /**
     * Método que nos permite ver cual es la posición del punto en el eje X.
     *
     * @return devuelve la posición del punto en el eje X
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Método que nos permite poner el punto en una posición concreta del eje X.
     *
     * @param posX [in] posición en donde queremos que este el punto en el eje
     * X.
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * Método que nos permite ver cual es la posición del punto en el eje Y.
     *
     * @return devuelve la posición del punto en el eje Y
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Método que nos permite poner el punto en una posición concreta del eje Y.
     *
     * @param posY [in] posición en donde queremos que este el punto en el eje
     * X.
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    /**
     * Método que nos permite ver cual es la posición del punto en el eje Z.
     *
     * @return devuelve la posición del punto en el eje Z
     */
    public double getPosZ() {
        return posZ;
    }

    /**
     * Método que nos permite poner el punto en una posición concreta del eje Z.
     *
     * @param posZ [in] posición en donde queremos que este el punto en el eje
     * Z.
     */
    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    /**
     * Método que traslada el punto a la posición del nuevo punto.
     *
     * @param dx [in] la cantidad de unidades que tenemos que desplazar el punto
     * en el eje x.
     * @param dy [in] la cantidad de unidades que tenemos que desplazar el punto
     * en el eje y.
     * @param dz [in] la cantidad de unidades que tenemos que desplazar el punto
     * en el eje z.
     */
    public void traslate(double dx, double dy, double dz) {
        this.posX += dx;
        this.posY += dy;
        this.posZ += dz;
    }

    /**
     * Este método nos sirve para comprobar cual de los puntos es mas grande.
     *
     * @param p [in] Objeto de tipo geoPoint con el que vamos a comparar.
     *
     * @return Devuelve verdadero si el punto es mas grande que el que se pasa
     * por parámetros, falso en caso contrario.
     */
    public boolean isGreaterThan(geoPoint p) {
        boolean ret = false;
        if (this.posX > p.getPosX() || this.posY > p.getPosY() || this.posZ > p.getPosZ()) {
            ret = true;
        }
        return ret;
    }

    /**
     * Este método nos sirve para comprobar cual de los puntos es mas pequeño.
     *
     * @param p [in] Objeto de tipo geoPoint con el que vamos a comparar.
     *
     * @return Devuelve verdadero si el punto es mas pequeño que el que se pasa
     * por parámetros, falso en caso contrario.
     */
    public boolean isLessThan(geoPoint p) {
        boolean ret = false;
        if (this.posX < p.getPosX() || this.posY < p.getPosY() || this.posZ < p.getPosZ()) {
            ret = true;
        }
        return ret;
    }

    /**
     * Con este método podemos averiguar cual de los puntos esta primero
     * @param p [in] punto con el que vamos a comparar.
     * @return El punto que está primero.
     */
    public geoPoint whichIsFirst(geoPoint p) {
        geoPoint aux = null;
        if (this.posX <= p.getPosX() && this.posY <= p.getPosY()) {
            aux = this;
        } else if (this.posX > p.getPosX() && this.posY < p.getPosY()) {
            aux = this;
        } else if (this.posX > p.getPosX() && this.posY > p.getPosY()) {
            aux = p;
        } else if (this.posX < p.getPosX() && this.posY > p.getPosY()) {
            aux = p;
        }
        return aux;
    }
}
