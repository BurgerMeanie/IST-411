/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/*
Project: Lab 2
Purpose Details: Threading Synchronization
Course: IST 411
Author: Team 1 (Kelly Bergamini, William Allwein, Hun Bae, Phillip Berry, Allea McFarlane)
Date Developed: 1/24/2023
Last Date Changed:
Revision: 1
*/

public class BankDeadlock {

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++){
            int fromAccount = i;
            Runnable r = () -> {
                try{
                    while (true){
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(toAccount, fromAccount, amount);
                    }
                } catch (InterruptedException ex){

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

}
