/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Project: Lab 2
Purpose Details: Threading Synchronization
Course: IST 411
Author: Team 1 (Kelly Bergamini, William Allwein, Hun Bae, Phillip Berry, Allea McFarlane, Jason Fang)
Date Developed: 1/24/2023
Last Date Changed:
Revision: 1
*/

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    private Condition sufficientFunds = bankLock.newCondition();

    public Bank(int n, double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) throws InterruptedException{
        bankLock.lock();
        try{
            while(accounts[from] < amount){
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signal();
        }finally{
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        bankLock.lock();
        try{
            double sum = 0;
            for(double a : accounts){
                sum+= a;
            }
            return sum;
        }finally{
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }

}
