import java.sql.*;
import java.util.Scanner;

public class Voting {
    public static void main (String[] args) {
        
        Scanner k = new Scanner (System.in);
        ResultSet rs = null;

        try {
            Class.forName("java.sql.DriverManager");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find DriverManager class.");
        }

        //Ask for SQL Details

        System.out.print("Host: ");
        System.out.println();

        char[] host = k.next().toCharArray();

        System.out.print("Database: ");
        System.out.println();

        char[] database = k.next().toCharArray();

        System.out.print("Username: ");
        System.out.println();

        char[] user = k.next().toCharArray();

        System.out.print("Password: ");
        System.out.println();

        char[] pass = k.next().toCharArray();

        private char[] url = getDatabaseURL(host, database, user, pass);

        //Try connection

        try (Connection conn = DriverManager.getConnection(String.valueOf(url));
             Statement stmt = conn.createStatement();) {

                System.out.print("Have you previously registered to vote? (y/n): ");

                String answer = k.nextLine();

                System.out.println();

                if (!hasRegistered(answer)) {

                    private boolean keepGoing = true;
                    char[] name = null;

                    while (keepGoing) {
                        System.out.println ("Please register using your information asked below.");
                        System.out.println();

                        System.out.print("Name: ");
                        System.out.println();

                        name = k.next().toCharArray();

                        System.out.print("Passphrase: ");
                        System.out.println();
                        System.out.println();

                        char[] passphrase = k.next().toCharArray();

                        if (validate(name, passphrase, "register", url)) {

                            register (name, passphrase, false, url);

                            keepGoing = false;
                            passphrase = null;
                        }

                    }

                    if (!keepGoing) {
                        login(k, name, conn);
                    }

                    
                } else {
                    login(k, name, conn);
                }

                host = null;
                database = null;
                user = null;
                pass = null;
                name = null;
                url = null;

        } catch (SQLException sqlEx) {
            System.out.println("Failed to connect to SQL Database.");
        }


    }

    private static void register (char[] name, char[] pass, Connection conn) {
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement();) {

            //not done

        } catch (SQLException sqlEx) {
            System.out.println("Failed to process votes.");
        }
    }

    private static void login(Scanner k, char[] name, Connection conn) {
        private boolean keepGoing = true; //need two different instances of keepGoing to keep track

        while (keepGoing) {
            System.out.println("Please login using your credentials.");
            System.out.println();

            System.out.print("Name: ");
            System.out.println();

            name = k.next().toCharArray();

            System.out.print("Passphrase: ");
            System.out.println();
            System.out.println();

            char[] passphrase = k.next().toCharArray();

            if (validate(name, passphrase, "login", conn)) {
                keepGoing = false;
            }
        }

        if (!keepGoing) { //assuming validated
            vote(k, name, conn);
        }
        
        name = null;
        passphrase = null;
        url = null;
    }

    private static void vote (Scanner k, char[] user, Connection conn) {
        System.out.println("Welcome! You may now vote below.");
        System.out.println();

        System.out.println("Presidential Candidates:");
        System.out.println();

        System.out.println("  Democratic:");
        System.out.println("    - (1) Mark Joe");
        System.out.println("    - (2) John Marsh");

        System.out.println();

        System.out.println("  Republican:");
        System.out.println("    - (3) Kevin Shane");
        System.out.println("    - (4) David Warner");

        int selection = k.nextInt();

        processVote(selection, "presidential", conn);

        selection = null;

        for (int x = 0; x < 25; x++) {
            System.out.println();
        }

        System.out.println("US Senate Candidate(s):");
        System.out.println();

        System.out.println("  Democratic:");
        System.out.println("    - (1) Gowtham Varu");

        System.out.println();

        System.out.println("  Republican:");
        System.out.println("    - (2) Mahinder Rana");

        selection = k.nextInt();

        processVote(selection, "senate", conn);

        selection = null;

        for (int x = 0; x < 25; x++) {
            System.out.println();
        }
    
        System.out.println("US House of Representatives Candidate(s):");
        System.out.println();

        System.out.println("  Democratic:");
        System.out.println("    - (1) Kaif Malik");

        System.out.println();

        System.out.println("  Republican:");
        System.out.println("    - (2) Jacob John");

        selection = k.nextInt();

        processVote(selection, "house", conn);

        selection = null;

        for (int x = 0; x < 25; x++) {
            System.out.println();
        }

        finished (true, user, conn);

        user = null;

        System.out.println("Thank you for voting!");

    }

    //STILL NEEDS DONE
    private static void processVote(int selection, String votingType, Connection conn) {
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement();) {

            if (votingType.equals("presidential")) {
                
                if (selection == 1) {
                    rs = stmt.executeQuery("INSERT INTO VOTES (")
                }

            } else if (votingType.equals("senate")) {

                rs = stmt.executeQuery("");

            } else if (votingType.equals("house")) {

            }

        } catch (SQLException sqlEx) {
            System.out.println("Failed to process votes.");
        }
    }

    private static void finished (boolean hasFinished, char[] user, Connection conn) {
        hasFinished = true;
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement();) {

            rs = stmt.executeQuery ("UPDATE USERS SET VOTED = " + hasFinished + " WHERE NAME = '" String.valueOf(user) + "';");

        } catch (SQLException sqlEx) {
            System.out.println("Failed to update voting status.");
        }
    }

    private static char[] getDatabaseURL(char[] host, char[] db, char[] user, char[] pass) {
        return "jdbc:sqlserver://" + String.valueOf(host) + ";database=" + String.valueOf(db) + ";user=" + String.valueOf(user) + ";password=" + String.valueOf(pass) + ";";
    }

    private static boolean hasRegistered (String s) {
        if (s.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validate (char[] name, char[] pass, String validationType, Connection conn) {
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement();) {

            if (validationType.equals("login")) {
                rs = stmt.executeQuery ("SELECT NAME, PASS FROM USERS WHERE USERS.NAME = '" + String.valueOf(name) + "' AND PLAYER.PASS = '" + String.valueOf(pass) + "';");

                while (rs.next()) {
                    if ((rs.getString(1) == String.valueOf(name)) && (rs.getString(2) == String.valueOf(pass))) {
                        return true;
                    } else {
                        System.out.println ("Your login credentials were incorrect.");
                        return false;
                    }
                }
            } else if (validationType.equals("register")) {
                //Source: https://www.w3schools.com/sql/sql_exists.asp
                rs = stmt.executeQuery("SELECT NAME FROM USERS WHERE USERS.NAME = '" + String.valueOf(name) + "';");

                //Source: https://stackoverflow.com/a/11288562

                if (rs.next()) {
                    System.out.println("That user already exists!");
                    return false;
                } else {
                    System.out.println("Thanks for registering.");
                    return true;
                }
            }

        } catch (SQLException sqlEx) {
            System.out.println("Failed to validate from database.");
        }
    }
}
