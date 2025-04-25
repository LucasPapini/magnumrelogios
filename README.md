# API de Gerenciamento de Veículos


Este projeto é uma API RESTful desenvolvida com Quarkus para gerenciar dados de veículos, incluindo a busca e atualização de informações como marca e observações. Ele se conecta a um banco de dados relacional (como Mysql) para armazenar e recuperar informações.

# Estrutura do Projeto

```
src/main/java/com/seuprojeto/
├── controller/      <- REST controllers
├── service/         <- Camada de regras de negócio
├── repository/      <- Acesso a dados (JPA/Panache)
├── domain/          <- Entidades, enums, value objects
└── dto/             <- DTOs para entrada/saída```
```


# Tecnologias Utilizadas
```
 - Quarkus
 - JPA/Hibernate
 - Mysql
 - Docker
 - Kafka 
 ```

# Endpoints
```
 Iniciar a busca dos dados e envia para  fila: /marcas/enviar-marcas
 ---
 Faz a busca de veiculos pela marca: /veiculos/buscar/audi
 [
  {
    "codigo": "1234",
    "modelo": "A3",
    "observacao": "Modelo 2020"
  },
  {
    "codigo": "5678",
    "modelo": "Q5",
    "observacao": "Modelo 2021"
  }
]
---
Realiza a busca de veiculos por codigo: /veiculos/buscar/codigo/1234
[
    {
        "codigo": "1234",
        "modelo": "A3",
        "observacao": "Modelo 2020"
    }
]
---
Atualiza informaçoes como modele e observacoes: veiculos/atualizar/veiculo/{codigo}
[
    {
        "modelo": "A3 2022",
        "observacao": "Novo modelo com mais recursos"
    }
]
```

# Como rodar o projeto

Necessário realizar a instalacao do container dp Kafka já existe um yaml com as configuraçoes, rode docker-compose up -d

Em seguida realize as configurações na arquivo application.properties das duas API's para se concetar com as filas, no meu caso ficou assim:
```
API-1
mp.messaging.outgoing.marcas-out.connector=smallrye-kafka
mp.messaging.outgoing.marcas-out.topic=marcas-dos-carros-topic
mp.messaging.outgoing.marcas-out.value.serializer=org.apache.kafka.common.serialization.StringSerializer
mp.messaging.outgoing.marcas-out.bootstrap.servers=localhost:9092
```

```
API-2
quarkus.http.port=8071

mp.messaging.incoming.marcas-in.connector=smallrye-kafka
mp.messaging.incoming.marcas-in.topic=marcas-dos-carros-topic
mp.messaging.incoming.marcas-in.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
mp.messaging.incoming.marcas-in.bootstrap.servers=localhost:9092
mp.messaging.incoming.marcas-in.auto.offset.reset=earliest
mp.messaging.incoming.marcas-in.group.id=kafka-consumer-marcas-v5
quarkus.kafka.devservices.enabled=false
```