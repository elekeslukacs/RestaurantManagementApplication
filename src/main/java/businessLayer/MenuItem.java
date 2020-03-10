package businessLayer;

import java.io.Serializable;

public interface MenuItem extends Serializable {
	public abstract int computePrice();
	public abstract String getName();
	public abstract void setName(String name);
	public abstract void setPrice(int price);
}
