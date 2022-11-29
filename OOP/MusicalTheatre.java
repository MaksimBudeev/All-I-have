import java.util.ArrayList;

public class MusicalTheatre extends Theatre{
    private String nameOfDirector;

    public MusicalTheatre(String name) {
        super(name, 55);
        nameOfDirector = "Maxim";

    }

    @Override
    public void webBuyTicket(Show show, TypeTickets typeTicket) {
        buyTickets(show,typeTicket);
    }

    @Override
    public void irlBuyTicket(Show show, TypeTickets typeTicket) {
        buyTickets(show, typeTicket);
    }


}
