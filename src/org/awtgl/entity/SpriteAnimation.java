package org.awtgl.entity;

import java.util.Map;

import org.awtgl.window.Image;

public class SpriteAnimation {
    
    // images_name : images
    private Map<String, Image[]> imagesMap;

    public SpriteAnimation(Map<String, Image[]> imagesMap) {
    
        this.imagesMap = imagesMap;

    }



    public Map<String, Image[]> getImages() {
    
        return this.imagesMap;
    
    }


    
    public void setImages(Map<String, Image[]> imagesMap) {
    
        this.imagesMap = imagesMap;
    
    }



    public Image[] getFrames(String name) {

        return this.imagesMap.get(name);

    }



    public void setFrames(String name, Image[] images) {

        this.imagesMap.put(name, images);

    }



    public Image getFrame(String name, int frame) {

        return this.imagesMap.get(name)[frame];

    }

}
