package org.example;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        caso1Estudiantes();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso2Articulos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso3Textos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso4Trabajadores();
    }

    public static void caso1Estudiantes() {
        System.out.println("============== CASO 1: ESTUDIANTES ==============\n");
        List<Alumno> estudiantes = Arrays.asList(
                new Alumno("Lucía Benítez", 8.2, "6A"),
                new Alumno("Tomás Herrera", 6.1, "6B"),
                new Alumno("Camila Gómez", 9.4, "6A"),
                new Alumno("Pablo López", 7.5, "6B"),
                new Alumno("Florencia Rivas", 5.8, "6A"),
                new Alumno("Ignacio Torres", 8.7, "6B"),
                new Alumno("Valentina Díaz", 9.9, "6A"),
                new Alumno("Rodrigo Álvarez", 6.7, "6B")
        );

        System.out.println("1. Estudiantes aprobados (nota ≥ 7) en mayúsculas:");
        List<String> aprobados = estudiantes.stream()
                .filter(e -> e.getNota() >= 7.0)
                .map(e -> e.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        aprobados.forEach(System.out::println);

        System.out.println("\n2. Promedio general de notas:");
        double promedioGeneral = estudiantes.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio: %.2f\n", promedioGeneral);

        System.out.println("\n3. Estudiantes agrupados por curso:");
        Map<String, List<Alumno>> estudiantesPorCurso = estudiantes.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        estudiantesPorCurso.forEach((curso, lista) -> {
            System.out.println("Curso " + curso + ":");
            lista.forEach(e -> System.out.println("  - " + e.getNombre()));
        });

        System.out.println("\n4. Los 3 mejores promedios:");
        estudiantes.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .forEach(e -> System.out.printf("  %s - Nota: %.2f\n", e.getNombre(), e.getNota()));
    }

    public static void caso2Articulos() {
        System.out.println("============== CASO 2: ARTÍCULOS ==============\n");
        List<Producto> articulos = Arrays.asList(
                new Producto("Teclado Mecánico RGB", "Periféricos", 120.0, 40),
                new Producto("Mouse Gamer Inalámbrico", "Periféricos", 85.0, 55),
                new Producto("Monitor 27\" 2K", "Pantallas", 320.0, 20),
                new Producto("Cable HDMI 2.1", "Accesorios", 18.0, 100),
                new Producto("Laptop Ultraliviana", "Computadoras", 950.0, 10),
                new Producto("Tablet Android 10\"", "Dispositivos", 210.0, 25),
                new Producto("Disco SSD 1TB", "Almacenamiento", 110.0, 60),
                new Producto("Memoria RAM 16GB", "Componentes", 70.0, 80)
        );

        System.out.println("1. Artículos con precio mayor a $100 (ordenados por precio):");
        articulos.stream()
                .filter(a -> a.getPrecio() > 100.0)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .forEach(System.out::println);

        System.out.println("\n2. Stock total por categoría:");
        Map<String, Integer> stockPorCategoria = articulos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.summingInt(Producto::getStock)));
        stockPorCategoria.forEach((cat, stock) -> System.out.printf("  %s: %d unidades\n", cat, stock));

        System.out.println("\n3. Artículos en formato nombre;precio:");
        String articulosConcatenados = articulos.stream()
                .map(a -> a.getNombre() + ";" + a.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(articulosConcatenados);

        System.out.println("\n4. Precio promedio:");
        double precioPromedioGeneral = articulos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.printf("  General: $%.2f\n", precioPromedioGeneral);

        System.out.println("  Por categoría:");
        Map<String, Double> promedioPorCategoria = articulos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.averagingDouble(Producto::getPrecio)));
        promedioPorCategoria.forEach((cat, promedio) -> System.out.printf("    %s: $%.2f\n", cat, promedio));
    }

    public static void caso3Textos() {
        System.out.println("============== CASO 3: TEXTOS ==============\n");
        List<Libro> textos = Arrays.asList(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 480, 22.5),
                new Libro("Los pilares de la Tierra", "Ken Follett", 920, 35.0),
                new Libro("1984", "George Orwell", 350, 18.9),
                new Libro("Rayuela", "Julio Cortázar", 600, 27.5),
                new Libro("Fahrenheit 451", "Ray Bradbury", 290, 15.2),
                new Libro("It", "Stephen King", 1130, 40.0),
                new Libro("El alquimista", "Paulo Coelho", 240, 14.8),
                new Libro("Drácula", "Bram Stoker", 520, 21.0)
        );

        System.out.println("1. Textos con más de 300 páginas (orden alfabético):");
        textos.stream()
                .filter(t -> t.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .forEach(titulo -> System.out.println("  - " + titulo));

        System.out.println("\n2. Promedio de páginas:");
        double promedioPaginas = textos.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.printf("  %.2f páginas\n", promedioPaginas);

        System.out.println("\n3. Cantidad de textos por autor:");
        Map<String, Long> textosPorAutor = textos.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()));
        textosPorAutor.forEach((autor, cantidad) -> System.out.printf("  %s: %d texto(s)\n", autor, cantidad));

        System.out.println("\n4. Texto más caro:");
        textos.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .ifPresent(texto -> System.out.println("  " + texto));
    }

    public static void caso4Trabajadores() {
        System.out.println("============== CASO 4: TRABAJADORES ==============\n");
        List<Empleado> trabajadores = Arrays.asList(
                new Empleado("Sofía Luna", "Ventas", 3100.0, 27),
                new Empleado("Lucas Romero", "IT", 4600.0, 38),
                new Empleado("Martina Salas", "Recursos Humanos", 2900.0, 29),
                new Empleado("Federico Paredes", "IT", 4100.0, 33),
                new Empleado("Patricia León", "Finanzas", 3700.0, 41),
                new Empleado("Juan Arce", "Ventas", 3300.0, 25),
                new Empleado("Gabriela Torres", "Marketing", 2800.0, 23),
                new Empleado("Nicolás Vega", "Finanzas", 3950.0, 31)
        );

        System.out.println("1. Trabajadores con salario mayor a $2000 (orden descendente):");
        trabajadores.stream()
                .filter(t -> t.getSalario() > 2000.0)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .forEach(System.out::println);

        System.out.println("\n2. Salario promedio general:");
        double salarioPromedio = trabajadores.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.printf("  $%.2f\n", salarioPromedio);

        System.out.println("\n3. Suma de salarios por departamento:");
        Map<String, Double> salariosPorDepartamento = trabajadores.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.summingDouble(Empleado::getSalario)));
        salariosPorDepartamento.forEach((dept, total) -> System.out.printf("  %s: $%.2f\n", dept, total));

        System.out.println("\n4. Los 2 trabajadores más jóvenes:");
        trabajadores.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .forEach(nombre -> System.out.println("  - " + nombre));
    }
}
