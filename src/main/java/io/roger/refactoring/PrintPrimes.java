package io.roger.refactoring;

/* Knuth's PrintPrimes / "Clean Code" 👀 */

public class PrintPrimes {
  public static void main(String[] args) {
    final int MAX = 1000;
    final int MAXWORDS = 30;

    int[] p = new int[MAX + 1];
    p[1] = 2;

    int n;
    int j = 1, k = 1;
    boolean jPrime;
    int ord = 2;
    int square = 9;
    int mult[] = new int[MAXWORDS + 1];

    while (k < MAX) {
      do {
        j += 2;

        if (j == square) {
          ord++;
          square = p[ord] * p[ord];
          mult[ord - 1] = j;
        }

        n = 2;
        jPrime = true;

        while (n < ord && jPrime) {
          while (mult[n] < j) {
            mult[n] = mult[n] + p[n] * 2;
          }

          if (mult[n] == j) {
            jPrime = false;
          }

          n++;
        }
      } while (!jPrime);

      k++;
      p[k] = j;
    }

    something(MAX, p);
  }

  public static void something(int max, int[] p) {
    final int RR = 50;
    final int CC = 4;
    int pageNumber = 1;
    int pageOffset = 1;

    while (pageOffset <= max) {
      System.out.println("The First " + max + " Prime Numbers --- Page " + pageNumber);

      for (int rowOffset = pageOffset; rowOffset < pageOffset + RR; rowOffset++) {
        for (int c = 0; c < CC; c++) {
          if (rowOffset + c * RR <= max) {
            System.out.format("%10d", p[rowOffset + c * RR]);
          }
        }
      }

      System.out.println("\f");

      pageNumber = pageNumber + 1;
      pageOffset = pageOffset + RR * CC;
    }
  }
}
