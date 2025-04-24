
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, String> users = new HashMap<>();
    private static String currentUser;
    private static String playerClass;
    private static int health = 100;
    private static int maxHealth = 100;
    private static int attack = 10;
    private static ArrayList<String> inventory = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n|===Добро пожаловать в игру ===|");
            System.out.println("\n*3" +
                    "===Пройдите регистрацию: ===*");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": register(); break;
                case "2": login(); break;
                case "3": return;
                default: System.out.println("Неверный выбор!");
            }
        }
    }
    private static void register() {
        System.out.print("Придумай логин: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Имя занято.");
            return;
        }
        System.out.print("Придумай пароль: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Успешно зарегистрирован!");
    }
    private static void login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Добро пожаловать, " + currentUser + "!");
            chooseClass();
        } else {
            System.out.println("Неверные данные!");
        }
    }

    // Выбор класса
    private static void chooseClass() {
        System.out.println("\nВыбери свой класс:");
        System.out.println("1. Силач (большой урон)");
        System.out.println("2. Боец (средний урон)");
        System.out.println("3. Лекарь (может лечить себя)");
        System.out.print("Класс: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                playerClass = "Силач";
                attack = 15;
                break;
            case "2":
                playerClass = "Боец";
                attack = 10;
                break;
            case "3":
                playerClass = "Лекарь";
                attack = 5;
                break;
            default:
                System.out.println("Неверный выбор!");
                return;
        }

        System.out.println("Ты выбрал класс: " + playerClass);
        startAdventure();
    }

    // Начало приключения
    private static void startAdventure() {
        System.out.println("\nПриключение начинается! Ты сражаешься с врагом!");
        battle();
    }

    // Бой
    private static void battle() {
        System.out.println("\nТы встретил врага! Начинается бой!");

        int enemyHealth = 50;
        int enemyAttack = 10;

        while (enemyHealth > 0 && health > 0) {
            System.out.println("Твоя жизнь: " + health);
            System.out.println("Жизнь врага: " + enemyHealth);
            System.out.println("Что ты хочешь сделать?");
            System.out.println("1. Атаковать");
            System.out.println("2. Применить лечение (если Лекарь)");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    enemyHealth -= attack;
                    System.out.println("Ты атаковал врага! Его жизнь: " + enemyHealth);
                    break;
                case "2":
                    if (playerClass.equals("Лекарь")) {
                        heal();
                    } else {
                        System.out.println("Ты не можешь лечить!");
                    }
                    break;
                default:
                    System.out.println("Неверный выбор!");
                    continue;
            }

            if (enemyHealth > 0) {
                health -= enemyAttack;
                System.out.println("Враг атакует тебя! Твоя жизнь: " + health);
            }
        }

        if (health <= 0) {
            System.out.println("Ты погиб! Игра завершена.");
        } else {
            System.out.println("Ты победил врага!");
        }
    }

    // Лечение для Лекаря
    private static void heal() {
        if (health < maxHealth) {
            health += 10;
            System.out.println("Ты исцелился! Твоя жизнь: " + health);
        } else {
            System.out.println("Ты уже полный!");
        }
    }
}