import java.util.*;

// Define the Car class to represent a car with an id and processing time
class Car {
    int id;
    int processingTime;

    // Constructor to initialize Car object with id and processing time
    public Car(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }
}

// Class for simulating FIFO parking lot
class FIFOParkingLot {
    // Nested class for FIFO Queue
    static class FIFOQueue {
        private Queue<Car> cars;

        // Constructor to initialize the FIFOQueue with a LinkedList
        public FIFOQueue() {
            cars = new LinkedList<>();
        }

        // Method to add a car to the queue
        public void push(Car car) {
            cars.offer(car);
        }

        // Method to remove and return the first car from the queue
        public Car popCar() {
            if (isEmpty())
                throw new NoSuchElementException("Queue is empty");
            return cars.poll();
        }

        // Method to check if the queue is empty
        public boolean isEmpty() {
            return cars.isEmpty();
        }
    }

    // Method to simulate the FIFO parking lot and return process completion times
    public static List<Integer> simulate(int N, List<Integer> processingTimes) {
        // Create a new FIFOQueue instance
        FIFOQueue fifoQueue = new FIFOQueue();
        List<Integer> completionTimes = new ArrayList<>();
        int totalTransactionTimeFIFO = 0;

        // Simulate the arrival of cars and add them to the FIFO queue
        for (int i = 0; i < N; i++) {
            int processingTime = processingTimes.get(i);
            fifoQueue.push(new Car(i + 1, processingTime));
        }

        // Process each car in FIFO order and calculate total transaction time
        while (!fifoQueue.isEmpty()) {
            Car car = fifoQueue.popCar();
            totalTransactionTimeFIFO += car.processingTime;
            completionTimes.add(totalTransactionTimeFIFO); // Add completion time to list
            System.out.println("Car " + car.id + " completed in " + totalTransactionTimeFIFO + " seconds");
        }

        return completionTimes;
    }
}

// Class for simulating Priority parking lot
class PriorityParkingLot {
    // Nested class for custom Priority Queue
    static class CustomPriorityQueue {
        private List<Car> cars;

        // Constructor to initialize the CustomPriorityQueue with an ArrayList
        public CustomPriorityQueue() {
            cars = new ArrayList<>();
        }

        // Method to add a car to the queue and maintain order based on processing time
        public void push(Car car) {
            cars.add(car);
            sort();
        }

        // Method to remove and return the first car from the queue
        public Car pop() {
            if (isEmpty())
                throw new NoSuchElementException("Queue is empty");
            return cars.remove(0);
        }

        // Method to check if the queue is empty
        public boolean isEmpty() {
            return cars.isEmpty();
        }

        // Method to sort the queue based on processing time using insertion sort
        private void sort() {
            int n = cars.size();
            for (int i = 1; i < n; ++i) {
                Car key = cars.get(i);
                int j = i - 1;

                while (j >= 0 && cars.get(j).processingTime > key.processingTime) {
                    cars.set(j + 1, cars.get(j));
                    j = j - 1;
                }
                cars.set(j + 1, key);
            }
        }
    }

    // Method to simulate the Priority parking lot and return process completion times
    public static List<Integer> simulate(int N, List<Integer> processingTimes) {
        // Create a new CustomPriorityQueue instance
        CustomPriorityQueue priorityQueue = new CustomPriorityQueue();
        List<Integer> completionTimes = new ArrayList<>();
        int totalTransactionTimePriority = 0;

        // Simulate the arrival of cars and add them to the priority queue
        for (int i = 0; i < N; i++) {
            int processingTime = processingTimes.get(i);
            priorityQueue.push(new Car(i + 1, processingTime));
        }

        // Process each car in priority order and calculate total transaction time
        while (!priorityQueue.isEmpty()) {
            Car car = priorityQueue.pop();
            totalTransactionTimePriority += car.processingTime;
            completionTimes.add(totalTransactionTimePriority); // Add completion time to list
            System.out.println("Car " + car.id + " completed in " + totalTransactionTimePriority + " seconds");
        }

        return completionTimes;
    }
}

// Main class
public class ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get number of cars from user
        System.out.print("Enter the number of cars: ");
        int N = scanner.nextInt();
        System.out.println("\n--------------------------------------------\n FIFO Parking Lot Simulation\n--------------------------------------------\n");
        List<Integer> processingTimes = generateRandomProcessingTimes(N);

        // Simulate FIFO parking lot and get process completion times
        List<Integer> fifoCompletionTimes = FIFOParkingLot.simulate(N, processingTimes);
        System.out.println("\n--------------------------------------------\n PRIOIRTY Parking Lot Simulation\n--------------------------------------------\n");
        // Simulate Priority parking lot and get process completion times
        List<Integer> priorityCompletionTimes = PriorityParkingLot.simulate(N, processingTimes);

        // Calculate gain in average transaction completion time
        double avgTransactionTimeFIFO = getAvgTransactionTime(fifoCompletionTimes);
        double avgTransactionTimePriority = getAvgTransactionTime(priorityCompletionTimes);
        double difference = avgTransactionTimeFIFO - avgTransactionTimePriority;
        double percentageGain = (difference / avgTransactionTimeFIFO) * 100;

        System.out.println("\nGain in average transaction completion time: " + difference + " seconds");
        System.out.println("Percentage gain: " + percentageGain + "%");

        scanner.close();
    }

    // Method to calculate average transaction completion time
    public static double getAvgTransactionTime(List<Integer> completionTimes) {
        int sum = 0;
        for (int time : completionTimes) {
            sum += time;
        }
        return (double) sum / completionTimes.size();
    }

    // Method to generate random processing times for cars
    public static List<Integer> generateRandomProcessingTimes(int N) {
        List<Integer> processingTimes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            // Generate random processing time between 10 and 300 seconds for each car
            int processingTime = random.nextInt(291) + 10;
            processingTimes.add(processingTime);
        }
        return processingTimes;
    }
}
