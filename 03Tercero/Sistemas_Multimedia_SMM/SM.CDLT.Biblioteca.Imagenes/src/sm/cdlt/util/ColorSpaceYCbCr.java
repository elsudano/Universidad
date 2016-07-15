package sm.cdlt.util;

import java.awt.color.ColorSpace;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 19-may-2016
 */
public class ColorSpaceYCbCr extends ColorSpace {

    public ColorSpaceYCbCr() {
        super(ColorSpace.TYPE_YCbCr, 3);
    }

    /*
    https://en.wikipedia.org/wiki/YCbCr
    ITU-R BT.709 conversion
    R = (1.16438 * (Y - 16)) + (1.59602 * (Cr - 128))
    G = (1.16438 * (Y - 16)) - (0.39176 * (Cb - 128)) - (0.81297 * (Cr - 128))
    B = (1.16438 * (Y - 16)) + (2.01723 * (Cb - 128))
    JPEG conversion
    R = Y + (1.402 * (Cr - 128))
    G = Y - (0.34414 * (Cb - 128)) - (0.71414 * (Cr - 128))
    B = Y + (1.772 * (Cb - 128))
     */
    @Override
    public float[] toRGB(float[] colorvalue) {
        final int K = 1;
        float[] rgb = new float[3];
        float Y = K * colorvalue[0] - 16;
        float Cb = K * colorvalue[1] - 128;
        float Cr = K * colorvalue[2] - 128;
        /*
        ITU-R BT.709 conversion
        rgb[0] = (float) (1.16438 * Y + 1.59602 * Cr);
        rgb[1] = (float) (1.16438 * Y - 0.39176 * Cb - 0.81297 * Cr);
        rgb[2] = (float) (1.16438 * Y + 2.01723 * Cb);
         */
 /*
        JPEG conversion*/
        rgb[0] = (float) (Y + (1.402 * Cr));
        rgb[1] = (float) (Y - (0.34414 * Cb) - (0.71414 * Cr));
        rgb[2] = (float) (Y + (1.772 * Cb));
        
        for (int i = 0; i < 3; i++) {
            if (rgb[i] > 1) {
                rgb[i] = 1;
            } else if (rgb[i] < 0) {
                rgb[i] = 0;
            }
        }
        return rgb;
    }

    /*
    ITU-R BT.709 conversion
    Y = 16 + (0.257 * R) + (0.504 * G) + (0.098 * B)
    Cb = 128 - (-0.148 * R) + (-0.291 * G) + 0.439 * B
    Cr = 128 + (0.439 * R) + (-0.368 * G) + (-0.071 * B)
    JPEG conversion
    Y = 0 + (0.299 * R) + (0.587 * G) + (0.114 * B)
    Cb = 128 - (-0.168736 * R) + (0.331264 * G) + 0.5 * B
    Cr = 128 + (0.439 * R) + (-0.368 * G) + (-0.071 * B)
     */
    @Override
    public float[] fromRGB(float[] colorvalue) {
        float[] yCbCr = new float[3];

        /*
        ITU-R BT.709 conversion
        yCbCr[0] = 16 + (float) (0.257 * colorvalue[0] + 0.504 * colorvalue[1] + 0.098 * colorvalue[2]);
        yCbCr[1] = 128 - (float) (-0.148 * colorvalue[0] + -0.291 * colorvalue[1] + 0.439 * colorvalue[2]);
        yCbCr[2] = 128 + (float) (0.439 * colorvalue[0] + -0.368 * colorvalue[1] + -0.071 * colorvalue[2]);
         */
        /*
        JPEG conversion*/
        yCbCr[0] = 0 + (float) (0.299 * colorvalue[0] + 0.587 * colorvalue[1] + 0.114 * colorvalue[2]);
        yCbCr[1] = 128 - (float) (-0.168736 * colorvalue[0] + 0.331264 * colorvalue[1] + 0.5 * colorvalue[2]);
        yCbCr[2] = 128 + (float) (0.439 * colorvalue[0] + (-0.368 * colorvalue[1]) + (-0.071 * colorvalue[2]));
        
        for (int i = 0; i < 3; i++) {
            if (yCbCr[i] > 255) {
                yCbCr[i] = 255;
            } else if (yCbCr[i] < 0) {
                yCbCr[i] = 0;
            }
        }
        return yCbCr;
    }

    public int[] fromRGB(int[] rgb) {
        int[] yCbCr = new int[3];
        fromRGB(rgb, yCbCr);
        return yCbCr;
    }
    
    public void fromRGB(int[] rgb, int[] yCbCr) {
        fromRGB(rgb[0], rgb[1], rgb[2], yCbCr);
    }
    
    public void fromRGB(int r, int g, int b, int[] yCbCr) {
        yCbCr[0] = 16 + ((int) (65.738 * r + 129.057 * g + 25.064 * b)) >> 8;
        yCbCr[1] = 128 + ((int) (-37.945 * r + -74.494 * g + 112.439 * b)) >> 8;
        yCbCr[2] = 128 + ((int) (112.439 * r + -97.154 * g + -18.285 * b)) >> 8;

        for (int i = 0; i < 3; i++) {
            if (yCbCr[i] > 255) {
                yCbCr[i] = 255;
            } else if (yCbCr[i] < 0) {
                yCbCr[i] = 0;
            }
        }
    }
    
    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        throw new UnsupportedOperationException("Not implemented yet. Method toCIEXYZ to ColorSpaceYCbCr");
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        throw new UnsupportedOperationException("Not implemented yet. Method fromCIEXYZ to ColorSpaceYCbCr");
    }
}
