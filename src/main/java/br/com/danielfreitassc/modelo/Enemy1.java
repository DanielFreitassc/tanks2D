package br.com.danielfreitassc.modelo;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
    private Image image;
    private int x,y;
    private int largura, altura;
    private boolean isVisivel;
  //private static final int LARGURA = 1980;
    private static int VELOCIDADE = 6;

    public Enemy1(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        var referencia = new ImageIcon(getClass().getResource("../res/tankinimigo.png"));
        image = referencia.getImage();
        
        this.largura = image.getWidth(null);
        this.altura = image.getHeight(null);
    }
    public void update() {
        this.x -= VELOCIDADE;
        System.out.println("Posição do inimigo: x=" + this.x + ", y=" + this.y);
    
        if (this.x + largura < 0) {
            
            this.x = 1024; 
        }
        
    }
    
    

    public Rectangle getBounds() {
        return new Rectangle(x,y,largura,altura);
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

