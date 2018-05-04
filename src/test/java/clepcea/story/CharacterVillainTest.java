package clepcea.story;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CharacterVillainTest {

    @Test
    public void testRegenerateHealth(){

        Character villain = new CharacterVillain("Hero", new BigDecimal(1000));
        villain.setHealth(900);

        ((CharacterVillain) villain).regenerateHealth();

        assertEquals(910,villain.getHealth());

    }

}
