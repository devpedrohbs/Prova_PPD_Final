# Protocolo do Servidor Telnet para os Filósofos

## Funcionamento do Servidor
O servidor **Telnet** escuta na porta 23 e permite que múltiplos clientes se conectem, cada um podendo interagir com um filósofo específico.

## Comandos Disponíveis

Os clientes podem interagir com o servidor usando os seguintes comandos:
Vamos usar um filoso com um ID que sera gerado pelo programa.

### 1. **NOVO**
Cria um novo filósofo e o conecta ao servidor
- Resposta do servidor: "Filosofo 0 foi criado e conectado."

### 2. **COMER**
Faz o filósofo começar a comer
- Resposta do servidor: "Filosofo 0 comecou a comer."

### 3. **STATUS**
Exibe o status do filósofo conectado, mostrando quantas vezes ele pensou e comeu.
- Resposta do servidor: "Filosofo 0 -> Pensou: 10 vezes, Comeu: 8 vezes.";

### 4. **SAIR**
Desconecta o filósofo atual.
- Resposta do servidor: "Filosofo 0 foi desconectado."

### 5. **LOGAR <ID>**
Reconecta-se a um filósofo existente pelo seu ID.
- Resposta do servidor: "Você se conectou ao filósofo 0."
- Se o ID não for válido, o servidor retorna: "Filosofo 0 não encontrado."


