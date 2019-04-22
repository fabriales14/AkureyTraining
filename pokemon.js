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


/**
* Initializes the module pokemon.
* @public
*/
function Pokemon(pName, pTipe, pAttack, pDefense){
    this.name = pName;
    this.tipe = pTipe;
    this.attack = pAttack;
    this.defense = pDefense;

    /**
     * This function returns the effectiveness of the damage,
     * based on the pokemon's attack type.
     */ 
    this.getEffectiveness = function(pTipe) {
        if (pTipe == this.tipe)
            return 0.5;
        else {
            if (pTipe == "fire"){
                if (this.tipe == "grass")
                    return 0.5;
                else if (this.tipe == "electric")
                    return 1;
                else
                    return 2;
            } else if (pTipe == "grass") {
                if (this.tipe == "fire")
                    return 2;
                else if (this.tipe == "electric")
                    return 1;
                else
                    return 0.5;
            } else {
                if (this.tipe == "fire")
                    return 0.5;
                else if (this.tipe == "electric")
                    return 2;
                else
                    return 2;
            }
        }
    };
}

/**
 * Function damage declaration. 50 * (attack/defense) * effectiveness
 */ 
var damage = function(pPokemon1, pPokemon2){
    var effectiveness = pPokemon1.getEffectiveness(pPokemon2.tipe);

    return Math.round(50 * (pPokemon1.attack / pPokemon2.defense) * effectiveness);
}

pokemon1 = new Pokemon("1","fire",25,56);
pokemon2 = new Pokemon("2","electric",31,47);

console.log(damage(pokemon1,pokemon2));