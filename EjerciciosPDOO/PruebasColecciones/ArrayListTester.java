package PruebasColecciones;

import java.util.ArrayList;

/**
 * Material del libro Big Java, Student Edition - http://bcs.wiley.com/he-bcs/Books?action=sitemap&itemId=0471697036&bcsId=2291
 * ¡Lo tenéis en la biblioteca!
 * 
   This program tests the ArrayList class.
*/
public class ArrayListTester
{
   public static void main(String[] args)
   {
      ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
      accounts.add(new BankAccount(1001));
      accounts.add(new BankAccount(1015));
      accounts.add(new BankAccount(1729));
      accounts.add(1, new BankAccount(1008));
      accounts.remove(0);

      System.out.println("size=" + accounts.size());
      BankAccount first = accounts.get(0);
      System.out.println("first account number=" + first.getAccountNumber());
      BankAccount last = accounts.get(accounts.size() - 1);
      System.out.println("last account number=" + last.getAccountNumber());
   }
}
