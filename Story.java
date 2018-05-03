package story;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class Story{

    private story.Character villain;
    private story.Character poorGuy;
    private story.Character hero;
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

        CharacterHandler.stealFromTo(poorGuy, villain, new BigDecimal(100));

        storyTeller.accept(String.format("\nDupa vizita lui %s la %s:\n",villain.getName(), poorGuy.getName()));
        storyTeller.accept(villain.getStatus());
        storyTeller.accept(poorGuy.getStatus());        
    }

    private void storyClimax(){
        storyTeller.accept(String.format("\n%s il prinde pe %s\n", hero.getName(), villain.getName()));
        storyTeller.accept(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        
        while(villain.getHealth()>0){
            CharacterHandler.atackFromTo(hero, villain);
            storyTeller.accept(String.format("%s il ataca pe %s cu un %s\n", hero.getName(), villain.getName(), hero.weaponName()));
            storyTeller.accept(String.format("%s are viata %s\n",villain.getName(), villain.getHealth()));
        }
        
    }

    private void storyEpilog(){
        BigDecimal amountRecovered = villain.getFortune();

        CharacterHandler.stealFromTo(villain, hero, amountRecovered);        

        storyTeller.accept(String.format("\n%s ia de la %s %s galbeni\n", hero.getName(), villain.getName(), amountRecovered.toString()));
        
        CharacterHandler.donateFromTo(hero, poorGuy, amountRecovered);        

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

        villain = new CharacterVillain("Zmeul smecher", new BigDecimal(1000));
        villain.setHealth(1000);

    }

    private void preparePoorGuy(){

        poorGuy = new CharacterVictim("Fratele mijlociu", new BigDecimal(1000));

    }

    private void prepareHero(){

        hero = new CharacterHero("Praslea cel voinic", new BigDecimal(1000), weapon);

    }

    public static void main(String ...args){
        new Story();
    }

}