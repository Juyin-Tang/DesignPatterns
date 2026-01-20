import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Map myMap = createMap();
        System.out.println("Map is created");
        myMap.display();
    }

    public static Map createMap(){
        Random random = new Random();
        int type = random.nextInt(2);

        if(type == 0){
            return new CityMap();
        }else {
            return new WildernessMap();
        }

        }
    }
abstract class Map{
    public abstract Tile createTile();

    public void display(){
        int size = 5;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                Tile t = createTile();
                System.out.print(t.getCharacter()+" ");
            }
            System.out.println();
        }
    }
}
abstract class Tile{
    public abstract char getCharacter();
    public abstract String getType();
    public abstract void action();
}

class CityMap extends Map{
    private Random random = new Random();
    @Override
    public Tile createTile() {
        int type = random.nextInt(3);
        if (type == 0) {
            return new RoadTile();
        } else if (type == 1) {
            return new ForestTile();
        } else {
            return new BuildingTile();
        }
    }
}

class WildernessMap extends Map{
    private Random random = new Random();
    @Override
    public Tile createTile() {
        int type = random.nextInt(3);
        if (type == 0) {
            return new ForestTile();
        } else if (type == 1) {
            return new WaterTile();
        } else {
            return new SwampTile();
        }
    }

}
class RoadTile extends Tile{
    @Override
    public char getCharacter() {
        return 'R';
    }
    @Override
    public String getType() {
        return "Road";
    }
    @Override
    public void action() {}
}
class ForestTile extends Tile{
    @Override
    public char getCharacter() {
        return 'F';
    }
    @Override
    public String getType() {
        return "Forest";
    }
    @Override
    public void action() {}
}
class BuildingTile extends Tile{
    @Override
    public char getCharacter() {
        return 'B';
    }
    @Override
    public String getType() {
        return "Building";
    }
    @Override
    public void action() {}
}
class WaterTile extends Tile{
    @Override
    public char getCharacter() {
        return 'W';
    }
    @Override
    public String getType() {
        return "Water";
    }
    @Override
    public void action() {}
}
class SwampTile extends Tile{
    @Override
    public char getCharacter() {
        return 'S';
    }
    @Override
    public String getType() {
        return "Swamp";
    }
    @Override
    public void action() {}
}
