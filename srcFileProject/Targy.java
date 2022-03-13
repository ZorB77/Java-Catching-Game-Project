import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Targy {

    private int x;
    private int y;

    private final Random rand;
    private BufferedImage img;
    private final TargyakElhelyezese targy;
    private int [] coordinate;

    public Targy(){

        targy = new TargyakElhelyezese(10 ,"src\\Kepek\\Items\\");
        rand = new Random();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src\\Kepek\\koordinatak.txt"));
            coordinate = reader.lines()
                    .mapToInt(Integer::parseInt).toArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getCoordinate(int i){

        return coordinate[i];
    }
    public int getTargyX(){

        return x;
    }

    public int getTargyY(){

        return y;
    }

    public void setTargyX(int x){

        this.x = x;
    }

    public void setTargyY(int y){

        this.y = y;
    }

    public void setKep(int i){

        img = targy.getItem(i);

    }
    public void reSetKoordinatak(){

        int r = rand.nextInt(3);
        if(r < 2) {
            setTargyY(-250);                                   //targyak ujbol a keret felso szelere kerulnek,es innen "potyoghatnak" le
        }
        else{
            setTargyY(15);
        }
        setTargyX(10 + rand.nextInt(900));

    }

    public void draw(Graphics g) {

        g.drawImage(img ,x,y,null);
    }



}
