package tries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Node{
	Node left, right;
	int value;
	public Node(){
		this.left  = this.right = null;
	}
	public void setValue(int n){
		value= n;
	}
	public int getValue(){
		return value;
	}
}
class Trie{
	int size;
	static int[] shift = new int[31];
	public Trie(int size){
		this.size = size;
		for(int i=0;i<31;i++){
			shift[i] = 1<< i;
		}
	}
	public void insert(Node root,int val){
		for(int i=size;i>=0;i--){
			//check if ith bit is set
			int checkSet = (val & shift[i]);
			if(checkSet ==0 ){
				if(root.left == null){
					root.left = new Node();
				}
				root = root.left;
			}else{
				if(root.right == null){
					root.right = new Node();
				}
				root  = root.right;
			}
		}
		root.setValue(val);
	}
	
	public int query(Node root,int val){
		for(int i=size;i>=0;i--){
			int checkSet = (val & shift[i]);;
			if(checkSet == 0){
				if(root.right != null){
					root = root.right;
				}else {
					root = root.left;
				}
			}else {
				if(root.left != null){
					root = root.left;
					
				}else {
					root = root.right;
				}
			}
		}
		return root.value;
	}
}
public class CodeChefRebxorAgain {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] ip = new int[N];
		int[] lbest = new int[N];
		int[] rbest = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			ip[i] = Integer.parseInt(st.nextToken());
		}
		Node left = new Node();
		int maxSize = 30;
		
		Trie trie = new Trie(maxSize);
		trie.insert(left,0);
		trie.insert(left, ip[0]);
		int xor = ip[0];
		lbest[0] = xor;
		for(int i=1;i<N;i++){
			xor = xor ^ ip[i];
			trie.insert(left, xor);
			int max = trie.query(left, xor);
			int maxVal = max ^ xor;
			lbest[i] = Math.max(maxVal, lbest[i-1]);
		}
		
		Node right = new Node();
		trie = new Trie(maxSize);
		trie.insert(right,0);
		trie.insert(right, ip[N-1]);
		xor = ip[N-1];
		rbest[N-1] = xor;
		for(int i=N-2; i>=0 ;i--){
			xor = xor ^ ip[i];
			trie.insert(right, xor);
			int max = trie.query(right,xor);
			int maxVal = max ^ xor;
			rbest[i] = Math.max(maxVal,rbest[i+1]);
		}
		int ans = rbest[0];
		for(int i=0;i<N;i++){
			if(i==N-1){
				ans = Math.max(ans, lbest[N-1]);
			}else {
				int temp = lbest[i]+rbest[i+1];
				ans = Math.max(temp, ans);
			}
		}
		pw.println(ans);
		pw.flush();
		pw.close();
	
	}
	
}
