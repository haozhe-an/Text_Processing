import java.util.LinkedList;
import java.util.Iterator;

public class TestTrie {

   public static void main (String [] args) {
      LinkedList<String> lls = new LinkedList<String>();
      lls.add("test");
      lls.add("tests");
      lls.add("worst-case");
      lls.add("trie");
      lls.add("tree");
      lls.add("algorithm");
      lls.add("binary");
      lls.add("average-case");
      lls.add("average");
      lls.add("trie");
      lls.add("tree");
      lls.add("test");
      lls.add("tests");
      lls.add("tests");

      Trie t = new Trie();

      Iterator itr = lls.iterator();

      System.out.println(itr.next());

      while (itr.hasNext()){
         String toAdd = (String)itr.next();
         int count = t.add(toAdd);
         System.out.println("Adding " + toAdd + " to Trie.");
         if (count == -1) {
            System.out.println("Incorrect count while adding " + toAdd);
            System.exit(1);
         }
      }

      lls.add("tr");
      lls.add("te");
      lls.add("aver");
      lls.add("");
      lls.add("worr");

      itr = lls.iterator();
      while (itr.hasNext()) {
         String query = (String)itr.next();
         if (t.searchPrefix(query) == null) 
            System.out.println("NULL was returned");
         else
            System.out.println(t.searchPrefix(query).getData() + ", "
               + t.searchPrefix(query).getCount());
      }
   }
} // End of testing class
