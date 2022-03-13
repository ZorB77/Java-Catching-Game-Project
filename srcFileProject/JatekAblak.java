import javax.swing.*;
import java.awt.*;

public class JatekAblak extends JFrame {


    public JatekAblak(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1014 ,700);
        this.setLayout(new BorderLayout());
        Jatekos player = new Jatekos();

        JatekModell p = new JatekModell(player);
        JatekView v = new JatekView(p);
        JatekKontroll kontroll = new JatekKontroll(p ,v);

        Thread th = new Thread(kontroll);
        th.start();
        this.add(v);
        this.setVisible(true);

    }

    public static void main(String[] args) {

        new JatekAblak();
    }

}
