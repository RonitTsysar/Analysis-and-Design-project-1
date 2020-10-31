import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        try{
            while (true)
            { 
                String command = scanner.nextLine();
                if (command == "")
                    continue;
    
                ArrayList<String> command_list = new ArrayList<String>(Arrays.asList(command.split(" ")));
                command = command_list.get(0);
                String type = command_list.get(1);
                String arg = command_list.get(2);
    
                switch (command) {
                    case "Add":
                        if (type == "WebUser") 
                            System.out.println(" Add WebUser ");
                        else
                            System.out.println(" Add Product ");
                        break;
    
                    case "Remove":
                        System.out.println(" Remove WebUser ");
                        break;
                    
                    case "Login":
                        System.out.println(" Login WebUser ");
                        break;
                    
                    case "Logout":
                        System.out.println(" Logout WebUser ");
                        break;
                               
                    case "Make":
                        System.out.println(" Make order ");
                        break;
    
                    case "Display":
                        System.out.println(" Display order ");
                        break;
                    
                    case "Link":
                        System.out.println(" Link Product ");
                        break;
    
                    case "Delete":
                        System.out.println(" Delete Product ");
                        break;
    
                    case "ShowAllObjects":
                        System.out.println(" ShowAllObjects ");
                        break;
    
                    case "ShowObjectId ":
                        System.out.println(" ShowObjectId ");
                        break;
                }
            }
        }
        catch(Exception e) 
        {
            scanner.close();
        }
    }
}
