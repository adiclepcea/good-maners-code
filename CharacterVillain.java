package story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CharacterVillain extends Character{
    private String name;
    private BigDecimal fortune;
    private int health;
    private Set<Weapon> weapons;
    private Optional<Weapon> activeWeapon = Optional.empty();

    public CharacterVillain(String name, BigDecimal fortune){
        super(name, fortune);
    }

    public void regenerateHealth(){
        health += 10;       
    }


}