# ShortLink


Debido a  los requerimientos de performance la arquitectura se dividió en dos ShortLink Api y Shortlink Server

# ShortLink API
Es una capa de servicios restful que realiza el management (ABM) de  shortlinks. Debido a que el requerimiento específico una baja cantidad de request, la arquitectura se mantuvo lo más simple posible debido a los tiempos de desarrollo y mantenibilidad a futuro.
@see /api

# ShortLink Server
Es un “micro web server” encargado solamente de la resolución de short links a la full URL.
La idea de este server es correr contenerizado en un ambiente que soporte crecimiento horizontal por esta razón dentro de su arquitectura de intento cuidar la performance, el footprint.
La arquitectura es un servicio de redireccionamiento desarrollado sobre Quarkus para correr sobre graalvm. 
@see /server

Manteniendo en ambos proyectos un mismo lenguaje de programación aunque diferentes frameworks y runtimes. 

# Base de datos
Para la base de datos utilizada por ambos componentes se decidió utilizar cassandra debido a ser una base de datos key value muy eficiente, tener capacidad de crecimiento horizontal y poseer buenas capacidades de fault tolerance.


requerimientos :

 - Java JDK11
 - Docker


Stack:
 - Cassandra 
 - Java11
 - graalVm 
