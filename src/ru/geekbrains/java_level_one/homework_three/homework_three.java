package ru.geekbrains.java_level_one.homework_three;

import java.util.Random;
import java.util.Scanner;

public class homework_three {

    // human
    private static final char DOT_HUMAN = 'X';
    // AI
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '_';
    // humanInput
    private static final Scanner SCANNER = new Scanner(System.in);
    // aiInput
    private static final Random RANDOM = new Random();
    // fieldSizeX
    private static int fieldSizeX;
    // fieldSizeY
    private static int fieldSizeY;
    // field
    private static char[][] field;

    // initField
    private static void initField() {
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }
    // printField
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++)
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++)
                System.out.print(field[i][j] + "|");
            System.out.println();
        }

        for (int i = 0; i <= fieldSizeX * 2 + 1; i++)
            System.out.print("-");
        System.out.println();
    }
    // humanTurn
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Введите координаты (гор и вер) от 1 до " + fieldSizeX + " через пробел>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
    }

    // artificialIntelligence
    private static int[] artificialIntelligence(int b[]) {
        int x;
        int y;

        // lineCheckWin
        for (int j = 0; j < fieldSizeY; j++) {
            char a[] = new char[3];
            for (int i = 0; i < fieldSizeX; i++) {
                a[i] = field[j][i];
                if (field[j][i] == DOT_EMPTY) {
                    b[0] = j;
                    b[1] = i;
                }
            }
            if ((a[0] == DOT_AI && a[1] == DOT_AI && a[2] == DOT_EMPTY)
                    || (a[0] == DOT_AI && a[1] == DOT_EMPTY && a[2] == DOT_AI)
                    || (a[0] == DOT_EMPTY && a[1] == DOT_AI && a[2] == DOT_AI)) {
                return b;
            }
        }

        // columnCheckWin
        for (int i = 0; i < fieldSizeX; i++) {
            char a[] = new char[3];
            for (int j = 0; j < fieldSizeY; j++) {
                a[j] = field[j][i];
                if (field[j][i] == DOT_EMPTY) {
                    b[0] = j;
                    b[1] = i;
                }
            }
            if ((a[0] == DOT_AI && a[1] == DOT_AI && a[2] == DOT_EMPTY)
                    || (a[0] == DOT_AI && a[1] == DOT_EMPTY && a[2] == DOT_AI)
                    || (a[0] == DOT_EMPTY && a[1] == DOT_AI && a[2] == DOT_AI)) {
                return b;
            }
        }

        // diagonalCheckWin
        char s[] = new char[3];
        char f[] = new char[3];
        for (int i = 0; i < fieldSizeX; i++) {
            s[i] = field[i][i];
            f[i] = field[i][fieldSizeX - 1 -i];
            if (field[i][i] == DOT_EMPTY) {
                b[0] = i;
                b[1] = i;
            }
        }
        if ((s[0] == DOT_AI && s[1] == DOT_AI && s[2] == DOT_EMPTY)
                || (s[0] == DOT_AI && s[1] == DOT_EMPTY && s[2] == DOT_AI)
                || (s[0] == DOT_EMPTY && s[1] == DOT_AI && s[2] == DOT_AI)) {
            return b;
        }
        for (int i = 0; i < fieldSizeX; i++) {
            s[i] = field[i][i];
            f[i] = field[i][fieldSizeX - 1 -i];
            if (field[i][fieldSizeX - 1 -i] == DOT_EMPTY) {
                b[0] = fieldSizeX - 1 -i;
                b[1] = i;
            }
        }
        if ((f[0] == DOT_AI && f[1] == DOT_AI && f[2] == DOT_EMPTY)
                || (f[0] == DOT_AI && f[1] == DOT_EMPTY && f[2] == DOT_AI)
                || (f[0] == DOT_EMPTY && f[1] == DOT_AI && f[2] == DOT_AI)) {
            return b;
        }

        // lineCheckSecurity
        for (int j = 0; j < fieldSizeY; j++) {
            char a[] = new char[3];
            for (int i = 0; i < fieldSizeX; i++) {
                a[i] = field[j][i];
                if (field[j][i] == DOT_EMPTY) {
                    b[0] = j;
                    b[1] = i;
                }
            }
            if ((a[0] == DOT_HUMAN && a[1] == DOT_HUMAN && a[2] == DOT_EMPTY)
                    || (a[0] == DOT_HUMAN && a[1] == DOT_EMPTY && a[2] == DOT_HUMAN)
                    || (a[0] == DOT_EMPTY) && a[1] == DOT_HUMAN && a[2] == DOT_HUMAN) {
                return b;
            }
        }

        // columnCheckSecurity
        for (int i = 0; i < fieldSizeX; i++) {
            char a[] = new char[3];
            for (int j = 0; j < fieldSizeY; j++) {
                a[j] = field[j][i];
                if (field[j][i] == DOT_EMPTY) {
                    b[0] = j;
                    b[1] = i;
                }
            }
            if ((a[0] == DOT_HUMAN && a[1] == DOT_HUMAN && a[2] == DOT_EMPTY)
                    || (a[0] == DOT_HUMAN && a[1] == DOT_EMPTY && a[2] == DOT_HUMAN)
                    || (a[0] == DOT_EMPTY) && a[1] == DOT_HUMAN && a[2] == DOT_HUMAN) {
                return b;
            }
        }

        // diagonalCheckSecurity
        char k[] = new char[3];
        char l[] = new char[3];
        for (int i = 0; i < fieldSizeX; i++) {
            k[i] = field[i][i];
            l[i] = field[i][fieldSizeX - 1 -i];
            if (field[i][i] == DOT_EMPTY) {
                b[0] = i;
                b[1] = i;
            }
        }
        if ((k[0] == DOT_HUMAN && k[1] == DOT_HUMAN && k[2] == DOT_EMPTY)
                || (k[0] == DOT_HUMAN && k[1] == DOT_EMPTY && k[2] == DOT_HUMAN)
                || (k[0] == DOT_EMPTY && k[1] == DOT_HUMAN && k[2] == DOT_HUMAN)) {
            return b;
        }
        for (int i = 0; i < fieldSizeX; i++) {
            l[i] = field[i][fieldSizeX - 1 -i];
            if (field[fieldSizeX - 1 -i][i] == DOT_EMPTY) {
                b[0] = fieldSizeX - 1 -i;
                b[1] = i;
            }

        }
        if ((l[0] == DOT_HUMAN && l[1] == DOT_HUMAN && l[2] == DOT_EMPTY)
                || (l[0] == DOT_HUMAN && l[1] == DOT_EMPTY && l[2] == DOT_HUMAN)
                || (l[0] == DOT_EMPTY && l[1] == DOT_HUMAN && l[2] == DOT_HUMAN)) {
            return b;
        }


        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        b[0] = y;
        b[1] = x;
        return b;
    }

    // aiTurn
    private static void aiTurn() {
        int x;
        int y;
        int b[] = new int[2];
        artificialIntelligence(b);
        y = b[0];
        x = b[1];
        field[y][x] = DOT_AI;
    }
    // isCellEmpty
    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }
    // isCellValid
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
    }
    // checkWin
    private static boolean checkWin(char c) {

        int win = c;

        for (int j = 0; j < fieldSizeY; j++) {
            int sum = 0;
            for (int i = 0; i < fieldSizeX; i++) {
                sum += field[j][i];
            }
            if (sum / fieldSizeX == win) return true;
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int sum = 0;
            for (int j = 0; j < fieldSizeY; j++) {
                sum += field[j][i];
            }
            if (sum / fieldSizeY == win) return true;
        }
        int j = fieldSizeX - 1;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i <= j; i++) {
            sum1 += field[i][i];
            sum2 += field[i][j-i];
        }
        if (sum1 / fieldSizeX == win || sum2 / fieldSizeX == win) return true;


        return false;
    }
    // checkDraw
    private static boolean isDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Human win!");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
        }
    }
}
