
# Sistema de Suporte a Chamados (Help Desk)

![Java CI/CD](https://github.com/suporte-disciplinado/helpdesk-api/actions/workflows/ci.yml/badge.svg)

## 📋 Descrição

O Sistema de Suporte a Chamados (Help Desk) é uma aplicação full stack desenvolvida com **Spring Boot** no backend e **React** no frontend.
A plataforma tem como objetivo gerenciar de forma eficiente solicitações de suporte, oferecendo uma interface simples, organizada e eficaz para abertura, acompanhamento e resolução de chamados.

Esse tipo de sistema é essencial para empresas que fornecem serviços, como provedores de internet, empresas de software ou qualquer organização que necessite oferecer suporte contínuo.

---

## 🎯 Objetivos de Aprendizado

- Implementar um sistema de gestão de chamados com funcionalidades de **criação**, **atribuição** e **rastreamento**.
- Desenvolver um **painel administrativo** com visualização de métricas e relatórios de performance da equipe de suporte.
- Criar uma **interface amigável e intuitiva** tanto para o usuário final quanto para os atendentes de suporte.
- Implementar recursos de **categorização e priorização** de chamados com base em seu tipo e urgência.

---

## 🧩 Funcionalidades Principais

### 📝 Abertura de Chamados

- Usuários podem registrar chamados detalhando o problema ou solicitação.
- Os chamados incluem:
  - Categoria (ex: Técnico, Financeiro, Comercial).
  - Prioridade (Baixa, Média, Alta).
  - Descrição do incidente.

### 👥 Atribuição e Gestão

- Chamados podem ser atribuídos automaticamente ou manualmente a atendentes.
- Atendentes têm acesso a um painel com:
  - Lista de chamados abertos, em andamento ou finalizados.
  - Detalhes do chamado, incluindo histórico de interações.
  - Usuários com perfil ADMIN, tem acesso a todos os chamados.
  - Usuário com perfil USER, tem acesso somentos aos chamados atribuidos a ele.

### ⚙️ Classificação e Priorização

- Chamados são classificados por categoria para facilitar o atendimento.
- É possível definir prioridade com base na urgência e no impacto do problema.

---

## 🛠️ Tecnologias Utilizadas

### Backend (Spring Boot)

- Java 17+
- Spring Web
- Spring Data JPA
- Spring Security
- Banco de dados: PostgreSQL

### Frontend (React)

- React 19
- React Router
- Axios para consumo de API
- Material UI

---

## ☸️ Kubernetes (Orquestração de Containers)

A aplicação foi preparada para execução em ambientes com **Kubernetes**, permitindo o gerenciamento escalável e resiliente de seus componentes.

### 🧱 Arquitetura Containerizada

- **Frontend React** e **Backend Spring Boot** são empacotados em containers independentes.
- O banco de dados PostgreSQL é executado como um serviço persistente no cluster.

### 🚀 Benefícios do uso do Kubernetes

- **Escalabilidade horizontal** dos pods.
- **Resiliência e alta disponibilidade** dos serviços.
- **Desacoplamento e modularização** entre as camadas da aplicação.
- Facilidade na realização de **rollouts e rollbacks**.
- Integração com ferramentas de observabilidade e monitoramento.

---

## 🔍 SonarQube (Análise de Qualidade de Código)

O projeto integra o uso do **SonarQube** para análise estática de código, garantindo que boas práticas sejam seguidas e que potenciais problemas sejam detectados antecipadamente.

### ✅ Benefícios da análise com SonarQube

- Identificação de **bugs**, **vulnerabilidades**, e **code smells**.
- Avaliação da **cobertura de testes automatizados**.
- Detecção de **código duplicado** e alta **complexidade ciclomática**.
- Melhoria contínua da **manutenibilidade** e **qualidade técnica** do código.
