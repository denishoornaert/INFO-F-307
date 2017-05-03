# Gotta Map'Em All : Projet de génie logiciel et gestion de projet (INFO-F-307)

Le projet a pour but de permettre à un utilisateur de l'application "Pokemon Go" de signaler aux autres utilisateurs les pokémons qu'il rencontre. Le programme se veut fonctionnel sur les trois OS les plus connus/utilisés (Linux, Windows et MacOS).

## Dépendances

Pour *fonctionner* (à partir d'un `.jar` déjà compilé), le projet a besoin des librairies suivantes :

 - [Java Runtime Environment 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html), pour exécuter le code Java
 - [SQLite 3](https://sqlite.org/download.html), pour gérer la base de données
 
Pour *compiler*, il faut également disposer de :

 - [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), pour compiler le code Java
 - [Maven 3](https://maven.apache.org/download.cgi), permettant de gérer la structure et les dépendances du code 

## Compilation

Une version du code source déjà compilée et compatible avec les plate-formes visées se trouve dans le dossier `dist/` du projet.

Afin de compiler le code source, exécuter la commande suivante à la racine du projet :  
```mvn compile```

## Démarrage 

### Serveur

Le déploiement du serveur n'est pas encore testé.

### Client

Pour lancer l'exécution du programme côté client, il suffit d'exécuter :  
```java -jar Groupe01.jar```

## License

[THE BEER-WARE LICENSE (Revision 42)](https://people.freebsd.org/~phk/)
