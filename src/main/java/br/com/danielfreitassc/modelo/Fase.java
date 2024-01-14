package br.com.danielfreitassc.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Enemy1> enemy1;
    private List<Tree> tree;
    private boolean emJogo;
    private int posicaoFundoX = 0;
    private int larguraTela;

    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);
        // Adicione esta linha para obter a largura da tela
        larguraTela = 980;  // Largura da sua tela
        var referencia = new ImageIcon(getClass().getResource("../res/background.png"));
        fundo = referencia.getImage();
        player = new Player();
        player.load();
        timer = new Timer(5,this);
        timer.start();
        inicializaInimigos();
        inicializaTree();
        emJogo = true;

        addKeyListener(new TecladoAdapter());
    }
    public void inicializaInimigos() {
        int cordenadas[] = new int[40];
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < cordenadas.length; i++) {
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x,y));
        }
    }
    public void inicializaTree() {
        int cordenadas[] = new int[10];
        tree = new ArrayList<Tree>();
        for (int i = 0; i < cordenadas.length; i++) {
            int x = (int)(Math.random() * 1050+1024);
            int y = (int)(Math.random() * 768+0);
            tree.add(new Tree(x,y));
        }
    }
    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo == true) {
            graficos.drawImage(fundo, -posicaoFundoX, 0, null);


           
            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
            List<Tiro> tiros = player.getTiros();
            for(int i = 0; i < tiros.size(); i++){
               Tiro m = tiros.get(i);
               m.load();
               graficos.drawImage(m.getImage(), m.getX(),m.getY(),this);
            }
            for (int o = 0; o < enemy1.size(); o++){
                Enemy1 in = enemy1.get(o);
                in.load();
                graficos.drawImage(in.getImage(), in.getX(), in.getY(),this);
            }
            for(int p = 0; p < tree.size(); p++){
                Tree q = tree.get(p);
                q.load();
                graficos.drawImage(q.getImage(), q.getX(),q.getY(),this);
             }
        } else {
            var fimJogo = new ImageIcon(getClass().getResource("../res/gameover.png"));
            graficos.drawImage(fimJogo.getImage(),380,300,null);
        }
       
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for(int p = 0; p <tree.size(); p++){
            Tree on = tree.get(p);
            if(on.isVisivel()){
                on.update();
            }else {
                tree.remove(p);
            }
        } 

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros.remove(i);
            }
        }
        for (int o = 0; o < enemy1.size(); o++){
            Enemy1 in = enemy1.get(o);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy1.remove(o);
            }
        }
         // Atualiza a posição do fundo para criar o efeito de movimento
    posicaoFundoX += 5;  // ou qualquer valor que você achar adequado

    // Reinicia a posição do fundo quando atinge o limite
    if (posicaoFundoX >= larguraTela) {
        posicaoFundoX = 0;
    }



        checarColisoes();
        repaint();
    }
    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;

        for(int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            formaEnemy1 = tempEnemy1.getBounds();
            if (formaNave.intersects(formaEnemy1)) {
                player.setVisivel(false);
                tempEnemy1.setVisivel(false);
                emJogo = false;
            }
        }
        List<Tiro> tiros = player.getTiros();
        for(int j = 0; j < tiros.size(); j++){
            Tiro tempTiro = tiros.get(j);
            formaTiro = tempTiro.getBounds();
            for(int o = 0; o < enemy1.size(); o++){
                Enemy1 tempEnemy1 = enemy1.get(o);
                formaEnemy1 = tempEnemy1.getBounds();
                if(formaTiro.intersects(formaEnemy1)){
                    tempEnemy1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        }
    }
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }
    }
}
