public class IntermediateState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        character.addExperience(15);
        System.out.println("Training... +15 EXP");

        if (character.getExperience() >= 70) {
            character.setState(new ExpertState());
            System.out.println("Level Up! You are now Expert.");
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        character.addHealth(10);
        System.out.println("Meditating... +10 HP");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You cannot fight at Intermediate level.");
    }

    @Override
    public String getLevelName() {
        return "Intermediate";
    }
}
