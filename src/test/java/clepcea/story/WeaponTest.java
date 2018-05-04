package clepcea.story;

import org.junit.Test;

import clepcea.story.Weapon;

import org.junit.Before;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class WeaponTest{

    clepcea.story.Character testCharacter;

    @Before
    public void init(){

        testCharacter  = new clepcea.story.CharacterVillain("Test", new BigDecimal(1000));
        testCharacter.setHealth(1000);
    }
    @Test
    public void TestHitWithForceLowerThanHealth(){
        
        Weapon weapon = new Weapon("Test weapon", 110);

        weapon.hit(testCharacter);

        assertEquals(890, testCharacter.getHealth());

    }

    @Test
    public void TestHitWithForceLargerThanHealth(){

        Weapon weapon = new Weapon("Test weapon too high power", 2000);

        weapon.hit(testCharacter);

        assertEquals(0, testCharacter.getHealth());
    }

}