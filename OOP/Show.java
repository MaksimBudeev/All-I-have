public class Show {
    private String name;
    private int vipTickets;
    private int casualTickets;

    public Show(String name, int vipTickets, int casualTickets) {
        this.name = name;
        this.vipTickets = vipTickets;
        this.casualTickets = casualTickets;

    }

    public void sellVipTicket(){
        vipTickets--;
        System.out.println("VIP билет успешно куплен. Приходите за 10 минут до начала");
    }

    public void sellCasualTicket(){
        casualTickets--;
        System.out.println("Обычный билет успешно куплен. Приходите за 10 минут до начала");
    }

    public void returnVipTicket() {
        vipTickets++;
        System.out.println("Вы вернули билет. Хорошего вам настроения!");
    }

    public void returnCasualTicket(){
        casualTickets++;
        System.out.println("Вы вернули билет. Хорошего вам настроения!");
    }

    public String getName() {
        return name;
    }

    public int getVipTickets() {
        return vipTickets;
    }

    public int getCasualTickets() {
        return casualTickets;
    }
}
