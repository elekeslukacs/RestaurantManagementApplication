package dataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class RestaurantSerializator {
	private String filename = "savedRestaurant.ser";
	public void serializeRestaurant(Restaurant r) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(filename);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(r);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in " + filename);
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public Restaurant deserializeRestaurant() {
		Restaurant r = null;
		try {
	         FileInputStream fileIn = new FileInputStream(filename);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         r = (Restaurant) in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Serialized data is loaded from " + filename);
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Restaurant class not found");
	         c.printStackTrace();
	         return null;
	      }
		return r;
	}
	
	public void serializeHash(HashSet<MenuItem> items) {
		
	try {
        FileOutputStream fileOut =
        new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(items);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in " + filename);
     } catch (IOException i) {
        i.printStackTrace();
     }
	}
	
	public HashSet<MenuItem> deserializeHash() {
		HashSet<MenuItem> r = null;
		try {
	         FileInputStream fileIn = new FileInputStream(filename);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         r = (HashSet<MenuItem>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Restaurant class not found");
	         c.printStackTrace();
	         return null;
	      }
		return r;
	}
	
}
