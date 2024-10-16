document.addEventListener('DOMContentLoaded', () => {
    const propertyForm = document.getElementById('propertyForm');
    const propertyList = document.getElementById('propertyList');
    const apiUrl = '/properties';

    // Cargar propiedades del usuario cuando la página se carga
    loadUserProperties();

    // Manejar el envío del formulario para crear o actualizar una propiedad
    propertyForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const propertyId = document.getElementById('propertyId').value;
        const address = document.getElementById('address').value;
        const price = document.getElementById('price').value;
        const size = document.getElementById('size').value;
        const description = document.getElementById('description').value;

        // Validar el formulario
        if (!address || !price || !size || !description) {
            alert("Todos los campos son obligatorios!");
            return;
        }

        const username = localStorage.getItem('username'); // Obtener el username del almacenamiento local
        const propertyData = { 
            address, 
            price: parseInt(price), 
            size: parseInt(size), 
            description, 
            user: { username } // Incluir el username en el objeto user
        };

        // Imprimir el JSON en la consola
        console.log("Datos de propiedad antes de enviar:", propertyData);
        console.log("JSON a enviar:", JSON.stringify(propertyData, null, 2));

        if (!propertyId) {
            // Crear propiedad
            await createProperty(propertyData);
        } else {
            // Actualizar propiedad
            await updateProperty(propertyId, propertyData);
        }

        propertyForm.reset();
        loadUserProperties(); // Recargar propiedades después de guardar
    });

    // Función para cargar propiedades del usuario
    async function loadUserProperties() {
        try {
            const username = localStorage.getItem('username'); 
            const response = await fetch(`${apiUrl}?username=${username}`);
            if (!response.ok) {
                throw new Error('Error al cargar propiedades');
            }
            const properties = await response.json();
            displayProperties(properties);
        } catch (error) {
            console.error('Error:', error);
            alert('No se pudieron cargar las propiedades.');
        }
    }

    // Función para mostrar las propiedades en la tabla
    function displayProperties(properties) {
        propertyList.innerHTML = '';
        properties.forEach(property => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${property.id}</td>
                <td>${property.address}</td>
                <td>${property.price}</td>
                <td>${property.size}</td>
                <td>${property.description}</td>
                <td>
                    <button class="edit-button" data-id="${property.id}">Editar</button>
                    <button class="delete-button" data-id="${property.id}">Eliminar</button>
                </td>
            `;
            propertyList.appendChild(row);
        });

        // Asigna los eventos a los botones "Editar" y "Eliminar"
        document.querySelectorAll('.edit-button').forEach(button => {
            button.addEventListener('click', () => editProperty(button.getAttribute('data-id')));
        });

        document.querySelectorAll('.delete-button').forEach(button => {
            button.addEventListener('click', () => deleteProperty(button.getAttribute('data-id')));
        });
    }

    async function createProperty(propertyData) {
        try {
            const username = localStorage.getItem('username'); // Obtener el username del almacenamiento local
            const response = await fetch(`http://localhost:8080/properties?username=${username}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(propertyData)
            });
    
            if (!response.ok) {
                const errorDetails = await response.text(); // Captura el detalle del error
                throw new Error(`Error al crear propiedad: ${response.status} ${errorDetails}`);
            }
    
            const createdProperty = await response.json();
            console.log("Propiedad creada:", createdProperty);
        } catch (error) {
            console.error("Error al crear propiedad:", error);
        }
    }
    
    
    // Función para actualizar una propiedad
async function updateProperty(id, property) {
    try {
        const username = localStorage.getItem('username'); // Asegúrate de enviar el username al actualizar
        const propertyData = { user: { username }, ...property }; // Incluir el username en el objeto user

        const response = await fetch(`${apiUrl}/${id}?username=${username}`, { // Añadir username como parámetro de consulta
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(propertyData)
        });

        if (!response.ok) {
            throw new Error('Error al actualizar propiedad');
        }
        alert('Propiedad actualizada con éxito.');
        loadUserProperties(); // Recargar propiedades después de la actualización
    } catch (error) {
        console.error('Error al actualizar propiedad:', error);
        alert('No se pudo actualizar la propiedad.');
    }
}


    // Función para eliminar una propiedad
  // Función para eliminar una propiedad
async function deleteProperty(id) {
    try {
        const username = localStorage.getItem('username'); // Obtener el username si es necesario
        const response = await fetch(`${apiUrl}/${id}?username=${username}`, { // Añadir username como parámetro de consulta si es necesario
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Error al eliminar propiedad');
        }
        alert('Propiedad eliminada con éxito.');
        loadUserProperties(); // Recargar propiedades después de la eliminación
    } catch (error) {
        console.error('Error al eliminar propiedad:', error);
        alert('No se pudo eliminar la propiedad.');
    }
}


    // Función para editar una propiedad (cargar datos en el formulario)
    function editProperty(id) {
        const property = [...propertyList.children].find(row => row.cells[0].textContent === id);
        document.getElementById('propertyId').value = id;
        document.getElementById('address').value = property.cells[1].textContent;
        document.getElementById('price').value = property.cells[2].textContent;
        document.getElementById('size').value = property.cells[3].textContent;
        document.getElementById('description').value = property.cells[4].textContent;
        document.getElementById('formTitle').textContent = 'Editar Propiedad';
    }
});
