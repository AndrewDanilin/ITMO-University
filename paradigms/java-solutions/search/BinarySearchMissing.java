package search;

public class BinarySearchMissing {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(iterativeSearch(number, a));
    }

    // Pred: forall i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
    // Post: R = index : (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) || (R == a.length && a[R - 1] > number)
    // || (R = insertion point && R < 0)
    public static int iterativeSearch(int number, int[] a) {
        // Pred: for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
        // Post: left == -1 && for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
        int left = -1;

        // Pred: for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
        // Post: right == a.length && for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
        int right = a.length;

        // Inv: (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
        // (right' - left' + 1 <= (right - left + 1) / 2) && (right - left >= 1)
        while (right - left > 1) {
            // Pred: (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
            // (right' - left' + 1 <= (right - left + 1) / 2) && (right - left > 1)
            // Post: mid = (left + right) / 2 && (a[left] > numbers || left == -1) &&
            // (a[right] <= number || right == a.length) && (right' - left' + 1 <= (right - left + 1) / 2) &&
            // (right - left > 1)
            int mid = (left + right) / 2;

            // Pred: true
            if (a[mid] <= number) {
                // Pred: a[mid] <= number && (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
                // (right' - left' + 1 <= (right - left + 1) / 2) && (right - left > 1)
                // Post: right = mid && left = left' && (a[left] > numbers || left == -1) &&
                // a[right] <= number && (right' - left' + 1 <= (right - left + 1) / 2)
                right = mid;

            } else {
                // Pred: a[mid] > number && (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
                // (right' - left' + 1 <= (right - left + 1) / 2) && (right - left > 1)
                // Post: left = mid && right = right' && (a[left] > number) &&
                // (a[right] <= number || right == a.length) && (right' - left' + 1 <= (right - left + 1) / 2) &&
                // (right - left >= 1)
                left = mid;
            }
        }

        // Pred: (right - left == 1) && (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length)
        // Post: R = index : (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) || (R == a.length && a[R - 1] > number)
        // || (R = -right - 1 && R < 0)
        if (right == a.length || (right != a.length && number != a[right])) {
            return (-right - 1);
        } else {
            return right;
        }
    }

    // Pred: for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
    // Post: R = index : (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) || (R == a.length && a[R - 1] > number)
    // || (R = insertion point && R < 0)
    public static int recursiveSearch(int number, int[] a) {
        return recursiveBinarySearch(-1, a.length, number, a);
    }

    // Pred: for all i, j == 0..a.length - 1 : i < j -> a[i] >= a[j]
    // Post: R = index : (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) || (R == a.length && a[R - 1] > number)
    // || (R = insertion point && R < 0)
    // Inv: (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
    // (right' - left' + 1 <= (right - left + 1) / 2) && right - left >= 1
    public static int recursiveBinarySearch(int left, int right, int number, int[] a) {
        if (right - left == 1) {
            // Pred: (right - left == 1) && (right >= 0 && right < a.length && a[right] <= number &&
            // (a[right - 1] > number || right == 0)) || (right == a.length && a[right - 1] > number)
            // Post: R = index : (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) || (R == a.length && a[R - 1] > number)
            // || (R = -right - 1 && R < 0)
            if (right == a.length || (right != a.length && number != a[right])) {
                return (-right - 1);
            } else {
                return right;
            }
        }

        // Pred: (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
        // (right' - left' + 1 <= (right - left + 1) / 2) && right - left > 1
        // Post: (mid = (right + left) / 2) && (a[left] > numbers || left == -1) && (a[right] <= number || right == a.length) &&
        // (right' - left' + 1 <= (right - left + 1) / 2) && right - left > 1
        int mid = (right + left) / 2;

        if (a[mid] <= number) {
            // Pred: (a[mid] <= number) && (a[left] > numbers || left == -1) &&
            // (a[right] <= number || right == a.length) && (right' - left' + 1 <= (right - left + 1) / 2) && right - left > 1
            // Post: left' = left && right' = mid && (a[left'] > number) &&
            // (a[right] <= number || right == a.length) && (right' - left' + 1 <= (right - left + 1) / 2) &&
            // (right - left >= 1)
            return recursiveBinarySearch(left, mid, number, a);
        } else {
            // Pred: (a[mid] > number) && (mid = (right + left) / 2) && (a[left] > numbers || left == -1) &&
            // (a[right] <= number || right == a.length) && (right' - left' + 1 <= (right - left + 1) / 2) && right - left > 1
            // Post: left' = mid && right' = right && (a[left] > number) &&
            // (a[right'] <= number) && (right' - left' + 1 <= (right - left + 1) / 2) &&
            // (right - left >= 1)
            return recursiveBinarySearch(mid, right, number, a);
        }
    }
}
