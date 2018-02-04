/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seproject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.tools.DocumentationTool.Location;

/**
 *
 * @author User1
 */
public class Draw extends JPanel {
    ArrayList<Circle> circle; 
    ArrayList<Line> line;
    Display disModel;
    int st= -1;
    
    public Draw(Display disModel, ArrayList<Circle> circle, ArrayList<Line> line)
    {
        this.circle= circle;
        this.line= line;
        this.disModel= disModel;
        addMouseListener (new DrawListener());
        addMouseMotionListener (new DrawMotionListener());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color classColor= Color.GREEN;
        super.paintComponent(g);
        try 
        {
            classColor= (Color)Class.forName("java.awt.Color").getField(disModel.nodeColor).get(null);
        }
        catch (Exception ex) 
        {
            
        }
        for(Circle cir: circle)
        {
            drawCircle(g, cir.name, cir.xCoor, cir.yCoor, cir.radius, classColor);
        }
        
        if (disModel.showConnections)
        {
            for (Line lin: line)
            {
                drawLine(g, lin.circle1, lin.circle2);
            }
        }
  }

    private void drawCircle (Graphics g, String name, int x, int y, int rad, Color col)
    {
        Color fontColor=Color.BLACK;
        
        int cx= x-rad<0? 0: x-rad;
        int cy= y-rad<0? 0: y-rad;
        g.setColor(col);
        g.fillOval(cx, cy, rad*2, rad*2);
        g.setColor(Color.BLACK);
        g.drawOval(cx, cy, rad*2, rad*2);
        if (disModel.showNotation)
        {
            try 
            {
                fontColor= (Color)Class.forName("java.awt.Color").getField(disModel.fontColor).get(null);
            }
            catch (Exception ex) 
            {

            }
            g.setColor(fontColor);
            g.setFont(new Font("TimesRoman", Font.PLAIN, disModel.fontSize));
            g.drawString(name, x, y);
        }
    }
    
    private void drawLine (Graphics g ,Circle circle1, Circle circle2)
    {
        g.setColor(Color.BLACK);
        g.drawLine(circle1.xCoor, circle1.yCoor, circle2.xCoor, circle2.yCoor);

    }
    
    private class DrawListener implements MouseListener
    {
        @Override
        public void mousePressed(MouseEvent event){
        //JPanel panel= (JPanel) event.getSource();
        //panel.getComponentAt(event.getX(), event.getY());
        //circle.add(new Circle("crazy", 200, 200, 100, Color.RED));
        //repaint();
        }
        public void mouseClicked(MouseEvent event){}
        public void mouseReleased(MouseEvent event){
        st= -1;
        }
        public void mouseEntered(MouseEvent event){}
        public void mouseExited(MouseEvent event){}
        
    }
    private class DrawMotionListener implements MouseMotionListener
    {
        @Override
        public void mouseDragged(MouseEvent event){
            int x= event.getX();
            int y= event.getY();
            if (st==-1)
            {
                for (Circle cir : circle)
                {
                    if (x > cir.xCoor-cir.radius && x < cir.xCoor+cir.radius && y > cir.yCoor-cir.radius && y < cir.yCoor+cir.radius)
                    {
                        cir.xCoor= x;
                        cir.yCoor= y;
                        st= circle.indexOf(cir);
                        break;
                    }
                }
            }
            else
            {
                circle.get(st).xCoor= x;
                circle.get(st).yCoor= y;
            }

            repaint();
        }
        public void mouseMoved(MouseEvent event){}
    }
    
    
}
