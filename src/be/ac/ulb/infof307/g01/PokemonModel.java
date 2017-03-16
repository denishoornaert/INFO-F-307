/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author remy
 */
public class PokemonModel {
    private final int _typeNumberMax = 2; // TODO : 
    private final String _name;
    private final PokemonTypeModel[] _type;
    private final String _pathImage;
    private final int _lifePoint,_attack,_defence,_specialAttack,_specialDefence,_speed;
    
    
    public PokemonModel(String name, PokemonTypeModel type, int lifePoint, int attack, int defence, int specialAttack, int specialDefence, int speed) {
        this(name, type, PokemonTypeModel.NONE, lifePoint, attack, defence, specialAttack, specialDefence, speed);
    }
    
    public PokemonModel(String name, PokemonTypeModel type1, PokemonTypeModel type2, int lifePoint, int attack, int defence, int specialAttack, int specialDefence, int speed) {
        this(name, new PokemonTypeModel[]{type1, type2}, lifePoint, attack, defence, specialAttack, specialDefence, speed);
    }
    
    public PokemonModel(String name, PokemonTypeModel[] type_array, int lifePoint, int attack, int defence, int specialAttack, int specialDefence, int speed) {
        _name = name;
        _type = type_array;
        _pathImage = findCorrespondingImagePath();
        _lifePoint = lifePoint;
        _attack = attack;
        _defence = defence;
        _speed = speed;
        _specialAttack = specialAttack;
        _specialDefence = specialDefence;
    }
    
    
    private String findCorrespondingImagePath() {
        return "assets" + File.separator + "sprites" + File.separator + _name + ".png";
    }
    
    
    public String getName() {
        return _name;
    }
    
    public PokemonTypeModel[] getType() {
        return _type;
    }
    
    public String getPathImage() {
        return _pathImage;
    }
    
    public int getLifePoint() {
       return _lifePoint;
    }

    public int getAttack() {
        return _attack;
    }

    public int getDefence() {
        return _defence;
    }

    public int getSpecialAttack() {
        return _specialAttack;
    }

    public int getSpecialDefence() {
        return _specialDefence;
    }

    public int getSpeed() {
        return _speed;
    }
    
    /**
     * Test if two pokemon have same name and type
     * 
     * @param otherPokemon the pokemon which must be compared
     * @return True if it's the same pokemon
     */
    public boolean equals(PokemonModel otherPokemon) {
        return otherPokemon._name.equals(_name) && 
                Arrays.equals(otherPokemon._type, _type) &&
                otherPokemon._attack == _attack &&
                otherPokemon._defence == _defence &&
                otherPokemon._lifePoint == _lifePoint &&
                otherPokemon._specialAttack == _specialAttack &&
                otherPokemon._specialDefence == _specialDefence &&
                otherPokemon._speed == _speed;
    }
 
}
