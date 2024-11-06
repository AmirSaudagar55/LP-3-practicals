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
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(charArray.length, new MyComparator());   //priority queue with size of CharArray Length and with custom comparator object 

        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);                  //The first node will never be compare so, object of MyComparator will not invoked. Upon adding second node comparator will compare and places the node with less frequency at head of queue so we can build huffman tree and for further nodes it will compared them with already present nodes in queue.
        }

        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.poll();       //poll() : It will fetch the first element from queue and pop it
            HuffmanNode y = q.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }

        return q.peek();  // Return the single node if only one node is left
    }

    // Recursive function to print the Huffman code and handle single-node cases
    public static void printCode(HuffmanNode root, String s, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If the tree has only one node, assign "0" to that character
        if (root.left == null && root.right == null) {
            if (s.equals("")) s = "0";  // Single character case
            System.out.println(root.c + ":" + s);
            huffmanCodes.put(root.c, s);
            return;
        }

        printCode(root.left, s + "0", huffmanCodes);
        printCode(root.right, s + "1", huffmanCodes);
    }

    // Function to calculate total bits required before and after Huffman encoding
    public static void calculateAndDisplayBits(String input, Map<Character, Integer> frequencyMap, Map<Character, String> huffmanCodes) {
        // Total bits before Huffman encoding (fixed-length encoding)
        int uniqueChars = frequencyMap.size();
        int bitsPerChar = (uniqueChars == 1) ? 1 : (int) Math.ceil(Math.log(uniqueChars) / Math.log(2));
        int totalBitsBefore = input.length() * bitsPerChar;
        
        System.out.println("\nTotal Bits Before Huffman Encoding (Fixed-Length): " + totalBitsBefore + " bits");

        // Total bits after Huffman encoding
        int totalBitsAfter = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();
            int codeLength = huffmanCodes.get(character).length();
            totalBitsAfter += frequency * codeLength;
        }

        System.out.println("Total Bits After Huffman Encoding: " + totalBitsAfter + " bits");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        Map<Character, Integer> frequencyMap = calculateFrequency(input);

        System.out.println("\nCharacter Frequencies:");
        frequencyMap.forEach((character, frequency) ->
            System.out.println(character + ": " + frequency)
        );

        int n = frequencyMap.size();
        char[] charArray = new char[n];
        int[] charfreq = new int[n];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            charArray[i] = entry.getKey();
            charfreq[i] = entry.getValue();
            i++;
        }

        HuffmanNode root = buildHuffmanTree(charArray, charfreq);

        System.out.println("\nHuffman Codes:");
        Map<Character, String> huffmanCodes = new HashMap<>();
        printCode(root, "", huffmanCodes);

        calculateAndDisplayBits(input, frequencyMap, huffmanCodes);
    }
}

// Node class for each node in the Huffman tree
class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

// Comparator class to compare nodes based on frequency
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}
