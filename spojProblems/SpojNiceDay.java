package binaryIndexedTree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

 class SpojNiceDay {
	static class Node implements Comparable<Node>{
		public int a,b,c;
		Node(int a,int b,int c){
			this.a = a;
			this.b = b;
			this.c = c;
		}
		@Override
		public int compareTo(Node a) {
			return (this.a < a.a ? -1:1);
		}
	}
	
	static int read (int n){
		int counter = n+1;
		int min = Integer.MAX_VALUE;
		while(counter>0){
			min = Math.min(bit[counter],min);
			counter = getParent(counter);
		}
		return min;
	}
	static int getParent(int N){
		return N - (N & -N);
	}
	static int getNext(int N){
		return N + (N & -N);
	}
	
	static void update(int n,int val){
		int counter = n+1;
		while(counter<bitN){
			bit[counter]= Math.min(bit[counter], val);
			counter = getNext(counter);
		}
		
	}
	
	static int bitN;
	static int[] bit;
	public static void main(String[] args)throws Exception{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int testcases = ir.readInt();
		for(int t=0;t<testcases;t++){
			int n = ir.readInt();
			Node[] ip = new Node[n];
			for(int i = 0 ; i< n ; i++){
				Node temp = new Node(ir.readInt()-1, ir.readInt()-1, ir.readInt()-1);
				ip[i] = temp;
			}
			//Sort by 'a' competition
			Arrays.sort(ip);
			bitN = n+1;
			bit = new int[bitN];
			Arrays.fill(bit, Integer.MAX_VALUE);
			int e = 0;
			for(int i=0; i<n ;i++){
				//get second competition as index and check the best value
				Node temp = ip[i];
				int second = temp.b;
				int bestTillSecond = read(second);
				if(bestTillSecond > temp.c){
					e++;
				}
				update(second,temp.c);
			}
			pw.println(e);
			
		}
		pw.flush();
		pw.close();
	}

}
 
 class InputReader {
     private InputStream stream;
     private byte[] buf = new byte[102400]; //a byte buffer to store the characters in
     private int curChar; //to hold the count of current character
     private int numChars; //number of characters
    
     public InputReader(InputStream stream) { //pass the inputstream class
             this.stream = stream;
     }
     public final int read() {
             if (numChars == -1)
                     throw new InputMismatchException();
             if (curChar >= numChars) {
                     curChar = 0;
                     try {
                             numChars = stream.read(buf); //this is the implicit function present in the inputStream which reads a Character into a temporary buffer
                     } catch (IOException e) {
                             throw new InputMismatchException();
                     }
                     if (numChars <= 0)
                             return -1;
             }
             return buf[curChar++];
     }
     public final int readInt() { //a function to read nextInteger skipping the newlines and empty spaces
             int c = read();
             while (isSpaceChar(c)) //
                     c = read();
             int sgn = 1;
             if (c == '-') { //if the number is negative
                     sgn = -1;
                     c = read();
             }
             int res = 0; //integer variable to hold the number
             do {
                     if (c < '0' || c > '9')
                             throw new InputMismatchException();
                     res *= 10;
                     res += c - '0';
                     c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
     }
     public final long readLong() { //similar as Integer
             int c = read();
             while (isSpaceChar(c))
                     c = read();
             int sgn = 1;
             if (c == '-') {
                     sgn = -1;
                     c = read();
             }
             long res = 0;
             do {
                     if (c < '0' || c > '9')
                             throw new InputMismatchException();
                     res *= 10;
                     res += c - '0';
                     c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
     }
     public final String readString() {
             int c = read(); //read function returns one character at a time and the unicode value of the character is returned
             while (isSpaceChar(c))
                     c = read();
             StringBuilder res = new StringBuilder(); //using a String Builder to build the String
             do {
                     res.appendCodePoint(c); // appendCodePoint function to append the character from its unicode value
                     c = read();
             } while (!isSpaceChar(c));
             return res.toString();
     }
     public final  static boolean isSpaceChar(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
     }
     public final String next() {
             return readString();
     }
}
