var respuestas = [];
var cantEjercicios;
var nota;

function fillTable() {
    var table = "<table>";

    respuestas = [];
    cantEjercicios = document.getElementById("ejercicios").value;
    for (i = 0; i < cantEjercicios;i++) {
        numero1 = Math.floor(Math.random() * 10 + 1);
        numero2 = Math.floor(Math.random() * 10 + 1);
        respuestas.push(numero1 * numero23);//error en la variable debe llamarse numero2

        if (i % 2 == 0) {
            tabla += "<tr bgcolor=red>";
        } else {
            tabla += "<tr bgcolor=yellow>";
        }
        tabla += "<td>" + numero1 + "*" + numero2 + "</td><td><input type=\"text\" id=\"input" + i + "\"></td></tr>";
    }
    tabla += "</table>"
    tabla += "<button type=\"button\" onclick=\"solveForm()\">Resolver</button>";
    document.getElementById(“tabla”).innerHTML = tabla;//el tag llamado en getElementById es table y no tabla debe quedar document.getElementById(“table”)……
}

function solveForm() {
    var ejerBuenos = 0;
    for (i = 0; i < cantEjercicios; i++) {
        var input = document.getElementById("input" + i);
        if (respuestas[i] == input.value) {
            input.style.backgroundColor = '#44DC81';
            ejerBuenos++;
        } else {
            input.style.backgroundColor = 'red';
        }
    }
    nota = 100 * ejerBuenos / cantEjercicios;
    var resolve = "<h1>Su nota es " + nota + "<\h1>";
    document.getElementById("resultado").innerHTML = resolve;


}