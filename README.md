# Space invaders
- [Semaine 1](#semaine1) 
- [Semaine 2](#semaine2) 
- [Semaine 3](#semaine3) 
- [Semaine 4](#semaine4) 

Fonctionalité 13 :x:

- [Glossaire](#glossaire) 

## Semaine 1 <a id="semaine 1"></a>
### Fonctinnalité 1 : Déplacer vaisseau dans espace de jeu :heavy_check_mark:
#### Story n°1 : Créer un espace de jeu 
#### Story n°2 : Positionner un nouveau vaisseau dans l'espace de jeu 
#### Story n°3 : Déplacer le vaisseau vers la droite dans l'espace de jeu 
#### Story n°4 : Déplacer le vaisseau vers la gauche dans l'espace de jeu 

### Fonctionnalité n°2 : Dimensionner le vaiseau :heavy_check_mark:
#### Etape n°1 : Positionner un nouveau vaisseau avec une dimension donnée 
#### Etape n°2 : Faire en sorte qu'il soit impossible de positionner un nouveau vaisseau qui déborde de l'espace de jeu 
#### Etape n°3 : Déplacer un vaisseau vers la droite en tenant compte de sa dimension 
#### Etape n°4 : Déplacer un vaisseau vers la gauche en tenant compte de sa dimension 
#### Refactoring

### Fonctionnalité n°3 : Choisir la vitesse du vaisseau :heavy_check_mark:

### :information_source: Diagramme de classe et nuage de mot:
#### (Fonctionnalité 1)
![Diagrammes de classes fonctionnalite 1](images/diagramme_fc1.png)
![Nuage de mots fonctionnalite 1](images/nuageMot_fc1.png)

------------- 

## Semaine 2 <a id="semaine 2"></a>
### Fonctionnalité n°4: Tirer un missile depuis le vaisseau :heavy_check_mark:
### Fonctionnalité n°5: Ajouter un envahisseur dans le jeu :heavy_check_mark:

### :information_source: Diagramme de classe et nuage de mot:
#### (Fonctionnalité 4)
![Nuage de mots fonctionnalite 1](images/nuageMot_fc4.png)

#### (Fonctionnalité 5)
![Diagrammes de classes fonctionnalite 5](images/diagramme_fc5.png)

------------- 

## Semaine 3 <a id="semaine 3"></a>
### Fonctionnalité n°6: Détecter une collision entre deux sprites :heavy_check_mark:
### Fonctionnalité n°7: Terminer la partie :heavy_check_mark:

### :information_source: Diagramme de classe et nuage de mot:
#### (Fonctionnalité 6)
![Diagrammes de classes fonctionnalite 6](images/diagramme_fc6.png)
![Nuage de mots fonctionnalite 6](images/nuageMot_fc6.png)

------------- 

## Semaine 4 <a id="semaine 4"></a>
### Fonctionnalité n°8 : Permettre au vaisseau de tirer plusieurs missiles :heavy_check_mark:
### Fonctionnalité n°9 : Envoyer une ligne d'envahisseurs :heavy_check_mark:
### Fonctionnalité n°10 : Gérer un score :heavy_check_mark:
### Fonctionnalité n°11 : Tirer un missile depuis un envahisseur de manière aléatoire :heavy_check_mark:
### Fonctionnalité n°12 : Envoyer une horde d'envahisseurs :heavy_check_mark:

### :information_source: Diagramme de classe et nuage de mot:
#### (Fonctionnalité 8)
![Diagrammes de classes fonctionnalite 8](images/diagramme_fc8.png)

#### (Fonctionnalité 12)
![Diagrammes de classes fonctionnalite 12](images/diagramme_fc12.png)
![Nuage de mots fonctionnalite 12](images/nuageMot_fc12.png)

------------- 

## Glossaire <a id="glossaire"></a>

:airplane: * **Vaisseau** : Véhicule héritant de spriteTireur. Il est controlé par le joueur et peut tirer des missiles.

:rocket: * **Missile Vaisseau** : Objet partant du vaisseau, pouvant détruire un envahisseur ou un missile de l'envahisseur lors de la collision.

:rocket: * **Missile Envahisseur** : Objet partant de l'envahisseur, pouvant détruire le vaisseau et faire perdre la partie. 

:space_invader: * **Envahisseur** : Ennemi tirant des missiles. Le but est de les détruires pour gagner le jeu. 

:dash: * **Vitesse** : Unité pour accelerer le deplacement des objets de type sprite. 

:boom: * **Collision** : Action lors de la rencontre de deux sprite.

* **Sprite** : Tout objet visible sur le terrain de jeu.

* **Sprite Tireur** : Hérite de sprite mais a la capacité de tirer des missiles.


------------- 
