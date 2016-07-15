package sm.cdlt.graphics;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 30-may-2016
 */
public abstract class myCurveShape extends myExtraInfo {

    /**
     * Constructor por defecto con 2 puntos de anclaje.
     *
     * @param pb [in] Punto de comienzo.
     * @param pe [in] Punto de final.
     */
    public myCurveShape(geoPoint pb, geoPoint pe) {
        super(pb);
        this.list_of_points.add(pe);
    }

    /**
     * Constructor ampliado con valores de diferentes propiedades de la forma.
     *
     * @param pb [in] Punto de comienzo.
     * @param pe [in] Punto de final.
     * @param c [in] Color de la linea.
     * @param s [in] Ancho de la linea.
     */
    public myCurveShape(geoPoint pb, geoPoint pe, Color c, float s) {
        super(pb);
        this.list_of_points.add(pe);
        this.border_color = c;
        this.line_width = s;
    }

    /**
     * Método para poder acceder a los objetos java de cada una de las formas.
     *
     * @return Objeto QuadCurve.Double.
     */
    public QuadCurve2D.Double getQuadCurve() {
        if (this.myForma instanceof QuadCurve2D.Double) {
            return (QuadCurve2D.Double) this.myForma;
        }
        return null;
    }

    /**
     * Método para poder acceder a los objetos java de cada una de las formas.
     *
     * @return Objeto CubicCurve2D.Double.
     */
    public CubicCurve2D.Double getCubicCurve() {
        if (this.myForma instanceof CubicCurve2D.Double) {
            return (CubicCurve2D.Double) this.myForma;
        }
        return null;
    }
}
