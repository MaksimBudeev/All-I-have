import java.util.ArrayList;
import java.util.HashMap;

public abstract class Theatre {
        protected ArrayList<Show> shows = new ArrayList<>();
        private String name;
        private int stageWidth;


        public void addShow(Show show){
                shows.add(show);
        }

        public Theatre(String name, int width) {
                this.name = name;
                this.stageWidth = width;
        }

        protected void buyTickets(Show show, TypeTickets typeTicket){
                if(typeTicket == TypeTickets.VIP_TICKET){
                        if(show.getVipTickets() != 0){
                                show.sellVipTicket();
                        }else if(show.getCasualTickets() != 0){
                                System.out.println("VIP билетов, увы, нет, но есть " + show.getCasualTickets() + " обычных билетов");
                        }else {
                                System.out.println("Sold out");
                        }
                }else if(typeTicket == TypeTickets.CASUAL_TICKET){
                        if(show.getCasualTickets() != 0){
                                show.sellCasualTicket();
                        }else if(show.getVipTickets() != 0){
                                System.out.println("VIP билетов, увы, нет, но есть " + show.getVipTickets() + "обычных билетов");
                        }else{
                                System.out.println("Sold out");
                        }
                }
        }

        public void returnTickets(Show show, TypeTickets typeTicket) {
                if(typeTicket == TypeTickets.VIP_TICKET){
                        show.returnVipTicket();
                }else if(typeTicket == TypeTickets.CASUAL_TICKET){
                        show.returnCasualTicket();
                }
        }

        public abstract void webBuyTicket(Show show, TypeTickets typeTicket);
        public abstract void irlBuyTicket(Show show, TypeTickets typeTicket);
}
