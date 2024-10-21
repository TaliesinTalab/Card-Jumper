public class Entities {
    private String name;
    private int y_coordinate;
    private int x_coordinate;

    public Entities(String initialName, int y, int x) {
        this.name = initialName;
        this.y_coordinate = y;
        this.x_coordinate = x;
    }


    // Setters
    public void setName(String new_name) {
        this.name = new_name;
    }
    public void setY_coordinate(int new_y) {
        this.y_coordinate = new_y;
    }
    public void setX_coordinate(int new_x) {
        this.x_coordinate = new_x;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getY_coordinate() {
        return y_coordinate;
    }
    public int getX_coordinate() {
        return x_coordinate;
    }




    public static void main(String[] args) {
        Entities John = new Entities("John", 1, 3);
        System.out.println(John.getName() + System.lineSeparator() + John.getX_coordinate() + System.lineSeparator()
        + John.getY_coordinate() + System.lineSeparator());
    }
}