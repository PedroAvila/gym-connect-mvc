$(document).ready(function () {
    console.log('Pedro estamos en el js datatable');

    $('#exampleGym').DataTable({
        debugger: true,
        ajax: {
            url: "/gyms/listData",
            dataSrc: "data" // Especificamos "data" para que DataTables use la clave "data" del JSON
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
