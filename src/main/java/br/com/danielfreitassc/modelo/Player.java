package br.com.danielfreitassc.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro> tiros;
    private boolean isVisivel;
    private Image imagemAtirando;
    private boolean atirando;

    public Player(){
        this.x = 100;
        this.y = 100;
        isVisivel = true;

        tiros = new ArrayList<Tiro>();
    }

    public void load(){
        var referencia = new ImageIcon(getClass().getResource("../res/tank.png"));
        imagem = referencia.getImage();

        var referenciaAtirando = new ImageIcon(getClass().getResource("../res/tankatirando.png"));
        imagemAtirando = referenciaAtirando.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void tiroSimples(){
        this.tiros.add(new Tiro(x+largura -10, y + (altura -69)));
        atirando = true;
    }
    public void fimTiro() {
        atirando = false;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,largura,altura);
    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_A) {
            tiroSimples();
        }
        if (codigo == KeyEvent.VK_UP) {
            dy=-3;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy=3;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx=-3;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx=3;
        }
    }

    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy=0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy=0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx=0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx=0;
        }
        if (codigo == KeyEvent.VK_A) {
            fimTiro(); 
        }
    }
    public boolean isVisivel(){
        return isVisivel;
    }
    public void setVisivel(boolean isVisivel){
        this.isVisivel = isVisivel;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Image getImagem() {
        if (atirando) {
           
            return imagemAtirando;
        } else {
            
            return imagem;
        }
    }
    public List<Tiro> getTiros() {
        return tiros;
    }
}