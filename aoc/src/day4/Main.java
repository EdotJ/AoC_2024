package day4;

import utils.Utils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {


    public static void main(String[] args) throws Exception {
        List<String> lines = Utils.readAllLines("day4/input.txt");

        char[][] characters = lines.stream().map(String::toCharArray).toList().toArray(new char[0][]);
        findXmasChars(characters);
        findMasChars(characters);
    }

    private static void findMasChars(char[][] characters) {
        int matches = 0;
        char[] searchChars = new char[]{'M', 'A', 'S'};
        for (int row = 0; row < characters.length; row++) {
            for (int col = 0; col < characters[row].length; col++) {
                if (row + searchChars.length - 1 >= characters.length || col + searchChars.length - 1>= characters[row].length) {
                    continue;
                }
                int finalRow = row;
                int finalCol = col;
                int rightCorner = col + searchChars.length - 1;
                if (isValid(characters, searchChars, (idx) -> finalRow + idx, (idx) -> finalCol + idx)              // \
                        && isValid(characters, searchChars, (idx) -> finalRow + idx, (idx) -> rightCorner - idx)    // /
                ) {
                    matches++;
                }

            }
        }

        System.out.println("X-MAS Matches: " + matches);
    }

    private static void findXmasChars(char[][] characters) {
        int matches = 0;

        char[] searchChars = new char[]{'X', 'M', 'A', 'S'};
        for (int row = 0; row < characters.length; row++) {
            for (int col = 0; col < characters[row].length; col++) {
                if (characters[row][col] != searchChars[0] && characters[row][col] != searchChars[searchChars.length - 1]) {
                    continue;
                }

                boolean canGoUp = row >= searchChars.length - 1;
                boolean canGoDown = characters.length - row >= searchChars.length;
                boolean canGoRight = characters[row].length - col >= searchChars.length;
                boolean canGoLeft = col >= searchChars.length - 1;

                int finalRow = row;
                int finalCol = col;

                if (canGoUp) {
                    if (isValid(characters, searchChars, (idx) -> finalRow - idx, (idx) -> finalCol)) {
                        matches++;
                    }

                    if (canGoLeft && isValid(characters, searchChars, (idx) -> finalRow - idx, (idx) -> finalCol - idx)) {
                        matches++;
                    }

                    if (canGoRight && isValid(characters, searchChars, (idx) -> finalRow - idx, (idx) -> finalCol + idx)) {
                        matches++;
                    }
                }

                if (canGoDown) {
                    if (isValid(characters, searchChars, (idx) -> finalRow + idx, (idx) -> finalCol)) {
                        matches++;
                    }

                    if (canGoLeft && isValid(characters, searchChars, (idx) -> finalRow + idx, (idx) -> finalCol - idx)) {
                        matches++;
                    }

                    if (canGoRight && isValid(characters, searchChars, (idx) -> finalRow + idx, (idx) -> finalCol + idx)) {
                        matches++;
                    }
                }

                if (canGoLeft && isValid(characters, searchChars, (idx) -> finalRow, (idx) -> finalCol - idx)) {
                    matches++;
                }

                if (canGoRight && isValid(characters, searchChars, (idx) -> finalRow, (idx) -> finalCol + idx)) {
                    matches++;
                }
            }
        }

        System.out.println("XMAS Matches: " + matches / 2);
    }

    private static boolean isValid(char[][] chars,
                                   char[] searchChars,
                                   Function<Integer, Integer> rowSupplier,
                                   Function<Integer, Integer> colSupplier) {
        boolean forwardMatch = true;
        boolean backwardMatch = true;

        for (int i = 0; i < searchChars.length; i++) {
            if (forwardMatch && chars[rowSupplier.apply(i)][colSupplier.apply(i)] != searchChars[i]) {
                forwardMatch = false;
            }

            if (backwardMatch && chars[rowSupplier.apply(i)][colSupplier.apply(i)] != searchChars[searchChars.length - i - 1]) {
                backwardMatch = false;
            }
        }

        return forwardMatch || backwardMatch;
    }
}
