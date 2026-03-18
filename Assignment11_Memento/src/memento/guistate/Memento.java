package memento.guistate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {
    private int[] options;
    private boolean isSelected;
    private String timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone();
        this.isSelected = isSelected;
        this.timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Memento created at " + timestamp);
    }

    public int[] getOptions() {
        return options;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String getDescription() {
        String colorStr = String.format("[%d,%d,%d]", options[0], options[1], options[2]);
        return colorStr + " Checkbox:" + isSelected + " at " + timestamp;
    }
}