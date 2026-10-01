# Nexus

**Nexus** é uma API REST para gerenciamento de eventos, ingressos e pedidos, desenvolvida como projeto de portfólio com foco em **Java e desenvolvimento backend**.

O projeto foi desenvolvido utilizando **Java 21 e Spring Boot**, aplicando conceitos de **Clean Architecture**, separação de responsabilidades e baixo acoplamento entre as camadas da aplicação.

### Funcionalidades

* Cadastro e autenticação de usuários
* Autenticação e autorização utilizando **Spring Security + JWT**
* Controle de acesso baseado em roles (`CUSTOMER`, `PRODUCER` e `ADMIN`)
* Criação e gerenciamento de eventos
* Criação e gerenciamento de ingressos
* Criação e gerenciamento de pedidos
* Validação de regras de negócio
* Persistência de dados utilizando PostgreSQL
* Versionamento do banco de dados com Flyway
* Documentação e consumo de APIs REST

### Tecnologias

* **Java 21**
* **Spring Boot**
* **Spring Security**
* **JWT**
* **Spring Data JPA / Hibernate**
* **PostgreSQL**
* **Flyway**
* **MapStruct**
* **Docker**

### Arquitetura

A aplicação segue princípios de **Clean Architecture**, buscando manter as regras de negócio independentes de frameworks e detalhes de infraestrutura.

A comunicação entre as camadas é realizada através de **use cases, gateways e interfaces**, permitindo reduzir o acoplamento e facilitar a evolução da aplicação.

### Objetivo

O Nexus está sendo desenvolvido como um projeto de aprendizado e portfólio, com o objetivo de aprofundar conhecimentos em **Java, Spring Boot, arquitetura de software, segurança, persistência de dados e desenvolvimento de APIs REST**, além de servir como base para futuras evoluções envolvendo **microsserviços, mensageria, cache, observabilidade e Kubernetes**.
