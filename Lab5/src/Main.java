
import model.CustomScanner;
import model.FiniteAutomata;
import model.Grammar;
import model.ParserLR0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

//    public static void main(String[] args) throws IOException {
//        FiniteAutomata FA = new FiniteAutomata("D:/YearIII/FLCD/Labs/Formal-Languages-and-Compiler-Design/Lab4/src/fa.txt");
//        FA.readFromFile();
//
//        while (true) {
//            display_menu();
//            String command = "";
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Enter command: ");
//            command = reader.readLine();
//
//            switch (command) {
//                case "1":
//                    System.out.println("States: ");
//                    System.out.println(FA.getSetOfStates());
//                    System.out.println("\n");
//                    break;
//                case "2":
//                    System.out.println("Alphabet: ");
//                    System.out.println(FA.getAlphabet());
//                    System.out.println("\n");
//                    break;
//                case "3":
//                    System.out.println("Transitions: ");
//                    System.out.println(FA.getTransitionsList());
//                    System.out.println("\n");
//                    break;
//                case "4":
//                    System.out.println("Final states: ");
//                    System.out.println(FA.getFinalStates());
//                    System.out.println("\n");
//                    break;
//                case "5":
//                    String message = FA.isDFA() ? "is a DFA" : "is NOT a DFA";
//                    System.out.println("The introduced FA " + message);
//                    System.out.println("\n");
//                    break;
//                case "6":
//                    if(!FA.isDFA()){
//                        System.out.println("FA needs to be a DFA.");
//                        break;
//                    }
//                    BufferedReader reader2 =  new BufferedReader(new InputStreamReader(System.in));
//                    System.out.println("Enter sequence: ");
//                    String sequence = reader2.readLine();
//                    String message2 = FA.isAccepted(sequence) ? "Sequence is accepted" : "Sequence is not accepted";
//                    System.out.println(message2);
//                    System.out.println("\n");
//                    break;
//                case "0":
//                    System.exit(0);
//                default:
//                    System.err.println("Unrecognized option");
//                    break;
//            }
//        }
//    }
//
//    private static void display_menu() {
//        System.out.println("1 - Show states");
//        System.out.println("2 - Show alphabet");
//        System.out.println("3 - Show transitions");
//        System.out.println("4 - Show final states");
//        System.out.println("5 - Is DFA?");
//        System.out.println("6 - Verify if a sequence is accepted by FA");
//        System.out.println("0 - Exit \n");
//    }


//    public static void main(String[] args) throws IOException {
//        CustomScanner scanner = new CustomScanner("programs/p1");
//        scanner.scan();
//
//        // PIF
//        scanner.classifyTokens();
//
//        // ST
//        scanner.writeToSymbolTable();
//    }

    private static void display_menu() {
        System.out.println("1 - Show non-terminals");
        System.out.println("2 - Show terminals");
        System.out.println("3 - Show productions");
        System.out.println("4 - Show starting symbol");
        System.out.println("5 - Show if CFG");
        System.out.println("0 - Exit \n");
    }

    public static void main(String[] args) throws IOException {
        Grammar grammar = new Grammar("src/data/g1.txt");
        grammar.readFromFile();

        while (true) {
            display_menu();
            String command = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter command: ");
            command = reader.readLine();

            switch (command) {
                case "1":
                    System.out.println("Non-terminals: ");
                    System.out.println(grammar.getSetOfNonTerminals());
                    System.out.println("\n");
                    break;
                case "2":
                    System.out.println("Terminals: ");
                    System.out.println(grammar.getSetOfTerminals());
                    System.out.println("\n");
                    break;
                case "3":
                    System.out.println("Productions: ");
                    grammar.getSetOfProductions().forEach(System.out::println);
                    System.out.println("\n");
                    break;
                case "4":
                    System.out.println("Starting symbol: ");
                    System.out.println(grammar.getStartingSymbol());
                    System.out.println("\n");
                    break;
                case "5":
                    if (grammar.isCFG())
                        System.out.println("The grammar is context free");
                    else
                        System.out.println("The grammar is context sensitive");
                    System.out.println("\n");
                    break;
                case "0":
                    System.exit(0);
                case "-":
                    ParserLR0 parserLR0 = new ParserLR0(grammar);
                    parserLR0.parse();

                default:
                    System.err.println("Unrecognized option");
                    break;
            }
        }

    }
}
