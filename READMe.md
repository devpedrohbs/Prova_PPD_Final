# Explicação

## Como Executar

### Requisitos

- Ter o Telnet ativo

### Passos para Executar

1. **Clonar o repositório:**

   Clone este repositório para o seu computador

2. **No PowerShell:**

     Colocar o caminho da pasta que está o codigo

3. **Execução:**

    - javac src/*.java
    - java src.TelnetServer
    - Abrir outro PowerShell
        - telnet localhost 23
        - Utilize os codigos do protocolo

## Principais Componentes

1. **TelnetServer**
Função:

- Inicia o servidor Telnet e escuta na porta 23. Cria os garfos e gerencia uma lista de filósofos. Aceita conexões de clientes e cria uma nova instância de PhilosopherManager para cada novo cliente.

2. **PhilosopherManager**
Função:

- Gerencia a comunicação entre o servidor e o cliente. Trata os comandos enviados pelos clientes e interage com os filósofos. Tem a lógica para criar novos filósofos, conectar com filósofos ja criados e gerenciar os estados de "comer" e "pensar".

3. **Philosopher**
Função:

- Representa um filósofo no sistema. Cada filósofo possui um ID, um contador de pensamentos e um contador de refeições.
Os filósofos alternam entre pensar e comer. Para comer, o filósofo precisa pegar dois garfos. O filósofo tem um limite de 10 refeições. 

4. **Fork**
Função:

- Representa um garfo que os filósofos usam para comer. Cada garfo é um recurso compartilhado entre filósofos





