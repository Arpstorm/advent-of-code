package com.arpstorm.aoc.year2025;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.awt.*;

public class AsciiGifExporter {

    // ------ CONFIGURATION ------

    private static final String FONT_NAME = "Consolas";
    private static final int FONT_SIZE = 24;

    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 26;

    private static final Color BG_COLOR = Color.BLACK;
    private static final Color MAIN_TEXT_COLOR = new Color(0, 255, 0);
    private static final Color GLOW_COLOR = new Color(0, 255, 128, 80);

    private static final int FRAME_DELAY_CS = 40; // 10 = 100ms per frame

    // ------ DEMO MAIN ------

    public static void main(String[] args) throws Exception {
        // Demo 1: simple green text (no value-based color)
        List<String[][]> frames = generateDemoFrames(10, 10, 20);
        exportAsciiGridGif(frames, new File("ascii-grid-basic.gif"));

        // Demo 2: value-based coloring from numeric strings
        List<String[][]> valueFrames = generateValueFramesDemo(10, 10, 20);
        BigDecimal maxValue = new BigDecimal("1000"); // example max
        exportAsciiGridGifWithValueStrings(valueFrames, maxValue, new File("ascii-grid-valued.gif"));
    }

    // ============================================================
    // 1) BASIC EXPORT: color is constant neon green
    // ============================================================

    public static void exportAsciiGridGif(List<String[][]> frames, File outputFile) throws IOException {
        if (frames == null || frames.isEmpty())
            throw new IllegalArgumentException("No frames to export");

        int rows = frames.get(0).length;
        int cols = frames.get(0)[0].length;

        int width = cols * CELL_WIDTH;
        int height = rows * CELL_HEIGHT;

        BufferedImage firstImage = renderFrame(frames.get(0), width, height);

        ImageOutputStream output = ImageIO.createImageOutputStream(outputFile);
        GifSequenceWriter gifWriter = new GifSequenceWriter(
                output, firstImage.getType(), FRAME_DELAY_CS, true
        );

        gifWriter.writeToSequence(firstImage);

        for (int i = 1; i < frames.size(); i++) {
            BufferedImage frame = renderFrame(frames.get(i), width, height);
            gifWriter.writeToSequence(frame);
        }

        gifWriter.close();
        output.close();
    }

    private static BufferedImage renderFrame(String[][] grid, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);

        g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        FontMetrics fm = g.getFontMetrics();

        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String s = grid[r][c];
                if (s == null || s.isEmpty()) s = " ";

                int x = c * CELL_WIDTH;
                int y = r * CELL_HEIGHT;

                int drawX = x + 2;
                int drawY = y + fm.getAscent();

                // Glow
                g.setColor(GLOW_COLOR);
                g.drawString(s, drawX - 1, drawY);
                g.drawString(s, drawX + 1, drawY);
                g.drawString(s, drawX, drawY - 1);
                g.drawString(s, drawX, drawY + 1);

