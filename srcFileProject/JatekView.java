import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class JatekView extends JPanel implements KeyListener, ActionListener {

    private final JatekModell modell;
    private final Targy[] targyak;
    private final Random rand;

    final JMenu Menu;
    private final JMenuItem Save;
    private final JMenuBar MenuBar;
    private JFileChooser FChooser;
    private FileWriter FWriter;

    private final JLabel scoreLabel;
    int x;

    public JatekView(JatekModell modell){

        this.modell = modell;
        this.setLayout(new BorderLayout());


        scoreLabel = new JLabel("Score : 0 ");
        scoreLabel.setHorizontalAlignment(JLabel.LEFT);
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setFont(new Font("Times New Roman" ,Font.PLAIN, 40));


        MenuBar = new JMenuBar();
        Menu = new JMenu("Settings");
        Save = new JMenuItem("Save");


        Menu.add(Save);

        MenuBar.add(Menu);
        Menu.setHorizontalAlignment(JMenu.RIGHT);
        this.add(MenuBar,BorderLayout.NORTH);

        Save.addActionListener(this);

        this.add(scoreLabel , BorderLayout.NORTH);

        targyak = new Targy[11];
        x = 10;
        rand = new Random();
        for(int i = 0;i < 10;i++){

            targyak[i] = new Targy();
            targyak[i].setTargyX(x);
            targyak[i].setKep(i);
            targyak[i].setTargyY(targyak[i].getCoordinate(i));  //targyakat probalom minel "hullamosabb" szinten mozogtatni
            x += 100;
        }

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(modell.getPlayer().backGround, 0, 0, null);               //hatteret ujra rajzolom
        int speed;


        for(int i = 0;i < 10;i++) {

            targyak[i].draw(g);
            speed = rand.nextInt(7) + rand.nextInt(8);                        //targyaknak random gyorsasagu esest adok

            if (targyak[i].getTargyY() < 600) {
                targyak[i].setTargyY(targyak[i].getTargyY() + speed);
            }
            else {
                targyak[i].reSetKoordinatak();                                          //ha elertem a keret meretet,ujra a felso reszre helyezem a targyakat
            }

            if(modell.getPlayer().getLife() == 0){
                this.remove(scoreLabel);
                this.add(MenuBar,BorderLayout.NORTH);
            }

            if(kiFogTargyak(i)){

                modell.getSound().playCaughtSound();
                targyak[i].reSetKoordinatak();                                              //ha elkapom a targyyat,akkor ujra a kepermyo felso reszere kerul es kezd ujra potyogni lefele

                if(i < 6){

                    modell.getPlayer().setScore(modell.getPlayer().getScore() + 10);
                }else{
                    if(modell.getPlayer().getScore() < 20){
                        modell.getPlayer().setScore(modell.getPlayer().getScore() - 5);
                    }
                    else {
                        modell.getPlayer().setScore(modell.getPlayer().getScore() - 7);
                    }

                    modell.getPlayer().setLife(modell.getPlayer().getLife() - 1);           //ha rossz targyat fog ki,veszit  1 eletet
                }

                scoreLabel.setText("Score :" + modell.getPlayer().getScore());
            }

        }
        if(modell.getPlayer().getLife() == 0){
            this.remove(scoreLabel);
            this.add(MenuBar,BorderLayout.NORTH);
        }
        modell.getPlayer().draw(g);                                                         //szinten a karakteremet is ujra rajzolom
    }


    public boolean ellenorizX(int index){

        return ((modell.getPlayer().getX()) <= targyak[index].getTargyX()) && (modell.getPlayer().getX() > (targyak[index].getTargyX() - 35));
    }

    public boolean ellenorizY(int index){

        return (targyak[index].getTargyY() >= 385) && (targyak[index].getTargyY() <= 520);    //karakter Y koordinataja 430,targyak meg ekkor kaphatok ki: 385 <-------------> 520
    }

    public boolean kiFogTargyak(int index) {

        return ellenorizX(index) && ellenorizY(index);
    }
    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_D){                                      //jobbra menet


            if(modell.getPlayer().getX() < 907) {                          //feltetel,hogy ne fusson ki a karakter a keretbol
                modell.getPlayer().setX(modell.getPlayer().getX() + 12);
            }

            if(!modell.getPlayer().getJobb()) {                             //ha mar jobbra volt a nezet,nem allitom azt,hogy volt nezetvaltas
                modell.getPlayer().setValtottE(true);
            }

            modell.getPlayer().setJobb(true);
            modell.getPlayer().setIdle(false);
            modell.getPlayer().setBal(false);
            modell.getPlayer().setPos(1);


        }
        else{
            if(keyCode == KeyEvent.VK_A){                                      //balra menet

                if(modell.getPlayer().getX() > 0) {                            //feltetel,hogy ne fusson ki a karakter a keretbol
                    modell.getPlayer().setX(modell.getPlayer().getX() - 12);
                }

                if(!modell.getPlayer().getBal()) {                              //ha mar balra volt a nezet,nem allitom azt,hogy volt nezetvaltas
                    modell.getPlayer().setValtottE(true);
                }

                modell.getPlayer().setBal(true);
                modell.getPlayer().setIdle(false);
                modell.getPlayer().setPos(0);
                modell.getPlayer().setJobb(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {                               //ha nincs egy gomb sem lenyomva,visszakerul a karakter idle pozicioba

        modell.getPlayer().setIdle(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Save) {
            FChooser = new JFileChooser();
            if (FChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    FWriter = new FileWriter(FChooser.getSelectedFile());
                    BufferedWriter BWriter = new BufferedWriter(FWriter);

                    BWriter.write(String.valueOf(modell.getPlayer().getScore()));
                    BWriter.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        //erre nincs szukseg
    }
}
