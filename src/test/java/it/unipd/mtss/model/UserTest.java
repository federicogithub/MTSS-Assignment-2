////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

public class UserTest {

    private User Federico = new User("fede01","Federico","De Sanctis",LocalDate.of(2001, 9, 12));

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNicknameNulloTest() {
        new User(null,"Federico","De Sanctis",LocalDate.of(2001, 9, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreNomeNulloTest() {
        new User("fede01",null,"De Sanctis",LocalDate.of(2001, 9, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreCognomeNulloTest() {
        new User("fede01","Federico",null,LocalDate.of(2001, 9, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costruttoreDataNullaTest() {
        new User("fede01","Federico","De Sanctis",null);
    }

    @Test
    public void getNameTest() {
        assertEquals(Federico.getName(), "Federico");
    }

    @Test
    public void getNicknameTest(){
        assertEquals(Federico.getNickname(), "fede01");
    }

    @Test
    public void getSurnameTest() {
        assertEquals(Federico.getSurname(), "De Sanctis");
    }

    @Test
    public void getAgeTest() {
        assertEquals(Federico.getAge(), 21);  
    }
}