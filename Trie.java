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
            ret += "\nl: " + this.left;
            ret += "\nr: " + this.right;
            ret += "\nm: " + this.middle;
            ret += "\ncount: " + this.count;
            ret += "\nisWord: " + this.isWord + " ]";
            return ret;
         }
   }

   /** Return true if the Trie is empty, else false */
   public boolean isEmpty() {
      if (root == null) return true;
      else return false;
   }

   private int addHelper (String s, Node curr) {
      // Base case: reached a null node
      if (curr == null) {
         curr = new Node(s.charAt(0));
         // Insert all remaining characters to the middle
         for (int i = 1; i < s.length(); i++) {
            curr.middle = new Node(s.charAt(i));
            curr = curr.middle;
         }
         curr.isWord = true;
         curr.count++;
         return curr.count;
      } else {
         if (s.charAt(0) == curr.data) {
            // String is already in the Trie
            if (s.length() == 1) {
               curr.isWord = true;
               curr.count++;
               return curr.count;
            }
            // Erase one character and go to node's middle
            addHelper(s.substring(1), curr.middle);

         } else if (s.charAt(0) < curr.data) { // Go to curr's left
            addHelper(s, curr.left);
         } else {
            // Go to node's right
            addHelper(s, curr.right);
         }
      }
      return 0;
   }

   public int add (String s) {
      // Edge case: empty string
      if (s.length() == 0) {
         return -1;
      }

      // Base case: if trie is empty, initialize it
      if (root == null) {
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


} // End of Trie class
