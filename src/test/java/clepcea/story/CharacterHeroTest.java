package clepcea.story;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CharacterHeroTest {

    @Test
    public void testManufactureWeaponToExpensive(){

        Weapon weapon = new Weapon("Pistol cu apa", 100);
        Weapon weapon1 = new Weapon("Ciorap murdar", 22000);

        Character hero = new CharacterHero("Hero", new BigDecimal(1000), weapon);

        ((CharacterHero) hero).manufactureWeapon(weapon1);

        assertEquals("1000",hero.getFortune().toString());
        assertFalse(hero.findMyWeaponByName(weapon1.getName()).isPresent());

    }

    @Test
    public void testManufactureWeapon(){

        Weapon weapon = new Weapon("Pistol cu apa", 100);
        Weapon weapon1 = new Weapon("Ciorap murdar", 200);

        Character hero = new CharacterHero("Hero", new BigDecimal(1000), weapon);

        ((CharacterHero) hero).manufactureWeapon(weapon1);

        assertEquals("980",hero.getFortune().toString());
        assertTrue(hero.findMyWeaponByName(weapon1.getName()).isPresent());

    }
}
