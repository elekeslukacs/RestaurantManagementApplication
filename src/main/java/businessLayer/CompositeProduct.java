package businessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem{
	private List<MenuItem> items = new ArrayList<MenuItem>();
	private String name;
	private int price;
	
	public CompositeProduct(String name) {
		this.name = name;
		this.price = 0;
	}
	
	public int computePrice() {
		int computedPrice = 0;
		for(MenuItem item : items) {
			computedPrice += ((BaseProduct)item).getPrice(); 
		}
		this.price = computedPrice;
		return computedPrice;
	}
	
	public void addBaseProduct(MenuItem part) {
		this.items.add(part);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeProduct other = (CompositeProduct) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
}
