package util;

import game.Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;

import boardfile.BoardFactory;

/**
 * Takes a board file and spits out a representative image.
 * Tool for debugging.
 */
public class BoardRenderer {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: java BoardRenderer <boardfile> <output dir>");
            System.exit(1);
        }
        
        File boardFile = new File(args[0]);
        File outDir = new File(args[1]);
        
        if(!boardFile.exists()) {
            System.out.println("Board file " + boardFile + " doesn't exist.");
            System.exit(1);
        }
        
        if(!outDir.exists() || !outDir.isDirectory()) {
            System.out.println("Output directory doesn't exist.");
            System.exit(1);
        }
        
        List<String> lines = null;
        try {
            lines = Files.readAllLines(boardFile.toPath(), StandardCharsets.UTF_8);
        } catch(IOException e) {
            System.out.println("Couldn't read file.");
            e.printStackTrace();
            System.exit(1);
        }
        
        String content = "";
        for(String line: lines) content += line;
        
        Board board = BoardFactory.parse(content);
        
        String[] renderLines = board.toString().split("\n");
        
        Color[] lookup = new Color[127];
        Random generator = new Random(1337);
        for(int i=0; i < lookup.length; i++)
            lookup[i] = new Color(127+generator.nextInt(128), 127+generator.nextInt(128), 127+generator.nextInt(128));
        
        int fontSize = 12;

        BufferedImage render = new BufferedImage(23*fontSize, 23*fontSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)render.getGraphics();
        
        g2.setFont(new Font( "SansSerif", Font.BOLD, fontSize ));
        for(int y=0; y < renderLines.length; y++) {
            String line = renderLines[y];
            for(int x=0; x < line.length(); x++) {
                char c = line.charAt(x);
                g2.setColor(lookup[c]);
                g2.drawString(""+c, fontSize + x*fontSize, fontSize + y*fontSize);
            }
        }
        
        g2.dispose();
        
        try {
            File outputfile = new File(outDir + "/" + FilenameUtils.getBaseName(args[0]) + ".png");
            ImageIO.write(render, "png", outputfile);
        } catch(IOException e) {
            System.out.println("Couldn't write rendered image.");
        }
    }
}
