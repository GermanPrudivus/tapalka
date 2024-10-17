package features.case_spining.components;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class BlurUtil {
    public static BufferedImage blur(BufferedImage image) {
        float[] matrix = {
            1f / 16, 2f / 16, 1f / 16,
            2f / 16, 4f / 16, 2f / 16,
            1f / 16, 2f / 16, 1f / 16
        };

        BufferedImageOp blurOp = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
        return blurOp.filter(image, null);
    }
}
