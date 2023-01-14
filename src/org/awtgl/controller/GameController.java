package org.awtgl.controller;

import org.lwjgl.input.Controller;

public class GameController {
    
    private Controller controller;

    public String name;

    public int buttonCount;
    public int[] buttons;
    public String[] buttonNames;

    public int axisCount;
    public int[] axises;
    public String[] axisNames;

    public GameController(Controller controller) {

        this.controller = controller;

        this.name = this.controller.getName();

        this.buttonCount = this.controller.getButtonCount();
        this.buttons = new int[this.buttonCount];
        this.buttonNames = new String[this.buttonCount];

        // Loops through each button and adds it to {buttons}
        for (int i = 0; i < this.buttonCount; i++) {
            
            this.buttons[i] = i;
            this.buttonNames[i] = this.controller.getButtonName(i);

        }

        this.axisCount = this.controller.getAxisCount();
        this.axises = new int[this.axisCount];
        this.axisNames = new String[this.axisCount];

        // Loops through each axis and adds it to {axises}
        for (int i = 0; i < this.axisCount; i++) {
            
            this.axises[i] = i;
            this.axisNames[i] = this.controller.getAxisName(i);

        }

    }



    public boolean buttonPressed(int buttonIndex) {

        // Poll controller updates
        this.controller.poll();

        // Check if button is pressed
        if (this.controller.isButtonPressed(buttonIndex)) {

            return true;

        }

        return false;

    }



    public boolean buttonPressed(String buttonName) {

        // Poll controller updates
        this.controller.poll();

        // Loop through button names and check if any match {buttonName}
        for (int i = 0; i < this.controller.getButtonCount(); i++) {

            if (this.controller.getButtonName(i).equals(buttonName)) {

                // Check if button is pressed
                if (this.controller.isButtonPressed(i)) {
        
                    return true;
        
                }
        
                return false;

            }

        }

        return false;

    }



    public boolean axisMoved(int axis, float threshhold) {

        // Poll controller updates
        this.controller.poll();

        // Check if {threshhold} is negative or positive and respond accordingly
        if (threshhold > 0) {

            // Check if the axis is past {threshhold}
            if (this.controller.getAxisValue(axis) >= threshhold) {

                return true;

            }

            return false;

        }

        if (threshhold < 0) {

            // Check if the axis is past {threshhold}
            if (this.controller.getAxisValue(axis) <= threshhold) {

                return true;

            }

            return false;

        }

        return false;

    }

    

    public boolean axisMoved(String axisName, float threshhold) {

        // Poll controller updates
        this.controller.poll();

        // Loop through button names and check if any match {buttonName}
        for (int i = 0; i < this.controller.getAxisCount(); i++) {

            if (this.controller.getAxisName(i).equals(axisName)) {

                // Check if {threshhold} is negative or positive and respond accordingly
                if (threshhold > 0) {

                    // Check if the axis is past {threshhold}
                    if (this.controller.getAxisValue(i) >= threshhold) {

                        return true;

                    }

                    return false;

                }

                if (threshhold < 0) {

                    // Check if the axis is past {threshhold}
                    if (this.controller.getAxisValue(i) <= threshhold) {

                        return true;

                    }

                    return false;

                }

                return false;

            }

        }

        return false;

    }



    public String getName() {
        return name;
    }



    public int getButtonCount() {
        return buttonCount;
    }



    public int[] getButtons() {
        return buttons;
    }



    public String[] getButtonNames() {
        return buttonNames;
    }



    public void setButtonNames(String[] buttonNames) {
        this.buttonNames = buttonNames;
    }



    public int getAxisCount() {
        return axisCount;
    }



    public int[] getAxises() {
        return axises;
    }



    public String[] getAxisNames() {
        return axisNames;
    }



    public void setAxisNames(String[] axisNames) {
        this.axisNames = axisNames;
    }

}
