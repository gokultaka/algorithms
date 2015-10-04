package binaryIndexedTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class SpojCardTrick {
	static int bitN;
	static int bit[];
	static void update(int i,int val){
		int counter = i+1;
		while(counter<bitN){
			bit[counter]= bit[counter]+val;
			counter += (counter & -counter);
		}
	}
	
	static int read(int i){
		//number of space till i
		int counter = i+1;
		int ans=0;
		while(counter>0){
			ans+= bit[counter];
			counter -= (counter & -counter);
		}
		return ans;
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int testcases = Integer.parseInt(br.readLine());
		for(int t=0;t<testcases;t++){
			int n = Integer.parseInt(br.readLine());
			bitN = n+1;
			bit = new int[bitN];
			for(int i=0;i<n;i++){
				update(i,1);
			}
			
			//bit will give how many spaces exists till i
			int[] ans = new int[n];
			Arrays.fill(ans, Integer.MAX_VALUE);
			int lastPos =-1;
			for(int i=0;i<n;i++){
				//have to fill from 1 to n
				int toFill = i+1;
				int ithPlace = (read(lastPos)+(toFill)) % (n-i) +1; //eg for i=0, ithPlace = 2 => 2nd free place is the position
				//2nd free place implies , do a binary search to find minimum X such that, there are atleast 1 space before
				int high = n-1;
				int low = 0;
				
				while(low<=high){
					int mid = (low)+(high-low)/2;
					int freeSpacesUpToMid = read(mid);
					if(freeSpacesUpToMid < ithPlace){
						low = mid+1;
					}else{
						if(freeSpacesUpToMid > ithPlace){
							high = mid-1;
						}else{
							//check if this space is free
							if (ans[mid]== Integer.MAX_VALUE){
								//it is free
								lastPos = mid;
								ans[mid]=toFill;
								update(mid,-1);
								break;
							}else{
								high = mid-1;
							}
						}
					}
				}
				
				
			
			}
			
			for(int i=0;i<n;i++){
				pw.print(ans[i]+" ");
			}
			pw.println();
			
			
			
		}
		pw.flush();
		pw.close();
	}
}
