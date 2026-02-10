import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameCharacter character = new GameCharacter("Hero");

        while (!(character.getState() instanceof MasterState)) {
            System.out.println("\n=== Character Status ===");
            character.showStatus();

            System.out.println("\nAvailable actions:");
            System.out.println("1. Train");
            System.out.println("2. Meditate");
            System.out.println("3. Fight");

            System.out.print("Choose action: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> character.train();
                case 2 -> character.meditate();
                case 3 -> character.fight();
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("\nGame Over. You reached Master level!");
        scanner.close();
    }
}
