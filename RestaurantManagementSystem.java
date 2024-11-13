import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class RestaurantManagementSystem
{
    static Scanner sc = new Scanner(System.in);
    static ArrayDeque<String> takeOrder = new ArrayDeque<>();
    static Queue<String> serveOrder = new ArrayDeque<>();
    static double totalBill = 0;
    static Map<String, Double> menu = new HashMap<>();
    public static void main(String[] args) 
    {
        
        int choice;
        do
        {
            System.out.println();
            System.out.println("------SERVICES------");
            System.out.println("1. View Food Menu");
            System.out.println("2. Give Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. Receive Order");
            System.out.println("5. View given order queue");
            System.out.println("6. View received order queue");
            System.out.println("7. Total Bill");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
            switch(choice)
            {
                case 1:
                { 
                    viewFoodMenu();
                    break;
                }
                case 2:
                {
                    giveOrder();
                    break;
                }
                case 3:
                {
                    cancelOrder();
                    break;
                }
                case 4:
                {
                    receiveOrder();
                    break;
                }
                case 5:
                {
                    viewGivenOrder();
                    break;
                }
                case 6:
                {
                    viewReceivedOrder();
                    break;
                }
                case 7:
                {
                    viewTotalBill();
                    break;
                }
                case 8:
                {
                    System.out.println("Thank you for visiting the restaurant!");
                    System.exit(0);
                    break;
                }
                default:
                System.out.println("Invalid choice...Enter choice between 1 to 7!");
            }

        }
        while(choice!=7);
    }

    static void viewFoodMenu()
    {
        System.out.println("-----Food category-----");
        System.out.println("1. Main course");
        System.out.println("2. Beverages");
        System.out.println("3. Desert");
        System.out.println("4. Exit");
        System.err.print("Select your category ");
        int select =sc.nextInt();
        switch(select)
        {
            case 1:
            {
                menu.put("Dosa", 5.99);
                menu.put("Pizza", 8.99);
                menu.put("Noodles", 4.99);
                break;
            }
            case 2:
            {
                menu.put("Coke", 1.99);
                menu.put("Coffee", 2.49);
                menu.put("Tea", 1.99);
                break;
            }
            case 3:
            {
                menu.put("Icecream", 3.99);
                menu.put("Custards", 2.49);
                menu.put("Cake", 4.99);
            }
            case 4:
            {
                System.exit(0);
                break;
            }
            default:
            System.out.println("Invalid choice...Enter choice between 1 to 4!");
        }
        
        System.out.println("Food Menu:");
        for (Map.Entry<String, Double> item : menu.entrySet()) 
        {
            System.out.println(item.getKey() + " - $" + item.getValue());
        }
    }

    static void giveOrder()
    {
        System.out.print("Enter the number of items you want to order: ");
        int numItem = sc.nextInt();

        for (int i = 1; i <= numItem; i++)
        {
            System.out.println("Item " + i);
            System.out.print("Enter the item to order: ");
            String item = sc.next().toLowerCase().trim();

            boolean itemExists = false;
            for (String menuItem : menu.keySet())
            {
                if (menuItem.equalsIgnoreCase(item))
                {
                    itemExists = true;
                    item=menuItem;
                    break;
                }
            }

            if (itemExists)
            {
                System.out.print("Enter the quantity: ");
                int quantity = sc.nextInt();
                takeOrder.add(item + " (Quantity: " + quantity + ")");
                totalBill = totalBill + menu.get(item) * quantity;
                System.out.println("Order added: " + item + " (Quantity: " + quantity + ")");
            }
            else 
            {
                System.out.println("Item not found in the menu.");
            }
        }   
    }

    static void cancelOrder() {
        if (!takeOrder.isEmpty()) 
        {
            System.out.print("Enter the item to cancel: ");
            String canceledItem = sc.next().toLowerCase().trim();
    
            boolean removed = false;
            Iterator<String> itr = takeOrder.iterator();
            while (itr.hasNext()) 
            {
                String order = itr.next();
                if (order.toLowerCase().contains(canceledItem)) 
                {
                    int startIndex = order.lastIndexOf("(Quantity: ");
                    int endIndex = order.lastIndexOf(")");
                    if (startIndex != -1 && endIndex != -1) 
                    {
                        String quantityStr = order.substring(startIndex + 11, endIndex).trim();
                        int quantity = Integer.parseInt(quantityStr);  
                      
                        String itemName = order.substring(0, startIndex - 1);    
                       
                        totalBill -= menu.get(itemName) * quantity;
                    }
    
                    itr.remove();
                    removed = true;
                    break;
                }
            }
    
            if (removed) 
            {
                System.out.println("Order canceled: " + canceledItem);
            } 
            else 
            {
                System.out.println("Item not found in the order queue.");
            }
        }
        else
        {
            System.out.println("No orders to cancel.");
        }
    }
    
    static void receiveOrder()
    {
        if (!takeOrder.isEmpty())
        {
            String receivedItem = takeOrder.poll();
            serveOrder.add(receivedItem);
            System.out.println("Received item: " + receivedItem);
        }
        else
        {
            System.out.println("No more items to receive.");
        }
    }

    static void viewGivenOrder()
    {
        if (takeOrder.isEmpty())
        {
            System.out.println("No orders in the given order queue.");
        }
        else
        {
            System.out.println("Given Order Queue:");
            for (String Ordereditem : takeOrder)
            {
                System.out.println(Ordereditem);
            }
        }
    }

    static void viewReceivedOrder()
    {
        if (serveOrder.isEmpty())
        {
            System.out.println("No items in the received order queue.");
        }
        else
        {
            System.out.println("Received Order Queue:");
            for (String receivedItem : serveOrder)
            {
                System.out.println(receivedItem);
            }
        }
    } 
    
    static void viewTotalBill()
    {
        System.out.println("Total Bill: $" + totalBill);
    }
}
