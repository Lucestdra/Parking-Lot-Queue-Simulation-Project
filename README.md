# Parking-Lot-Queue-Simulation-Project
This repository contains a Java-based Parking Lot Queue Simulation application, developed for the CENG 202 - Data Structures course at Izmir Katip Celebi University. The project focuses on simulating a parking lot exit queue using two different queueing structures: First-In-First-Out (FIFO) and Priority Queue.

#Features
Simulated Queueing Structures: Implements FIFO and Priority Queue to manage car exits, simulating real-world parking scenarios.
Dynamic Simulation: Simulates the arrival of N cars with randomly assigned processing times and tracks their completion times.
Efficiency Comparison: Compares the efficiency of FIFO and Priority Queue by calculating and displaying the average and gain in transaction completion times.

#Project Structure
Car Class: Represents a car with attributes like id and processing time.
FIFOQueue and CustomPriorityQueue Classes: Implement the queue functionalities with FIFO and priority-based ordering.
ParkingLot Class: The main driver class that handles user interactions, initiates simulations, and calculates performance metrics.

#Future Enhancements
Improved Data Structures: Explore more efficient data structures like Deque or built-in PriorityQueue for better performance.
Sophisticated Random Generation: Implement more realistic variations in processing times using advanced distributions.
Error Handling: Expand error handling to catch unexpected crashes or invalid data during execution.
Visualization: Integrate visual representations of queue behavior to enhance understanding of the simulation.
