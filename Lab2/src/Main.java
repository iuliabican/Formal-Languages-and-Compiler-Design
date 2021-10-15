import model.CustomHashTable;

public class Main {

    public static void main(String[] args) {
        System.out.println("Inserting values ...");
        CustomHashTable symbolTable = new CustomHashTable(17);

        // Inserting some values
        symbolTable.insert("a");
        symbolTable.insert("ab");
        symbolTable.insert("cd");
        symbolTable.insert("dc");
        symbolTable.insert("ba");
        symbolTable.insert("a");

        System.out.println("\nStarting tests ...");
        // Test if all values have been inserted (all should return true)
        assert symbolTable.insert("a");
        assert symbolTable.insert("ab");
        assert symbolTable.insert("cd");
        assert symbolTable.insert("dc");
        assert symbolTable.insert("ba");
        assert !symbolTable.insert("a");

        assert 12 == symbolTable.find("a");
        assert 8 == symbolTable.find("ab");
        assert -1 == symbolTable.find("iulia");
        System.out.println(symbolTable);
    }
}
