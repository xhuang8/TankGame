/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import tankgame.model.*;
import tankgame.view.*;
/**
 *
 * @author XiaoQian Huang
 */
public class Tankworld extends JFrame  implements KeyListener, ActionListener{
    public Game g;
    public BackgroundView view;
    Timer t;
    public static Tankworld instance;
    private Image offScreenImage;
    
    Tankworld() throws FileNotFoundException
    {
        super();
        setup();        
    }

    private void setup() throws FileNotFoundException
    {
        setSize(1024+32+20, 1024);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBackground(Color.BLACK);
        addKeyListener(this);
        this.t = new Timer(16, this);
        this.g = new Game();
        this.view = new BackgroundView(g);
        t.setRepeats(true);
        t.setActionCommand("timer");
        t.start();
        this.offScreenImage = this.createImage(1024+32+20,1024);
        Tankworld.instance = this;
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
         g.keyPress(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if("timer".equals(ae.getActionCommand())) 
        {
            g.update();
            repaint();
            //return ;
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        if(offScreenImage == null) return;
        Graphics gOff = offScreenImage.getGraphics();
        gOff.clearRect(0, 0, this.getWidth(), this.getHeight());
        view.update(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }    
}
