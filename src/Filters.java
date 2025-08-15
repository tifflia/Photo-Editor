import java.awt.*;

/**
 * The {@code Filters} class provides different methods of editing a {@code Picture} object.
 * After the images are done being modified, the Picture will be immediately updated in the window on the screen.
 */
public class Filters {
    /**
     * Applies a blur filter to the given Picture with the given intensity.
     * @param picture the picture fwhich the filter will be applied to
     * @param n intensity at which the picture will be blurred from a scale of 1-10
     */
    public static void blur (Picture picture, int n) {
        for (int i = 0; i < n; i++) {
            blur(picture);
        }
        picture.show();
    }

    /**
     * Applies a blur filter to the given Picture.
     * @param picture the picture which the filter will be applied to
     */
    private static void blur(Picture picture) {
        int r = 0, g = 0, b = 0;
        int numSummed = 0;
        int width = picture.width();
        int height = picture.height();
        // iterate through each pixel
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                r = 0;
                g = 0;
                b = 0;
                numSummed = 0;
                // sum rgb values for neighboring pixels only
                for (int i = -1; i < 2; i++)  {
                    for (int j = -1; j < 2; j++) {
                        // only counting if not at the current pixel and not out of bounds
                        if (!(i == 0 && j == 0) && !((col + i < 0 || col + i > width-1) || (row + j < 0 || row + j > height-1))){
                            Color pixel = picture.get(col + i, row + j);
                            r += pixel.getRed();
                            g += pixel.getGreen();
                            b += pixel.getBlue();
                            numSummed++;
                        }
                    }
                }
                // replace the value of the old pixel with new rgb values
                Color blurred = new Color(r/numSummed, g/numSummed, b/numSummed);
                picture.set(col, row, blurred);
            }
        }
    }

    /**
     * Applies a sharpen filter to the given Picture with the given intensity.
     * @param picture the picture which the filter will be applied to
     * @param n intensity at which the picture will be sharpened from a scale of 1-10
     */
    public static void sharpen(Picture picture, int n) {
        for (int i = 0; i < n; i++) {
            sharpen(picture);
        }
        picture.show();
    }

    /**
     * Applies a sharpen filter to the given Picture using unsharp masking.
     * @param picture the picture which the filter will be applied to
     */
    private static void sharpen(Picture picture) {
        int r = 0, g = 0, b = 0;
        int numSummed = 0;
        int width = picture.width();
        int height = picture.height();
        // iterate through each pixel
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                r = 0;
                g = 0;
                b = 0;
                numSummed = 0;
                // get blurred pixel first
                for (int i = -1; i < 2; i++)  {
                    for (int j = -1; j < 2; j++) {
                        // only counting if not at the current pixel and not out of bounds
                        if (!(i == 0 && j == 0) && !((col + i < 0 || col + i > width-1) || (row + j < 0 || row + j > height-1))){
                            Color pixel = picture.get(col + i, row + j);
                            r += pixel.getRed();
                            g += pixel.getGreen();
                            b += pixel.getBlue();
                            numSummed++;
                        }
                    }
                }
                Color original = picture.get(col, row);
                // unsharp masking: original + (original - blurred) = 2 * original - blurred = sharpened
                int sharpR = 2 * (original.getRed()) - (r/numSummed);
                int sharpG = 2 * (original.getGreen()) - (g/numSummed);
                int sharpB = 2 * (original.getBlue()) - (b/numSummed);
                // ensure sharpened rgb values are min 0 and max 255
                sharpR = Math.min(Math.max(0, sharpR), 255);
                sharpG = Math.min(Math.max(0, sharpG), 255);
                sharpB = Math.min(Math.max(0, sharpB), 255);
                // replace the value of the pixel with new rgb values
                Color sharpened = new Color(sharpR, sharpG, sharpB);
                picture.set(col, row, sharpened);
            }
        }
    }

    /**
     * Inverts the rgb values of the pixels in the given picture.
     * @param picture the picture which the filter will be applied to
     */
    public static void invert(Picture picture) {
        // subtract each rgb value from 255 to get the inverted value
        for (int col = 0; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                Color pixel = picture.get(col, row);
                int r = 255 - pixel.getRed();
                int g = 255 - pixel.getGreen();
                int b = 255 - pixel.getBlue();
                Color inverted = new Color(r, g, b);
                // replace the value of the pixel with new rgb values
                picture.set(col, row, inverted);
            }
        }
        picture.show();
    }

    /**
     * Applies a black and white filter to the given Picture.
     * @param picture the picture which the filter will be applied to
     */
    public static void bw(Picture picture) {
        // rgb becomes grayscale when all values are equal to each other, so find avg of rgb values to get grayscale value
        for (int col = 0; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                Color pixel = picture.get(col, row);
                int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
                Color bw = new Color(avg, avg, avg);
                // replace the value of the pixel with new rgb values
                picture.set(col, row, bw);
            }
        }
        picture.show();
    }
}
