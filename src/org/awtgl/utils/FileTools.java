package org.awtgl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileTools {
    
    public static String[] readLines(String path) {

        List<String> lines = new ArrayList<String>() {};

        try {

            File file = new File(path);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                lines.add(line);

            }

            reader.close();

            return lines.toArray(new String[lines.size()]);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        return new String[] {};

    }



    public static void writeLines(String path, String[] lines) {

        try {
            
            FileWriter writer = new FileWriter(path);
            
            for (String line : lines) {
                
                writer.write(line + "\n");

            }

            writer.close();

        } catch (IOException e) {
            
            e.printStackTrace();

        }

    }



    public static void writeString(String path, String text) {

        try {
            
            FileWriter writer = new FileWriter(path);
                
            writer.write(text);

            writer.close();

        } catch (IOException e) {
            
            e.printStackTrace();

        }

    }

}
