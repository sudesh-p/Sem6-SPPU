$(document).ready(() => {
    setInterval(getData, 1000)
});
let idToUpdate = 0
function getData() {
    let ajax = new XMLHttpRequest()
    ajax.open("GET", "getAllRecipes.php", true)
    ajax.send()
    ajax.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let data = JSON.parse(this.responseText)
            let html = ""
            for(let i = 0; i < data.length; i++) {
                let id = data[i].id;
                let name = data[i].recipe_name
                let int = data[i].recipe_int
                let proc = data[i].recipe_proc

                html += '<div class="recipe">'
                html += '<div class="side-button">'
                html += ' <button onclick="deleteRecipe('+ id +')">Delete</button>'
                html += ' <button onclick="startUpdatingRecipe(' + id + ')">Update</button>'
                html += '</div>'
                html += '<h2>'+ name + '</h2>';
                html += '<h4>Ingredients: </h4>';
                html += '<p>' + int + '</p>';
                html += '<h4>Procedure: </h4>';
                html += '<p>' + proc + '</p>';
                html += '</div>';

                document.getElementById("recipes").innerHTML = html
            }
        }
    }
}

let modal = document.getElementById("myModal");
let modal_up = document.getElementById("myModal-update");
let addBtn = document.getElementById("addBtn");
let span = document.getElementById("close");
let spanUp = document.getElementById("close-up");

addBtn.onclick = () => {
    modal.style.display = "block";
}

span.onclick = () => {
    modal.style.display = "none";
}

spanUp.onclick = () => {
    modal_up.style.display = "none";
}

window.onclick = (event) => {
    if (event.target === modal && event.target === modal_up) {
        modal.style.display = "none";
    }
}

function addTheRecipe() {
    let nameOfRecipe = $('#nameOfRecipe')
    let intOfRecipe = $('#intOfRecipe')
    let procOfRecipe = $('#procOfRecipe')

    let name = nameOfRecipe.val()
    let ingredients = intOfRecipe.val()
    let procedure = procOfRecipe.val()

    let reply = $.ajax({
        url : "addrecipe.php",
        type: "POST",
        cache: false,
        data: {"name":name, "int": ingredients, "proc": procedure}
    })

    if(reply) {
        alert("Recipe is added Successfully !!!")
        document.getElementById('nameOfRecipe').value = ''
        document.getElementById('intOfRecipe').value = ''
        document.getElementById('procOfRecipe').value = ''
    }
    console.log(name, ingredients, procedure)
}

function deleteRecipe(id) {
    let reply = $.ajax({
        url : "deleterecipe.php",
        type: "POST",
        cache: false,
        data: {"id":id}
    })

    if(reply) {
        alert("Recipe is deleted Successfully !!!")
    }
    console.log('delete' , id)
}

function startUpdatingRecipe(id) {
    modal_up.style.display = "block"
    $.ajax({
        url: 'getOneRecipeById.php',
        type: "POST",
        dataType: 'json',
        data: {"id": id},
        cache: false
    }).done(
        function(data){
            document.getElementById("nameOfRecipe-update").value = data[0]
            document.getElementById("intOfRecipe-update").value = data[1]
            document.getElementById("procOfRecipe-update").value = data[2]
            idToUpdate = id
        }
    );
}

function updateRecipe() {
    let nameOfRecipe = $('#nameOfRecipe-update')
    let intOfRecipe = $('#intOfRecipe-update')
    let procOfRecipe = $('#procOfRecipe-update')

    let name = nameOfRecipe.val()
    let ingredients = intOfRecipe.val()
    let procedure = procOfRecipe.val()

    console.log(idToUpdate, name, ingredients, procedure)

    let reply = $.ajax({
        url : "updaterecipe.php",
        type: "POST",
        cache: false,
        data: {"id": idToUpdate, "name":name, "int": ingredients, "proc": procedure}
    })

    if(reply) {
        alert("Recipe of '" + name + "' is updated Successfully !!!")
        document.getElementById('nameOfRecipe-update').value = ''
        document.getElementById('intOfRecipe-update').value = ''
        document.getElementById('procOfRecipe-update').value = ''
    }
    console.log(name, ingredients, procedure)
}
