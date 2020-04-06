package fr.unilim.iut.spaceinvaders;

public class Vaisseau {

	int x;
	int y;

	public Vaisseau(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
    public boolean occupeLaPosition(int x, int y) {
		return (this.x==x) && (this.y==y);
	}

}