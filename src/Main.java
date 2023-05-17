public class Main {
    public static void main(String[] args) {
        System.out.println("Exercise 1: Boxes");
        boxes();
        System.out.println();

        System.out.println("Exercise 2: Passbook");
        passbook();
    }

    public static void boxes(){
        Box box1 = new Box(1.2, 1.4, 3.8, "green");
        Box box2 = new Box(2.0, 4.7, 5.0, "red");
        Box box3 = new Box(1.0, 1.0, 1.0, "white");

        box1.showInfo();
        box2.showInfo();
        box3.showInfo();
    }

    public static void passbook(){
        Passbook sb = new Passbook(1104711, 1000, 3);
        sb.payIn(60000);

        System.out.println("Kapital: " + sb.getCapital());
        System.out.println("Ertrag nach 6 Jahr: " + sb.getEarnings(6));

        sb.interest();
        System.out.println("Kapital: " + sb.getCapital());
    }
}

