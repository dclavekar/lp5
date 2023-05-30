import java.util.Scanner;

import javax.sound.midi.SysexMessage;

public class bully {

    static boolean state[] = new boolean[5];

    public static void up(int pup) {
        if (pup == 5) {
            state[pup - 1] = true;
            System.out.println("p5 is cordinator");
        } else if (state[pup - 1]) {
            System.out.println("process already up!");
        } else {
            state[pup - 1] = true;
            System.out.println(pup + " is upped now.");
            System.out.println("!! ELECTION !!");
            for (int i = pup+1; i <= 5; i++) {
                if (state[i - 1]) {
                    System.out.println("process" + i + " has sent ALIVE msg. Job of initiator is done.");
                    break;
                }
            }
        }
    }

    public static void down(int pdown) {
        if (state[pdown - 1] == false) {
            System.out.println("process is already down!!");
        } else {
            state[pdown - 1] = false;
            System.out.println(pdown + " is successfully downed");
        }

    }

    public static void send_msg(int ini) {
        if (state[ini-1]) {
            System.out.println("!! ELECTION !!");
            for (int i = ini ; i<=5; i++) {
                System.out.println("Initiator " + ini + " sending msg to " + i);
            }
            for (int i = 5; i >= ini; i--) {
                if (state[i - 1]) {
                    System.out.println(i + " has replied with OK msg. New cordinator is " + i);
                    break;
                }
            }
        }
        else System.out.println("Initator down itself!!");

    }

    public static void main(String args[]) {
        System.out.println("hi");
        for(int i=0;i<5;i++)
        {
            state[i]=true;
        }
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Enter choice.\n1. up a process\n2. down a process\n3. send a message from a process\n4. exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("which process to up??");
                    int pup = sc.nextInt();
                    up(pup);
                }
                    break;
                case 2: {
                    System.out.println("which process to down??");
                    int pdown = sc.nextInt();
                    down(pdown);
                }
                    break;
                case 3: {
                    System.out.println("which process to send a msg from (initiator)??");
                    int ini = sc.nextInt();
                    send_msg(ini);
                }
                    break;
                case 4:
                    return;
            }

        } while (choice != 4);
    }
}
