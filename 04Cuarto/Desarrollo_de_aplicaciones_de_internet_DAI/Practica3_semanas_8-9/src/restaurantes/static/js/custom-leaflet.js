$(function() {
    var map = L.map('map').setView([37.183054, -3.6021928],13);
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.sudano.net">Sudano.net</a>',
        maxZoom: 18
    }).addTo(map);
});