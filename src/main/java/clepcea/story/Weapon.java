package clepcea.story;

public class Weapon{

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
