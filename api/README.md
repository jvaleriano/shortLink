# ShortLink API

## Servicios
El api posee la documentacion de OpenApi y tambien disponibiliza una ui en /swagger-ui.html para consumir los servicios sin nececidad de un cliente.


## Development config

set spring.profiles.active=dev

Docker start Cassandra:
docker-compose -f .\src\main\docker\cassandra.yml up -d

