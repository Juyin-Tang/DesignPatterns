public class NoviceState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        character.addExperience(10);
        System.out.println("Training... +10 EXP");

        if (character.getExperience() >= 30) {
            character.setState(new IntermediateState());
            System.out.println("Level Up! You are now Intermediate.");
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("You cannot meditate at Novice level.");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You cannot fight at Novice level.");
    }

    @Override
    public String getLevelName() {
        return "Novice";
    }
}
