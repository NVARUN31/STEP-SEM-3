
import java.util.Scanner;

class Team {
    String name;
    int size;

    Team(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String toString() {
        return name + "(" + size + ")";
    }
}

public class HackathonRegistrationDesk {

    public static void sortTeams(Team[] teams) {
        for (int i = 1; i < teams.length; i++) {
            Team key = teams[i];
            int j = i - 1;

            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }

            teams[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of teams: ");
        int n = sc.nextInt();

        Team[] teams = new Team[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter team name: ");
            String name = sc.next();

            System.out.print("Enter team size: ");
            int size = sc.nextInt();

            teams[i] = new Team(name, size);
        }

        sortTeams(teams);

        System.out.println("Sorted teams:");
        for (Team team : teams) {
            System.out.print(team + " ");
        }

        sc.close();
    }
}
