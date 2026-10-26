# Calculadora Básica en Java (JFrame)

Actividad 4 del curso Administración de Proyectos de Software, Universidad Tecmilenio.

Calculadora de escritorio desarrollada en un Sprint de 2 horas, con interfaz gráfica en Java Swing (JFrame) y cuatro operaciones: suma, resta, multiplicación y división. Valida campos vacíos, valores no numéricos y división entre cero, y conserva un historial de las operaciones realizadas en la sesión.

## Estructura

| Archivo | Responsabilidad |
| --- | --- |
| `Calculadora.java` | Lógica de negocio con los métodos sumar, restar, multiplicar y dividir |
| `CalculadoraGUI.java` | Ventana JFrame, captura de datos, validaciones, resultado e historial |
| `Main.java` | Punto de entrada; abre la ventana en el hilo de eventos de Swing |

## Ejecución

Abrir la carpeta en NetBeans (File > Open Project), seleccionar el proyecto y presionar Run Project (F6). Requiere JDK 17 o superior.

Desde terminal: `mvn package` y después `java -jar target/CalculadoraBasica-1.0.jar`.
