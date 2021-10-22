package model;

import java.util.Arrays;

public class CustomHashTable {

    private String[] symbolTable;
    private int capacity;

    public CustomHashTable(int capacity) {
        this.capacity = capacity;
        this.symbolTable = new String[capacity];
    }

    private int hashFunction(String identifier) {
        int sum = 0;
        for(int i = 0; i < identifier.length(); i++) {
            sum += identifier.charAt(i);
        }
        return sum % this.capacity;
    }

    /*
     * Add the identifier to the symbol table
     * Collision problems are solved using Linear Probing
     *
     * params: String identifier
     * return: TRUE if the identifier doesn't exist in symbol table
     *       FALSE otherwise
     *
     * */

    public boolean insert(String identifier) {
        // Check if already in symbol table
        for (String s : symbolTable) {
            if (s != null && s.equals(identifier)) {
                System.out.println("Already in symbol table.");
                return false;
            }
        }
        int hashValue = hashFunction(identifier);
        if (symbolTable[hashValue] == null) {
            symbolTable[hashValue] = identifier;
            System.out.println("Insert " + identifier + " at position " + hashValue);
            return true;
        }
        // Else, we have a collision
        int nextAvailablePosition = hashValue;
        while (symbolTable[nextAvailablePosition] != null) {
            nextAvailablePosition++;
        }
        if (symbolTable[nextAvailablePosition] == null) {
            symbolTable[nextAvailablePosition] = identifier;
            System.out.println("Insert " + identifier + " at position " + nextAvailablePosition);
            return true;
        }
        System.out.println("Insert failed.");
        return false;
    }

    /*
     * Search for the identifier in the symbol table
     *
     * params: String identifier
     * return: position (INTEGER) if the identifier exists in symbol table
     *       -1 otherwise
     *
     * */

    public int find(String identifier) {
        int hashValue = hashFunction(identifier);
        while (symbolTable[hashValue] != null) {
            if(symbolTable[hashValue].equalsIgnoreCase(identifier)) {
                return hashValue;
            }
            hashValue ++;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "symbolTable=" + Arrays.toString(symbolTable) +
                '}';
    }

}
