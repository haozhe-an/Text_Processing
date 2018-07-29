import java.util.LinkedList;
import java.util.Iterator;

public class Trie {
   // Field of Trie class
   protected Node root;

   /** No-argument constructor. Initialize an empty Trie */
   public Trie () {
      root = null;
   }

   // Inner class that creates the node
   protected class Node {
      Node middle;
      Node left;
      Node right;

      char data;
      boolean isWord;
      int count;

      /** Constructor. Initialize a Trie Node with the given char without a child */
      public Node (char c) {
         data = c;
         // All other variables get default values
         count = 0;
         isWord = false;
      }

      public char getData(){
         return this.data;
      }

      public int getCount(){
         return this.count;
      }

      @Override
         public String toString() {
            String ret = "[data: " + this.data;
            ret += "  count: " + this.count;
            ret += "  isWord: " + this.isWord;
            ret += "\tl: " + this.left;
            ret += "\tr: " + this.right;
            ret += "\tm: " + this.middle + " ]";
            return ret;
         }
   }

   /** Return true if the Trie is empty, else false */
   public boolean isEmpty() {
      if (root == null) return true;
      else return false;
   }

   private int addHelper (String s, Node curr) {
      // Assuming curr cannot be null, but s can be an empty string
      if (s.length() == 0)
         return 0;

      char curr_char = s.charAt(0);
      // Base case 1:
      if (curr_char < curr.data && curr.left == null) {
         curr.left = new Node(curr_char);
         return addHelper(s, curr.left);
      }

      // Base case 2:
      if (curr_char == curr.data && curr.middle == null) {
         for (int i = 1; i < s.length(); i++) {
            curr.middle = new Node(s.charAt(i));
            curr = curr.middle;
         }
         curr.isWord = true;
         curr.count++;
         return curr.count;
      }

      // Base case 3:
      if (curr_char > curr.data && curr.right == null) {
         curr.right = new Node(curr_char);
         return addHelper(s, curr.right);
      }

      // Recursive cases
      if (curr_char < curr.data)
         return addHelper(s, curr.left);
      else if (curr_char > curr.data)
         return addHelper(s, curr.right);
      else 
         if (s.length() > 1)
            return addHelper(s.substring(1), curr.middle);
         else
            curr.isWord = true;
            curr.count++;
            return curr.count;
   }

   public int add (String s) {
      // Edge case: empty string
      if (s.length() == 0) {
         return -1;
      }

      // Base case: if trie is empty, initialize it
      if (isEmpty()) {
         root = new Node(s.charAt(0));
         Node curr = root;
         for (int i = 1; i < s.length(); i++) {
            curr.middle = new Node(s.charAt(i));
            curr = curr.middle;
         }
         curr.count = 1;
         curr.isWord = true;
         return curr.count;
      }

      // Else build tree recursively
      return addHelper(s, root);
   }


   /** Given a reference to a string, find it in the Trie.
    * Return the node corresponding to the last character of 
    * the input String; null if the String was not in the Trie. */
   public Node searchPrefix (String s) {
      if (s.length() == 0) return null;

      Node curr = root;
      Node prev = root;

      for (int i = 0; i < s.length(); ) {
         // If no such string is in the Trie, return nullptr
         if (curr == null) return null;

         // If equal, go to current node's middle
         if (s.charAt(i) == curr.data) {
            prev = curr;
            curr = curr.middle;
            i++;
         } else if (s.charAt(i) < curr.data) { // Go to left
            prev = curr;
            curr = curr.left;
         } else { // Go to right
            prev = curr;
            curr = curr.right;
         }
      }
      return prev;
   }

   /** Pre-order dfs */
   public LinkedList<Node> dfs (){
      LinkedList<Node> ret = new LinkedList<Node>();
      dfs_helper(ret, root);
      return ret;
   }

   private void dfs_helper(LinkedList<Node> list, Node curr) {
      if (curr == null)
         return;

      list.add(curr);
      dfs_helper(list, curr.left);
      dfs_helper(list, curr.middle);
      dfs_helper(list, curr.right);

   }

   @Override
      public String toString() {
         /*
            LinkedList<Node> result = dfs();
            Iterator itr = result.iterator();
            String ret = "";

            while (itr.hasNext()){
            ret += itr.next().toString() + "\n";
            }

            return ret; */
         return root.toString();
      }


} // End of Trie class
