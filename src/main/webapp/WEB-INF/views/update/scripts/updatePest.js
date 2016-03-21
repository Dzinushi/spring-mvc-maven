var allPoisons;
$.get("http://localhost:8080/spring-mvc-maven/update/updatePest/", function(data, status) {
    allPoisons = data.allPoisons;
    $.each(allPoisons, function (i, item) {
        allPoisons.append(item);
    });
});


//     function createTable() {
//     var myTable = document.getElementById("pestPoisonTable");
//     var table = document.createElement("table");
//     var tableBody = document.createElement("tbody");
//     // Формирование шапки таблицы
//
//     for (var j = 0; j < ${pest.poisonDTOs.size()}; ++j){
//         var row = document.createElement("tr");
//         var cell = document.createElement("td");
//         var pestPoisonName = ${pest.poisonDTOs.get(j).name};
//
//         cell.innerHTML = "<select name='poisonNames'>" +
//             "<c:forEach items='${allPoisons}' var='poison'>" +
//             "<option value='${poison.name}' ${pestPoison.name == poison.name ? 'selected = "selected"' : ''}'>${poison.name}</option>" +
//         "</c:forEach>" +
//         "</select>";
//         row.appendChild(cell);
//         tableBody.appendChild(row);
//         break;
//     }
//
//     table.appendChild(tableBody);
//     table.setAttribute("border", "1");
//     myTable.appendChild(table);
// }
// function deleteLink(poisonId) {
//     var row = document.getElementById(poisonId);
//     row.parentNode.removeChild(row);
// }
// function addLink(){
//     var table = document.getElementById("pestPoisonTable");
//     var row = table.insertRow();
//     row.id = ${allPoisons.size() != 0 ? allPoisons.get(0).id : -1};
//     var cell0 = row.insertCell(0);
//     var cell1 = row.insertCell(1);
//     cell0.innerHTML = "<select name='poisonNames'>" +
//         "<c:forEach items='${allPoisons}' var='poison'>" +
//         "<option value='${poison.name}'>${poison.name}</option>" +
//         "</c:forEach>" +
//         "</select>";
//     cell1.innerHTML = "<input type='button' value='Удалить' onclick='deleteLink(${allPoisons.size() != 0 ? allPoisons.get(0).id : -1})'>";
//     table.appendChild(row);
// }
// function changePoison(poisonId){
//
// }
