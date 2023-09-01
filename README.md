# BackendTcc

## Visão Geral

O projeto de Back End do Gerenciador de Tarefas é uma aplicação desenvolvida utilizando as tecnologias Java, Spring Boot 3.1.2 e autenticação JWT para fornecer um sistema de gerenciamento de tarefas. A aplicação permite aos usuários criar, visualizar, atualizar e excluir tarefas, com um sistema de controle de acesso baseado em funções (RBAC). Usuários autenticados com a função de administrador (admin) têm permissão para realizar operações de criação, atualização e exclusão de tarefas, além de obter detalhes de todas as tarefas e tarefas por ID. Usuários autenticados com a função de usuário (USER) têm permissão somente para obter detalhes de todas as tarefas e tarefas por ID.

## Tecnologias Utilizadas

- Java: Linguagem de programação utilizada para desenvolver o Back End da aplicação.
- Spring Boot 3.1.2: Framework utilizado para criar e configurar a aplicação, incluindo a criação de endpoints da API, gerenciamento de dependências e segurança.
- Autenticação JWT (JSON Web Token): Mecanismo de autenticação utilizado para autenticar os usuários e gerar tokens de acesso.
- MySQL: Banco de dados relacional utilizado para armazenar os dados das tarefas e dos usuários.
- Docker: Plataforma de virtualização que permite empacotar e executar aplicações em contêineres.

## Endpoints da API

### Autenticação

Endpoint: `POST /auth/login`

Descrição: Autentica o usuário e fornece um token de acesso.

Corpo da Requisição:

```json
{
    "login": "admin",
    "password": "senha"
}
```

### Listar Tarefas

Endpoint: `GET /tasks`

Descrição: Retorna uma lista de todas as tarefas cadastradas no sistema.

```json
[
    {
        "id": 1,
        "title": "Estudar para o exame de Programação",
        "description": "Revisar os capítulos 5 a 8 do livro-texto e fazer exercícios práticos.",
        "completedStatus": "PENDING"
    },
    {
        "id": 2,
        "title": "Projeto de Grupo: Desenvolvimento de Aplicativo",
        "description": "Reunião para discutir a ideia do aplicativo e atribuir tarefas aos membros do grupo.",
        "completedStatus": "IN_PROGRESS"
    },
    {
        "id": 3,
        "title": "Preparar apresentação de Seminário",
        "description": "Criar slides e ensaiar a apresentação sobre metodologias ágeis.",
        "completedStatus": "PENDING"
    },
    {
        "id": 4,
        "title": "Entrega do Trabalho de Banco de Dados",
        "description": "Finalizar a modelagem do banco de dados e escrever a documentação técnica.",
        "completedStatus": "COMPLETED"
    }
]
```

### Criar Tarefa

Endpoint: `POST /tasks`

Descrição: Cria uma nova tarefa no sistema. Somente usuários com função de administrador (admin) têm permissão para acessar este endpoint.

Corpo da Requisição:

```json
{
     "title": "Estudar java",
    "description": "Estudar Java, Spring Boot",
    "completedStatus": "IN_PROGRESS"
}
```

### Atualizar Tarefa

Endpoint: `PUT /tasks/{id}`

Descrição: Atualiza os detalhes de uma tarefa existente. Somente usuários com função de administrador (admin) têm permissão para acessar este endpoint.

Corpo da Requisição:

```json
{
    "title": "Estudar Java",
    "description": "Estudar Java, Spring Boot e MySQL",
    "completedStatus": "IN_PROGRESS"
}
```

### Excluir Tarefa

Endpoint: `DELETE /tasks/{id}`

Descrição: Remove uma tarefa do sistema. Somente usuários com função de administrador (admin) têm permissão para acessar este endpoint.

### Detalhes da Tarefa

Endpoint: `GET /tasks/{id}`

Descrição: Retorna os detalhes de uma tarefa específica. Todos os usuários autenticados têm permissão para acessar este endpoint.

Resposta de Exemplo:

```json
{
    "id": 1,
    "title": "Estudar para o exame de Programação",
    "description": "Revisar os capítulos 5 a 8 do livro-texto e fazer exercícios práticos.",
    "completedStatus": "COMPLETED"
}
```

### Detalhes do Usuário Autenticado

Endpoint: `GET /auth/users`

Descrição: Retorna os detalhes do usuário autenticado.

Resposta de Exemplo:

```json
[
    {
        "id": 1,
        "login": "maxsuel",
        "role": "ADMIN"
    },
    {
        "id": 2,
        "login": "maxaluno",
        "role": "USER"
    }
]
```

## Controle de Acesso

A autenticação e autorização da API são baseadas em funções.

- Usuários com a função de administrador (admin) têm permissão para criar, atualizar e excluir tarefas, além de obter detalhes de todas as tarefas e tarefas por ID.
- Usuários com a função de usuário (USER) têm permissão somente para obter detalhes de todas as tarefas e tarefas por ID.

## Modelo de Dados

Cada tarefa é representada por um objeto com as seguintes propriedades:

```modelo

- `id` (number): Identificador único da tarefa.
- `title` (string): Título da tarefa.
- `description` (string): Descrição detalhada da tarefa.
- `completedStatus` (string): Status de conclusão da tarefa ("COMPLETED", "IN_PROGRESS" ou "PENDING").
```

## Considerações Finais

O Projeto Back End do Gerenciador de Tarefas oferece uma API robusta para a manipulação de tarefas, com um sistema de controle de acesso que garante que apenas usuários autenticados com as funções adequadas possam realizar operações sensíveis. O sistema é flexível e pode ser integrado com outras partes do sistema, como um Front End para uma experiência completa de gerenciamento de tarefas. O uso de tecnologias como Java, Spring Boot 3.1.2, autenticação JWT e MySQL rodando no Docker contribui para a segurança e escalabilidade da aplicação.
