import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Auto> autos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String nombreDueno, placa;
        double precio = 0;

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Añadir Auto");
            System.out.println("2. Calcular impuestos");
            System.out.println("3. Salir");
            int eleccion = scanner.nextInt();
            scanner.nextLine();
            switch (eleccion) {
                case 1:
                    System.out.println("Ingrese el nombre del dueño:");
                    nombreDueno = scanner.nextLine();
                    boolean placaValida;
                    do {
                        System.out.println("Ingrese la placa del Auto:");
                        placa = scanner.nextLine();
                        placaValida = true;
                        for (Auto auto : autos) {
                            if (auto.getPlaca().equalsIgnoreCase(placa)) {
                                placaValida = false;
                                System.out.println("Error: La placa ingresada ya está registrada.");
                                break;
                            }
                        }
                    } while (!placaValida);

                    boolean ingresoValido = false;
                    do {
                        try {
                            System.out.println("Ingrese el precio del Auto:");
                            precio = Double.parseDouble(scanner.nextLine());
                            if (precio <= 0) {
                                throw new NumberFormatException("Ingrese no valido");
                            }
                            ingresoValido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } while (!ingresoValido);
                    autos.add(new Auto(nombreDueno, placa, precio));
                    break;

                case 2:
                    if (autos.isEmpty()) {
                        System.out.println("No hay Autos añadidos.");
                        break;
                    }
                    System.out.println("Seleccione un Auto:");
                    for (int i = 0; i < autos.size(); i++) {
                        System.out.println((i + 1) + ". " + autos.get(i).getNombreDueno() + " - " + autos.get(i).getPlaca());
                    }
                    int eleccionCarro = scanner.nextInt() - 1;
                    Auto autoSeleccionado = autos.get(eleccionCarro);
                    System.out.println(autoSeleccionado.Impuestos(autoSeleccionado, scanner));
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
