import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TargyakElhelyezese {

    private final BufferedImage[] itemek;

    public TargyakElhelyezese(int size ,String path){

        itemek = new BufferedImage[size];

        for(int i = 0;i < size;i++){

            try{

                itemek[i] = ImageIO.read(new File(path + "Item" + i + ".png" )) ;
            }catch(IOException e){

                System.out.println("Hiba a kep beolvasasakor!");
            }
        }
    }

    public BufferedImage getItem(int index) {

        return itemek[index];
    }

}
