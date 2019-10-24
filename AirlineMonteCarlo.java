
/**
 * Finds the average number of times a flight is overbooked and the average number of seats filled in a given flight
 *
 * @author (Yazzy Ahuja)
 * @version (APCS-A 10-24-2019)
 */
public class AirlineMonteCarlo
{
    // instance variables
    private int ticketsSold;
    private int seats;
    private double noShowProbability;
    private final int NUM_SIMULATIONS = 100000;
    private int overBooked;
    private int extraSeats;
    private double percent;
    private double average;

    /**
     * Constructor for objects of class AirlineMonteCarlo
     */
    public AirlineMonteCarlo(int t, int s, double n)
    {
       this.ticketsSold = t;
       this.seats = s;
       this.noShowProbability = n;
    }

    
    /**
     * Simulates one flight
     *
     * @return    numShow
     * returns the number of people who showed up
     */
    public int simulateOneFlight()
    {
        int numShow = 0;
        for (int i = 1; i <= ticketsSold; i++)
        {
            if(Math.random() >= noShowProbability)
            {
                numShow = numShow + 1;
            }
        }
        return numShow;
    }
    
    /**
     * Runs the simulateOneFlight
     * Finds the average seats filled and times overbooked
     *
     */
    public void runSimulation()
    {
        int runningTotal = 0;
        for (int i = 1; i <= NUM_SIMULATIONS; i++)
        {
            int numShow = simulateOneFlight();
            runningTotal = runningTotal + numShow;
            if (numShow > seats)
            {
                overBooked = overBooked + 1; // adds to the number of times the flight was overbooked whenever there are more ppl than seats
            }
        }
        average = (double)(runningTotal) / NUM_SIMULATIONS; // calculates the average number of seats filled
        percent = (double)overBooked * 100 / NUM_SIMULATIONS; // calculates the number of times the flight was overbooked
    }
    
    /**
     * Outputs all the information like number of tickets, number of seats, no show prob, average, and percent
     *
     *
     */
    public void reportResults()
    {
        System.out.println("Simulations: " + ticketsSold + " tickets sold for " + seats + " seats" + "; no-show probability = " + noShowProbability);
        System.out.println("Based on 100000 simulations:");
        System.out.println("Average seats filled: " + average);
        System.out.println("Number of times overbooked: " + overBooked + " or " + percent + " percent");
    }
    
    
    /**
     * Simulates a flight with a specific number of tickets sold, seats, and no show prob
     *
     */
    public static void main(String [] args)
    {
        AirlineMonteCarlo mySim = new AirlineMonteCarlo (151, 143, 0.08);
        mySim.runSimulation();
        mySim.reportResults();
    }
}
