import java.util.Comparator; 
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue; 
import java.util.Scanner; 

class Huffman { 

    // Function to calculate frequency of each character in the string
    public static Map<Character, Integer> calculateFrequency(String input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    // Function to build the Huffman tree using a priority queue
    public static HuffmanNode buildHuffmanTree(char[] charArray, int[] charfreq) {
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(charArray.length, new MyComparator());

        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        return root;
    }

    // Recursive function to print the Huffman code by traversing the Huffman tree
    public static void printCode(HuffmanNode root, String s) { 
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(root.c + ":" + s);
            return;
        }

        printCode(root.left, s + "0"); 
        printCode(root.right, s + "1"); 
    }

    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter a string:"); 
        String input = scanner.nextLine(); 

        // Step 1: Calculate frequency of each character
        Map<Character, Integer> frequencyMap = calculateFrequency(input);

        // Display character frequencies
        System.out.println("\nCharacter Frequencies:");
        frequencyMap.forEach((character, frequency) -> 
            System.out.println(character + ": " + frequency)
        );

        // Step 2: Prepare character array and frequency array from the map
        int n = frequencyMap.size();
        char[] charArray = new char[n];
        int[] charfreq = new int[n];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            charArray[i] = entry.getKey();
            charfreq[i] = entry.getValue();
            i++;
        }

        // Step 3: Build Huffman Tree
        HuffmanNode root = buildHuffmanTree(charArray, charfreq);

        // Step 4: Print Huffman Codes by traversing the tree
        System.out.println("\nHuffman Codes:");
        printCode(root, ""); 
    } 
} 

// Node class is the basic structure of each node in the Huffman tree
class HuffmanNode { 
    int data; 
    char c; 
    HuffmanNode left; 
    HuffmanNode right; 
} 

// Comparator class helps to compare nodes based on their frequency (data)
class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y) { 
        return x.data - y.data; 
    } 
} 
