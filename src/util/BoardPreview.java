package util;

import game.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import boardfile.BoardFactory;

/**
 * Takes a board file and spits out a representative image.
 * Tool for debugging.
 */
public class BoardPreview extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int FONT_SIZE = 12;

    private JButton buttonChoose;
    private JLabel labelImage;
    
    private BufferedImage renderImage;
    
    private Color[] lookup = new Color[127];
    {
        Random generator = new Random(1337);
        for(int i=0; i < lookup.length; i++)
            lookup[i] = new Color(127+generator.nextInt(128), 127+generator.nextInt(128), 127+generator.nextInt(128));
    }
    
    public BoardPreview(final File boardDir) {
        setTitle("Board Preview");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        renderImage = new BufferedImage(23*FONT_SIZE, 23*FONT_SIZE, BufferedImage.TYPE_INT_RGB);
        
        buttonChoose = new JButton("Choose Board");
        buttonChoose.setName("choose");
        buttonChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Board board = chooseBoard(boardDir);
                render(board);
            }
        });
        
        labelImage = new JLabel(new ImageIcon(renderImage));
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        content.add(labelImage, BorderLayout.NORTH);
        content.add(buttonChoose, BorderLayout.SOUTH);
        add(content);
        
        pack();
        setVisible(true);
    }
    
    private Board chooseBoard(File boardDir) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Board File", "pb");

        final JFileChooser fc = new JFileChooser(boardDir);
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(BoardPreview.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File boardFile = fc.getSelectedFile();
            
            try {
                return BoardFactory.parse(BoardFactory.readFile(boardFile.toString(), StandardCharsets.UTF_8));
            } catch(IOException e) {
                JOptionPane.showMessageDialog(this, "yes");
                return null;
            }
        } else {
            return null;
        }
    }
    
    private void render(Board board) {
        String[] renderLines = board.toString().split("\n");

        Graphics2D g2 = (Graphics2D)renderImage.getGraphics();
        
        g2.setFont(new Font( "SansSerif", Font.BOLD, FONT_SIZE));
        
        g2.clearRect(0, 0, getWidth(), getHeight());
        for(int y=0; y < renderLines.length; y++) {
            String line = renderLines[y];
            for(int x=0; x < line.length(); x++) {
                char c = line.charAt(x);
                g2.setColor(lookup[c]);
                g2.drawString(""+c, FONT_SIZE + x*FONT_SIZE, FONT_SIZE + y*FONT_SIZE);
            }
        }
        
        g2.dispose();
        labelImage.setIcon(new ImageIcon(renderImage));
    }
    
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("usage: java BoardRenderer <board dir>");
            System.exit(1);
        }
        
        File boardDir = new File(args[0]);
        
        if(!boardDir.exists() || !boardDir.isDirectory()) {
            System.out.println("Output directory doesn't exist.");
            System.exit(1);
        }
        
        new BoardPreview(boardDir);
    }
}
