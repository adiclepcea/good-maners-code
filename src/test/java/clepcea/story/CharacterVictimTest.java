package clepcea.story;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CharacterVictimTest {

    @Test
    public void TestSellWeapon(){
        Character victim = new CharacterVictim("Just a victim", new BigDecimal(1000));

        Weapon weapon  = new Weapon("First weapon", 500);

        victim.findWeapon(weapon);

        ((CharacterVictim) victim).sellWeapon(weapon.getName());

        assertEquals("1050", victim.getFortune().toString());

        assertFalse(victim.findMyWeaponByName(weapon.getName()).isPresent());
    }

}
