////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.ItemType;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.Order;
import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BillImplTest {

    private List<EItem> itemsOrdered;
    private BillImpl testBill; 
    private User user; 
    private LocalTime oreSeiMezza;

    @Before
    public void setup() {
        itemsOrdered = new ArrayList<EItem>();
        testBill = new BillImpl();
        oreSeiMezza = LocalTime.of(18,30,00,00);
        user = new User("fede01","Federico","De Sanctis",LocalDate.of(2001, 9, 12));
    }

    @Test
    public void calcoloDelTotaleTest(){

        itemsOrdered.add(new EItem( ItemType.Processor, "Intel",200.00));
        itemsOrdered.add(new EItem( ItemType.Motherboard, "Gigabyte", 150.00));
        itemsOrdered.add(new EItem( ItemType.Keyboard, "Logitech", 80.00));
        itemsOrdered.add(new EItem( ItemType.Mouse, "Razer", 50.00));
        itemsOrdered.add(new EItem( ItemType.Mouse, "Logitech", 60.00));

        assertEquals(540, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test(expected=BillException.class)
    public void calcoloDelTotaleConListaOrdiniVuotaTest() {

        testBill.getOrderPrice(itemsOrdered, user);

    }

    @Test(expected=BillException.class)
    public void calcoloDelTotaleConListaOrdiniNullaTest() {
        itemsOrdered = null;
        testBill.getOrderPrice(itemsOrdered, user);

    }

    @Test
    public void totaleConScontoSulMenoCaroSePiùDiCinqueProcessoriTest() {

        for(int i=0; i<6; i++) {
            itemsOrdered.add(new EItem( ItemType.Processor, "AMD",100.00));
        }       
        assertEquals(550, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void totaleConScontoSulMenoCaroSePiùDiDieciMouseTest() {

        for(int i=0; i<11; i++) {
            itemsOrdered.add(new EItem( ItemType.Mouse, "Razer",50.00));
        }
        assertEquals(500, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void totaleConScontoSulMenoCaroSeStessiMouseETastiereTest() {

        for(int i=0; i<2; i++) {
            itemsOrdered.add(new EItem( ItemType.Mouse, "Razer",50.00));
        }
        for(int i=0; i<2; i++) {
            itemsOrdered.add(new EItem( ItemType.Keyboard, "Logitech",80.00));
        }
        assertEquals(210, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void totaleConScontoSeOltre1000euroDiSpesaTest() {

        itemsOrdered.add(new EItem( ItemType.Processor, "Intel top",1100.00));

        assertEquals(990.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test(expected=BillException.class)
    public void oltreTrentaElementiPerOrdineTest() {

        for(int i=0; i<35; i++) {
            itemsOrdered.add(new EItem( ItemType.Processor, "Intel",150.00));
        }

        testBill.getOrderPrice(itemsOrdered, user);
    }	

    @Test
    public void totaleConCommissioneSeMenoDi10euroTest() {

        itemsOrdered.add(new EItem( ItemType.Mouse, "Logitech",5.00));

        assertEquals(7.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void ordiniRegalatiAdUtentiMinorenniTest() {

        //lista con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();
        //itemsOrdered -> lista elementi in un ordine, ne creo una uguale per tutti gli ordini
        itemsOrdered.add(new EItem( ItemType.Processor, "Intel",150.00));
        itemsOrdered.add(new EItem( ItemType.Motherboard, "Gigabyte",100.00));
        //11 utenti minorenni
        String[] nomi = new String[]{"Mike", "Elton", "Adri", "Alby",
                        "Jon", "Bert", "Chri", "Luis", "Sal", "Eddy", "Astro"};
        User user = null;
        //inserisco 11 ordini
        for (int i = 0; i < 11; i++) {
            //nuovo utente minorenne
            user = new User(nomi[i]+"_test",nomi[i],"prova",LocalDate.of(2010, 5, 12));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
        }

        List<Order> ordiniGratis = testBill.getFreeOrders(ordini);
        int numeroOrdiniRegalati = 0;

        for (Order ord : ordiniGratis) {
            if(ord.getPrice() == 0) {
                numeroOrdiniRegalati++;
            }
        }
        //controllo che siano stati regalati 10 ordini di minorenni nell'orario statbilito
        assertEquals(10,numeroOrdiniRegalati); 
    }

    //si assume che se non vengono effettuate almeno 10 ordinazioni non vengono regalati ordini
    @Test(expected=BillException.class)
    public void ordiniRegalatiSeMenoDi10ordiniTest() { 

        //lista con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();
        //itemsOrdered -> lista elementi in un ordine, ne creo una uguale per tutti gli ordini
        itemsOrdered.add(new EItem( ItemType.Keyboard, "Logitech",80.00));
        //3 utenti minorenni
        String[] nomi =new String[]{"Mike", "Elton", "Adri"};
        User user = null;
        //inserisco 3 ordini
        for (int i = 0; i < 3; i++) {
            //nuovo utente minorenne
            user = new User(nomi[i]+"_test",nomi[i],"prova",LocalDate.of(2010, 5, 12));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
        }

        testBill.getFreeOrders(ordini);
    }

}