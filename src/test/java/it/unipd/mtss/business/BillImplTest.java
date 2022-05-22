////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.ItemType;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BillImplTest {

    private List<EItem> itemsOrdered;
    private BillImpl testBill;
    private User user;

    @Before
    public void setup() {
        itemsOrdered = new ArrayList<EItem>();
        testBill = new BillImpl();
        user = new User("fede01","Federico","De Sanctis",LocalDate.of(2001, 9, 12));
    }

    @Test
    public void calcoloDelTotaleTest(){

        itemsOrdered.add(new EItem( ItemType.Processor, "Intel",200.00));
        itemsOrdered.add(new EItem( ItemType.Motherboard, "Gigabyte", 150.00));
        itemsOrdered.add(new EItem( ItemType.Keyboard, "Logitech", 80.00));
        itemsOrdered.add(new EItem( ItemType.Mouse, "Razer", 50.00));

        assertEquals(480, testBill.getOrderPrice(itemsOrdered,user), 0.0);
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

}