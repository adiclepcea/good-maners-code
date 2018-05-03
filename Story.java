import java.math.BigDecimal;

public class Story{

    public Story(){
        Bulan x = new Bulan();
        Bulan y = new Bulan();

        x.setCumIlStriga("Zmeul shmecher");
        y.setCumIlStriga("Fratele mijlociu");

        x.setVerzisori(new BigDecimal(1000));
        y.setVerzisori(new BigDecimal(1000));

        System.out.println(String.format("%s are %s\r\n",x.getCumIlStriga(), x.getVerzisori().toString()));
        System.out.println(String.format("%s are %s\r\n",y.getCumIlStriga(), y.getVerzisori().toString()));

        x.smangleste(y, new BigDecimal(100));

        System.out.println(String.format("\r\nDupa vizita lui %s la %s:\r\n",x.getCumIlStriga(), y.getCumIlStriga()));
        System.out.println(String.format("%s are %s\r\n",x.getCumIlStriga(), x.getVerzisori().toString()));
        System.out.println(String.format("%s are %s\r\n",y.getCumIlStriga(), y.getVerzisori().toString()));

    }

    public static void main(String ...args){
        new Story();
    }

    class Bulan{
        private String cumIlStriga;
        private BigDecimal verzisori;

        public void setCumIlStriga(String x){
            this.cumIlStriga = x;
        }

        public String getCumIlStriga(){
            return cumIlStriga;
        }

        public void setVerzisori(BigDecimal x){
            this.verzisori = (new BigDecimal(0)).add(x);
        }

        public BigDecimal getVerzisori(){
            return (new BigDecimal(0)).add(verzisori);
        }

        public void smangleste(Bulan looser, BigDecimal cat){

            BigDecimal deSmanglit = looser.getVerzisori().compareTo(cat)>=0?cat:looser.getVerzisori();
            
            looser.setVerzisori(looser.getVerzisori().subtract(deSmanglit));
            setVerzisori(getVerzisori().add(deSmanglit));            
        
        }

    }

}