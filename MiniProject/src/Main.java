class QuickFitMemoryAllocator {

    // MemoryBlock class to represent each memory block with size and its usage status
    static class MemoryBlock {
        int size; // Size of the memory block
        boolean isUsed; // Boolean to indicate if the memory block is in use

        // Constructor to initialize the memory block with size and mark it as unused initially
        MemoryBlock(int size) {
            this.size = size;
            this.isUsed = false;
        }
    }

    public static void main(String[] args) {
        // Memory blocks of different sizes (40KB, 120KB, and 300KB)
        MemoryBlock[] memory40KB = new MemoryBlock[1]; // Array for 40KB block size
        memory40KB[0] = new MemoryBlock(40); // Creating a 40KB block

        MemoryBlock[] memory120KB = new MemoryBlock[1]; // Array for 120KB block size
        memory120KB[0] = new MemoryBlock(120); // Creating a 120KB block

        MemoryBlock[] memory300KB = new MemoryBlock[1]; // Array for 300KB block size
        memory300KB[0] = new MemoryBlock(300); // Creating a 300KB block

        // Array to represent job sizes (in KB)
        int[] jobSizes = {300, 80, 40}; // Jobs of size 300KB, 80KB, and 40KB

        // Process each job in the jobSizes array
        for (int jobSize : jobSizes) {
            // Allocate job to the respective memory block if size matches
            if (jobSize == 40) {
                allocateJob(memory40KB, jobSize, "40KB");
            } else if (jobSize == 120) {
                allocateJob(memory120KB, jobSize, "120KB");
            } else if (jobSize == 300) {
                allocateJob(memory300KB, jobSize, "300KB");
            } else {
                System.out.println("Job of size " + jobSize + "KB failed to allocate.");
            }
        }

        // Final State: Print memory blocks and their status
        System.out.println("\nFinal Memory State Table:");
        printMemoryState(memory40KB, "40KB");
        printMemoryState(memory120KB, "120KB");
        printMemoryState(memory300KB, "300KB");

        // Summary of Memory State: Print the availability and utilization of each block size
        printSummaryState(memory40KB, "40KB");
        printSummaryState(memory120KB, "120KB");
        printSummaryState(memory300KB, "300KB");
    }

    // Method to allocate job to an available memory block
    private static void allocateJob(MemoryBlock[] memoryArray, int jobSize, String blockSize) {
        boolean allocated = false; // Flag to track if job was allocated successfully
        for (int i = 0; i < memoryArray.length; i++) {
            // Check if block is available and can accommodate the job size
            if (!memoryArray[i].isUsed && memoryArray[i].size >= jobSize) {
                memoryArray[i].isUsed = true; // Mark the block as used
                System.out.println("Job of size " + jobSize + "KB allocated to " + blockSize + " block " + (i + 1));
                allocated = true; // Set flag to true as job is allocated
                break; // Exit loop after successful allocation
            }
        }
        // If job couldn't be allocated, print failure message
        if (!allocated) {
            System.out.println("Job of size " + jobSize + "KB failed to allocate.");
        }
    }

    // Method to print the state of memory blocks (whether each block is used or available)
    private static void printMemoryState(MemoryBlock[] memoryArray, String blockSize) {
        System.out.print(blockSize + " List: ");
        // Loop through the memory blocks and print their status (used or available)
        for (int i = 0; i < memoryArray.length; i++) {
            if (memoryArray[i].isUsed) {
                System.out.print("Block " + (i + 1) + " (" + memoryArray[i].size + "KB - Used) ");
            } else {
                System.out.print("Block " + (i + 1) + " (" + memoryArray[i].size + "KB - Available) ");
            }
        }
        System.out.println();
    }

    // Method to print summary of memory state (number of available and utilized blocks for each block size)
    private static void printSummaryState(MemoryBlock[] memoryArray, String blockSize) {
        System.out.println("\nSummary of Memory State for " + blockSize + " blocks:");
        System.out.println("Size-Specific List:");
        System.out.println("Available Blocks\tUtilized Blocks");

        int availableCount = 0; // Counter for available blocks
        int utilizedCount = 0; // Counter for utilized blocks

        // Loop through the memory blocks and count available and utilized blocks
        for (MemoryBlock block : memoryArray) {
            if (block.isUsed) {
                utilizedCount++; // Increment utilized block count
            } else {
                availableCount++; // Increment available block count
            }
        }

        // Print the summary of available and utilized blocks
        System.out.println(blockSize + " available:\t" + availableCount);
        System.out.println(blockSize + " utilized:\t" + utilizedCount);
    }
}
