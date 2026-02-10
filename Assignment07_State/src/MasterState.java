public class MasterState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        System.out.println("You are already a Master. The game has ended.");
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("You are already a Master. The game has ended.");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You are already a Master. The game has ended.");
    }

    @Override
    public String getLevelName() {
        return "Master";
    }
}
