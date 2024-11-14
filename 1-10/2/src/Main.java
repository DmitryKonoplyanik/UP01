import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        QuadraticEquation q1 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation q2 = new QuadraticEquation(2, 3, 4);
        QuadraticEquation q3 = q1.add(q2);
        System.out.println("q1 + q2 = " + q3.a + "x^2 + " + q3.b + "x + " + q3.c);
        QuadraticEquation q4 = q1.subtract(q2);
        System.out.println("q1 - q2 = " + q4.a + "x^2 + " + q4.b + "x + " + q4.c);
        QuadraticEquation q5 = q1.multiply(q2);
        System.out.println("q1 * q2 = " + q5.a + "x^2 + " + q5.b + "x + " + q5.c);
        QuadraticEquation q6 = q1.divide(q2);
        System.out.println("q1 / q2 = " + q6.a + "x^2 + " + q6.b + "x + " + q6.c);
        List<QuadraticEquation> equations = new ArrayList<>();
        equations.add(q1);
        equations.add(q2);
        equations.add(q3);
        equations.add(q4);
        equations.add(q5);
        equations.add(q6);
        List<Double> roots = new ArrayList<>();
        for (QuadraticEquation equation : equations) {
            double[] equationRoots = equation.getRoots();
            for (double root : equationRoots) {
                roots.add(root);
            }
        }
        Collections.sort(roots);
        System.out.println("Наименьший корень: " + roots.get(0));
        System.out.println("Наибольший корень: " + roots.get(roots.size() - 1));
    }
}

class QuadraticEquation {

    double a;
    double b;
    double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public boolean hasRealRoots() {
        return getDiscriminant() >= 0;
    }

    public double[] getRoots() {
        double d = getDiscriminant();
        if (d < 0) {
            return new double[0];
        } else if (d == 0) {
            double x = -b / (2 * a);
            return new double[]{x};
        } else {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            return new double[]{x1, x2};
        }
    }

    public double getExtremePoint() {
        return -b / (2 * a);
    }

    public boolean isDecreasing() {
        return a < 0;
    }

    public boolean isIncreasing() {
        return a > 0;
    }

    public QuadraticEquation add(QuadraticEquation other) {
        double newA = this.a + other.a;
        double newB = this.b + other.b;
        double newC = this.c + other.c;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation subtract(QuadraticEquation other) {
        double newA = this.a - other.a;
        double newB = this.b - other.b;
        double newC = this.c - other.c;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation multiply(QuadraticEquation other) {
        double newA = this.a * other.a;
        double newB = this.a * other.b + this.b * other.a;
        double newC = this.a * other.c + this.b * other.b + this.c * other.a;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation divide(QuadraticEquation other) {
        double newA = this.a / other.a;
        double newB = (this.b * other.a - this.a * other.b) / (other.a * other.a);
        double newC = (this.c * other.a - this.b * other.b + this.a * other.c) / (other.a * other.a * other.a);
        return new QuadraticEquation(newA, newB, newC);
    }
}
