package GUI;

import java.io.*;

public class SistemIO {

    public static void saveToFile(Sistem sistem, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(sistem);
            System.out.println("Sistem başarıyla kaydedildi.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sistem loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Sistem) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

