package napakalaki;

/**
 * Clase enumerada que contiene el tipo o tipos de regalos que puede ganar un
 * jugador
 *
 * @author: Carlos de la Torre
 */
public enum TreasureKind {

    ARMOR, ONEHAND, BOTHHANDS, HELMET, SHOES, NECKLACE;

    /**
     * Generá una cadena la cual contiene los datos formateados para imprimirlos
     * por pantalla
     *
     * @return string que contiene los datos del objeto.
     */
    @Override
    public String toString() {
        switch (this) {
            case ARMOR:
                return "Armadura";
            case ONEHAND:
                return "1 Mano";
            case BOTHHANDS:
                return "2 Manos";
            case HELMET:
                return "Casco";
            case SHOES:
                return "Calzado";
            case NECKLACE:
                return "Collar";
            default:
                return "Error";
        }
    }

}
