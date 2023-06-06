package Spaceship;

public class Cargo {
    private String name;
    private int count;

    public Cargo() {}
    public Cargo(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public @Override String toString() {
        return "Cargo: " + this.name + ", Count: " + this.count;
    }
}
