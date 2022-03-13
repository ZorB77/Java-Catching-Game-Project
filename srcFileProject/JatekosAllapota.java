import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JatekosAllapota {

    private final BufferedImage[] jobbra;
    private final BufferedImage[] balra;
    private BufferedImage hatter;

    public JatekosAllapota(int size ,String path){

        jobbra = new BufferedImage[size];
        balra = new BufferedImage[size];


        for(int i = 0;i < size;i++){

            try{

                String kep = "Run__00" + i + ".png";
                String kep2 = "RunLeft__00" + i + ".png";

                jobbra[i] = ImageIO.read(new File(path  + kep));
                balra[i] = ImageIO.read(new File(path  + kep2));

            }catch(IOException e){
                System.out.println("Hiba a kep " + i + ". beolvasasnal!");
            }
        }

        try{
            hatter = ImageIO.read(new File(path + "Hatter.jpg"));
        }catch(IOException e){
            System.out.println("Hiba a kep beolvasasnal!");
        }

    }

    public BufferedImage getJatekosPozicio(int irany ,int index){

        if(irany == 0){
            return balra[index];
        }
       return jobbra[index];
    }

    public BufferedImage getHatter(){

        return hatter;
    }
}
