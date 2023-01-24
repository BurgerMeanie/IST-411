/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Arrays;
import java.util.concurrent.locks.condition;
import java.util.concurrent.locks.Locks;
import java.util.concurrent.locks.ReentrantLock;

package deadlock;


/*
Project: Lab 2
Purpose Details: Threading Synchronization
Course: IST 411
Author: Team 1 (Kelly Bergamini, William Allwein, Hun Bae, Phillip Berry, Allea McFarlane)
Date Developed: 1/24/2023
Last Date Changed:
Revision: 1
*/

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
}
