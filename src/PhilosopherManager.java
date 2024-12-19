package src;
import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class PhilosopherManager extends Thread {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private Philosopher currentPhilosopher = null; 
    private static final ConcurrentHashMap<Integer, Philosopher> philosophers = new ConcurrentHashMap<>();

    public PhilosopherManager(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            
            out.println("HELLO! Bem-vindo ao servidor dos filósofos.");
            out.println("Conectado com: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            out.println("Aqui estão os comandos que você pode usar:");
            out.println("1. NOVO - Cria um novo filósofo.");
            out.println("2. COMER - Começa a comer automaticamente.");
            out.println("3. STATUS - Exibe o status do filósofo.");
            out.println("4. SAIR - Desconecta o filósofo atual.");
            out.println("5. LOGAR <id> - Reconecta a um filósofo pelo ID.\n");

            String command;
            while ((command = in.readLine()) != null) {
                String[] parts = command.split(" ");
                String action = parts[0].toUpperCase();

                switch (action) {
                    case "NOVO":
                    if (currentPhilosopher == null) {
                        if (philosophers.size() >= TelnetServer.NUMBER_OF_PHILOSOPHERS) {
                            out.println("Erro: Numero máximo de filósofos atingido. Não é possível criar mais.\n");
                        } else {
                            int id = philosophers.size();
                            Philosopher philosopher = new Philosopher(id);
                            philosophers.put(id, philosopher);
                            currentPhilosopher = philosopher;
                            out.println("Filosofo " + id + " foi criado e conectado.\n");
                        }
                    } else {
                        out.println("Voce ja esta conectado a um filosofo. Use SAIR para desconectar primeiro.\n");
                    }
                    break;

                    case "COMER":
                        if (currentPhilosopher != null) {
                            new Thread(() -> {
                                try {
                                    currentPhilosopher.eat(); 
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                            out.println("Filosofo " + currentPhilosopher.getId() + " comecou a comer.\n");
                        } else {
                            out.println("Nenhum filosofo conectado. Use NOVO ou LOGAR.\n");
                        }
                        break;

                    case "STATUS":
                        if (currentPhilosopher != null) {
                            out.println(currentPhilosopher.getStatus());
                        } else {
                            out.println("Nenhum filosofo conectado. Use NOVO ou LOGAR.\n");
                        }
                        break;

                    case "SAIR":
                        if (currentPhilosopher != null) {
                            out.println("Filosofo " + currentPhilosopher.getId() + " foi desconectado.\n");
                            currentPhilosopher = null;
                        } else {
                            out.println("Nenhum filosofo conectado.\n");
                        }
                        break;

                    case "LOGAR":
                        if (currentPhilosopher == null) {
                            if (parts.length > 1) {
                                int id = Integer.parseInt(parts[1]);
                                if (philosophers.containsKey(id)) {
                                    currentPhilosopher = philosophers.get(id);
                                    out.println("Vocs se conectou ao filosofo " + id + ".\n");
                                } else {
                                    out.println("Filosofo " + id + " nao encontrado.\n");
                                }
                            } else {
                                out.println("Uso: LOGAR <id>\n");
                            }
                        } else {
                            out.println("Voce ja esta conectado a um filosofo. Use SAIR para desconectar.\n");
                        }
                        break;

                    default:
                        out.println("Comando desconhecido. Digite um comando valido.\n");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConcurrentHashMap<Integer, Philosopher> getPhilosophers() {
        return philosophers;
    }
}
