import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<Block> blocks = new LinkedList<>();
        Map<Integer, String> idMap = new HashMap<>();

        System.out.print("Enter the total number of blocks: ");
        int M = input.nextInt();

        System.out.print("Enter the size of each block in KB: ");
        int start = 0;
        for (int i = 0; i < M; i++) {
            int size = input.nextInt();
            blocks.add(new Block(size, start));
            start += size;
        }

        System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
        int strategy = input.nextInt();
        AllocationStrategies allocator;

        if (strategy == 1)
            allocator = new FirstFit();
        else if (strategy == 2)
            allocator = new BestFit();
        else if(strategy == 3)
            allocator = new WorstFit();
        else{
            allocator = new WorstFit();
            System.out.println("invalide input!!");
            System.exit(0);
        }

        System.out.println("Memory blocks are created…");
        printInitialMemory(blocks);

        while (true) {
            System.out.println("============================================");
            System.out.println("1) Allocates memory blocks");
            System.out.println("2) De-allocates memory blocks");
            System.out.println("3) Print report about the current state of memory and internal Fragmentation");
            System.out.println("4) Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the process ID and size of process: ");
                    String pIDstr = input.next();
                    int pSize = input.nextInt();
                    int pID = pIDstr.hashCode();

                    idMap.put(pID, pIDstr);
                    if(allocator.allocate(pID, pSize, blocks)){

                    for (Block block : blocks) {
                        if (block.getStatus().equals("allocated") && block.getPID() == pID) {
                            System.out.printf("%s Allocated at address: %d, and the internal fragmentation is %d\n",
                                    pIDstr, block.getStart(), block.getInternalFragmentation());
                            break;
                        }
                    }}
                    break;

                case 2:
                    System.out.print("Enter the process ID to deallocate: ");
                    String idStr = input.next();
                    int id = idStr.hashCode();
                    deallocate(id, idStr, blocks);
                    break;

                case 3:
                    printReport(blocks, idMap);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Prints the initial memory layout
    public static void printInitialMemory(LinkedList<Block> blocks) {
        System.out.println("Memory blocks:");
        System.out.println("============================================");
        System.out.println("Block#     size      start-end      status");
        System.out.println("============================================");
        int i = 0;
        for (Block block : blocks) {
            System.out.printf("Block%-6d %-9d %-4d-%-10d %-6s\n", i++, block.getSize(), block.getStart(), block.getEnd(), block.getStatus());
        }
        System.out.println("============================================");
    }

    // Static method to print full memory report
    public static void printReport(LinkedList<Block> blocks, Map<Integer, String> idMap) {
        System.out.println("Memory blocks:");
        System.out.println("=========================================================================");
        System.out.println("Block#   Size    Start-End    Status     ProcessID  InternalFragmentation");
        System.out.println("=========================================================================");
        int i = 0;
        for (Block block : blocks) {
            String pidName = (block.getPID() == -1) ? "Null" : idMap.getOrDefault(block.getPID(), "???");
            System.out.printf("Block%-4d %-7d %-3d-%-8d %-11s %-10s %d\n",
                    i++, block.getSize(), block.getStart(), block.getEnd(),
                    block.getStatus(), pidName, block.getInternalFragmentation());
        }
        System.out.println("=========================================================================");
    }

    // Static method to deallocate a process by PID
    public static void deallocate(int pID, String pIDStr, LinkedList<Block> blocks) {
        boolean found = false;
        for (Block block : blocks) {
            if (block.getPID() == pID) {
                block.setStatus("free");
                block.setPID(-1);
                block.setInternalFragmentation(0);
                found = true;
            }
        }
        if (found)
            System.out.println(pIDStr + " deallocated.");
        else
            System.out.println("Process not found.");
    }
}
