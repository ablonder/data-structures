/**
 * Load a tree from a text file.  Format is line based, with each line
 * consisting of a String for data, followed by two ints indicating if
 * the node has a left child or right child.  (1 is yes, 0 is no).
 * Ordering of nodes is postorder.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 */

import java.io.*;
import java.util.*;

public class TreeLoader {

    BinaryTree<String> loadTreeFromFile(String fname) throws IOException
    {
    	Stack<BinaryTree<String>> treestack = new Stack<BinaryTree<String>>();
    	Scanner reader = null;
    	try{
    		reader = new Scanner(new File(fname));
    	} catch (FileNotFoundException e) {
    		System.out.println("File does not exist.");
    		System.exit(1);
    	}
    	String data;
    	int left;
    	int right;
    	BinaryTree<String> righttree;
    	BinaryTree<String> lefttree;
    	while(reader.hasNextLine() && reader.hasNext()){
    		lefttree = new EmptyTree<String>();
    		righttree = new EmptyTree<String>();
    		data = reader.next();
    		left = reader.nextInt();
    		right = reader.nextInt();
    		
    		if(right == 1) righttree = treestack.pop();
    		if(left == 1) lefttree = treestack.pop();
    		treestack.push(new ConsTree<String>(data, lefttree, righttree));
    		reader.nextLine();
    	}
    	
    	if(treestack.empty()) return new EmptyTree<String>();
        return treestack.pop();
    }

    // So you can test your tree loader
    public static void main(String[] args) throws IOException {
        if(args.length!=1){
            System.out.println("Usage:  java TreeLoader filename");
        }
        else {
            TreeLoader tl = new TreeLoader();
            BinaryTree<String> t = tl.loadTreeFromFile(args[0]);
            System.out.println(t);
        }
    }
}
