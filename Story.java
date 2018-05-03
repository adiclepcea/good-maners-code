import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Story{

    public Story(){
        Character villain = new Character();
        Character poorGuy = new Character();
        Character hero = new Character();

        villain.setName("Zmeul shmecher");
        poorGuy.setName("Fratele mijlociu");
        hero.setName("Praslea cel voinic");

        
        Weapon bow = new Weapon("arc", 90);

        hero.findWeapon(bow);
        hero.armWith("arc");

        villain.setFortune(new BigDecimal(1000));
        poorGuy.setFortune(new BigDecimal(1000));
        hero.setFortune(new BigDecimal(1000));

        villain.setHealth(1000);

        System.out.println(String.format("%s are %s\n",villain.getName(), villain.getFortune().toString()));
        System.out.println(String.format("%s are %s\n",poorGuy.getName(), poorGuy.getFortune().toString()));

        villain.steal(poorGuy, new BigDecimal(100));

        System.out.println(String.format("\nDupa vizita lui %s la %s:\n",villain.getName(), poorGuy.getName()));
        System.out.println(String.format("%s are %s\n",villain.getName(), villain.getFortune().toString()));
        System.out.println(String.format("%s are %s\n",poorGuy.getName(), poorGuy.getFortune().toString()));


        System.out.println(String.format("\n%s il prinde pe %s\n", hero.getName(), villain.getName()));
        System.out.println(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        
        while(villain.getHealth()>0){
            hero.atack(villain);
            System.out.println(String.format("%s il ataca pe %s cu un %s\n", hero.getName(), villain.getName(), hero.weaponName()));
            System.out.println(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        }

        BigDecimal amountRecovered = villain.getFortune();

        hero.steal(villain, amountRecovered);        

        System.out.printf(String.format("\n%s ia de la %s %s galbeni\n", hero.getName(), villain.getName(), amountRecovered.toString()));
        
        hero.donate(poorGuy, amountRecovered);

        System.out.printf(String.format("%s da inapoi lui %s %s galbeni\n", hero.getName(), poorGuy.getName(), amountRecovered.toString()));
        System.out.printf(String.format("%s are acum %s galbeni\n", poorGuy.getName(), poorGuy.getFortune().toString()));

    }

    public static void main(String ...args){
        new Story();
    }

    class Character{
        private String name;
        private BigDecimal fortune;
        private int health;
        private Set<Weapon> weapons;
        private Optional<Weapon> activeWeapon = Optional.empty();

        Character(){
            weapons = new HashSet<>();
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

        //DRY - 1
        //could have been delete, but loose is more apropriate to the story
        public void looseWeapon(String weaponName){
            Optional<Weapon> weapon = weapons
                .stream()
                .filter(w -> 
                    w.getName().equals(weaponName))
                .findFirst();
            if(weapon.isPresent() && weapons.contains(weapon.get())){
                weapons.remove(weapon.get());
            }
        }

        //DRY - 1
        public void armWith(String weaponName){
            Optional<Weapon> weapon = weapons
                .stream()
                .filter(w -> 
                    w.getName().equals(weaponName))
                .findFirst();
            if(weapon.isPresent() && weapons.contains(weapon.get())){
                activeWeapon = weapon;
            }
        }

        public void atack(Character character){
            if(activeWeapon.isPresent()){
                activeWeapon.get().hit(character);
            }
        }

        public void steal(Character stolenFrom, BigDecimal amountToSteal){

            BigDecimal toSteal = stolenFrom.getFortune().compareTo(amountToSteal)>=0?amountToSteal:stolenFrom.getFortune();
            
            stolenFrom.setFortune(stolenFrom.getFortune().subtract(toSteal));
            setFortune(getFortune().add(toSteal));            
        
        }

        public void donate(Character donateTo, BigDecimal amountDonated){
            donateTo.setFortune(donateTo.getFortune().add(amountDonated));
        }

    }

    class Weapon{

        private final String name;
        private final int force;

        Weapon(String name, int force){
            this.name = name;
            this.force = force;
        }

        public String getName(){
            return name;
        }

        public int getForce(){
            return force;
        }

        public void hit(Character target){
            target.setHealth(target.getHealth()-Math.min(force, target.getHealth()));
        }
    }

}