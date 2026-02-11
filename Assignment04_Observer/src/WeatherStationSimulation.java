import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

interface Observer {
    void update(float temperature);
}

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

    public abstract void startStation();
    public abstract void stopStation();
}

class ConcreteWeatherStation extends WeatherStation implements Runnable {
    private static final float MIN_TEMP = -20.0f;
    private static final float MAX_TEMP = 45.0f;

    private Thread weatherThread;
    private Random random = new Random();
    private volatile boolean running = true;

    public ConcreteWeatherStation() {
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
                int sleepTime = 1000 + random.nextInt(4000);
                Thread.sleep(sleepTime);

                float change = random.nextBoolean() ? 1.0f : -1.0f;
                float newTemp = temperature + change;

                if (newTemp >= MIN_TEMP && newTemp <= MAX_TEMP) {
                    temperature = newTemp;
                    System.out.printf("%n[Weather Station] Temperature changed: %.1f°C → %.1f°C (%+.1f°C)%n",
                            newTemp - change, temperature, change);

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

class PhoneDisplay implements Observer {
    private String userName;

    public PhoneDisplay(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(float temperature) {
        System.out.printf("[%s's Phone] Current temperature: %.1f°C", userName, temperature);

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
        System.out.printf("[%s Billboard] Temperature: %.1f°C%n", location, temperature);
    }

    @Override
    public String toString() {
        return location + " Billboard";
    }
}

public class WeatherStationSimulation {
    public static void main(String[] args) {
        System.out.println("      WEATHER STATION SIMULATION      ");

        System.out.println();

        ConcreteWeatherStation weatherStation = new ConcreteWeatherStation();

        Observer user1Phone = new PhoneDisplay("Alice");
        Observer user2Phone = new PhoneDisplay("Bob");
        Observer weatherWebsite = new WebDisplay("WeatherForecast");
        Observer cityBillboard = new BillboardDisplay("City Center");

        System.out.println("\n--- Registering Observers ---");
        weatherStation.registerObserver(user1Phone);
        weatherStation.registerObserver(user2Phone);
        weatherStation.registerObserver(weatherWebsite);
        weatherStation.registerObserver(cityBillboard);

        System.out.println("\n--- Starting Simulation (30 seconds total) ---");
        weatherStation.startStation();

        try {
            System.out.println("\n[PHASE 1] All 4 observers receiving updates...");
            Thread.sleep(15000);


            System.out.println("~~ AFTER 15 SECONDS: Removing one observer (Alice's Phone) ~~");


            weatherStation.removeObserver(user1Phone);

            System.out.println("\n[PHASE 2] Now only 3 observers receiving updates...");
            System.out.println("(Alice's phone should no longer receive notifications)");
            Thread.sleep(15000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weatherStation.stopStation();

    }
}