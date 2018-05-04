package clepcea.story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CharacterVictim extends Character{

    public CharacterVictim(String name, BigDecimal fortune){

        super(name, fortune);

    }

    public void sellWeapon(String weaponName){

        Optional<Weapon> weapon = findMyWeaponByName(weaponName);

        if(weapon.isPresent()){
            setFortune(getFortune().add(new BigDecimal(weapon.get().getForce()/10)));
            looseWeapon(weaponName);
        }

    }


}