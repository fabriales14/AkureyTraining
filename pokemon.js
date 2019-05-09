/*!
 * All Rights Reserved
 * This software is proprietary information of
 * Intelligent Sense
 * Use is subject to license terms.
 * Filename: pokemon.js
 */

 /*
  * Author:      falvarado@akurey.com / Fabricio Alvarado Esquivel
  * Date:        22/04/2019
  * Description: Pokemon damage assignment, using JavaScript.
  */


var rules = {
    fire : {
        "grass" : 2,
        "water" : 0.5,
        "electric" : 1
    },
    water : {
        "fire" : 2,
        "grass" : 0.5,
        "electric" : 0.5
    },
    grass : {
        "fire" : 0.5,
        "electric" : 1
    },
    electric : {
        "grass" : 1,
        "water" : 2,
        "fire" : 1
    }
}

/**
* Initializes the module pokemon.
* @public
*/
function Pokemon(pName, pType, pAttack, pDefense){
    this.name = pName;
    this.type = pType;
    this.attack = pAttack;
    this.defense = pDefense;
}

/**
 * Function damage declaration. 50 * (attack/defense) * effectiveness
 */ 
var damage = function(pPokemon1, pPokemon2){
    var effectiveness;
    const equalType = 0.5;
    if (pPokemon1.type == pPokemon2.type)
        ffectiveness = equalType;
    else
        effectiveness = rules[pPokemon1.type][pPokemon2.type];
    return Math.round(50 * (pPokemon1.attack / pPokemon2.defense) * effectiveness);
}

pokemon1 = new Pokemon("1","fire",25,56);
pokemon2 = new Pokemon("2","electric",31,47);

console.log(damage(pokemon1,pokemon2));