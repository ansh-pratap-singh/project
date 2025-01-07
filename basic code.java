import java.util.Scanner;

// Character class
class Character {
    String name;
    int health;
    int attack;
    int defense;

    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}

// Main Game Class
public class BattleBound {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battle Bound: The Rise of Darkness!");
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();

        Character player = new Character(playerName, 50, 10, 5);

        // Enemies
        Character[] enemies = {
            new Character("Shadow Wolf", 20, 8, 2),
            new Character("Dark Knight", 30, 12, 4),
            new Character("Demon Lord", 50, 15, 6)
        };

        // Game Loop
        for (Character enemy : enemies) {
            System.out.println("\nA " + enemy.name + " appears!");

            while (player.isAlive() && enemy.isAlive()) {
                System.out.println("\n" + player.name + "'s Health: " + player.health);
                System.out.println(enemy.name + "'s Health: " + enemy.health);
                System.out.print("Choose an action (attack/defend/run): ");
                String action = scanner.nextLine().toLowerCase();

                switch (action) {
                    case "attack":
                        int playerDamage = Math.max(0, player.attack - enemy.defense);
                        enemy.takeDamage(playerDamage);
                        System.out.println("You attack " + enemy.name + " for " + playerDamage + " damage!");
                        break;
                    case "defend":
                        System.out.println("You defend and reduce incoming damage!");
                        player.defense += 2;
                        break;
                    case "run":
                        System.out.println("You fled the battle!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid action. Try again.");
                        continue;
                }

                // Enemy's turn to attack
                if (enemy.isAlive()) {
                    int enemyDamage = Math.max(0, enemy.attack - player.defense);
                    player.takeDamage(enemyDamage);
                    System.out.println(enemy.name + " attacks you for " + enemyDamage + " damage!");
                }
            }

            if (!player.isAlive()) {
                System.out.println("\nYou have been defeated...");
                System.out.println("Game Over.");
                scanner.close();
                return;
            } else {
                System.out.println("\nYou defeated " + enemy.name + "!");
            }
        }

        System.out.println("\nCongratulations! You have defeated the forces of darkness!");
        scanner.close();
    }
}