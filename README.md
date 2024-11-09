
# Biblioteca - TP Paradigmas

## Ejemplar

### **GET** - Obtener todos los ejemplares
`GET /system/api/v1/ejemplares`

Devuelve una lista con todos los ejemplares registrados.

---

### **GET** - Obtener todos los libros disponibles
`GET /system/api/v1/ejemplares/disponibles`

Devuelve una lista de ejemplares disponibles para préstamo.

---

### **PUT** - Modificar el estado de un ejemplar
`PUT /system/api/v1/ejemplares/{id}`

Actualiza el estado de un ejemplar (prestado o no prestado). Requiere un JSON en el cuerpo con el atributo `esPrestado`.

**Body**:
```json
{ "esPrestado": true }
```

### DELETE - Eliminar ejemplar por id


`DELETE /system/api/v1/ejemplares/3`

---


## Estudiantes


### GET - Get todos los estudiantes

`GET /system/api/v1/estudiantes`

---

### GET - Get estudiante por id


`GET /system/api/v1/estudiantes/2`

---
﻿  

### POST - Crear estudiante

`POST /system/api/v1/estudiantes`

**Body:**

```json
{
    "nombre": "abel",
    "apellido": "monetti"
}
```

---

### DELETE - Eliminar estudiante por id


`DELETE /system/api/v1/estudiantes/1`

---

### GET - Get prestamo por estudiante id


`GET /system/api/v1/estudiantes/2/prestamos`

Si un estudiante tiene un prestamo asociado, este endpoint lo devuelve


---


## Prestamos

### GET - Get prestamos


`GET /system/api/v1/prestamos`

---

### GET - Get prestamo por id

`GET /system/api/v1/prestamos/1`

---
﻿  

### POST - Crear prestamo

`POST system/api/v1/prestamos?idEstudiante=2&idLibro=2&diasDuracion=7`

Para crear el prestamo hay que pasarle los parametros como queryParams en la url directamente y usar un post.

﻿  
**Query Params**

`idEstudiante=2`

`idLibro=2`

`diasDuracion=7`

**Body:**

```json
{
    "idEstudiante": 2,
    "idLibro": 2,
    "diasDuracion": 7
}
```
---

### DELETE - Eliminar prestamo por id


`DELETE /system/api/v1/prestamos/1`

---

### PUT - Marcar devolucion

`PUT /system/api/v1/prestamos/2`

Cuando marcas la devolucion se borra el prestamo y se pone el libro en esPrestado = false

---

## Libros


### GET - Get libros

`GET /system/api/v1/libros`

---

### GET - Get libro por id

`GET /system/api/v1/libros/2`

---

### POST - Crear libro

`POST /system/api/v1/libros`

**Body:**

```json
{
    "titulo": "clean code",
    "autor": "uncle bob",
    "genero": "cs",
    "anioPublicacion": 2002
}
```

---

### PUT - Actualizar libro por id

`PUT /system/api/v1/libros/1`

**Body:**

```json
{
    "titulo": "Desing patters",
    "autor": "uncle bob",
    "genero": "cs",
    "anioPublicacion": 2005
}
```

---
### DELETE - Eliminar libro por id

`DELETE /system/api/v1/libros/1`

---

### POST - Agregar ejemplar

`POST /system/api/v1/libros/2/ejemplar`

Este endpoint solo agrega un ejemplar de un cierto libro. Hay que crear por lo menos 1 ejemplar despues de crear un libro para poder usar los otros endpoints. La creacion de un libro viene con 0 ejemplares asociados por defecto.
```json
{
    "esPrestado": true
}

