"""
Ce code lit le fichier pokemonList.txt et genère du code SQL à copier dans
Database.sql, afin de générer tous les pokémons et leurs types.
"""

with open("pokemonList.txt") as file:
    for line in file:
        line = line.strip()
        if line != "":
            name, types = line.split(":")
            types = types.split()
            print('("{}", "sprites/{}.png", (SELECT `Id` FROM PokemonType WHERE `Name`="{}"), {}),'.format(name, name.lower(), types[0],
                ("NULL" if len(types) == 1 else '(SELECT `Id` FROM PokemonType WHERE `Name`="{}")'.format(types[1]))))


