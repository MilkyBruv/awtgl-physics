package org.awtgl.controller;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;

public class ControllerManager {

    public static GameController[] getAllControllers() {

        // Returns array of all connected controllers/devices

        // Try aqquire all connected controllers/devices
        try {

            Controllers.create();

        } catch (LWJGLException e) {

            e.printStackTrace();

        }

        // Create an empty list of <GameControllers>
        List<GameController> conConts = new ArrayList<GameController>() {};

        // Poll available controllers
        Controllers.poll();

        // Loop through all polled controllers and add them to {conConts}
        for (int i = 0; i < Controllers.getControllerCount(); i++) {

            conConts.add(new GameController(Controllers.getController(i)));
            
        }

        // Return array of connected controllers
        return conConts.toArray(new GameController[conConts.size()]);

    }



    public static GameController[] getControllers(String name) {

        // Returns array of connected controllers/devices with the name of {name}

        // Try aqquire all connected controllers/devices
        try {

            Controllers.create();

        } catch (LWJGLException e) {

            e.printStackTrace();

        }

        // Create an empty list of <GameControllers>
        List<GameController> conConts = new ArrayList<GameController>() {};

        // Poll available controllers
        Controllers.poll();

        // Loop through all polled controllers and add them to {conConts} if their name matches the {name} parameter
        for (int i = 0; i < Controllers.getControllerCount(); i++) {

            if (Controllers.getController(i).getName().equals(name)) {

                conConts.add(new GameController(Controllers.getController(i)));

            }
            
        }

        // Return array of controllers
        return conConts.toArray(new GameController[conConts.size()]);

    }



    public static GameController[] getController(String name, int count) {

        // Returns {count} of the connected controllers/devices with the name of {name}

        // Checks if {count} is greater than 1
        if (count > 0) {
    
            // Try aqquire all connected controllers/devices
            try {
    
                Controllers.create();
    
            } catch (LWJGLException e) {
    
                e.printStackTrace();
    
            }
    
            // Create an empty list of <GameControllers>
            List<GameController> conConts = new ArrayList<GameController>() {};
    
            // Poll available controllers
            Controllers.poll();

            int contCount = 0;

            // Loop through each polled controller and check if there are still more controllers to add and if they have the name of {name}
            for (int i = 0; i < Controllers.getControllerCount(); i++) {

                if (Controllers.getController(i).getName().equals(name) && contCount < count) {

                    conConts.add(new GameController(Controllers.getController(i)));
                    contCount++;

                }
                
            }

            // Return array of controllers
            return conConts.toArray(new GameController[conConts.size()]);

        }

        return null;

    }

}
