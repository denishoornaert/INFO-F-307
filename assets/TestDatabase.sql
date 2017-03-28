BEGIN TRANSACTION;

-- Create table
CREATE TABLE "PokemonType" (
	`Id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Name`	TEXT UNIQUE
);

CREATE TABLE "PokemonPokemonTypeLink" (
	`TypeId`	INTEGER NOT NULL,
	`PokemonId`	INTEGER NOT NULL,
	PRIMARY KEY(`TypeId`,`PokemonId`)
);

CREATE TABLE `Pokemon` (
	`Id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL UNIQUE,
	`ImagePath`	TEXT NOT NULL
);

CREATE TABLE "Marker" (
	`Id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`PokemonId`	INTEGER NOT NULL,
	`X`	INTEGER NOT NULL,
	`Y`	INTEGER NOT NULL,
	`TimeStamp`	TEXT NOT NULL,
	FOREIGN KEY(`PokemonId`) REFERENCES Pokemon
);


-- Insert into PokemonType
INSERT INTO `PokemonType` VALUES (1,'Electrik');
INSERT INTO `PokemonType` VALUES (2,'Water');

-- Insert Pokemon
INSERT INTO `Pokemon` VALUES (1,'Pikachu','path to pikachu');
INSERT INTO `Pokemon` VALUES (2,'Mystherbe','path to Mystherbe');

-- Insert a link between Pikachu and Electrik
INSERT INTO `PokemonPokemonTypeLink` VALUES (1,1);

COMMIT;
