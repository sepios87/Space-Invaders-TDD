package fr.unilim.iut.spaceinvaders;

public class Position {
   private int x;
   private int y;

   public Position(int x, int y) {
	   this.x = x;
	   this.y = y;
   }

   public int abscisse() {
	   return this.x;
   }

  public int ordonnee() {
	  return this.y;
  }
  
  public void changerAbscisse(int nouvelleAbscisse) {
      this.x = nouvelleAbscisse;
 }

 public void changerOrdonnee(int nouvelleOrdonnee) {
     this.y = nouvelleOrdonnee;
 }

}  
