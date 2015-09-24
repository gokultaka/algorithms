
import java.util.Arrays;

public class BinaryIndexedTreeImplementation {
	static int binaryBITN;
	public static void main(String[] args){
		int[] ip = {12,1,23,4,5,6,7,8,9,10};
		int n = ip.length;
		 binaryBITN = n+1;
		int[] binaryBIT = new int[binaryBITN];
		
		Arrays.fill(binaryBIT, 0);
		for(int i=0;i<=ip.length;i++){
			int counter = i+1;
			while(counter<binaryBITN){
				binaryBIT[counter]+=ip[i];
				counter = getNextPlace(counter);
			}
		}
		for(int i=0;i<n;i++){
			//cumulative sum from 0,i
			int index = i+1;
			int sum = 0;
			while(index>0){
				sum+= binaryBIT[index];
				index = getParent(index);
			}
			System.out.println("Value of S(0,"+i+") is :"+sum);
		}
	}
	static int getNextPlace(int counter){
		return counter + (counter & -counter);
	}
	static int getParent(int counter){
		return counter - (counter & -counter);
	}
	
	static int getSum(int[] binary,int index){
		index = index+1;
		int sum = 0;
		while(index>0){
			sum+= binary[index];
			index = getParent(index);
		}
		return sum;
	}
	
	
}
