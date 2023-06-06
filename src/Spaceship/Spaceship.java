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

    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }
    public double getPowerSupply() {
        return this.powerSupply;
    }
    public void setPowerSupply(double powerSupply) {
        this.powerSupply = powerSupply;
    }
    public double getShields() { return this.shields; }
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
    public int getAndroids() { return this.androids; }
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

    /**
     * add cargo to the manifest
     * if cargo with same name exists already, the count value will be added to the existing cargo count value
     * @param cargo the cargo, that will be added to the manifest
     */
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

    /**
     * remove cargo from the manifest
     * if cargo count value is greater than the stored amount, the stored amount will be completely removed
     * if no cargo exists, nothing happens
     * @param cargo the cargo, that will be removed from the manifest
     */
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

    /**
     * shoots phaser cannon at target spaceship
     * if the power supply is less than 50, it won't shoot
     * if the power is equal or greater than 50, it will shoot and target uses the recordHit() method
     * a message will be sent using the sendMessage() method
     * @param target the spaceship targeted by the weapon
     */
    public void shootPhaserCannon(Spaceship target) {
        if(this.powerSupply < 50) {
            this.sendMessage("-=*Click*=-");
        } else {
            this.powerSupply -= 50;
            this.sendMessage("Phaser Cannon fired.");
            target.recordHit();
        }
    }
    /**
     * shoots a photon torpedo at target spaceship
     * if there are no torpedos loaded, it won't shoot
     * if there are torpedos, it will shoot, decrement photonTorpedo and target uses the recordHit() method
     * a message will be sent using the sendMessage() method
     * @param target the spaceship targeted by the weapon
     */
    public void shootPhotonTorpedo(Spaceship target) {
        if(this.photonTorpedo < 1) {
            this.sendMessage("-=*Click*=-");
        } else {
            this.photonTorpedo --;
            this.sendMessage("Photon Torpedo launched.");
            target.recordHit();
         }
    }

    /**
     * reloads the photon torpedos from the manifest by the count value
     * if no torpedos are in the manifest, a message will be sent using sendMessage
     * if there are enough torpedos stored, they will be added to photonTorpedo and the manifest value will be updated, when there are photon torpedos left over or removed
     * if there are supposed to be more torpedos loaded than there are stored, all stored torpedos are loaded
     * if all torpedos are loaded, the photon torpedos object will be removed from the manifest
     * in any case there will be a console specific console message
     * @param count how many photon torpedos should be reloaded
     */
    public void reloadPhotonTorpedo(int count) {
        int index = this.getManifestIndex("Photon Torpedo");
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

    /**
     * generates a random number (0, 100)
     * checks if the number of helping androids is valid, less or equal to the number on the spaceship
     * if not the number is set to the number specified by the spaceship
     * then the repair value is calculated, depending on the random value, the android count and the number of things to be repaired
     * then depending on if the value is supposed to be repaired, the values are updated by the repair value
     * @param powerSupply true, if powerSupply should be repaired
     * @param shields true, if shields should be repaired
     * @param hullStrength true, if hullStrength should be repaired
     * @param androids count of helping androids
     */
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

    /**
     * console message that the spaceship was hit
     * if shields are greater than 50, 50 is deducted
     * if shields are less than 50, shields are set to 0 and the rest value is will be deducted from hullStrength and powerSupply
     * if hullStrength is less than 50 or the rest value, hullStrength is set to 0 and a message will be sent using sendMessage()
     * if powerSupply is less than 50 or the rest value, powerSupply is set to 0
     */
    private void recordHit() {
        System.out.println(this.name + " was hit!");
        System.out.println();
        if(this.shields > 50) {
            this.shields -= 50;
        } else if(this.shields > 0) {
            double x = 50 - this.shields;
            this.shields = 0;
            if(this.hullStrength > x) {
                this.hullStrength -= x;
            } else {
                this.hullStrength = 0;
                this.lifeSupport = 0;
                this.sendMessage("All life support was destroyed.");
            }
            if(this.powerSupply > x) {
                this.powerSupply -= x;
            } else {
                this.powerSupply = 0;
            }
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
