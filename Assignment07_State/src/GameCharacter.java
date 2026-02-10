public class GameCharacter {
    private String name;
    private int experience;
    private int health;
    private CharacterState state;

    public GameCharacter(String name) {
        this.name = name;
        this.experience = 0;
        this.health = 100;
        this.state = new NoviceState();
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public void train() {
        state.train(this);
    }

    public void meditate() {
        state.meditate(this);
    }

    public void fight() {
        state.fight(this);
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void addHealth(int amount) {
        health += amount;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public CharacterState getState() {
        return state;
    }

    public void showStatus() {
        System.out.println("Name: " + name);
        System.out.println("Level: " + state.getLevelName());
        System.out.println("EXP: " + experience);
        System.out.println("HP: " + health);
    }
}
