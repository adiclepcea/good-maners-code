package clepcea.story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CharacterVillain extends Character{

    public CharacterVillain(String name, BigDecimal fortune){
        super(name, fortune);
    }

    public void regenerateHealth(){
        setHealth(getHealth()+10);
    }


}