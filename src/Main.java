import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] chars = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                chars[i][j] = 32;
            }
        }
        printTTT(chars);
        System.out.print("Enter the coordinates: ");
        String coordinatesIp = sc.nextLine();
        byte start = 0;
        char player = 'X';
        while (!finished(chars)) {
            if (start % 2 == 0) {
                player = 'X';
            } else {
                player = 'O';
            }
            checkCoordinates(coordinatesIp, chars, player);
            printTTT(chars);
            if (charWins(chars, player)){
                break;
            } else if (draw(chars)) {
                break;
            }
            System.out.print("Enter the coordinates: ");
            coordinatesIp = sc.nextLine();
            start++;
        }

    }

    // Check valid coordinates of player
    static void checkCoordinates(String coordinatesIp, char[][] chars, char player) {
        // Coordinates must be 2 numbers
        for (int i = 0; i < coordinatesIp.length(); i++) {
            if (Character.isAlphabetic(coordinatesIp.charAt(i))) {
                System.out.println("You should enter numbers!\nEnter the coordinates: ");
                checkCoordinates(new Scanner(System.in).nextLine(), chars, player);
            }
        }
        int first = Character.getNumericValue(coordinatesIp.charAt(0));
        int second = Character.getNumericValue(coordinatesIp.charAt(2));

        // Coordinates must be 2 numbers must be larger than 0 and less than 3
        if (first < 1 || first > 3 || second < 1 || second > 3) {
            System.out.println("Coordinates should be from 1 to 3!\nEnter the coordinates: ");
            checkCoordinates(new Scanner(System.in).nextLine(), chars, player);
        } else if (chars[first - 1][second - 1] == 32) {
            chars[first - 1][second - 1] = player;
        } else {
            // If the cell is occupied. Re-input coordinates
            System.out.print("This cell is occupied! Choose another one!\nEnter the coordinates: ");
            checkCoordinates(new Scanner(System.in).nextLine(), chars, player);
        }
    }

    // Print the grid
    static void printTTT(char[][] chars) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |\n", chars[i][0], chars[i][1], chars[i][2]);
        }
        System.out.println("---------");
    }

    // Return true if the game is finished, otherwise return false
    static boolean finished(char[][] chars) {
        for (char[] row : chars) {
            for (char each : row) {
                if (each == 32) {
                    return false;
                }
            }
        }
        return true;
    }

    // Return true if character check wins, otherwise return false
    static boolean charWins(char[][] chars, char check) {
        boolean end = false;
        // Diagonal
        if (chars[0][0] == chars[1][1] && chars[0][0] == chars[2][2] && chars[0][0] == check) {
            System.out.println(check + " wins");
            end = true;
        }
        if (chars[0][2] == chars[1][1] && chars[0][2] == chars[2][0] && chars[0][2] == check) {
            System.out.println(check + " wins");
            end = true;
        }

        // Horizontal
        for (int i = 0; i < 3; i++) {
            if (chars[i][0] == chars[i][1] && chars[i][0] == chars[i][2] && chars[i][0] == check) {
                System.out.println(check + " wins");
                end = true;
            }
        }

        // Vertical
        for (int i = 0; i < 3; i++) {
            if (chars[0][i] == chars[1][i] && chars[1][i] == chars[2][i] && chars[0][i] == check) {
                System.out.println(check + " wins");
                end = true;
            }
        }
        return end;
    }

    // Return true if the game is finished and neither 'X' nor 'O' wins
    static boolean draw(char[][] chars) {
        if (finished(chars) && !charWins(chars, 'X') && !charWins(chars, 'X')) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
}
