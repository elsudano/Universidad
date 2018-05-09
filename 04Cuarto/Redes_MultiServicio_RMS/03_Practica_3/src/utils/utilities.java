package utils;

/**
 *
 * @author usuario
 */
public class utilities {

    // Funcion que convierte un array de bytes a un entero
    public static int byteArrayToInt(byte[] b) {
        switch (b.length) {
            case 4:
                return b[0] << 24 | (b[1] & 0xff) << 16 | (b[2] & 0xff) << 8 | (b[3] & 0xff);
            case 2:
                return 0x00 << 24 | 0x00 << 16 | (b[0] & 0xff) << 8 | (b[1] & 0xff);
            case 1:
                return b[0];
            default:
                return 0;
        }
    }

    // Funcion que convierte un entero a bytes
    public static byte[] toBytes(int i) {
        byte[] resultado = new byte[4];
        resultado[0] = (byte) (i >> 24);
        resultado[1] = (byte) (i >> 16);
        resultado[2] = (byte) (i >> 8);
        resultado[3] = (byte) (i /*>> 0*/);
        return resultado;
    }
}
