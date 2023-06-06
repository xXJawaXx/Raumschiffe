package Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Spaceship {
    private String name;
    private double powerSupply;
    private double shields;
    private double lifeSupport;
    private double hullStrength;
    private int photonTorpedo;
    private int androids;
    private final List<Cargo> manifest = new ArrayList<>();
    private final static List<String> broadcastCommunicator = new ArrayList<>();

    public Spaceship() {}
    public Spaceship(String name, double powerSupply, double shields, double lifeSupport, double hullStrength, int photonTorpedo, int androids) {
        this.name = name;
        this.powerSupply = powerSupply;
        this.shields = shields;
        this.lifeSupport = lifeSupport;
        this.hullStrength = hullStrength;
        this.photonTorpedo = photonTorpedo;
        this.androids = androids;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPowerSupply() {
        return this.powerSupply;
    }
    public void setPowerSupply(double powerSupply) {
        this.powerSupply = powerSupply;
    }
    public double getShields() {
        return this.shields;
    }
    public void setShields(double shields) {
        this.shields = shields;
    }
    public double getLifeSupport() {
        return this.lifeSupport;
    }
    public void setLifeSupport(double lifeSupport) {
        this.lifeSupport = lifeSupport;
    }
    public double getHullStrength() {
        return this.hullStrength;
    }
    public void setHullStrength(double hullStrength) {
        this.hullStrength = hullStrength;
    }
    public int getPhotonTorpedo() {
        return this.photonTorpedo;
    }
    public void setPhotonTorpedo(int photonTorpedo) {
        this.photonTorpedo = photonTorpedo;
    }
    public int getAndroids() {
        return this.androids;
    }
    public void setAndroids(int androids) {
        this.androids = androids;
    }

    public void showSpaceshipInfo() {
        System.out.println("Spaceship");
        System.out.println("Name: " + this.name);
        System.out.println("Power Supply: " + this.powerSupply);
        System.out.println("Shields: " + this.shields);
        System.out.println("Life Support: " + this.lifeSupport);
        System.out.println("Hull Strength: " + this.hullStrength);
        System.out.println("Photon Torpedo: " + this.photonTorpedo);
        System.out.println("Android: " + this.androids);
        System.out.println();
    }
    public void showManifest() {
        System.out.println(this.name);
        for(Cargo cargo: this.manifest) {
            System.out.println(cargo);
        }
        System.out.println();
    }

    public void addCargo(Cargo cargo) {
        int index = this.getManifestIndex(cargo.getName());
        if(index > -1) {
            Cargo updatedCargo = this.manifest.get(index);
            updatedCargo.setCount(updatedCargo.getCount() + cargo.getCount());
            this.manifest.set(index, updatedCargo);
        } else {
            this.manifest.add(cargo);
        }
    }
    public void removeCargo(Cargo cargo) {
        int index = this.getManifestIndex(cargo.getName());
        if(index > -1) {
            Cargo cargoM = this.manifest.get(index);
            if(cargoM.getCount() > cargo.getCount()) {
                cargoM.setCount(cargoM.getCount() - cargo.getCount());
                this.manifest.set(index, cargoM);
            } else {
                this.manifest.remove(cargoM);
            }
        }
    }

    public void shootPhaserCannon(Spaceship target) {
        if(this.powerSupply < 50) {
            this.sendMessage("-=*Click*=-");
        } else {
            this.powerSupply -= 50;
            this.sendMessage("Phaser Cannon fired.");
            target.recordHit();
        }
    }
    public void shootPhotonTorpedo(Spaceship target) {
        if(this.photonTorpedo < 1) {
            this.sendMessage("-=*Click*=-");
        } else {
            this.photonTorpedo --;
            this.sendMessage("Photon Torpedo launched.");
            target.recordHit();
         }
    }
    public void reloadPhotonTorpedo(int count) {
        int index = this.getManifestIndex("Photon Torpedo");
        //int index = this.manifest.indexOf(this.manifest.contains());
        if(index > -1) {
            Cargo cargoM = this.manifest.get(index);
            if(cargoM.getCount() > count) {
                cargoM.setCount(cargoM.getCount() - count);
                this.manifest.set(index, cargoM);
                this.photonTorpedo += count;
                System.out.println(count + " Photon Torpedo(s) inserted.");
            } else if(this.manifest.get(index).getCount() == count){
                this.photonTorpedo += count;
                this.manifest.remove(cargoM);
                System.out.println(count + " Photon Torpedo(s) inserted.");

            } else {
                this.photonTorpedo += cargoM.getCount();
                this.manifest.remove(cargoM);
                System.out.println(cargoM.getCount() + " Photon Torpedo(s) inserted.");

            }
        } else {
            System.out.println("No Photon Torpedos found!");
            this.sendMessage("-=*Click*=-");
        }
        System.out.println();
    }

    public void sendRepairOrder(boolean powerSupply, boolean shields, boolean hullStrength, int androids) {
        Random r = new Random();
        int rnd = r.nextInt(101);

        if(androids > this.androids){
            androids = this.androids;
        }

        double repair = 0;
        if(powerSupply && shields && hullStrength) {
            repair = (rnd * androids)/3.;
        } else if((powerSupply && shields) || (powerSupply && hullStrength) || (shields && hullStrength)) {
            repair = (rnd * androids)/2.;
        } else if(powerSupply || shields || hullStrength) {
            repair = rnd * androids;
        }

        if(powerSupply) {
            if(this.powerSupply + repair > 100) {
                this.powerSupply = 100;
            } else {
                this.powerSupply += repair;
            }
        }
        if(shields) {
            if(this.shields + repair > 100) {
                this.shields = 100;
            } else {
                this.shields += repair;
            }
        }
        if(hullStrength) {
            if(this.hullStrength + repair > 100) {
                this.hullStrength = 100;
            } else {
                this.hullStrength += repair;
            }
        }
    }

    public void sendMessage(String msg) {
        broadcastCommunicator.add(this.name + ": " + msg);
    }

    public static void showLog() {
        for(String msg: broadcastCommunicator) {
            System.out.println(msg);
        }
        System.out.println();
    }

    private void recordHit() {
        System.out.println(this.name + " was hit!");
        System.out.println();
        if(this.shields > 50) {
            this.shields -= 50;
        } else if(this.shields > 0) {
            double x = 50 - this.shields;
            this.shields = 0;
            this.hullStrength -= x;
            this.powerSupply -= x;
        } else {
            if(this.hullStrength > 50) {
                this.hullStrength -= 50;
            } else {
                this.hullStrength = 0;
                this.lifeSupport = 0;
                this.sendMessage("All life support was destroyed.");
            }
            if(this.powerSupply > 50) {
                this.powerSupply -= 50;
            } else {
                this.powerSupply = 0;
            }
        }
    }

    private int getManifestIndex(String name) {
        return IntStream.range(0, this.manifest.size()).filter(i -> this.manifest.get(i).getName().equals(name)).findFirst().orElse(-1);
    }
}
