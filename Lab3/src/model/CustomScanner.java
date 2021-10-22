package model;

import javafx.util.Pair;

import java.io.*;
import java.util.*;



public class CustomScanner {
    private String fileName;
    private boolean isStringLexicallyCorrect = true;
    private boolean isCharLexicallyCorrect = true;
    private List<String> tokenList = new ArrayList<String>();
    private List<String> separatorList = new ArrayList<String>();
    private List<Pair<String, Integer>> detectedTokens = new ArrayList<>();
    private String stringConstant = "";
    private String charConstant = "";
    private int currentLine = 0;
    private int capacity = 97;
    private CustomHashTable hashTable = new CustomHashTable(capacity);

    public CustomScanner(String fileName) {
        this.fileName = fileName;
        this.readTokens();
        this.readSeparators();
    }

    private void readTokens() {
        try {
            File myObj = new File("scanner_input/token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tokenList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void readSeparators() {
        try {
            File myObj = new File("scanner_input/token");
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < 28; i++){
                String data = myReader.nextLine();
                separatorList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Boolean isConstant(String token) throws FileNotFoundException {
        return Boolean.TRUE;
        //TODO
    }

    private Boolean isIdentifier(String token) throws FileNotFoundException {
        return Boolean.TRUE;
        //TODO
    }

    private Boolean isStringConstant(String token) {
        if (token.charAt(0) == '"' && token.charAt(token.length() - 2) == '"') {
            String withoutQuote = token.substring(1, token.length() - 2);
            return withoutQuote.length() > 1;
        } else {
            return false;
        }
    }

    private Boolean isCharConstant(String token) {
        if (String.valueOf(token.charAt(0)).equals("'") && String.valueOf(token.charAt(token.length() - 2)).equals("'")) {
            String withoutQuote = token.substring(1, token.length() - 2);
            return withoutQuote.length() <= 1;
        } else {
            return false;
        }
    }

    private Boolean isReservedOperatorSeparator(String myToken) {
        return Boolean.TRUE;
        //TODO
    }

    public void classifyTokens() throws IOException {
        ;
        //TODO
    }

    public void writeToSymbolTable() throws FileNotFoundException {
        ;
        //TODO
    }

    public void scan() {
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());
                currentLine ++;
                while (data.hasNext()) {
                    String word = data.next();
                    boolean hasSeparator = false;

                    for (String separator : separatorList) {
                        // General separator cases
                        if (word.contains(separator)) {
                            hasSeparator = true;
                            this.splitWordWithSeparator(word, separator, currentLine);
                            break;
                        }
                    }

                    if (!hasSeparator && !isStringLexicallyCorrect) {
                        stringConstant += word + " ";
                    }
                    if (!hasSeparator && !isCharLexicallyCorrect) {
                        charConstant += word + " ";
                    }
                    if (!hasSeparator && isStringLexicallyCorrect && isCharLexicallyCorrect) {
                        detectedTokens.add(new Pair<String, Integer>(word, currentLine));
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void splitWordWithSeparator(String word, String separator, Integer line) {
        String[] splitList;

        boolean specialCase = false
        char doubleQuotes = '"';
        String stringDoubleQuotes = String.valueOf(doubleQuotes);

        // String part
        if (word.contains(stringDoubleQuotes) && !isStringLexicallyCorrect) {
            specialCase = true;

            this.isStringLexicallyCorrect = true;
            if (word.charAt(word.length() - 1) == ';') {
                String newWord = word.substring(0, word.length() - 1);
                stringConstant += newWord + " ";
                detectedTokens.add(new Pair<String, Integer>(stringConstant, currentLine));
                detectedTokens.add(new Pair<String, Integer>(";", currentLine));
                stringConstant = "";
                return;
            } else {
                stringConstant += word + " ";
                detectedTokens.add(new Pair<String, Integer>(stringConstant, currentLine));
                stringConstant = "";
                return;
            }
        }

        if (!isStringLexicallyCorrect) {
            //TODO
            return;
        }

        if (separator.charAt(0) == '"' && isStringLexicallyCorrect) {
            //TODO
            return;
        }

        // Char part
        if (word.contains("'") && !isCharLexicallyCorrect) {
            specialCase = true;

            this.isCharLexicallyCorrect = true;
            //TODO rest
        }

        if (!isCharLexicallyCorrect) {
            specialCase = true;
            charConstant += word + " ";
            return;
        }

        //TODO Check each separator
        //TODO Special cases

    }
}
