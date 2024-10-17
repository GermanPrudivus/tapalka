package common;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomFontLoader {
    public static Font loadFont(String fontFileName, float size) {
        try (InputStream is = CustomFontLoader.class.getResourceAsStream("/" + fontFileName)) {
            if (is == null) {
                throw new IOException("Font file not found: " + fontFileName);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }
}
