import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jatekos {

    BufferedImage img;
    BufferedImage backGround;

    private boolean bal;
    private boolean jobb;
    private boolean helybenAll;
    private boolean valtottE;

    private int merreMegy;
    private int kep;
    private int x;
    private int y;
    private int frissites;
    private int score;
    private final JatekosAllapota idle;
    private final JatekosAllapota run;

    private int life;
    public Jatekos(){

        idle = new JatekosAllapota(10 ,"src\\Kepek\\Idle\\");
        run = new JatekosAllapota(10 ,"src\\Kepek\\Run\\");
        backGround = run.getHatter();

        score = 0;
        kep = 0;
        merreMegy = 1;
        frissites = 0;          //counter,mely segit a kepeknek frissiteseben

        valtottE = false;
        helybenAll = true;
        bal = false;
        jobb = false;

        life = 3;
        x = 270;               //karakter pozicionalasanak koordinatai
        y = 430;

    }

    public void voltValtas(){

        if (valtottE){
            valtottE = false;
            frissites = 0;
            kep = 0;
        }
    }
    public void updatePos(){

        voltValtas();

        if(helybenAll){                                 //idle pozicio

            if(frissites > 2) {
                if (kep < 9) {
                    kep++;
                } else {
                    kep = 0;
                }
                frissites = 0;
            }

            img = idle.getJatekosPozicio(merreMegy ,kep);

        }else {

            if(frissites > 2){
                if(bal) {                              //balra megy
                    if (kep < 9) {
                        kep++;
                    } else {
                        kep = 0;
                    }
                }
            else {                                      //jobbra megy
                if (jobb) {
                    if (kep < 9) {
                        kep++;
                    } else {
                        kep = 0;
                    }
                }
            }
                frissites = 0;
            }
            img = run.getJatekosPozicio(merreMegy ,kep);
        }
        frissites++;
    }

    public void setPos(int merreMegy){

        this.merreMegy = merreMegy;
    }

    public void draw(Graphics g) {

        g.drawImage(img ,x,y,null);
    }

    public void setValtottE(boolean valtottE){

        this.valtottE = valtottE;
    }

    public void setIdle(boolean helybenAll){

        this.helybenAll = helybenAll;
    }

    public void setBal(boolean bal){

        this.bal = bal;
    }

    public void setJobb(boolean jobb){

        this.jobb = jobb;
    }

    public boolean getJobb(){

        return jobb;
    }

    public boolean getBal(){

        return bal;
    }

    public int getX() {

        return x;
    }



    public void setX(int x){

        this.x = x;
    }

    public void setY(int y){

        this.y = y;
    }

    public int getScore(){

        return score;
    }

    public void setScore(int score){

        this.score = score;
    }

    public int getLife(){

        return life;
    }

    public void setLife(int life){

        this.life = life;
    }

    public void gameOver(){

        String path = "C:\\Users\\rober\\Desktop\\Egyetem II\\Java\\srim2084_Projekt\\Kepek\\";
        try {
            img = ImageIO.read(new File(path + "GameOver.jpg"));
        }catch(IOException e){
            System.out.println("Hiba kep beolvasakor!");
        }
    }
}
