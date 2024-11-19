public abstract class Entities {
    private String name;
    private int y_coordinate;
    private int x_coordinate;

    public Entities(String name, int y, int x) {
        this.name = name;
        this.y_coordinate = y;
        this.x_coordinate = x;
    }

    // Setters
    public void setName(String new_name) {this.name = new_name;}
    public void setY_coordinate(int new_y) {this.y_coordinate = new_y;}
    public void setX_coordinate(int new_x) {this.x_coordinate = new_x;}

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

    // Other functions
    abstract void attack();


    // main
    public static void main(String[] args) {
    }
}