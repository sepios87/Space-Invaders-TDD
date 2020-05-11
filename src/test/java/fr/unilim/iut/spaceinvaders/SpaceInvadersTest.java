package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import spaceinvaders.utils.DebordementEspaceJeuException;
import spaceinvaders.utils.HorsEspaceJeuException;
import spaceinvaders.utils.MissileException;

    public class SpaceInvadersTest {
    	
    	 private SpaceInvaders spaceinvaders;
    	
    	@Before
	    public void initialisation() {
		    spaceinvaders = new SpaceInvaders(15, 10);
	    }
	
        @Test
        public void test_AuDebut_JeuSpaceInvaderEstVide() {
             assertEquals("" + 
             "...............\n" + 
             "...............\n" +
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test
    	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {
        	try {
    		SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
    		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14,10), 1);
        	 } catch (final HorsEspaceJeuException e) {
        	 }
    	}
        
        @Test
        public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
             try {
                 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
                 fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
             } catch (final HorsEspaceJeuException e) {
             }


             try {
                 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
                 fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
             } catch (final HorsEspaceJeuException e) {
             }


             try {
                 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
                 fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
             } catch (final HorsEspaceJeuException e) {
             }


             try {
                 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
                 fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
             } catch (final HorsEspaceJeuException e) {
             }

         }
        
        @Test
    	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
    		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7,9), 1);
    		assertEquals("" + 
    		"...............\n" + 
    		"...............\n" +
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		".......VVV.....\n" + 
    		".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    	}
        
        @Test
    	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
    		
    		try {
    			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9, 2), new Position(7, 9), 1);
    			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
    		} catch (final DebordementEspaceJeuException e) {
    		}
    		
    		
    		try {
    			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 4), new Position(7, 1), 1);
    			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
    		} catch (final DebordementEspaceJeuException e) {
    		}
    			
    	}
        
        @Test
    	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
    		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7,9), 1);
    		assertEquals("" + 
    		"...............\n" + 
    		"...............\n" +
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		"...............\n" + 
    		".......VVV.....\n" + 
    		".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    	}
        
        public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
            spaceinvaders.deplacerSpriteVersLaDroite(this.spaceinvaders.recupererVaisseau());
            assertEquals("" + 
            "...............\n" + 
            "...............\n" +
            "...............\n" + 
            "...............\n" + 
            "...............\n" + 
            "...............\n" + 
            "...............\n" + 
            "...............\n" + 
            "..........VVV..\n" + 
            "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test
        public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

        	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
        	spaceinvaders.deplacerSpriteVersLaDroite(this.spaceinvaders.recupererVaisseau());
           assertEquals("" + 
           "...............\n" + 
           "...............\n" +
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "............VVV\n" + 
           "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test
        public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

           spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
           spaceinvaders.deplacerSpriteVersLaDroite(this.spaceinvaders.recupererVaisseau());
           assertEquals("" + 
           "...............\n" + 
           "...............\n" +
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "............VVV\n" + 
           "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test
        public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

           spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
           spaceinvaders.deplacerSpriteVersLaGauche(this.spaceinvaders.recupererVaisseau());

           assertEquals("" + 
           "...............\n" + 
           "...............\n" +
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "....VVV........\n" + 
           "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
       }
        
        @Test
        public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

    	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
           spaceinvaders.deplacerSpriteVersLaGauche(this.spaceinvaders.recupererVaisseau());

           assertEquals("" + 
           "...............\n" + 
           "...............\n" +
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "VVV............\n" + 
           "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
         }
        
        @Test
        public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

           spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
           spaceinvaders.deplacerSpriteVersLaGauche(this.spaceinvaders.recupererVaisseau());

           assertEquals("" + 
           "...............\n" + 
           "...............\n" +
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "...............\n" + 
           "VVV............\n" + 
           "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
         }
        
        @Test
        public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

        	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
        	try {
        		spaceinvaders.tirerMissileVaisseau(new Dimension(3,2),2);
        	} catch (spaceinvaders.utils.MissileException e) {
        		e.printStackTrace();
        	}

          assertEquals("" + 
          "...............\n" + 
          "...............\n" +
          "...............\n" + 
          "...............\n" + 
          "...............\n" + 
          "...............\n" + 
          ".......MMM.....\n" + 
          ".......MMM.....\n" + 
          ".....VVVVVVV...\n" + 
          ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
       }
        
        @Test(expected = MissileException.class)
    	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
    	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
    	   spaceinvaders.tirerMissileVaisseau(new Dimension(7,9),1);
    	}
        
        @Test
        public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

     	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
     	   spaceinvaders.tirerMissileVaisseau(new Dimension(3,2),1);
     	   for (int i = 1; i <8 ; i++) {
     		   spaceinvaders.deplacerMissile();
     	   }
     	   
     	   spaceinvaders.deplacerMissile();
     	   
            assertEquals("" +
            "...............\n" + 
            "...............\n" +
            "...............\n" + 
            "...............\n" +
            "...............\n" +
            "...............\n" + 
            "...............\n" +
            "...............\n" + 
            ".....VVVVVVV...\n" + 
            ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test
    	public void test_Envahisseur_DeplacerEnvahisseurVersLaGauche() {
    		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(3,9), 1);
            spaceinvaders.deplacerSpriteVersLaGauche(this.spaceinvaders.recupererEnvahisseurs().get(0));

             assertEquals("" + 
             "...............\n" + 
             "...............\n" +
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "..EE...........\n" + 
             "..EE...........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
           }
        
        @Test
    	public void test_Envahisseur_DeplacerEnvahisseurVersLaDroite() {
    		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(3,9), 1);
    		spaceinvaders.deplacerSpriteVersLaDroite(this.spaceinvaders.recupererEnvahisseurs().get(0));

             assertEquals("" + 
             "...............\n" + 
             "...............\n" +
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "...............\n" + 
             "....EE.........\n" + 
             "....EE.........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
           }
        
        @Test
    	public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
    		
    		try {
    			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(9, 2), new Position(7, 9), 1);
    			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
    		} catch (final DebordementEspaceJeuException e) {
    		}
    		
    		
    		try {
    			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 4), new Position(7, 1), 1);
    			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
    		} catch (final DebordementEspaceJeuException e) {
    		}
    			
    	}
        
        @Test 
        public void test_PlusieursEnvahisseursPositionneDansEspaceJeu() {
        	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(3,9), 1);
        	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(7,2), 1);
        	
        	 assertEquals("" + 
                     "...............\n" + 
                     ".......EE......\n" +
                     ".......EE......\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...EE..........\n" + 
                     "...EE..........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        @Test 
        public void test_PlusieursEnvahisseursDeplacementADroiteDansEspaceJeu() {
        	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(3,9), 1);
        	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(7,2), 1);
        	
        	spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererEnvahisseurs().get(0));
        	spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererEnvahisseurs().get(1));
        	
        	 assertEquals("" + 
                     "...............\n" + 
                     "........EE.....\n" +
                     "........EE.....\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "...............\n" + 
                     "....EE.........\n" + 
                     "....EE.........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
        //test collision missile
        
    }