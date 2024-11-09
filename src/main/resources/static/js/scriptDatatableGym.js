$(document).ready(function () {
    console.log('Pedro estamos en el js datatable');
    preload();

    $('#exampleGym').DataTable({
        // debugger: true,
        ajax: {
            url: "/gyms/listData",
            dataSrc: "data", // Especificamos "data" para que DataTables use la clave "data" del JSON 
        },
        columns: [
            { data: "id", visible: false },
            { data: "code" },
            { data: "name" },
            { data: "address" },
            { data: "phone" },
            { data: "creationDate" }
        ],
    });
});


// function preload() {
//     var preloadFalse = $(".preloadFalse");
//     var preloadTrue = $(".preloadTrue");

//     if (preloadFalse.length > 0) {
//         preloadFalse.each(function (i) {
//             var el = $(this);

//             $(el).ready(function () {
//                 $(preloadTrue[i]).delay(3000).fadeOut();
//                 $(el).animate({ "opacity": 1, "height": "auto" }, 3000);

//                 setTimeout(() => {
//                     $(preloadTrue[i]).remove();
//                 }, 3000)
//             })
//         })
//     }
// }

function preload() {
    var preloadFalse = $(".preloadFalse"); // Elementos que se mostrarán después de la carga
    var preloadTrue = $(".preloadTrue");   // Elementos de animación de carga

    if (preloadFalse.length > 0) {
        preloadFalse.css("opacity", 0); // Asegúrate de ocultar los elementos de datos al inicio
        preloadTrue.show(); // Mostrar el elemento de carga

        // Simula la carga de datos
        setTimeout(function () {
            // Ocultar la animación de carga con desvanecimiento y luego mostrar los datos
            preloadTrue.fadeOut(500, function () {
                preloadFalse.each(function (i, el) {
                    $(el).animate({ "opacity": 1, "height": "auto" }, 0); // Muestra los datos de forma gradual
                });
            });
        }, 3000); // Cambia este valor según el tiempo que quieras mantener la animación de carga
    }
}