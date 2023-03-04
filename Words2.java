import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Words2 {

    static boolean checkIFConnected(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                count++;
        }
        if (count == 1)
            return true;
        else
            return false;
    }

    static int bfs(Map<String, List<String>> graph, String[] words, String source, String target) {
        List<String> list = new ArrayList<>();
        // adjecent to the source// but , big
        for (int i = 0; i < words.length; i++) {
            if (checkIFConnected(source, words[i])) {
                list.add(words[i]);
            }
        }
        int count = 1;
        Set<String> visited = new HashSet<>();
        List<String> tempList = new ArrayList<>();
        while (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                for (String s : graph.get(list.get(i))) {
                    if (s.equals(target))
                        return count;
                    if (!visited.contains(s))
                        tempList.add(s);
                }
                if (i == list.size() - 1) {
                    count++;
                    list = tempList;
                    tempList = new ArrayList<>();
                }
            }
        }
        return -1;
    }

    static int shortestWordEditPath(String source, String target, String[] words) {
        // your code goes here
        // make a graph
        // make a adgecency list for the source
        // bfs from source to destination
        // if we find the dest word we return the count
        // else we return -1;

        Map<String, List<String>> graph = new HashMap<>();
        // create the graph
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // check if the words are connected
                String word1 = words[i];
                String word2 = words[j];
                List<String> l1 = graph.getOrDefault(word1, new ArrayList<>());
                List<String> l2 = graph.getOrDefault(word2, new ArrayList<>());
                if (checkIFConnected(word1, word2)) {
                    l1.add(word2);
                    l2.add(word1);
                }
                graph.put(word1, l1);
                graph.put(word2, l2);
            }
        }
        System.out.println(graph);
        // return 0;
        return bfs(graph, words, source, target);

    }

    public static void main(String[] args) {
        int ans=shortestWordEditPath("bit", "dog", new String[] { "but", "put", "big", "pot", "pog", "dog", "lot" });
        System.out.println(ans);
    }
}
