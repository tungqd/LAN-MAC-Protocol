/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lmp;

import java.util.Random;

/**
 *
 * @author tdang
 */
public class station {
    private int nextAttempt;
    private int collisionCount;
    
    public station() {
        nextAttempt = 0;
        collisionCount = 0;
    }
    public void reset() {
        nextAttempt = collisionCount = 0;
    }
    public boolean transmits(int T) {
        return nextAttempt == T;
    }
    public void collide() {
        collisionCount ++;
        nextAttempt += 1 + backoff(collisionCount);
    }
    private static int backoff(int k) {
        Random rand = new Random();
        int n = k;
        int delayVal = rand.nextInt((int) (Math.pow(2, n))-1);
        return delayVal;
        
    }
    
}
