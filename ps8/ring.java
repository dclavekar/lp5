import java.util.Scanner;

public class ring {

    public static boolean state[] = new boolean[5];
    static int cordinator;

    public static void view_ring() {
        System.out.println("***");
        for (int i = 0; i < 5; i++) {
            if (state[i])
                System.out.println(i + 1 + " ");
        }
        System.out.println("***");
    }

    public static void up(int i) {
        if (state[i - 1])
            System.out.println("process already up!");
        else {
            state[i - 1] = true;
            System.out.println("Process upped");
        }
    }

    public static void down(int i) {
        if (!state[i - 1])
            System.out.println("process already down!");
        else {
            state[i - 1] = false;
            System.out.println("Process downed");
        }
    }

    public static void election(int ini) {
        System.out.println("!! ELECTION !!");
        ini=ini-1;
        int current_coordinator = ini;
        int token = (ini + 1)%5; ;

        while (token != ini) {
            
            if (state[token]) {
                System.out.println("Token at " + (token+1));
                if (token > current_coordinator) {
                    current_coordinator = token;
                }
                
            }
            token = (token + 1) % 5;

        }
        cordinator = current_coordinator;
        System.out.println("New cordinator is " + (cordinator+1));

    }

    public static void send_msg(int ini) {
        if (state[ini - 1]) {
            System.out.println("Sending msg to cordinator from " + ini);

            if (state[cordinator - 1]) {
                System.out.println("Cordinator alive.");
            } else {
                System.out.println("Cordinator not responding... Initiating election!");
                election(ini);
            }
        } else {
            System.out.println("Process down right now. Cant send msg");
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            state[i] = true;
        }
        cordinator = 5;
        int choice = 0;
        System.out.println("Hi");
        do {
            System.out.println("***********Menu***********");
            System.out.println("1. up a process");
            System.out.println("2. down a process");
            System.out.println("3. message coordinator");
            System.out.println("4. Election");
            System.out.println("5. View Ring");
            System.out.println("6. Exit");
            System.out.println("**************************");
            System.out.println("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice)

            {
                case 1: {
                    System.out.println("Enter process to up:");
                    int pup = sc.nextInt();
                    up(pup);
                }
                    break;
                case 2: {
                    System.out.println("Enter process to down:");
                    int pdown = sc.nextInt();
                    down(pdown);
                }
                    break;
                case 3: {
                    System.out.println("Enter process to send msg to cordinator:");
                    int ini = sc.nextInt();
                    send_msg(ini);
                }
                    break;
                case 4: {
                    System.out.println("Enter process to initiate election:");
                    int ini = sc.nextInt();
                    election(ini);
                }
                    break;
                case 5: {
                    view_ring();
                }
                    break;
                case 6: {
                    return;
                }

            }

        } while (choice != 6);
    }
}
