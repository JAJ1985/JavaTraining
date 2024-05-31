public class ReturnStatement {
    public static void main (String[] args){
        printMessage();
        add(5, 4);
    }

    public static void printMessage (){
        System.out.println("This is our first method!");
    }

    public static void  add(int a, int b) {
        System.out.println(a + b);
    }
}
