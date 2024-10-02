document.addEventListener('DOMContentLoaded', () => {
    const propertyForm = document.getElementById('propertyForm');
    const propertyList = document.getElementById('propertyList');
    const apiUrl = '/properties'; // Define tu URL base para la API

    // Cargar todas las propiedades cuando la página se carga
    loadProperties();

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
            alert("All fields are required!");
            return;
        }

        const propertyData = { address, price, size, description };

        if (!propertyId) {
            // Crear propiedad
            await createProperty(propertyData);
        } else {
            // Actualizar propiedad
            await updateProperty(propertyId, propertyData);
        }

        propertyForm.reset();
        loadProperties(); // Recargar propiedades después de guardar
    });

    // Función para cargar todas las propiedades
    function loadProperties() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(properties => displayProperties(properties))
            .catch(error => console.error('Error al cargar propiedades:', error));
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
                    <button class="edit-button" data-id="${property.id}">Edit</button>
                    <button class="delete-button" data-id="${property.id}">Delete</button>
                </td>
            `;
            propertyList.appendChild(row);
        });

        // Asigna los eventos a los botones "Edit" y "Delete"
        document.querySelectorAll('.edit-button').forEach(button => {
            button.addEventListener('click', () => editProperty(button.getAttribute('data-id')));
        });

        document.querySelectorAll('.delete-button').forEach(button => {
            button.addEventListener('click', () => deleteProperty(button.getAttribute('data-id')));
        });
    }

    // Función para crear una nueva propiedad
    async function createProperty(property) {
        await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(property)
        })
        .catch(error => console.error('Error al crear propiedad:', error));
    }

    // Función para actualizar una propiedad
    async function updateProperty(id, property) {
        await fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(property)
        })
        .then(() => loadProperties())  // Recargar propiedades después de la actualización
        .catch(error => console.error('Error al actualizar propiedad:', error));
    }

    // Función para eliminar una propiedad
    function deleteProperty(id) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(() => loadProperties())  // Recargar propiedades después de la eliminación
        .catch(error => console.error('Error al eliminar propiedad:', error));
    }

    // Función para editar una propiedad (cargar datos en el formulario)
    function editProperty(id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => response.json())
            .then(property => {
                document.getElementById('propertyId').value = property.id;
                document.getElementById('address').value = property.address;
                document.getElementById('price').value = property.price;
                document.getElementById('size').value = property.size;
                document.getElementById('description').value = property.description;

                document.getElementById('formTitle').textContent = 'Edit Property';
            })
            .catch(error => console.error('Error al cargar la propiedad para editar:', error));
    }
});
