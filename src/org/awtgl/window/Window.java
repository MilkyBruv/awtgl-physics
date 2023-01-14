package org.awtgl.window;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {

    protected GamePanel gamePanel;
    protected GameSettings gameSettings;
    protected Updater gameUpdater;
    protected int mode;

    public static final int FIT_INNER_DISPLAY = 0;
    public static final int SCALE_INNER_DISPLAY = 1;
    
    public Window(int width, int height, String title, boolean resizeable, int fps, GameSettings gameSettings, Updater gameUpdater, int mode) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(resizeable);
        this.setTitle(title);
        this.getContentPane().setBackground(new Color(0x000000));
        this.gameSettings = gameSettings;
        this.gameUpdater = gameUpdater;
        this.gamePanel = new GamePanel(width, height, fps, gameUpdater, this);
        this.gameUpdater.cursor = new Cursor(this.gamePanel);
        this.mode = mode;
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



    public void setIcon() {

        

    }



    public void setCursor(Image image) {

        java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
            
            image.getBufferedImage(), 
            new Point(0, 0), 
            "custom cursor"
        
        );

        this.getContentPane().setCursor(cursor);

    }



    public void start() {

        this.gamePanel.startgameThread();

    }

}
