import java.util.ArrayList;

public class DramaTheatre extends Theatre{
    private String nameOfDirector;

    public DramaTheatre(String name) {
        super(name, 60);
        nameOfDirector = "Valera";
    }


    @Override
    public void webBuyTicket(Show show, TypeTickets typeTicket) {
        buyTickets(show, typeTicket);
    }

    @Override
    public void irlBuyTicket(Show show, TypeTickets typeTicket) {
        buyTickets(show, typeTicket);
    }


}
