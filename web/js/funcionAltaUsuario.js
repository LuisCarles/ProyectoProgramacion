/* 
 Codigo basado de el Ing William
 */


function coincidirContrasena() {

    var txtContrasena = document.getElementById("passwordRegistro");
    var txtRepetirContrasena = document.getElementById("passwordRepRegistro");
    var avisoContrasena = document.getElementById("avisoContrasena");
    var btnEnviarDatos = document.getElementById("btnRegistrar");

   btnEnviarDatos.disabled = false; //Boton en desactivado o apagado

    if (txtContrasena.value.length == 0 || txtRepetirContrasena.value.length == 0) {
        avisoContrasena.innerHTML = "Ninguna de las contraseñas pueden quedar vacias";
        avisoContrasena.style.color = "blue";
        btnEnviarDatos.disabled = true;

    } else if (txtContrasena.value !== txtRepetirContrasena.value) {
        avisoContrasena.innerHTML = "Contraseñas son erroneas por que no coinciden";
        avisoContrasena.style.color = "red";
        btnEnviarDatos.disabled = true;

    } else {
        avisoContrasena.innerHTML = "Las contraseñas coinciden";
        avisoContrasena.style.color = "green";
        btnEnviarDatos.disabled = false;
    }


}
