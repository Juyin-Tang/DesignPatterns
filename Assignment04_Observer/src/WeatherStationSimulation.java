import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 1. Observer Interface
interface Observer {
    void update(float temperature);
}

//  2. Subject (Weather Station Base)
abstract class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    protected float temperature;

    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Registered observer: " + observer.getClass().getSimpleName());
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Removed observer: " + observer.getClass().getSimpleName());
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public float getTemperature() {
        return temperature;
    }

    // Abstract methods for starting/stopping
    public abstract void startStation();
    public abstract void stopStation();
}

// 3. Concrete Weather Station
class ConcreteWeatherStation extends WeatherStation implements Runnable {
    private static final float MIN_TEMP = -20.0f;
    private static final float MAX_TEMP = 45.0f;

    private Thread weatherThread;
    private Random random = new Random();
    private volatile boolean running = true;

    public ConcreteWeatherStation() {
        // Set initial random temperature between -10°C and 35°C
        this.temperature = MIN_TEMP + 10 + random.nextFloat() * (MAX_TEMP - MIN_TEMP - 20);
        System.out.printf("Weather station initialized! Current temperature: %.1f°C%n", temperature);
    }

    @Override
    public void startStation() {
        if (weatherThread == null) {
            weatherThread = new Thread(this, "WeatherStation-Thread");
            weatherThread.start();
        }
    }

    @Override
    public void stopStation() {
        running = false;
        if (weatherThread != null) {
            weatherThread.interrupt();
        }
    }

    @Override
    public void run() {
        System.out.println("Weather station started simulating temperature changes...");

        try {
            while (running && !Thread.currentThread().isInterrupted()) {
                // Random interval between updates: 1-5 seconds
                int sleepTime = 1000 + random.nextInt(4000);
                Thread.sleep(sleepTime);

                // Random temperature change: +1 or -1 degree
                float change = random.nextBoolean() ? 1.0f : -1.0f;
                float newTemp = temperature + change;

                // Check temperature boundaries
                if (newTemp >= MIN_TEMP && newTemp <= MAX_TEMP) {
                    temperature = newTemp;
                    System.out.printf("%n[Weather Station] Temperature changed: %.1f°C → %.1f°C (%+.1f°C)%n",
                            newTemp - change, temperature, change);

                    // Notify all registered observers
                    notifyObservers();
                } else {
                    System.out.printf("[Weather Station] Temperature %.1f°C out of range [%.1f°C ~ %.1f°C], keeping current value%n",
                            newTemp, MIN_TEMP, MAX_TEMP);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Weather station thread interrupted.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Weather station stopped.");
    }
}

//  4. Concrete Observers
class PhoneDisplay implements Observer {
    private String userName;

    public PhoneDisplay(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(float temperature) {
        System.out.printf("[%s's Phone] Current temperature: %.1f°C", userName, temperature);

        // Personalized message based on temperature
        if (temperature > 30) {
            System.out.println(" - Hot weather, stay hydrated!");
        } else if (temperature < 0) {
            System.out.println(" - Freezing weather, dress warmly!");
        } else if (temperature > 20) {
            System.out.println(" - Pleasant weather, enjoy outdoors!");
        } else {
            System.out.println(" - Cool weather, light jacket recommended.");
        }
    }

    @Override
    public String toString() {
        return userName + "'s Phone Display";
    }
}

class WebDisplay implements Observer {
    private String websiteName;

    public WebDisplay(String websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public void update(float temperature) {
        // Determine weather condition based on temperature
        String condition;
        if (temperature > 25) condition = "Sunny";
        else if (temperature > 15) condition = "Partly Cloudy";
        else if (temperature > 5) condition = "Cloudy";
        else condition = "Cold";

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s Website] Live Weather: %.1f°C, %s | Updated: %s%n",
                websiteName, temperature, condition, time);
    }

    @Override
    public String toString() {
        return websiteName + " Web Display";
    }
}

class BillboardDisplay implements Observer {
    private String location;

    public BillboardDisplay(String location) {
        this.location = location;
    }

    @Override
    public void update(float temperature) {
        // Simple display for billboards
        System.out.printf("[%s Billboard] Temperature: %.1f°C%n", location, temperature);
    }

    @Override
    public String toString() {
        return location + " Billboard";
    }
}

// 5. Main Simulation Class
public class WeatherStationSimulation {
    public static void main(String[] args) {
        System.out.println("      WEATHER STATION SIMULATION      ");

        System.out.println();

        // 1. Create weather station (automatically sets random initial temperature)
        ConcreteWeatherStation weatherStation = new ConcreteWeatherStation();

        // 2. Create different types of observers

        Observer user1Phone = new PhoneDisplay("Alice");
        Observer user2Phone = new PhoneDisplay("Bob");
        Observer weatherWebsite = new WebDisplay("WeatherForecast");
        Observer cityBillboard = new BillboardDisplay("City Center");

        // 3. Register all observers
        System.out.println("\n--- Registering Observers ---");
        weatherStation.registerObserver(user1Phone);
        weatherStation.registerObserver(user2Phone);
        weatherStation.registerObserver(weatherWebsite);
        weatherStation.registerObserver(cityBillboard);

        // 4. Start the weather station thread (automatic updates begin)
        System.out.println("\n--- Starting Simulation (30 seconds total) ---");
        weatherStation.startStation();

        try {
            // Phase 1: Run with all observers for 15 seconds
            System.out.println("\n[PHASE 1] All 4 observers receiving updates...");
            Thread.sleep(15000);


            System.out.println("~~ AFTER 15 SECONDS: Removing one observer (Alice's Phone) ~~");


            // 5. Dynamically remove one observer
            weatherStation.removeObserver(user1Phone);

            // Phase 2: Continue simulation with 3 observers for 15 seconds
            System.out.println("\n[PHASE 2] Now only 3 observers receiving updates...");
            System.out.println("(Alice's phone should no longer receive notifications)");
            Thread.sleep(15000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 6. Stop the simulation
        weatherStation.stopStation();

    }
}