                // Main text
                g.setColor(MAIN_TEXT_COLOR);
                g.drawString(s, drawX, drawY);
            }
        }

        g.dispose();
        return img;
    }

    // ============================================================
    // 2) VALUE-BASED EXPORT: color depends on numeric string value
    // ============================================================

    /**
     * Export GIF where each grid cell is a numeric String (possibly very large),
     * used both as drawn text and as value for coloring.
     *
     * Color scaling:
     *   value = 0      -> pure green  (0,255,0)
     *   value = maxVal -> yellow-ish (255,255,0) = green + red
     */
    public static void exportAsciiGridGifWithValueStrings(
            List<String[][]> frames,
            BigDecimal maxValue,
            File outputFile
    ) throws IOException {

        if (frames == null || frames.isEmpty())
            throw new IllegalArgumentException("No frames to export");

        if (maxValue == null)
            throw new IllegalArgumentException("maxValue must not be null");

        int rows = frames.get(0).length;
        int cols = frames.get(0)[0].length;

        int width = cols * CELL_WIDTH;
        int height = rows * CELL_HEIGHT;

        BufferedImage firstImage = renderFrameWithValueStrings(frames.get(0), width, height, maxValue);

        ImageOutputStream output = ImageIO.createImageOutputStream(outputFile);
        GifSequenceWriter gifWriter = new GifSequenceWriter(
                output, firstImage.getType(), FRAME_DELAY_CS, true
        );

        gifWriter.writeToSequence(firstImage);

        for (int i = 1; i < frames.size(); i++) {
            BufferedImage frame = renderFrameWithValueStrings(frames.get(i), width, height, maxValue);
            gifWriter.writeToSequence(frame);
        }

        gifWriter.close();
        output.close();
    }

    private static BufferedImage renderFrameWithValueStrings(
            String[][] grid,
            int width,
            int height,
            BigDecimal maxValue
    ) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);

        g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        FontMetrics fm = g.getFontMetrics();

        int rows = grid.length;
        int cols = grid[0].length;

        boolean maxNonPositive = maxValue.compareTo(BigDecimal.ZERO) <= 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                String raw = grid[r][c];
                if (raw == null || raw.isEmpty()) raw = ".";

                String s = raw.trim();

                String charToDraw = s;  // what character gets printed

                Color mainColor;
                Color glowColor;

                // ==============================================================
                // SPECIAL CASE "^"  → RED SYMBOL
                // ==============================================================
                if (s.equals("^")) {
                    charToDraw = "^";
                    mainColor = new Color(255, 50, 50);
                    glowColor = new Color(255, 40, 40, 100);
                }

                else {
                    // Try numeric conversion
                    boolean isNumeric = true;
                    BigDecimal val = BigDecimal.ZERO;

                    try {
                        val = new BigDecimal(s);
                    } catch (NumberFormatException ex) {
                        isNumeric = false;
                    }

                    // ==============================================================
                    // NUMERIC CASE → DRAW "|" IN VALUE-BASED COLOR
                    // ==============================================================
                    if (isNumeric && !maxNonPositive) {
                        charToDraw = "|";

                        double norm;

                        if (val.compareTo(BigDecimal.ZERO) <= 0) {
                            norm = 0.0;
                        } else if (val.compareTo(maxValue) >= 0) {
                            norm = 1.0;
                        } else {
                            BigDecimal ratio = val.divide(maxValue, MathContext.DECIMAL64);
                            norm = ratio.doubleValue();
                            if (norm < 0.0) norm = 0.0;
                            if (norm > 1.0) norm = 1.0;
                        }

                        mainColor = colorForNorm(norm);  // green → yellow
                        glowColor = new Color(
                                mainColor.getRed(),
                                mainColor.getGreen(),
                                mainColor.getBlue(),
                                80 // glow alpha
                        );
                    }
                    // ==============================================================
                    // NON-NUMERIC SYMBOL → NORMAL GREEN
                    // ==============================================================
                    else {
                        charToDraw = s;
                        mainColor = MAIN_TEXT_COLOR;
                        glowColor = GLOW_COLOR;
                    }
                }

                // ----- Draw characters -----

                int x = c * CELL_WIDTH;
                int y = r * CELL_HEIGHT;

                int drawX = x + 2;
                int drawY = y + fm.getAscent();

                // Glow
                g.setColor(glowColor);
                g.drawString(charToDraw, drawX - 1, drawY);
                g.drawString(charToDraw, drawX + 1, drawY);
                g.drawString(charToDraw, drawX, drawY - 1);
                g.drawString(charToDraw, drawX, drawY + 1);

                // Main text
                g.setColor(mainColor);
                g.drawString(charToDraw, drawX, drawY);
            }
        }

        g.dispose();
        return img;
    }

    /**
     * norm in [0,1]:
     *   0 -> green      (0,255,0)
     *   1 -> red+green  (255,255,0)
     * This is literally "additional red mixed into green".
     */
    private static Color colorForNorm(double norm) {
        if (norm < 0.0) norm = 0.0;
        if (norm > 1.0) norm = 1.0;

        int red = (int) Math.round(norm * 255);
        int green = 255;
        int blue  = (int) Math.round(norm * 255);

        return new Color(red, green, blue);
    }

    // ------ DEMO FRAME GENERATORS (optional) ------

    private static List<String[][]> generateDemoFrames(int frameCount, int rows, int cols) {
        List<String[][]> frames = new ArrayList<>();

        for (int f = 0; f < frameCount; f++) {
            String[][] grid = new String[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    char base = (char) ('A' + (r + c + f) % 26);
                    grid[r][c] = String.valueOf(base);
                }
            }
            frames.add(grid);
        }
        return frames;
    }

    // Demo: numeric strings that increase with frame index
    private static List<String[][]> generateValueFramesDemo(int frameCount, int rows, int cols) {
        List<String[][]> frames = new ArrayList<>();

        for (int f = 0; f < frameCount; f++) {
            String[][] grid = new String[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    long base = (r * cols + c) * 10L + f * 20L;
                    grid[r][c] = Long.toString(base); // could be any big decimal string
                }
            }
            frames.add(grid);
        }
        return frames;
    }

    // ------ GIF WRITER CLASS ------

    public static class GifSequenceWriter {
        protected ImageWriter gifWriter;
        protected ImageWriteParam imageWriteParam;
        protected IIOMetadata imageMetaData;

        public GifSequenceWriter(ImageOutputStream out, int imageType, int delayCS, boolean loop) throws IOException {
            Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("gif");
            if (!writers.hasNext()) throw new IllegalStateException("No GIF writers available");
            gifWriter = writers.next();

            imageWriteParam = gifWriter.getDefaultWriteParam();
            ImageTypeSpecifier type = ImageTypeSpecifier.createFromBufferedImageType(imageType);

            imageMetaData = gifWriter.getDefaultImageMetadata(type, imageWriteParam);
            String metaFormat = imageMetaData.getNativeMetadataFormatName();

            IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormat);

            IIOMetadataNode gfx = getNode(root, "GraphicControlExtension");
            gfx.setAttribute("disposalMethod", "none");
            gfx.setAttribute("userInputFlag", "FALSE");
            gfx.setAttribute("transparentColorFlag", "FALSE");
            gfx.setAttribute("delayTime", Integer.toString(delayCS));
            gfx.setAttribute("transparentColorIndex", "0");

            IIOMetadataNode appExtensions = getNode(root, "ApplicationExtensions");
            IIOMetadataNode app = new IIOMetadataNode("ApplicationExtension");
            app.setAttribute("applicationID", "NETSCAPE");
            app.setAttribute("authenticationCode", "2.0");

            int loopVal = loop ? 0 : 1;
            byte[] loopBytes = new byte[]{1, (byte) (loopVal & 0xFF), (byte) ((loopVal >> 8) & 0xFF)};
            app.setUserObject(loopBytes);
            appExtensions.appendChild(app);

            imageMetaData.setFromTree(metaFormat, root);

            gifWriter.setOutput(out);
            gifWriter.prepareWriteSequence(null);
        }

        public void writeToSequence(BufferedImage img) throws IOException {
            gifWriter.writeToSequence(new IIOImage(img, null, imageMetaData), imageWriteParam);
        }

        public void close() throws IOException {
            gifWriter.endWriteSequence();
        }

        private static IIOMetadataNode getNode(IIOMetadataNode root, String name) {
            for (int i = 0; i < root.getLength(); i++) {
                if (root.item(i).getNodeName().equalsIgnoreCase(name)) {
                    return (IIOMetadataNode) root.item(i);
                }
            }
            IIOMetadataNode node = new IIOMetadataNode(name);
            root.appendChild(node);
            return node;
        }
    }
}
