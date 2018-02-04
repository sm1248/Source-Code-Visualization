/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.awt.Color;

/**
 *
 * @author User1
 */
public class Circle {
    String name;
    int xCoor;
    int yCoor;
    int radius;  
    
    public Circle (String named, int x, int y, int rad)
    {
        name= named;
        xCoor= x;
        yCoor= y;
        radius= rad;
    }
}
