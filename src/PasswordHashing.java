import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordHashing {

    // Metode til at hashe et password med SHA-256
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Metode til at verificere om input-password matcher en gemt hash
    public static boolean verifyPassword(String inputPassword, String storedHash) throws NoSuchAlgorithmException {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input til password
            System.out.print("Indtast et password: ");
            String originalPassword = scanner.nextLine();

            // Generér hash og udskriv den
            String hashedPassword = hashPassword(originalPassword);
            System.out.println("Hashed password: " + hashedPassword);

            // Verificering af password
            System.out.print("Indtast password igen for at verificere: ");
            String verifyPasswordInput = scanner.nextLine();
            boolean isMatch = verifyPassword(verifyPasswordInput, hashedPassword);

            if (isMatch) {
                System.out.println("Verificering vellykket! Password matcher.");
            } else {
                System.out.println("Verificering mislykkedes! Password matcher ikke.");
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hash-algoritmen blev ikke fundet: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
