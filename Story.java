import java.math.BigDecimal;

public class Story{

    public Story(){
        Character villain = new Character();
        Character poorGuy = new Character();


        villain.setName("Zmeul shmecher");
        poorGuy.setName("Fratele mijlociu");

        villain.setFortune(new BigDecimal(1000));
        poorGuy.setFortune(new BigDecimal(1000));

        System.out.println(String.format("%s are %s\r\n",villain.getName(), villain.getFortune().toString()));
        System.out.println(String.format("%s are %s\r\n",poorGuy.getName(), poorGuy.getFortune().toString()));

        villain.steal(poorGuy, new BigDecimal(100));

        System.out.println(String.format("\r\nDupa vizita lui %s la %s:\r\n",villain.getName(), poorGuy.getName()));
        System.out.println(String.format("%s are %s\r\n",villain.getName(), villain.getFortune().toString()));
        System.out.println(String.format("%s are %s\r\n",poorGuy.getName(), poorGuy.getFortune().toString()));

    }

    public static void main(String ...args){
        new Story();
    }

    class Character{
        private String name;
        private BigDecimal fortune;

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

        public void steal(Character stolenFrom, BigDecimal amountToSteal){

            BigDecimal toSteal = stolenFrom.getFortune().compareTo(amountToSteal)>=0?amountToSteal:stolenFrom.getFortune();
            
            stolenFrom.setFortune(stolenFrom.getFortune().subtract(toSteal));
            setFortune(getFortune().add(toSteal));            
        
        }


    }

}