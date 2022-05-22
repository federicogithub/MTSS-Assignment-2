////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EItemTest {

    private EItem Processor;
    private EItem Motherboard;
    private EItem Keyboard;
    private EItem Mouse;

    @Before
    public void setup() {
        Processor = new EItem( ItemType.Processor, "Intel", 200.00);
        Motherboard = new EItem(ItemType.Motherboard, "Gigabyte", 150.00);
        Keyboard = new EItem( ItemType.Keyboard, "Logitech", 80.00);
        Mouse = new EItem( ItemType.Mouse, "Razer", 50.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreTipologiaElementoNulloTest() {
        new EItem(null, "Gigabyte", 150.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNomeElementoNulloTest() {
        new EItem(ItemType.Processor, null, 4D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttorePrezzoElementoNegativoTest() {
        new EItem(ItemType.Motherboard, "Gigabyte", -150.00);
    }

    @Test
    public void getNameTest() {
        assertEquals("Intel", Processor.getName());
        assertEquals("Gigabyte", Motherboard.getName());
        assertEquals("Logitech", Keyboard.getName());
        assertEquals("Razer", Mouse.getName());		
    }

    @Test
    public void getPriceTest() {
        assertEquals(200.00, Processor.getPrice(), 0.0);
        assertEquals(150.00, Motherboard.getPrice(), 0.0);
        assertEquals(80.00, Keyboard.getPrice(), 0.0);
	    assertEquals(50.00, Mouse.getPrice(), 0.0);
    }

    @Test
    public void getTypeTest() {
        assertEquals(ItemType.Processor, Processor.getType());
        assertEquals(ItemType.Motherboard, Motherboard.getType());
        assertEquals(ItemType.Keyboard, Keyboard.getType());
        assertEquals(ItemType.Mouse, Mouse.getType());
    }
}