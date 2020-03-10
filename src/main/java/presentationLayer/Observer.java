package presentationLayer;

import businessLayer.Restaurant;

public abstract class Observer {
	protected Restaurant res;
	public abstract void update();
}
