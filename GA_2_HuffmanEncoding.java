import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // Constructor for a leaf node (character node)
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Constructor for an internal node (frequency node)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

// Comparator for the priority queue
class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.frequency - node2.frequency;
    }
}

public class HuffmanEncoding {

    // Function to build the Huffman Tree
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new HuffmanComparator());

        // Create a leaf node for each character and add it to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree
        while (priorityQueue.size() > 1) {
            // Remove the two nodes of lowest frequency
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            // Create a new internal node with these two nodes as children
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }

        // The remaining node is the root of the Huffman Tree
        return priorityQueue.poll();
    }

    // Recursive function to generate Huffman Codes
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If this is a leaf node, then it contains one of the input characters
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        // Traverse the left and right children
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    // Function to encode an input string using the generated Huffman Codes
    public static String encode(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(character));
        }

        return encodedText.toString();
    }

    // Function to calculate frequency of each character in the input text
    public static Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char character : text.toCharArray()) {
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }

        return frequencyMap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text to encode: ");
        String text = scanner.nextLine();

        // Step 1: Calculate the frequency of each character
        Map<Character, Integer> frequencyMap = calculateFrequency(text);

        // Step 2: Build the Huffman Tree
        HuffmanNode root = buildHuffmanTree(frequencyMap);

        // Step 3: Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Print the Huffman Codes for each character
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Step 4: Encode the input text
        String encodedText = encode(text, huffmanCodes);
        System.out.println("\nEncoded Text: " + encodedText);

        scanner.close();
    }
}
