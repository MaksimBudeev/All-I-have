import java.util.ArrayList;

public class OperaTheatre extends Theatre{
    private String nameOfDirector;

    public OperaTheatre(String name) {
        super(name, 80);
        nameOfDirector = "Stepan";
    }

    @Override
    public void webBuyTicket(Show show, TypeTickets typeTicket) {
        System.out.println("В интернете нельзя купить билеты в данный театр");
    }

    @Override
    public void irlBuyTicket(Show show, TypeTickets typeTicket) {
        buyTickets(show, typeTicket);
    }


}
