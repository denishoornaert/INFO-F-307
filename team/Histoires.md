# Histoires
Informations récapitulatives concernant les différentes histoires.

#### Quelques précisions
Un point correspond à une heure de travail (approximatif).  Par itération il faut accomplir 70 
points.


----------------------


## Pondération
| Priorité/3 | N° | Description | Difficulté/10 | Risque/3 | Heures/? | Points |
| ------ | ------ | ------ | ------ | ------ | ------ | ------ |
| 1 | [1](#repérage-p-sur-carte) | Repérage P. sur carte | 7 | 2 | 49 | / |
|   | [5](#login-et-e-mail) | Login et e-mail | 6 | 1 | 42 |  |
| 2 | [2](#recherche-et-filtre) | Recherche et filtre | 5 | 1 | 35 |  |
|   | [3](#géolocalisation-et-proximité) | Géolocalisation et proximité | 5 | 2 | 35 |  |
|   | [6](#réputation) | Réputation | 4 | 1 | 28 | / |
|   | 10 | Synchronisation et gestion de conflits | 7 | 3 | 49 |  |
| 3 | 4 | Intégration avec Maps | 5 | 3 | 35 | / |
|   | 7 | Tuto | 7 | 1 | 49 |  |
|   | 8 | Application mobile | 8 | 3 | 56 |  |
|   | 9 | Partage sur réseaux sociaux | 2 | 1 | 14 |  |
|   | 11 | P-dex | 4 | 1 | 28 |  |


----------------------


## Description


### Repérage P. sur carte
**Instructions originales:**           
- Afficher des "épingle" montrant la position des P.
- Sélectionner une épingle pour afficher la date et l'heure où ce P. a été repéré.
- Ajouter un P. sur la carte (via point-and-clic)
- Possibilité de changer la date et heure de repérage du P. lors de l'ajout (ci-dessus)
- Possibilité de Zoom/Dezoom
- Les épingles les plus proches (qui se superposent) devront être fusionnée et n'indiquer que le
nombre de P. représenté par cette épingle (fusionnée)

**Tâches en plus:**          
- Système permettant de stoquer les P. sur la carte

:question: **Question:**       
- Que se passe t'il lorsqu'on clic sur une épingle "fusionnée" (qui regroupe d'autres épingle) ?
    - On affiche tous les pokemons de cette épingle (possiblement grand :confused:)
    - On ne fait rien (comme s'il n'y avait rien)
    - On zoom pour montrer les différentes épingles
    - Autre ?


### Recherche et filtre
**Instructions originales:**           
- `10h` Rechercher des P. sur la carte actuelle en fonction de leur nom 
- `6h` Limité l'affichage des P. sur la carte en fonction de la recherche 
- `10h` Possibilité de recherche via 2 critères (ou plus) (par exemple: "feu qui ne peut pas voler") 
- `20h` Sauvegarde des recherches
    - (question) Permettre d'utilisé les recherches sauvegardés

##### Total: 46h


:question: **Question:**       
- La sauvegarde de recherche est-elle commune à tous les comptes ?  Si non, comment faire pour 
les visiteurs ?


### Géolocalisation et proximité
**Instructions originales:**           
- Afficher les P. se trouvant dans un rayon (100 m) autour de la position de l'utilisateur
    -> Comment afficher ?  Les heures vont dépendre de ça
- (4h) Géolocalisation (récupération de la position du joueur)
- (4h) Se positionner manuellement sur la carte (point-and-clic)
- (4h) Choisir le rayon parmis une liste donnée


### Intégration avec Maps
**Instructions originales:**           
- Intégration de cartes réelles fournies par systèmes existants (comme Google Maps ou OpenStreetMaps)
- Affichage du chemin le plus court menant à un ou plusieurs P

Liens utiles:
- [Google Maps API](https://developers.google.com/maps/-)
- [Google Maps markers](https://developers.google.com/maps/documentation/android-api/marker)
- [Google Maps Android API Samples](https://github.com/googlemaps/android-samples)
- [OpenStreetMaps API](http://wiki.openstreetmap.org/wiki/API_v0.6)


### Login et e-mail
**Instructions originales:**           
- Système pour afficher et accepter les conditions conditions du service définies par 
l'administrateur du système
- Indiquer ses informations personnels
    - Nom d'utilisateur
    - Adresse email
    - Mot de passe
- Système de vérification d'email (en envoyant un mail (ce mail contiendra un lien faisant 
référence à un token permettant de valider l'email de l'utilisateur))
- Permettre de modifier les informations de son profil
- Permettre de changer son adresse email mais obligation de refaire une vérification
- Possibilité de se déconnecter
                 
**Sous Tâches**                
- `10h` Envoie d'email de confirmation de compte (token dans la base de donnée)
- `15h` Mettre à jour les informations personnel
- `30h` Serveur d'authentification (API REST)
- `5h` Interface de login
- `5h` Message d'information (pas de connexion, perte de connexion)       
_Si tout intégéré au serveur_          
- `20h` Refactoring du client afin de tout mettre en ligne


:question: **Question:**       
- Que faire si l'utilisateur change d'adresse email sans confirmer ?  Le compte est utilisable ?
Avec quelle adresse ?
- Manière d'interface de login
- Quels accès a-t-on quand on est pas connecté ?


### Réputation
**Instructions originales:**           
- MAKE Afficher les P. sur la cartes et le moment où ils ont été signalé (normalement déjà fait au point 1)
    - Question par rapport au point 1
- MAKE Afficher les caractéristiques des P.
    - Point de vie
    - Attaque
    - Défense
- `10h` Possibilité de modifier les informations d'un P. **qu'on a signalé** (uniquement).
- MAKE Système de réputation du signalement (indisponible pour celui qui a créé le signalement).  Système
de réputation simple: `+1` et `-1` (simplement)

**Suggestion d'affichage:**               
- La couleur de l'épingle change en fonction de la réputation (soit devient plus transparent, soit
change de couleur (vert ?))


### Tuto
**Instructions originales:**           
- Aide sur l'utilisation de l'application (texte explicatif)
- Possibilité de lancé une demo plus interactif (montrant des fonctionnalités spécifique).
          
Lien utiles:
- https://github.com/amlcurran/ShowcaseView
- https://github.com/worker8/TourGuide


### Application mobile
**Instructions originales:**           
- Portabilité sur Android
           

### Partage sur réseaux sociaux


### Synchronisation et gestion de conflits
**Instructions originales:**           
- (15h) Intégration des cartes sur le serveur 
- Modification de la carte en étant hors ligne
    - Dépend de l'architecture déjà présente
- (6h) Envoyer une carte au serveur
- Résoudre les conflits (transparent)
    - Qu'est-ce qu'un conflit pour le client (même heure, même coordonnées)
    - Comment choisir lequelle gardé si conflit



### P-dex


