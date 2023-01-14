package org.awtgl.window;

public class GameSettings {
    
    protected int tileSize;
    protected int scale;
    protected int tiledWidth;
    protected int tiledHeight;
    protected int width;
    protected int height;

    public GameSettings(int tileSize, int tiledWidth, int tiledHeight) {

        this.tileSize = tileSize;
        this.tiledWidth = tiledWidth;
        this.tiledHeight = tiledHeight;
        this.width = this.tileSize * this.tiledWidth;
        this.height = this.tileSize * this.tiledHeight;

    }

    

    public int getTileSize() {
        return tileSize;
    }



    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }



    public int getScale() {
        return scale;
    }



    public void setScale(int scale) {
        this.scale = scale;
    }



    public int getTiledWidth() {
        return tiledWidth;
    }



    public void setTiledWidth(int tiledWidth) {
        this.tiledWidth = tiledWidth;
    }



    public int getTiledHeight() {
        return tiledHeight;
    }



    public void setTiledHeight(int tiledHeight) {
        this.tiledHeight = tiledHeight;
    }



    public int getWidth() {
        return width;
    }



    public void setWidth(int width) {
        this.width = width;
    }



    public int getHeight() {
        return height;
    }



    public void setHeight(int height) {
        this.height = height;
    }

}
