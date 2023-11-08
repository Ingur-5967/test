#1(python)
input_value = input()

result = ""
string = ""
is_reverse = False

for arg in input_value:
    if (arg == "(" and not is_reverse):
        is_reverse = True
        result += string
        string = ""
        continue
    elif (arg == ")" and is_reverse):
        is_reverse = False
        result += string
        string = ""
        continue
        
    if is_reverse == False:
        string = string + arg
    else:
        string = arg + string
        
result += string

print(result)

#2(java)
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();

        if(value.length() > 1000) return;

        System.out.println(buildPalindrome(value));
    }

    public static String buildPalindrome(String join) {

        String value = "", buffered = "";
        int index = 0;

        if(join.contentEquals(new StringBuffer(join).reverse()) || join.isEmpty()) return join;
        
        while(!(join + value).contentEquals(buffered)) {
            for (int i = 0; i < join.length(); i++) {

                value = new StringBuffer(join.substring(0, i + index)).reverse().toString();
                buffered = new StringBuffer(join + value).reverse().toString();

                if((join + value).contentEquals(buffered)) return join + value;
            }
            index++;
        }
        return join;
    }
}


#3(java)
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int rows, length;

        Scanner scanner = new Scanner(System.in);

        try {
            rows = Integer.parseInt(scanner.nextLine());
            length = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | InputMismatchException e) { return; }

        String cost = scanner.nextLine();

        if(!cost.contains(" "))
            return;

        String[] rooms = cost.split(" ");

        if((rows > 5 || length > 5) || rooms.length > (rows * length)) return;

        boolean outBoundsCheck = Arrays.stream(rooms).map(Integer::valueOf).allMatch(num -> num > 25 || num < 0);
        boolean sumCheck = Arrays.stream(rooms).mapToInt(Integer::valueOf).sum() > 100;

        if(outBoundsCheck || sumCheck)
            return;

        System.out.println(calculateCost(rows, length, rooms));
    }

    public static int calculateCost(int rows, int length, String[] infoRooms) {

        int counter = 0, result = 0;
        List<Room> roomList = new ArrayList<>();

        for(int row = 0; row < rows; row++) {
            for(int pos = 0; pos < length; pos++) {
                roomList.add(
                        new Room(row, pos, Integer.parseInt(infoRooms[counter]),
                                Integer.parseInt(infoRooms[counter]) == 0)
                );
                counter++;
            }
        }

        List<Integer> blacklistPosition = new ArrayList<>();

        for(Room room : roomList) {
            if(blacklistPosition.contains(room.getPosition())) continue;

            if(room.isLocked()) {
                blacklistPosition.add(room.getPosition());
                continue;
            }
            result += room.getWeight();
        }

        return result;
    }

    public static class Room {
        private final int row, position, weight;
        private final boolean isLocked;
        public Room(int row, int position, int weight, boolean isLocked) {
            this.row = row;
            this.position = position;
            this.weight = weight;
            this.isLocked = isLocked;
        }
        public int getRow() {
            return row;
        }
        public int getPosition() {
            return position;
        }
        public boolean isLocked() {
            return isLocked;
        }
        public int getWeight() {
            return weight;
        }
    }
}
