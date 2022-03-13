public class JatekModell {

    private final Jatekos player;


    private final Sound sound;
    public JatekModell(Jatekos player){

        this.player = player;
        sound = new Sound();
    }

    public Jatekos getPlayer(){

        return player;
    }



    public Sound getSound(){
        return sound;
    }

}
