public class NumberUtils {

    //convert 123 to [1, 2, 3]
    private static int[] toIntArray(String numStr) {
        return String.valueOf(numStr).chars().map(c -> c -= '0').toArray();
    }
}
