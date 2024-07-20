
import java.util.Scanner;

public class Auto {
    private String nombreDueno;
    private String placa;
    private double precio;
    private double precioDescuento;


    public Auto(String nombreDueno, String placa, double precio) {
        this.nombreDueno = nombreDueno;
        this.placa = placa;

        this.precio = precio;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(double precioDescuento) {
        this.precioDescuento = precioDescuento;
    }


    int ProntoPago(Scanner scanner) {
        System.out.println("¿Elija su plan de pago?");
        System.out.println("0. 30 días\n1. 15 días\n2. El mismo día");
        int opcion = scanner.nextInt();

        return switch (opcion) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            default -> throw new IllegalStateException("Valor no valido: " + opcion);
        };
    }

    boolean Servicio(Scanner scanner) {
        System.out.println("¿El Auto es de servicio público?");
        System.out.println("1. Sí\n2. No");
        int opcion = scanner.nextInt();
        return opcion == 1;
    }

    boolean TrasladoCuenta(Scanner scanner) {
        System.out.println("¿El Auto está siendo trasladado de dueño?");
        System.out.println("1. Sí\n2. No");
        int opcion = scanner.nextInt();
        return opcion == 1;
    }

    public String Impuestos(Auto auto, Scanner scanner) {
        double precioOriginal = auto.getPrecio();
        double precioConDescuento = precioOriginal;
        double precioFinal;

        String descuentosAplicados = null;
        int dias = ProntoPago(scanner);
        switch (dias) {
            case 0:
                precioConDescuento *= 0.05;
                descuentosAplicados+=("Pago 30 días (5%)\n");
                break;
            case 1:
                precioConDescuento *= 0.10;
                descuentosAplicados+=("Pago 15 días (10%)\n");
                break;
            case 2:
                precioConDescuento *= 0.15;
                descuentosAplicados+=("Pago el mismo día (15%)\n");
                break;
        }

        if (Servicio(scanner)) {
            precioConDescuento += 50;
            descuentosAplicados+=("Servicio Público ($50)\n");
        } else {
            precioConDescuento += 80;
        }

        if (dias != 2 && TrasladoCuenta(scanner)) {
            precioConDescuento *= 0.90;
            descuentosAplicados+=("Traslado de Cuenta (10%)\n");
        } else if (dias == 2 && TrasladoCuenta(scanner)) {
            System.out.println("Este descuento no aplica si paga el mismo dia.");
        }

        precioFinal = precioOriginal + precioConDescuento;
        auto.setPrecioDescuento(precioConDescuento);
        return "Precio original: $" + precioOriginal + "\n" +
                "Descuentos aplicados:\n" + descuentosAplicados +
                "Impuestos: $" + precioConDescuento + "\n" +
                "Precio final: $" + precioFinal + "\n";
    }
}
