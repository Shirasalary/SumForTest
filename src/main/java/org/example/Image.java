package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Image extends JFrame {

    //איך להוסיף את התמונה לפריים
    public void addToFrame(){
        try {
            URL jetImage = getClass().getClassLoader().getResource("jet.jpg");
            BufferedImage bufferedImage = null;
            if (jetImage != null) {
                bufferedImage = mirror(ImageIO.read(jetImage));
                this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
                JLabel label = new JLabel(new ImageIcon(bufferedImage));
                this.add(label);
            }
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);


            //איך לצייר את התמונה על הדיסק
            ImageIO.write(bufferedImage, "jpg", new File("C:\\Users\\הילה\\Downloads\\stream_api-master - Copy\\Pic\\src\\main\\resources\\hila2.jpg"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    //מצייר את הגבולות של התמונה
    public BufferedImage drawEdges (BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        IntStream.range(1, original.getWidth() - 1).forEach(x -> {
            IntStream.range(1, original.getHeight() - 1).forEach(y -> {
                Color current = new Color(original.getRGB(x, y));
                Color north = new Color(original.getRGB(x, y + 1));
                Color south = new Color(original.getRGB(x, y - 1));
                Color east = new Color(original.getRGB(x - 1, y));
                Color west = new Color(original.getRGB(x + 1, y));
                if (isDifferent(current, north) ||
//                        isDifferent(current, south) ||
//                        isDifferent(current, east) ||
                        isDifferent(current, west)) {
                    output.setRGB(x, y, Color.BLUE.getRGB());
                } else {
                    output.setRGB(x, y, original.getRGB(x, y));

                }
            });
        });
        return output;
    }


    private boolean isDifferent (Color color1, Color color2) {
        boolean different = false;
        int redDiff = Math.abs(color1.getRed() - color2.getRed());
        int greenDiff = Math.abs(color1.getGreen() - color2.getGreen());
        int blueDiff = Math.abs(color1.getBlue() - color2.getBlue());
        if (redDiff + greenDiff + blueDiff > 30) {
            different = true;
        }
        return different;
    }

    public boolean isBlackish (Color color) {
        return color.getRed() + color.getGreen() + color.getBlue() < 50;
    }

    public boolean isRedish (Color color) {
        return color.getRed() > 160 && color.getGreen() < 100 && color.getBlue() < 100;
    }

    //הופך את התמונה כמו מראה
    public BufferedImage mirror(BufferedImage original) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight() - 1; i++) {
            for (int j = 0; j < original.getWidth() - 1; j++) {
                processed.setRGB(i, j, original.getRGB(original.getWidth() - i - 1, j));
            }
        }
        return processed;
    }

    //עושה תמונה בשחור לבן
    public BufferedImage grayScale (BufferedImage original) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight() - 1; i++) {
            for (int j = 0; j < original.getWidth() - 1; j++) {
                Color color = new Color(original.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int average = (red + green + blue) / 3;
                Color newColor = new Color(average, average, average);
                processed.setRGB(i, j, newColor.getRGB());
            }
        }
        return processed;

    }

    //מערבבת את הצבעים
    public BufferedImage shuffle (BufferedImage original) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight() - 1; i++) {
            for (int j = 0; j < original.getWidth() - 1; j++) {
                Color color = new Color(original.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(blue, green, red);
                processed.setRGB(i, j, newColor.getRGB());
            }
        }
        return processed;
    }

    //יוצרת תמונה עם צבעים נגדיים לצבעים המקוריים
    public BufferedImage negative (BufferedImage original) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight() - 1; i++) {
            for (int j = 0; j < original.getWidth() - 1; j++) {
                Color color = new Color(original.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(255 - red, 255 - green, 255  - blue);
                processed.setRGB(i, j, newColor.getRGB());
            }
        }
        return processed;

    }


    public BufferedImage contrast (BufferedImage original, int by) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight() - 1; i++) {
            for (int j = 0; j < original.getWidth() - 1; j++) {
                Color color = new Color(original.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(itensify(red, by), itensify(green, by), itensify(blue, by));
                processed.setRGB(i, j, newColor.getRGB());
            }
        }
        return processed;

    }

    public int itensify (int original, int by) {
        if (original > 128) {
            original *= ((100 + by) / 100);
        } else {
            original *= ((100 - by) / 100);
        }
        return Math.min(255, original);

    }


    private boolean isBigAverage(Color one, Color second) {
        boolean result = false;
        if (Math.abs((one.getBlue() + one.getGreen() + one.getRed()) / 3 - (second.getBlue() + second.getGreen() + second.getRed()) / 3) > 50)
            result = true;

        return result;
    }

    public BufferedImage borders(BufferedImage original) {
        BufferedImage processed = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        return processed;
    }


//    private boolean isDifferent(Color color1, Color color2) {
//        int rgb1 = color1.getRed() + color1.getGreen() + color1.getBlue();
//        int rgb2 = color2.getRed() + color2.getGreen() + color2.getBlue();
//        return false;
//    }

    public static Color getMostCommonPixelColor(BufferedImage image) {
        // Create a map to store the count of each color
        Map<Integer, Integer> colorCountMap = new HashMap<>();

        // Iterate over each pixel in the image and count the occurrences of each color
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                if (pixel == Color.RED.getRGB()) {
                    continue;
                }


                // Increment the count for this color in the map
                int count = colorCountMap.getOrDefault(pixel, 0);
                colorCountMap.put(pixel, count + 1);
            }
        }

        // Find the color with the maximum count
        int maxCount = 0;
        int mostCommonColor = 0;
        for (Map.Entry<Integer, Integer> entry : colorCountMap.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                mostCommonColor = entry.getKey();
            }
        }

        // Extract the RGB values from the most common color
        int red = (mostCommonColor & 0x00ff0000) >> 16;
        int green = (mostCommonColor & 0x0000ff00) >> 8;
        int blue = mostCommonColor & 0x000000ff;

        // Create and return the Color object
        return new Color(red, green, blue);
    }

    public static BufferedImage changeColorToRed(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new BufferedImage with the same dimensions and type as the original image
        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        // Iterate over each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the color of the current pixel
                Color pixelColor = new Color(image.getRGB(x, y));

                // If the color matches the specified color, change it to red
                if (pixelColor.equals(color)) {
                    pixelColor = Color.RED;
                }

                // Set the modified color in the new image
                newImage.setRGB(x, y, pixelColor.getRGB());
            }
        }

        // Return the new image
        return newImage;
    }

}
