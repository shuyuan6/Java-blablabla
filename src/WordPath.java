import java.util.*;
public class WordPath {

    /*
     *  source: "abcd"
     *  target: "wxyz"
     *  dict: ["abcz", "abyz", "axyz", "bcfe", axyh"]
     *
     *  output ["abcd", "abcz", "abyz", "axyz", "wxyz"]
     *
     *  Graph: vertex, edge
     *
     *  Map<String, List<String>> word2words = new HashMap<String, LinkedList<String>>();
     *  word2words.put("abcd", new LinkedList<String>());
     *  word2words.get("abcd").add("abcz");
     *
     *  // { "abcd" : ["abcz"]}
     *
     *  word2words.get("abcd").add("abch");
     *
     *  // { "abcd" : ["abcz", "abch"]}
     *
     *  word2words.put("abcz", new LinkedList<String>());
     *  word2words.get("abcz").add("abcd");
     *
     *  // {
     *         "abcd" : ["abcz", "abch"],
     *         "abcz" : ["abcd"]
     *     }
     */

     static int getDistance(String word1, String word2){
         int distance = 0;
         for (int i =  0; i < word1.length(); i++){
             if (word1.charAt(i) != word2.charAt(i)){
                 distance++;
             }
         }
         return distance;
     }

     static List<String> findWordPath(String source, String target, List<String> dict) {

         Map<String, List<String>> word2words = new HashMap<>();
         List<String> words = new ArrayList<>();
         words.add(source);
         words.add(target);
         for(int i = 0; i < dict.size(); i++){
             words.add(dict.get(i));
         }

         for (String word1 : words){
             word2words.put(word1, new ArrayList<>());
             for (String word2 : words){
                 if (getDistance(word1, word2) == 1){
                     word2words.get(word1).add(word2);
                 }
             }
         }

         Set<String> keys = word2words.keySet();
         for (String k : keys){
             List<String> value = word2words.get(k);
             System.out.printf("Key: %s, ", k);
             System.out.print("Value: ");
             for (String v : value){
                 System.out.printf("%s ", v);
             }
             System.out.print("\n");
         }

         Set<String> visited = new HashSet<>();
         Queue<List<String>> q = new LinkedList<>();
         List<String> path = new LinkedList<>();
         path.add(source);
         q.add(path);
         for(String e : path){
             System.out.println(e);
         }
         System.out.println();
         visited.add(source);

         while(!q.isEmpty()){

             List<String> curr = q.remove();
             String last = curr.get(curr.size()-1);
             if(last.equals(target)){
                 return curr;
             }
             // if curr is target we are done
             List<String> neighbors = word2words.get(last);
             for (String neighbor : neighbors) {
                 if (!visited.contains(neighbor)) {
                     visited.add(neighbor);
                     List<String>curr1 = new ArrayList<>(curr);
                     curr1.add(neighbor);
                     q.add(curr1);
                     for(String e : curr1) {
                         System.out.println(e);
                     }
                     System.out.println();
                 }
             }
         }
         return new ArrayList<>();
     }

    public static void main(String[] args) {
        //int d = getDistance("abc", "xyz");
        //System.out.println("distance = " + d);

        List<String> dict = new ArrayList<>();
        dict.add("abz");
        dict.add("asc");
        dict.add("ayz");
        dict.add("agw");
        dict.add("asb");
        dict.add("abw");
        dict.add("agz");

        List<String> n = findWordPath("abc", "agz", dict);
        System.out.println("The answer is: ");
        for(String m : n){
            System.out.println(m);
        }
        //System.out.printf("There is a path: %b", n);
    }
}




