package clepcea.story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CharacterHero extends Character{

    CharacterHero(String name, BigDecimal fortune, Weapon weapon){
        super(name, fortune);
        this.findWeapon(weapon);
        this.armWith(weapon.getName());            
    }

    public void manufactureWeapon(Weapon weapon){
        BigDecimal manufacturingCost = new BigDecimal(weapon.getForce()/10);
        if(getFortune().compareTo(manufacturingCost)>0) {
            findWeapon(weapon);
            setFortune(getFortune().subtract(manufacturingCost));
        }
    }


}