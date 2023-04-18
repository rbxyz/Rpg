package Rpg_Teste2;
import java.util.Scanner;

public class Rpg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao simulador de RPG!");
        
        // Criação do personagem
        System.out.println("Crie seu personagem:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Classe: ");
        String classe = scanner.nextLine();
        Personagem personagem = new Personagem(nome, classe);
        System.out.println("Seu personagem foi criado:");
        System.out.println(personagem);
        
        // Criação do inimigo
        Inimigo inimigo = new Inimigo("Goblin", 5, 2);
        System.out.println("Um " + inimigo + " apareceu!");
        
        // Início da batalha
        while (personagem.estaVivo() && inimigo.estaVivo()) {
            System.out.println("----------------------");
            System.out.println(personagem);
            System.out.println(inimigo);
            System.out.println("----------------------");
            System.out.println("O que você quer fazer?");
            System.out.println("1. Atacar");
            System.out.println("2. Curar");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            if (escolha == 1) {
                personagem.atacar(inimigo);
                if (inimigo.estaVivo()) {
                    inimigo.atacar(personagem);
                }
            } else if (escolha == 2) {
                personagem.curar();
                inimigo.atacar(personagem);
            } else {
                System.out.println("Opção inválida!");
            }
        }
        
        // Fim da batalha
        System.out.println("----------------------");
        if (personagem.estaVivo()) {
            System.out.println("Você venceu a batalha!");
        } else {
            System.out.println("Você perdeu a batalha...");
        }
        scanner.close();
    }
}

class Personagem {
    private String nome;
    private String classe;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    
    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.vidaMaxima = 10;
        this.vida = this.vidaMaxima;
        if (classe.equalsIgnoreCase("guerreiro")) {
            this.ataque = 4;
            this.defesa = 2;
        } else if (classe.equalsIgnoreCase("mago")) {
            this.ataque = 2;
            this.defesa = 4;
        } else {
            this.ataque = 3;
            this.defesa = 3;
        }
    }
    
    public void atacar(Inimigo inimigo) {
        inimigo.perderVida(this.ataque);
    }
    
    public void curar() {
        if (this.vida < this.vidaMaxima) {
            this.vida += 2;
            System.out.println("Você se curou em 2 pontos de vida!");
            if (this.vida > this.vidaMaxima) {
                this.vida = this.vidaMaxima;
            }
        } else {
            System.out.println("Você já está com a vida cheia!");
        }
    }
    
    public void perderVida(int dano) {
        int danoReal = dano - this.defesa;
        if (danoReal < 0) {
            danoReal = 0;
        }
        this.vida -= danoReal;
        System.out.println(this.nome + " perdeu " + danoReal + " pontos de vida!");
        if (!estaVivo()) {
            System.out.println(this.nome + " morreu...");
        }
    }
    
    public boolean estaVivo() {
        return this.vida > 0;
    }
    
    public String toString() {
        return this.nome + " (" + this.classe + ") Vida: " + this.vida + "/" + this.vidaMaxima;
    }
}

class Inimigo {
    private String nome;
    private int vida;
    private int ataque;
    
    public Inimigo(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }
    
    public void atacar(Personagem personagem) {
        personagem.perderVida(this.ataque);
    }
    
    public void perderVida(int dano) {
        this.vida -= dano;
        System.out.println(this.nome + " perdeu " + dano + " pontos de vida!");
        if (!estaVivo()) {
            System.out.println(this.nome + " morreu!");
        }
    }
    
    public boolean estaVivo() {
        return this.vida > 0;
    }
    
    public String toString() {
        return this.nome + " Vida: " + this.vida;
    }
}

