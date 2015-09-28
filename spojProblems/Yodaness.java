package binaryIndexedTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Yodaness {
	
	static int bitN;
	static int[] bit ;
	static void update(int N){
		int counter = N+1;
		while(counter<bitN){
			bit[counter]++;
			counter = getNext(counter);
		}
	}
	
	static int read(int N){
		int counter  = N+1;
		int ans = 0;
		while(counter >0){
			ans += bit[counter];
			counter = getParent(counter);
		}
		return ans;
	}
	static int getNext(int N){
		return N + (N & -N);
	}
	
	static int getParent(int N){
		return N - (N & -N);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int testcases = Integer.parseInt(br.readLine());
		for(int t=0;t<testcases;t++){
			int N = Integer.parseInt(br.readLine()); // no of words
			StringTokenizer st = new StringTokenizer(br.readLine());
			HashMap<String,Integer> yoda = new HashMap<String,Integer>();
			for(int i=0;i<N;i++){
				yoda.put(st.nextToken(), i);
			}
			bitN = N+1;
			bit = new int[bitN];
			int[] ip = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){
				ip[i] = yoda.get(st.nextToken());
			}
			
			int ans = 0;
			
			//Insert numbers from N-1 and check how many numbers less than N-1 are already in
			
			for(int i=N-1;i>=0;i--){
				ans += read(ip[i]);
				update(ip[i]);
			}
			pw.println(ans);
		}
		pw.flush();
		pw.close();
	}

}
