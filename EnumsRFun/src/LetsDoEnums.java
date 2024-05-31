public class LetsDoEnums {

    enum Flavor{
        CHOCOLATE,
        VANILLA,
        STRAWBERRY;
    }

    public static void main(String[] args){
        Flavor flav = Flavor.CHOCOLATE;

        if(flav == flav.VANILLA) {
            System.out.println("It's vanilla");
        }else if(flav == flav.CHOCOLATE){
            System.out.println("It's chocolate");
        }else if(flav == flav.STRAWBERRY) {
            System.out.println("It's strawberry");
        }
    }
}