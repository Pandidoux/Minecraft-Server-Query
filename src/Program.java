import query.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
// import java.io.PrintWriter;

public class Program {
    
    public static void main(String[] args) throws FileNotFoundException{
        
        // Create variables
        String ipAddress = "127.0.0.1"; // Default IP address
        int port = 25565; // Default Query port
        String format = "JSON_FULL"; // RAW_BASIC, RAW_FULL, JSON_BASIC, JSON_FULL
        String outputFile = "";
        Boolean help = false;
        Boolean helpsmall = false;
        Boolean verbose = false;

        // Handle arguments
        if (args.length > 0) {
            String arg;
            for(int i=0; i<args.length; i++){
                arg = args[i].toLowerCase();
                if ( arg.equals("-help") || arg.equals("-h") ) {
                    help = true;
                    break;
                } else if ( arg.equals("-ip") || arg.equals("-i") ) {
                    if ( args[i+1].substring(0,1).equals("-") || args[i+1].substring(0,1).equals(" ")) {
                        System.out.println("[PARAMETER ERROR] => " + args[i] + " " + args[i+1]);
                        help = true;
                    } else {
                        ipAddress = args[i+1];
                    }
                } else if ( arg.equals("-port") || arg.equals("-p") ) {
                    if ( args[i+1].substring(0,1).equals("-") || args[i+1].substring(0,1).equals(" ")) {
                        System.out.println("[PARAMETER ERROR] => " + args[i] + " " + args[i+1]);
                        help = true;
                    } else {
                        port = Integer.parseInt(args[i+1]);
                    }
                } else if ( arg.equals("-output") || arg.equals("-o") ) {
                    if ( args[i+1].substring(0,1).equals("-") || args[i+1].substring(0,1).equals(" ")) {
                        System.out.println("[PARAMETER ERROR] => " + args[i] + " " + args[i+1]);
                        help = true;
                    } else {
                        outputFile = args[i+1];
                    }
                } else if ( arg.equals("-format") || arg.equals("-f") ) {
                    if ( args[i+1].substring(0,1).equals("-") || args[i+1].substring(0,1).equals(" ")) {
                        System.out.println("[PARAMETER ERROR] => " + args[i] + " " + args[i+1]);
                        help = true;
                    } else {
                        format = args[i+1];
                    }
                } else if ( arg.equals("-verbose") || arg.equals("-v") ) {
                    verbose = true;
                }
            }
        } else {
            helpsmall = true;
        }

        if (verbose) {
            System.out.println("PARAMETERS USED:");
            System.out.println("    IP: " + format);
            System.out.println("    PORT: " + format);
            System.out.println("    OUTPUT: " + outputFile);
            System.out.println("    FORMAT: " + format);
        }

        // Print small help
        if(helpsmall || help) {
            System.out.println("#====================================================#");
            System.out.println("|               Minecraft-Status-Query               |");
            System.out.println("#====================================================#");
            System.out.println("HELP: Use -help or -h");
            if (!help) {
                System.exit(0);
            }
        }
        // Print help
        if(help) {
            System.out.println("");
            System.out.println("========== LIST OF THE PARAMETERS ==========");
            System.out.println("    [HELP] View this help page: -HELP, -help, -H, -h");
            System.out.println("");
            System.out.println("    [IP] Set IP Address of the Minecraft server: -IP, -ip, -I, -i");
            System.out.println("        Usage: Must be followed by the IP address of the Minecraft server");
            System.out.println("        Default:");
            System.out.println("            If not specified: Assume Minecraft server is running on localhost => 127.0.0.1");
            System.out.println("        Example:");
            System.out.println("            java -jar Minecraft-Status-Query.jar -IP 127.0.0.1");
            System.out.println("");
            System.out.println("    [PORT] Set Query Port of the Minecraft server: -PORT, -port, -P, -p");
            System.out.println("        Usage: Must be followed by the query port of the Minecraft server");
            System.out.println("        Default:");
            System.out.println("            If not specified: Use default query port of Minecraft servers => 25565");
            System.out.println("        Example:");
            System.out.println("            java -jar Minecraft-Status-Query.jar -PORT 25565");
            System.out.println("");
            System.out.println("    [FORMAT] Specify output format of the data: -FORMAT, -format, -F, -f");
            System.out.println("        Usage: Must be followed by RAW_BASIC, RAW_FULL, JSON_BASIC or JSON_FULL.");
            System.out.println("            RAW_BASIC:  Return basics informations separated by commas");
            System.out.println("            RAW_FULL:   Return all informations separated by commas");
            System.out.println("            JSON_BASIC: Return basics informations formated in JSON");
            System.out.println("            JSON_FULL:  Return all informations formated in JSON");
            System.out.println("        Default:");
            System.out.println("            If not specified: Default format is => JSON_FULL");
            System.out.println("        Example:");
            System.out.println("            java -jar Minecraft-Status-Query.jar -F JSON_FULL");
            System.out.println("");
            System.out.println("    [OUTPUT] Output result of the query in a file: -OUTPUT, -output, -O, -o");
            System.out.println("        Usage: Must be followed by a file path or a file name (with file extention)");
            System.out.println("        Default:");
            System.out.println("            If not specified: Create file => Minecraft-Status-Query.json (in the current directory)");
            System.out.println("        Example:");
            System.out.println("            java -jar Minecraft-Status-Query.jar -OUTPUT Minecraft-Status-Query.json");
            System.out.println("");
            System.out.println("    [VERBOSE] Display more details in the console: -VERBOSE, -verbose, -V, -v");
            System.out.println("        Example:");
            System.out.println("            java -jar Minecraft-Status-Query.jar -VERBOSE");
            System.out.println("");
            System.out.println("========== CREDIT ==========");
            System.out.println("Server query class: rmmccann [https://github.com/rmmccann/Minecraft-Status-Query.git]");
            System.out.println("Executable program easy to use: Pandidoux");
            System.exit(0);
        }

        // Do server query
        if ( !helpsmall && !help ) {

            if (verbose) { System.out.println("Sending query..."); }
            MCQuery query = new MCQuery();
            query.setServerIpAddress(ipAddress);
            query.setServerQueryPort(port);
            String status = "";
            String status_out = "";

            if ( format.equals("RAW_BASIC") ) { //basic status
                if (verbose) { System.out.println("RAW_BASIC => basic status"); }
                status_out = query.basicStat().toString();
                
            } else if ( format.equals("RAW_FULL") ) { //full status
                if (verbose) { System.out.println("RAW_FULL => full status"); }
                status_out = query.fullStat().toString();
                
            } else if ( format.equals("JSON_BASIC") ) { //getting the result as a json string
                if (verbose) { System.out.println("JSON_BASIC => getting the result as a json string"); }
                status = query.basicStat().asJSON();
                status_out = status.substring(1,status.length()-1);
                
            } else if ( format.equals("JSON_FULL") ) { //getting full status as a json string
                if (verbose) { System.out.println("JSON_FULL => getting full status as a json string"); }
                status = query.fullStat().asJSON();
                status_out = status.substring(1,status.length()-1);
            }
            System.out.println(status_out);

            // Write output in a file
            if ( !outputFile.equals("") ) {
                if (verbose) { System.out.println("Write output to file"); }
                try {
                    FileWriter myWriter = new FileWriter(outputFile);
                    myWriter.write(status_out);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred with file output.");
                    e.printStackTrace();
                }
            }

        }

        System.exit(0);
    }

}
