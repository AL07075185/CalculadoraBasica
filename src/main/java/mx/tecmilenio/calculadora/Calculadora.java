package mx.tecmilenio.calculadora;

/**
 * Logica de negocio de la calculadora.
 * HU-02 Suma y resta | HU-03 Multiplicacion y division
 */
public class Calculadora {

    /** Suma dos numeros. */
    public double sumar(double numeroUno, double numeroDos) {
        return numeroUno + numeroDos;
    }

    /** Resta el segundo numero al primero. */
    public double restar(double numeroUno, double numeroDos) {
        return numeroUno - numeroDos;
    }

    /** Multiplica dos numeros. */
    public double multiplicar(double numeroUno, double numeroDos) {
        return numeroUno * numeroDos;
    }

    /**
     * Divide el primer numero entre el segundo.
     *
     * @throws ArithmeticException si el divisor es cero
     */
    public double dividir(double numeroUno, double numeroDos) {
        if (numeroDos == 0) {
            throw new ArithmeticException("No es posible dividir entre cero.");
        }
        return numeroUno / numeroDos;
    }
}
