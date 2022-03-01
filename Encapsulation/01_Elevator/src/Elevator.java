public class Elevator {
    int currentFloor = 1;
    int minFloor;
    int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor () {
        return currentFloor;
    }

    public void moveDown () {
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
    }

    public void moveUp () {
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
    }

    public void move (int floor) {
        if (floor > maxFloor || floor < minFloor) {
            System.out.println("Ошибка");
        } else {
            currentFloor = currentFloor + floor;
        }
    }
}