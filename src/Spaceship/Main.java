package Spaceship;

public class Main {
    public static void main(String[] args) {
        Spaceship klingon = new Spaceship("IKS Hegh'ta", 100, 100, 100, 100, 1, 2);
        klingon.addCargo(new Cargo("Ferengi Slug Juice", 200));
        klingon.addCargo(new Cargo("Bat'Ieth Klingon Sword", 200));
        klingon.showSpaceshipInfo();
        klingon.showManifest();

        Spaceship romulan = new Spaceship("IRW Khazara", 100, 100, 100, 100, 2, 2);
        romulan.addCargo(new Cargo("Borg Scrap", 5));
        romulan.addCargo(new Cargo("Red Matter", 2));
        romulan.addCargo(new Cargo("Plasma Weapon", 50));
        romulan.showSpaceshipInfo();
        romulan.showManifest();

        Spaceship vulcan = new Spaceship("Ni'Var", 80, 80, 50, 100, 0, 5);
        vulcan.addCargo(new Cargo("Research Probe", 35));
        vulcan.addCargo(new Cargo("Photon Torpedo", 3));
        vulcan.showSpaceshipInfo();
        vulcan.showManifest();

        klingon.shootPhotonTorpedo(romulan);
        romulan.shootPhaserCannon(klingon);
        vulcan.sendMessage("Violence is not logical.");
        klingon.showSpaceshipInfo();
        klingon.showManifest();
        vulcan.sendRepairOrder(true, true, true, vulcan.getAndroids());
        vulcan.reloadPhotonTorpedo(3);
        klingon.shootPhotonTorpedo(romulan);
        klingon.shootPhotonTorpedo(romulan);
        klingon.showSpaceshipInfo();
        klingon.showManifest();
        romulan.showSpaceshipInfo();
        romulan.showManifest();
        vulcan.showSpaceshipInfo();
        vulcan.showManifest();
        Spaceship.showLog();
    }
}
