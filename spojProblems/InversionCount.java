package binaryIndexedTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class InversionCount{
	static int[] bit;
	static int bitN;
	static int N;
	
	static int getNext(int N){
		return N + (N & -N);
	}
	static int getParent(int N){
		return N - (N & -N);
	}
	static void update(int i){
		int counter = i+1;
		while(counter<bitN){
			bit[counter]++;
			counter = getNext(counter);
		}
	}
	
	static int read(int i){
		int counter = i+1;
		int sum = 0;
		while(counter>0){
			sum+= bit[counter];
			counter = getParent(counter);
		}
		return sum;
	}
	
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int testcases = Integer.parseInt(br.readLine());
		for(int t=0;t<testcases;t++){
			br.readLine();
			N  = Integer.parseInt(br.readLine());
			bitN = N+1;
			bit = new int[bitN];
			int[] ip = new int[N];
			Integer[] order = new Integer[N];
			int[] sortOrder = new int[N];
			for(int i=0;i<N;i++){
				ip[i] = Integer.parseInt(br.readLine());
			}
			for(int i=0;i<N;i++){
				order[i] = i;
			}
			Arrays.sort(order, (a,b)-> Integer.compare(ip[a],ip[b]));
			for(int i=0;i<N;i++){
				sortOrder[order[i]]=i;
			}
			long ans = 0;
			for(int i=N-1;i>=0;i--){
				ans += read(sortOrder[i]);
				update(sortOrder[i]);
			}
			pw.println(ans);
		}
		pw.flush();
		pw.close();
	}
}