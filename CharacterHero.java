package story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CharacterHero extends Character{
    private String name;
    private BigDecimal fortune;
    private int health;
    private Set<Weapon> weapons;
    private Optional<Weapon> activeWeapon = Optional.empty();


    CharacterHero(String name, BigDecimal fortune, Weapon weapon){
        super(name, fortune);
        this.findWeapon(weapon);
        this.armWith(weapon.getName());            
    }

    public void manufactureWeapon(Weapon weapon){
        findWeapon(weapon);
    }


}