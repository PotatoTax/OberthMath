import java.util.Scanner;
import java.util.ArrayList;

class OberthMath {
    private static double mu = 398600441800000.0;
    private static int radius = 6378;

    private float apogee;
    private float perigee;
    private float current;
    private float thrust;
    private float mass;

    private float semiMajor;

    private double vApogee;
    private double vPerigee;
    private double vCurrent;

    private ArrayList<double[]> powers;

    ArrayList<double[]> getPowers() {
        return powers;
    }

    OberthMath() {
        powers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Apogee : ");
        apogee = scanner.nextInt();
        System.out.print("Perigee : ");
        perigee = scanner.nextInt();
        System.out.print("Current : ");
        current = scanner.nextInt();
        System.out.print("Thrust : ");
        thrust = scanner.nextInt();
        System.out.print("Ship Mass : ");
        mass = scanner.nextInt();

        scanner.close();

        semiMajor = (apogee + perigee) / 2 + radius;
        System.out.println("\nSemi-Major Axis : " + semiMajor);

        vApogee = visViva(apogee);
        System.out.println("\nVelocity at Apogee : " + vApogee);

        vPerigee = visViva(perigee);
        System.out.println("Velocity at Perigee : " + vPerigee);

        vCurrent = visViva(current);
        System.out.println("Current Velocity : " + vCurrent + "\n");

        float elev = perigee;
        for (int i = 0; i <= 100; i++) {
            double velocity = visViva(elev);
            double power = (thrust / mass) * velocity;
            powers.add(new double[] {elev, power});
            elev += (apogee - perigee) / 100;
        }
    }

    private double visViva(float altitude) {
        return Math.sqrt(mu * (2/(1000*(altitude + radius)) - 1/(semiMajor*1000)));
    }

    public static void main(String[] args) {
        new OberthMath();
    }
}