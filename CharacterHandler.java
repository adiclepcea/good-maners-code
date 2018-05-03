package story;

import java.math.BigDecimal;

public class CharacterHandler{
        

    public static void atackFromTo(Character source, Character target){
        if(source.getActiveWeapon().isPresent()){
            if(!(source instanceof CharacterVictim))
                source.getActiveWeapon().get().hit(target);
        }
    }

    public static void stealFromTo(Character stolenFrom, Character stolenTo, BigDecimal amountToSteal){

        BigDecimal toSteal = BigDecimal.ZERO;
        
        if(!(stolenFrom instanceof CharacterHero)){
            toSteal = stolenFrom.getFortune().compareTo(amountToSteal)>=0?amountToSteal:stolenFrom.getFortune();
            if(stolenTo instanceof CharacterVictim){
                if(stolenFrom.getFortune().compareTo(amountToSteal.divide(new BigDecimal(10)))>=0){
                    toSteal = amountToSteal.divide(new BigDecimal(10));
                }else{
                    toSteal = stolenFrom.getFortune();
                }
            }
        }

        stolenFrom.setFortune(stolenFrom.getFortune().subtract(toSteal));
        stolenTo.setFortune(stolenTo.getFortune().add(toSteal));            
    
    }

    public static void donateFromTo(Character donateFrom, Character donateTo, BigDecimal amountDonated){
        BigDecimal donated = amountDonated.compareTo(donateFrom.getFortune())>0?donateFrom.getFortune():amountDonated;
        donateTo.setFortune(donateTo.getFortune().add(donated));
        donateFrom.setFortune(donateFrom.getFortune().subtract(donated));
    }
}