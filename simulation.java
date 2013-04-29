/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lmp;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 *
 * @author tdang
 */
public class simulation {
    //Number of stations
    public static int N = 20;
    //number of runs
    public static int RUNCOUNT = 100;
    
    public static station[] S = new station[N];
    static DecimalFormat numberFormat = new DecimalFormat("#.00");
    
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
    
    public static void main(String argv[]) {
             
        int i;
        int[] host = new int[N];
        
        for (i=0; i<RUNCOUNT; i++) {
            ArrayList oneRun = run(N);
            for (int j=0; j<oneRun.size(); j++) {
               // System.out.println(oneRun.get(j));
            	host[j] += (Integer) oneRun.get(j);          
            }
        }
        double[] avg = new double[N];
       for (i=0; i<N; i++) {
           avg[i] = ((double) host[i]/RUNCOUNT) ;
           System.out.println("Average delayed time for " + (i+1) + " is: " 
                   + numberFormat.format(avg[i]));
       }

    }
}
