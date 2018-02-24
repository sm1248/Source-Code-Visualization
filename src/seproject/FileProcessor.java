/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author User1
 */
public class FileProcessor {

    private String fileName;
    private String fileContents;
    //private ArrayList<ArrayList> connectionGraph;
    ArrayList<Circle> circle;
    ArrayList<Line> line;

    public FileProcessor() {
        circle = new ArrayList<Circle>();
        line = new ArrayList<Line>();
    }

    public String loadFile(File file) {
        BufferedReader reader = null;
        Random rand = new Random(1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            String textLine = "";
            boolean first = true;
            reader = new BufferedReader(new FileReader(file));
            while ((textLine = reader.readLine()) != null) {
                if (!first) {
                    Circle cir1 = new Circle(textLine.split("\t")[0], rand.nextInt(750)+25, rand.nextInt(550)+25, 5 + 1 * (Integer.parseInt(textLine.split("\t")[3])));
                    Circle cir2 = new Circle(textLine.split("\t")[2], rand.nextInt(750)+25, rand.nextInt(550)+25, 5 + 1 * (Integer.parseInt(textLine.split("\t")[3])));

                    circle.add(cir1);
                    circle.add(cir2);
                    line.add(new Line(cir1, cir2));
                }
                first = false;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public ArrayList<Circle> getCircleObject() {
        return circle;
    }

    public ArrayList<Line> getLineObject() {
        return line;
    }

    private void parseContents(String st) {

    }

//    public void newFile()
//    {
//        
//    }
}
