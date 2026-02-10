public class ExpertState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        character.addExperience(20);
        System.out.println("Training... +20 EXP");

        if (character.getExperience() >= 120) {
            character.setState(new MasterState());
            System.out.println("Congratulations! You are now a Master!");
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        character.addHealth(15);
        System.out.println("Meditating... +15 HP");
    }

    @Override
    public void fight(GameCharacter character) {
        character.addExperience(25);
        character.addHealth(-20);
        System.out.println("Fighting! +25 EXP, -20 HP");

        if (character.getExperience() >= 120) {
            character.setState(new MasterState());
            System.out.println("Congratulations! You are now a Master!");
        }
    }

    @Override
    public String getLevelName() {
        return "Expert";
    }
}
