/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lmp;

import java.util.ArrayList;


/**
 *
 * @author tdang
 */
public class simulation {
    //Number of stations
    public static int N = 5;
    //number of runs
    public static int RUNCOUNT = 1;
    
    public static station[] S = new station[N];
    
    public static ArrayList run(int N) {
        
        ArrayList<Integer> results = new ArrayList();   
        
        int time = 0;
        int i;
        //Initialize 
        for (int j=0; j<N; j++) {
            S[j] = new station();
        }
       
        for (i=0;i<N;i++) {
            S[i].reset();
        }
        while (results.size() <N) {
            int count = 0;
            //int j = -1;
            for (i=0; i<N; i++) {
                if (S[i].transmits(time)) {
                   // j=i;
                    ++count;
                }
            }
            if (count ==1) {
                results.add(time);
            }
            else if (count > 1) {
                for (i=0;i<N;i++) {
                    if (S[i].transmits(time)) {
                        S[i].collide();
                    }
                }
            }
             ++time;   
        }
        return results;
    }
    
    public static double avg(ArrayList arrivalTimes)
    {
        double total = 0;
        for(int i = 0;i <= arrivalTimes.size()-1; i++)
        {
            total =  total + (Integer) arrivalTimes.get(i);
        }
        return total / arrivalTimes.size(); // returns the average
    }
    
    public static void main(String argv[]) {
       
         
        int i, runsum = 0;
        for (i=0; i<RUNCOUNT; i++) {
            ArrayList oneRun = run(N);
            for (int j=0; j<oneRun.size(); j++) {
                
                System.out.println(oneRun.get(j));
            }
        }
        //System.out.println("runsum =" + runsum + "RUNCOUNT = " +
        //        RUNCOUNT + "average= " + ((double) (runsum / RUNCOUNT)));
    }
}
