class Polynomial {
    private int[] coefficients;
    private int degree;

    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
    }

    public double calculate(double x) {
        double result = 0;
        for (int i = 0; i <= degree; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.degree, other.degree);
        int[] newCoefficients = new int[maxDegree + 1];
        for (int i = 0; i <= this.degree; i++) {
            newCoefficients[i] += this.coefficients[i];
        }
        for (int i = 0; i <= other.degree; i++) {
            newCoefficients[i] += other.coefficients[i];
        }
        return new Polynomial(newCoefficients);
    }

    public Polynomial subtract(Polynomial other) {
        int maxDegree = Math.max(this.degree, other.degree);
        int[] newCoefficients = new int[maxDegree + 1];
        for (int i = 0; i <= this.degree; i++) {
            newCoefficients[i] += this.coefficients[i];
        }
        for (int i = 0; i <= other.degree; i++) {
            newCoefficients[i] -= other.coefficients[i];
        }
        return new Polynomial(newCoefficients);
    }

    public Polynomial multiply(Polynomial other) {
        int newDegree = this.degree + other.degree;
        int[] newCoefficients = new int[newDegree + 1];
        for (int i = 0; i <= this.degree; i++) {
            for (int j = 0; j <= other.degree; j++) {
                newCoefficients[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }
        return new Polynomial(newCoefficients);
    }

    public void print() {
        for (int i = degree; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (i != degree) {
                    if (coefficients[i] > 0) {
                        System.out.print(" + ");
                    } else {
                        System.out.print(" - ");
                    }
                }
                if (Math.abs(coefficients[i]) != 1 || i == 0) {
                    System.out.print(Math.abs(coefficients[i]));
                }
                if (i > 0) {
                    System.out.print("x");
                    if (i > 1) {
                        System.out.print("^" + i);
                    }
                }
            }
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        int[] coefficients1 = {1, 2, 3};
        Polynomial polynomial1 = new Polynomial(coefficients1);
        System.out.print("Polynomial 1: ");
        polynomial1.print();

        int[] coefficients2 = {4, 5, 6};
        Polynomial polynomial2 = new Polynomial(coefficients2);
        System.out.print("Polynomial 2: ");
        polynomial2.print();

        System.out.print("Polynomial 1 + Polynomial 2: ");
        polynomial1.add(polynomial2).print();

        System.out.print("Polynomial 1 - Polynomial 2: ");
        polynomial1.subtract(polynomial2).print();

        System.out.print("Polynomial 1 * Polynomial 2: ");
        polynomial1.multiply(polynomial2).print();

        System.out.println("Value of Polynomial 1 at x = 2: " + polynomial1.calculate(2));
    }
}
