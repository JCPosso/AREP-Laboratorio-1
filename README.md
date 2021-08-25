# Introduccion MVN , Git y Heroku
## Descripcion
En el siguiente programa se construirá una aplicación para consultar el mercado de valores de las acciones negociadas en Bolsa.
La aplicación recibirá el identificador de una acción, por ejemplo “MSFT” para Microsoft  y deberá mostrar el histórico de la valoración intra-día, diaria, semanal y mensual.
## Prerrequisitos
Antes de ejecutar el proyecto es necesario instalar los siguientes programas:
* [Java](https://www.java.com/es/download/ie_manual.jsp). versión 11 o superior. 
* [Maven](https://maven.apache.org/). 
* [GIT](https://git-scm.com/). 
* Version de compilación 1.8 o superior
## Instalación
Para descargar el proyecto primero debemos clonar el repositorio con ayuda de la consola de comandos:
```
git clone https://github.com/JCPosso/AREP-Laboratorio-1
```

## Ejecución
Para ejecutar el proyecto usamos Maven en el directorio raiz del proyecto  usando el siguiente comando.
```
mvn package
```
## Pruebas
Para dar arranque a todas las pruebas Ejecutamos con la consola de comandos  :
```
mvn test -Dtest=ClientTest
```
## Despliegue HEROKU

[![Heroku](src/main/resources/public/heroku.jpg)](https://heroku-app-arep.herokuapp.com/)

## Autor
[Juan Camilo Posso Guevara](https://github.com/JCPosso)
## Derechos de Autor
**©** _Juan Camilo Posso G., Escuela Colombiana de Ingeniería Julio Garavito._
## Licencia
Licencia bajo  [GNU General Public License](https://github.com/JCPosso/AREP-Laboratorio-1/blob/master/LICENSE).
