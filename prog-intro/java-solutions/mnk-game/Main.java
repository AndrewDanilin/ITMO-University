import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int m = 0;
        int n = 0;
        int k = 0;
        int pointsToWin = 0;
        int result;
        Scanner in;
        while (true) {
            in = new Scanner(System.in);
            System.out.println("Enter board's width: ");
            try {
                m = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid board's width");
            }
        }
        while (true) {
            in = new Scanner(System.in);
            System.out.println("Enter board's height: ");
            try {
                n = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid board's height");
            }
        }
        while (true) {
            in = new Scanner(System.in);
            System.out.println("Enter points to win: ");
            try {
                k = in.nextInt();
                if (k > Math.max(m, n)) {
                    System.out.println(k + " is invalid number of points");
                    continue;
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invaild number of points");
            }
        }
        while (true) {
            try {
                in = new Scanner(System.in);
                System.out.println("Enter points to win in match: ");
                pointsToWin = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invaild number of points to win");
            }
        }
        Match match = new Match(pointsToWin, new RandomPlayer(), new RandomPlayer());
        match.play(m, n, k);
    }
}


