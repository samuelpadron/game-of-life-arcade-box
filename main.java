import model.World;

public class main {
    public static void main(String[] args){
        World world = World.getInstance();
        System.out.print(world.getString());
    }
}
