/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.model;
import java.io.*;
import java.util.*;
/**
 *
 * @author XiaoQian Huang
 */
public class MapBuild {
    private int [][]map;
    private int W;
    private int H;
    
    public void setMap(int[][] map)
    {
        this.map = map;
    }

    public int[][] getMap() 
    {
        return map;
    }

    public void setW(int W)
    {
        this.W = W;
    }

    public int getW() 
    {
        return W;
    }

    public void setH(int H) 
    {
        this.H = H;
    }
    
    public int getH()
    {
        return H;
    }

    MapBuild(String filename) throws FileNotFoundException 
    {
        load(filename);
    }
    
    void load(String filename) throws FileNotFoundException 
    {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        setW(sc.nextInt());
        setH(sc.nextInt());
        setMap(new int[getW()][]);
        for(int i=0; i<getW(); ++i)
        {
            getMap()[i] = new int[getH()];
            for(int j=0; j<getH(); ++j) 
            {
                getMap()[i][j] = sc.nextInt();
            }
        }
        sc.close();
    }
}
