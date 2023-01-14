package org.awtgl.window;

public class Cursor {

    private GamePanel gamePanel;
    private int[] prevPos;

    protected Cursor(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.prevPos = new int[] {0, 0};

    }



    public int[] getPos() {

        int[] pos = new int[] {0, 0};

        try {

            pos = new int[] {

                Math.round(Math.round(this.gamePanel.mainWindow.getContentPane().getMousePosition().x / (this.gamePanel.mainWindow.getContentPane().getWidth() / this.gamePanel.displayWidth) / this.gamePanel.scale) - ((this.gamePanel.displayX / this.gamePanel.scale))),
                Math.round(Math.round(this.gamePanel.mainWindow.getContentPane().getMousePosition().y / (this.gamePanel.mainWindow.getContentPane().getHeight() / this.gamePanel.displayHeight) / this.gamePanel.scale) - ((this.gamePanel.displayY / this.gamePanel.scale)))

            };

            this.prevPos = pos;

        } catch (NullPointerException e) {

            pos = this.prevPos;

        }

        return pos;

    }

}
