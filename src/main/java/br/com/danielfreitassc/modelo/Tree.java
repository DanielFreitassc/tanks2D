package br.com.danielfreitassc.modelo;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tree {
    private Image image;
    private int x,y;
    private int largura, altura;
    private boolean isVisivel;
  //private static final int LARGURA = 1980;
    private static int VELOCIDADE = 1;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        var referencia = new ImageIcon(getClass().getResource("../res/movel.png"));
        image = referencia.getImage();
        
        this.largura = image.getWidth(null);
        this.altura = image.getHeight(null);
    }
    public void update() {
        if(this.x < -1000) {
            this.x = largura;
            Random a = new Random();
            int m = a.nextInt(500);
            this.x = m + 1024;
            Random r = new Random();
            int n = r.nextInt(500);
            this.y = n;
        } else{
            this.x -= VELOCIDADE;
        }

        
    }    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    public boolean isVisivel() {
        return isVisivel;
    }
    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }
    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }
    public Image getImage() {
        return image;
    }
}

