package businessLayer;

import java.util.Date;

public class Order {
	private int orderID;
	private Date date;
	private int table;
	
	public Order(int id, int table) {
		this.orderID = id;
		this.date = new Date();
		this.table = table;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + orderID;
		result = prime * result + table;
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
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderID != other.orderID)
			return false;
		if (table != other.table)
			return false;
		return true;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getDate() {
		return date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}
	
	

}
