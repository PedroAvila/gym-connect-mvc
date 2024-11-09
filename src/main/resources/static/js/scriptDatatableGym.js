$(document).ready(function () {
    console.log('Pedro estamos en el js datatable');
    preload();

    $('#exampleGym').DataTable({
        debugger: true,
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


function preload() {
    var preloadFalse = $(".preloadFalse");
    var preloadTrue = $(".preloadTrue");

    if (preloadFalse.length > 0) {
        preloadFalse.each(function (i) {
            var el = $(this);

            $(el).ready(function () {
                $(preloadTrue[i]).delay(3000).fadeOut();
                $(el).animate({ "opacity": 1, "height": "auto" }, 3000);

                setTimeout(() => {
                    $(preloadTrue[i]).remove();
                }, 3000)
            })
        })
    }
}