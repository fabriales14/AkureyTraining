/*!
 * All Rights Reserved
 * This software is proprietary information of
 * Intelligent Sense
 * Use is subject to license terms.
 * Filename: main.js
 */

/*
 * Author:      falvarado@gmail.com
 * Date:        03/05/2019
 * Description: Contains the mustache templates, AJAX and promises functions.
 */

var companiesList;
var tmpl = document.getElementById("chuckTmpl").innerHTML;

var companiesJSON = new Promise(
    function (resolve, reject) {
        var xhhtp = new XMLHttpRequest();
        xhhtp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                resolve(xhhtp.response);
            }
        };
        xhhtp.open("GET", "https://api.myjson.com/bins/uptto", true);
        xhhtp.send();
    }
)

var getCompanies = function () {
    companiesJSON.then(function (companies) {
        companiesList = JSON.parse(companies)
        var html = Mustache.to_html(tmpl, JSON.parse(companies));
        var box = document.getElementById("box");
        box.innerHTML = html;
    }).catch(function (error) {
        console.log(error);
    })
}

function filterCompanies() {
    pName = document.getElementById("search_input").value;
    companiesListAux = { "companies": [] };
    companiesListAux.companies = companiesList.companies.filter(elem => elem.name.toLowerCase().includes(pName.toLowerCase()));
    var html = Mustache.to_html(tmpl, companiesListAux);
    var box = document.getElementById("box");
    box.innerHTML = html;
}

function handler(e) {
    if (e.keyCode === 13) {
        filterCompanies();
    }
}

window.onload = getCompanies;