import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class Story{

    private Character villain;
    private Character poorGuy;
    private Character hero;
    private Weapon weapon;
    private Consumer<String> storyTeller;

    public Story(){        
        
        prepareOutput();

        prepareCharacters();

        tellStory(villain, poorGuy, hero);
    }

    private void tellStory(Character villain, 
        Character poorGuy, 
        Character hero){

        storyIntro();
        storyClimax();
        storyEpilog();
        
    }

    private void storyIntro(){
        storyTeller.accept(villain.getStatus());
        storyTeller.accept(poorGuy.getStatus());

        villain.steal(poorGuy, new BigDecimal(100));

        storyTeller.accept(String.format("\nDupa vizita lui %s la %s:\n",villain.getName(), poorGuy.getName()));
        storyTeller.accept(villain.getStatus());
        storyTeller.accept(poorGuy.getStatus());        
    }

    private void storyClimax(){
        storyTeller.accept(String.format("\n%s il prinde pe %s\n", hero.getName(), villain.getName()));
        storyTeller.accept(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        
        while(villain.getHealth()>0){
            hero.atack(villain);
            storyTeller.accept(String.format("%s il ataca pe %s cu un %s\n", hero.getName(), villain.getName(), hero.weaponName()));
            storyTeller.accept(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        }
        
    }

    private void storyEpilog(){
        BigDecimal amountRecovered = villain.getFortune();

        hero.steal(villain, amountRecovered);        

        storyTeller.accept(String.format("\n%s ia de la %s %s galbeni\n", hero.getName(), villain.getName(), amountRecovered.toString()));
        
        hero.donate(poorGuy, amountRecovered);        

        storyTeller.accept(String.format("%s da inapoi lui %s %s galbeni\n", hero.getName(), poorGuy.getName(), amountRecovered.toString()));
        storyTeller.accept(String.format("%s are acum %s galbeni\n", poorGuy.getName(), poorGuy.getFortune().toString()));
    }


    private void prepareOutput(){
        storyTeller = System.out::println;
    }

    private void prepareCharacters(){
    
        prepareWeapon();
        prepareVillain();
        preparePoorGuy();
        prepareHero();

    }

    private void prepareWeapon(){

        weapon = new Weapon("arc", 90);

    }

    private void prepareVillain(){

        villain = new Character("Zmeul smecher", new BigDecimal(1000));
        villain.setHealth(1000);

    }

    private void preparePoorGuy(){

        poorGuy = new Character("Fratele mijlociu", new BigDecimal(1000));

    }

    private void prepareHero(){

        hero = new Character("Praslea cel voinic", new BigDecimal(1000), weapon);

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

        Character(String name, BigDecimal fortune){
            this();
            this.name = name;
            this.fortune = fortune;
        }

        Character(String name, BigDecimal fortune, Weapon weapon){
            this(name, fortune);
            this.findWeapon(weapon);
            this.armWith(weapon.getName());            
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
            
            Optional<Weapon> weapon = findMyWeaponByName(weaponName);

            if(weapon.isPresent() && weapons.contains(weapon.get())){
                weapons.remove(weapon.get());
            }
        }

        //DRY - 1
        public void armWith(String weaponName){
            
            Optional<Weapon> weapon = findMyWeaponByName(weaponName);
            
            if(weapon.isPresent() && weapons.contains(weapon.get())){
                activeWeapon = weapon;
            }
        }

        private Optional<Weapon> findMyWeaponByName(String weaponName){
            return weapons
                .stream()
                .filter(w -> 
                    w.getName().equals(weaponName))
                .findFirst();
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

        private String getStatus(){
            return String.format("%s are %s\n",getName(), getFortune().toString());
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