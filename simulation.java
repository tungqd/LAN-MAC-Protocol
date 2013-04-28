/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lmp;

import java.util.Scanner;

/**
 *
 * @author tdang
 */
public class simulation {
    //Number of stations
    public static int N = 5;
    //number of runs
    public static int RUNCOUNT = 100;
    
    station[] S = new station[N];
    
    public int run(int N) {
        int time = 0;
        int i;
        //Initialize 
        for (i=0;i<N;i++) {
            S[i].reset();
        }
        while (true) {
            int count = 0;
            int j = -1;
            for (i=0; i<N; i++) {
                if (S[i].transmits(time)) {
                    j=i;
                    ++count;
                }
            }
            if (count ==1) {
                return time;
            }
            else if (count > 1) {
                for (i=0;i<N;i++) {
                    if (S[i].transmits(time)) {
                        S[i].collide();
                    }
                }
            ++time;
            }
        }
    }
    
    public static void main(String argv[]) {
        simulation S = new simulation();
        int i, runsum = 0;
        for (i=0; i<RUNCOUNT; i++) {
            runsum += S.run(N);
        }
        System.out.println("runsum =" + runsum + "RUNCOUNT = " +
                RUNCOUNT + "average= " + ((double) (runsum / RUNCOUNT)));
    }
}
