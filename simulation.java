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
    public static int RUNCOUNT = 100;
    
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
        int hostA = 0;
        int hostB = 0;
        int hostC = 0;
        int hostD = 0;
        int hostE = 0;
        
        for (i=0; i<RUNCOUNT; i++) {
            ArrayList oneRun = run(N);
            for (int j=0; j<oneRun.size(); j++) {
                
            	int temp = (Integer) oneRun.get(j);
            	
                if(j == 0)
                {
                	hostA = hostA + temp;
                }
                if(j == 1)
                {
                	hostB = hostB + temp;
                }
                if(j == 2)
                {
                	hostC = hostC + temp;
                }
                if(j == 3)
                {
                	hostD = hostD + temp;
                }
                if(j == 4)
                {
                	hostE = hostE + temp;
                }
            }
        }
        System.out.println(hostA/RUNCOUNT);
        System.out.println(hostB/RUNCOUNT);
        System.out.println(hostC/RUNCOUNT);
        System.out.println(hostD/RUNCOUNT);
        System.out.println(hostE/RUNCOUNT);
        
        //System.out.println("runsum =" + runsum + "RUNCOUNT = " +
        //        RUNCOUNT + "average= " + ((double) (runsum / RUNCOUNT)));
    }
}
