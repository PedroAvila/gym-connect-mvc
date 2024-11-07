// async function loadFragmentAndData() {
//     try {
//         // Llamada para obtener el fragmento HTML
//         const fragmentResponse = await fetch('/gyms/gymFragment');
//         const fragmentHtml = await fragmentResponse.text();

//         // Inserta el fragmento en el DOM
//         document.getElementById('output').innerHTML = fragmentHtml;

//         // Llama al endpoint que devuelve el JSON
//         const response = await fetch('/gyms/listData');
//         const data = await response.json();
//         console.log('Estamos en el fragmento');
//         // Una vez que tienes el JSON, puedes poblar los datos en el fragmento
//         const gyms = data.gyms;
//         const gymContainer = document.getElementById('output');

//         // gyms.forEach(gym => {
//         //     const gymElement = document.createElement('div');
//         //     gymElement.textContent = `Nombre: ${gym.name}, Ubicación: ${gym.location}`;
//         //     gymContainer.appendChild(gymElement);
//         // });
//     } catch (error) {
//         console.error('Error al cargar el fragmento o los datos:', error);
//     }
// }

// // Ejecuta la función para cargar el fragmento y los datos al cargar la página
// document.addEventListener('DOMContentLoaded', loadFragmentAndData);

document.addEventListener('DOMContentLoaded', () => {
    console.log("El DOM está listo, iniciando loadFragmentAndData...");

});
