package story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Character{
    public String name;
    private BigDecimal fortune;
    private int health;
    private Set<Weapon> weapons;
    private Optional<Weapon> activeWeapon = Optional.empty();

    
    public Character(){
        weapons = new HashSet<>();
    }

    public Character(String name, BigDecimal fortune){
        this();
        this.name = name;
        this.fortune = fortune;
    }

    public String weaponName(){
        if(activeWeapon.isPresent()){
            return activeWeapon.get().getName();
        }

        return "nada";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setFortune(BigDecimal fortune){
        this.fortune = (new BigDecimal(0)).add(fortune);
    }

    public BigDecimal getFortune(){
        return (new BigDecimal(0)).add(fortune);
    }


    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    //could have been add, but find is more apropriate to the story
    public void findWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    //could have been delete, but loose is more apropriate to the story
    public void looseWeapon(String weaponName){
        Optional<Weapon> weapon = findMyWeaponByName(weaponName);

        if(weapon.isPresent()){
            weapons.remove(weapon.get());
        }
    }

    public void armWith(String weaponName){
        Optional<Weapon> weapon = findMyWeaponByName(weaponName);
        
        if(weapon.isPresent()){
            activeWeapon = weapon;
        }
    }

    Optional<Weapon> findMyWeaponByName(String weaponName){
        return weapons
            .stream()
            .filter(w -> 
                w.getName().equals(weaponName))
            .findFirst();
    }        

    public Optional<Weapon> getActiveWeapon(){
        return activeWeapon;
    }

    public String getStatus(){
        return String.format("%s are %s\n",getName(), getFortune().toString());
    }

}
