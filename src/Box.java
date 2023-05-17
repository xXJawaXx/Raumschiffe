public class Box {
    public double height;
    public double length;
    public double depth;
    public String color;

    public Box(){}

    public Box(double height, double length, double depth, String color){
        this.height = height;
        this.length = length;
        this.depth = depth;
        this.color = color;
    }

    public double getVolume(){
        return this.height * this.length * this.depth;
    }

    public String getColor(){
        return this.color;
    }

    public void showInfo(){
        System.out.println("Volume: " + this.getVolume() + " Color: " + this.getColor());
    }
}
