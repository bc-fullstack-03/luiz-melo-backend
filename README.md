# Social Network Parrot

Este repositório contém uma API de uma rede social em Springboot que pode ser executado no Docker.

## Ferramentas Utilizadas

- Java 17
- Springboot 3.0.6
- NoSQL: MongoDB
- Docker

## Executando o Projeto no Docker

1. Clone o projeto:
    ```
    git clone https://github.com/bc-fullstack-03/luiz-melo-backend
    ```

2. Construa a imagem do Docker:
    ```
    docker build -t luizcapeme/parrot
    ```
3. Execute o container:
    ```
    docker run -p 8082:8082 luizcapeme/parrot
    ```

