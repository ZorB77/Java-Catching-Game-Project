public class JatekKontroll implements Runnable {

    private final JatekModell modell;
    private final JatekView view;

    public JatekKontroll(JatekModell modell ,JatekView view){

        this.modell = modell;
        this.view = view;
    }

    @Override
    public void run(){


        while(modell.getPlayer().getLife() > 0){

            modell.getPlayer().updatePos();
            view.repaint();

            try{
                Thread.sleep(50);
            }
            catch(InterruptedException e){
                System.out.println("Hiba a szalban!");
            }
        }


        modell.getSound().stopMusic();
        modell.getPlayer().setX(390);
        modell.getPlayer().setY(250);
        modell.getPlayer().gameOver();
        modell.getSound().playGameOverSound();
        view.repaint();
    }
}
