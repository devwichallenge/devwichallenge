About this project:
# devwichallenge   

:::::::::::::::::::::::: API REST (Alta de Usuarios) ::::::::::::::::::::::

Se trata de un servicio rest que expone un metodo POST para dar de alta usuarios.

Consideraciones: Esta aplicacion fue desarrollada con Java 8 y Spring Boot.

La aplicacion esta empaquetada en un archivo .jar que se encuantra en la raiz del proyecto el cual debe ejecutar 
sobre la linea de comandos de la siguiente manera:

java -jar demo-0.0.1-SNAPSHOT.jar

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
Descripción del servico:

Dar de alta usuarios:
Method: POST
URL:
http://localhost:8080/approval/user

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "userR13",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
En el servico se valida las siguientes acciones:

* Ninguno de los campos puede ser nulo.
* El email debe ser valido ejemplo: Debe contener "@" y debera terminar en .dominio.
* El email no se puede guardar mas de una vez.
* El passowrd debera contener letras Mayusculas y Minusculas y al menos 2 numeros. 

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
Si se cumplieron los requisitos previamente mencionados el response debe ser algo como esto:

{
    "id": "882af9be-5bff-4475-b95e-b747bdf5bdbe",
    "created": "2022-01-16T11:13:14.537394",
    "modified": "2022-01-16T11:13:14.537402",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXRoMCI6Imp1YW5Acm9kcmlndWV6MDQub3JnIiwiaXNzIjoiYXV0aDAiLCJleHAiOjE2NDI5NDcxOTR9.P-W85L_jqBHJrN5oVGQ5jhYiyA73QYC5ODlFjbTHYduGgfyTDdrwmZGLi7S1dyeHI984QgPblXa6agoPH3a-FQ",
    "last_login": "2022-01-16T11:13:14.537406",
    "isactive": true
}