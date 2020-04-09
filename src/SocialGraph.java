import java.util.*;

public class SocialGraph {

        public static class NameAndGeneration {
            private String id;
            private int generation;

            public int getGeneration(){
                return generation;
            }
            public String getId(){
                return id;
            }
            public NameAndGeneration(String id, int generation){
                this.id = id;
                this.generation = generation;
            }

        }


    static Set<String> find2ndDegreeFriends(String id, List<String> relations) {
        Map<String, List<String>> friends = new HashMap<>();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < relations.size(); i++){
            names.add(relations.get(i));
        }

        for (int i = 0; i < names.size(); i++){
            if (i % 2 == 0) {
                String name1 = names.get(i);
                String name2 = names.get(i+1);
                if (!friends.containsKey(name1)){
                    friends.put(name1, new ArrayList<>());
                }
                if (!friends.containsKey(name2)){
                    friends.put(name2, new ArrayList<>());
                }
                friends.get(name1).add(name2);
                friends.get(name2).add(name1);
            }
        }

        Set<String> keys = friends.keySet();
        for (String k : keys){
            List<String> value = friends.get(k);
            System.out.printf("Key: %s, ", k);
            System.out.print("Value: ");
            for (String v : value){
                System.out.printf("%s ", v);
            }
            System.out.print("\n");
        }

        Set<String> visited = new HashSet<>();
        Queue<NameAndGeneration> q = new LinkedList<>();
        NameAndGeneration r = new NameAndGeneration(id, 0);
        Set<String> result = new HashSet<>();
        q.add(r);
        visited.add(r.getId());

        while (!q.isEmpty()){
            NameAndGeneration curr = q.remove();
            List<String> neighbor = friends.get(curr.getId());
            for (int i = 0; i < neighbor.size(); i++){
                if (!visited.contains(neighbor.get(i))){
                    visited.add(neighbor.get(i));
                    NameAndGeneration nr = new NameAndGeneration(neighbor.get(i), curr.getGeneration()+1);
                    q.add(nr);
                    result.add(neighbor.get(i));
                }
            }
            if (curr.getGeneration() >= 2){
                break;
            }
        }
        return result;
    }


    public static void main(String [] args) {
        String id = "b";
        List<String> relations = new ArrayList<>();

        relations.add("a");
        relations.add("b");

        relations.add("b");
        relations.add("c");

        relations.add("a");
        relations.add("c");

        relations.add("c");
        relations.add("d");

        relations.add("d");
        relations.add("e");

        relations.add("e");
        relations.add("f");

        relations.add("c");
        relations.add("e");

        Set<String> ret = find2ndDegreeFriends(id, relations);
        for (String i : ret){
            System.out.println("Friends 2 degree: " + i);
        }



            // ret must be {"b", "c", "d", "e"}
    }
}
