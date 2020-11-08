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
        fieldSizeX = 5;
        fieldSizeY = 5;
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
    // aiTurn
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
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
            int sumWinX = 0;
            for (int i = 0; i < fieldSizeX; i++) {
                int a = field[j][i];
                if (i > 0 && i < fieldSizeX - 1 && a != win) {
                    sumWinX = 0;
                    break;
                }
                if (a == win) sumWinX++;
                if (sumWinX == fieldSizeX - 1) return true;
            }
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int sumWinY = 0;
            for (int j = 0; j < fieldSizeY; j++) {
                int a = field[j][i];
                if (j > 0 && j < fieldSizeY - 1 && a != win) {
                    sumWinY = 0;
                    break;
                }
                if (a == win) sumWinY++;
                if (sumWinY == fieldSizeX - 1) return true;
            }
        }
        int j = fieldSizeX - 1;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i <= j; i++) {
            int a = field[i][i];
            int b = field[i][j - i];
            if (i > 0 && i < j && a != win) {
                sum1 = 0;
            }
            if (i > 0 && i < j && b != win) {
                sum2 = 0;
            }
            if (a == win) {
                sum1++;
            }
            if (b == win) {
                sum2++;
            }
            if (sum1 == j || sum2 == j) {
                return true;
            }
        }
        sum1 = 0;
        sum2 = 0;
        int sumA = 0;
        int sumB = 0;
        int sumC = 0;
        int sumD = 0;
        for (int i = 0; i < j; i++) {
            sumA += field[i][i + 1];
            sumB += field[j - i][i + 1];
        }
        for (int i = 1; i < fieldSizeY; i++) {
            sumC += field[i][i - 1];
            sumD += field[j - i][i - 1];
        }
        if (sumA / j == win ||
                sumB / j == win ||
                sumC / j == win ||
                sumD / j == win) return true;

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
