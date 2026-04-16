import java.util.*;

public class blackjack123 {

    static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean turno = true;
        int saldo = 67000;
        int puntata = 0;
        int sommaGiocatore = 0;
        //test
        System.out.println("Ciao benvenuto al gioco del blackjack, vuoi giocare? (s/n)");
        String risposta = scanner.nextLine();

        switch (risposta) {
            case "s":
                System.out.println("Iniziamo!");
                break;
            case "n":
                System.out.println("Peccato, magari la prossima volta!");
                return;
            default:
                System.out.println("Risposta non valida, chiudo il gioco.");
                return;
        }

        while (turno) {

            int carta1 = pescacarta();
            int carta2 = pescacarta();
            int carta1Dealer = pescacarta();
            int carta2Dealer = pescacarta();
            int contacarte = 2;

            System.out.println(" hai " + saldo + " fiches . Quanto vuoi puntare? ");
            puntata = scanner.nextInt();
            scanner.nextLine();

            if (puntata <= 0 || puntata > saldo) {
                System.out.println("Puntata non valida");
            } else {

                if (puntata == 67 || puntata == 67000) {
                    System.out.println("Carte giocatore: 10 11 la tua mano vale: 21");
                    System.out.println("Carta dealer: " + carta1Dealer);
                    System.out.println("Blackjack! Hai vinto!");
                    saldo += puntata * 1.5;
                } else {

                    stampacartegiocatore(carta1, carta2);
                    stampacartadealer(carta1Dealer);

                    sommaGiocatore = carta1 + carta2;
                    int sommaDealer = carta1Dealer + carta2Dealer;

                    boolean keepasking = true;

                    while (keepasking) {

                        System.out.println("Vuoi pescare un'altra carta? (s/n)");
                        String risposta2 = scanner.nextLine();

                        switch (risposta2) {

                            case "s":
                                int carta3 = pescacarta();
                                sommaGiocatore += carta3;
                                contacarte++;

                                System.out.println("Hai pescato: " + carta3);
                                System.out.println("La tua mano ora vale: " + sommaGiocatore);

                                if (sommaGiocatore > 21) {
                                    System.out.println("Hai sballato! Il dealer vince.");
                                    saldo -= puntata;
                                    keepasking = false;
                                    if (saldo <= 0) {
                                        System.out.println("Hai finito i soldi! Il gioco è finito.");
                                        turno = false;
                                    }
                                }
                                break;

                            case "n":
                                System.out.println("Il dealer pesca la sua seconda carta... " + carta2Dealer);

                                while (sommaDealer <= 17 && sommaDealer <= 21) {
                                    int carta3Dealer = pescacarta();
                                    sommaDealer += carta3Dealer;
                                    System.out.println("Il dealer pesca: " + carta3Dealer +
                                            " la mano del dealer ora vale: " + sommaDealer);
                                }

                                System.out.println("La mano del dealer vale: " + sommaDealer);

                                if (sommaGiocatore == 21 && contacarte == 2) {
                                    System.out.println("Blackjack! Hai vinto!");
                                    saldo += puntata * 1.5;
                                } else if (sommaDealer > 21 || sommaGiocatore > sommaDealer) {
                                    System.out.println("Complimenti, hai vinto!");
                                    saldo += puntata;
                                } else if (sommaGiocatore < sommaDealer) {
                                    System.out.println("Il dealer vince!");
                                    saldo -= puntata;
                                    if (saldo <= 0) {
                                        System.out.println("Hai finito le fiches ! Il gioco è finito.");
                                        turno = false;
                                    }
                                } else {
                                    System.out.println("Pareggio!");
                                }

                                keepasking = false;
                                break;

                            default:
                                System.out.println("Risposta non valida");
                                break;
                        }
                    }
                }
                if(turno==false){
                }
                else{
                    System.out.println("Vuoi continuare a giocare? (s/n)");
                    String continua = scanner.nextLine();
                    switch (continua) {
                        case "s":

                            break;
                        case "n":
                            System.out.println("Grazie per aver giocato! Il tuo saldo finale è: " + saldo);
                            turno = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public static int pescacarta() {
        return random.nextInt(10) + 1;
    }

    public static void stampacartegiocatore(int a, int b) {
        System.out.println("Carte giocatore: " + a + " " + b + " la tua mano vale: " + (a + b));
    }

    public static void stampacartadealer(int a) {
        System.out.println("Carta dealer: " + a);
    }
}