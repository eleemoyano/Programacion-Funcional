package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        caso1Alumnos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso2Productos();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso3Libros();
        System.out.println("\n" + "=".repeat(80) + "\n");
        caso4Empleados();
    }

    // ============== CASO 1: ALUMNOS ==============
    public static void caso1Alumnos() {
        System.out.println("============== CASO 1: ALUMNOS ==============\n");

        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Elena Ríos", 7.8, "5C"),
                new Alumno("Marcos Soto", 5.9, "5D"),
                new Alumno("Julia Vidal", 9.1, "5C"),
                new Alumno("Andrés Castro", 7.2, "5D"),
                new Alumno("Silvia Peña", 6.4, "5C"),
                new Alumno("David Navarro", 8.3, "5D"),
                new Alumno("Natalia Gil", 9.8, "5C"),
                new Alumno("Ricardo Blasco", 6.9, "5D")
        );

        // 1. Nombres de aprobados (≥7) en mayúsculas y ordenados
        System.out.println("1. Alumnos aprobados (nota ≥ 7) en mayúsculas:");
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        aprobados.forEach(System.out::println);

        // 2. Promedio general de notas
        System.out.println("\n2. Promedio general de notas:");
        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio: %.2f\n", promedioGeneral);

        // 3. Agrupar alumnos por curso
        System.out.println("\n3. Alumnos agrupados por curso:");
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println("Curso " + curso + ":");
            lista.forEach(a -> System.out.println("  - " + a.getNombre()));
        });

        // 4. Los 3 mejores promedios
        System.out.println("\n4. Los 3 mejores promedios:");
        alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .forEach(a -> System.out.printf("  %s - Nota: %.2f\n", a.getNombre(), a.getNota()));
    }

    // ============== CASO 2: PRODUCTOS ==============
    public static void caso2Productos() {
        System.out.println("============== CASO 2: PRODUCTOS ==============\n");

        List<Producto> productos = Arrays.asList(
                new Producto("Auriculares Inalámbricos", "Audio", 95.00, 45),
                new Producto("Power Bank 10000mAh", "Accesorios", 30.50, 60),
                new Producto("Smartwatch Deportivo", "Wearables", 150.00, 25),
                new Producto("Funda Silicona Móvil", "Accesorios", 15.00, 100),
                new Producto("Televisor QLED 55\"", "Visual", 950.00, 12),
                new Producto("Proyector Portátil", "Visual", 320.00, 18),
                new Producto("Router WiFi 6", "Redes", 85.00, 50),
                new Producto("Cargador Rápido USB-C", "Accesorios", 20.00, 75)
        );

        // 1. Productos con precio > 100, ordenados por precio descendente
        System.out.println("1. Productos con precio mayor a $100 (ordenados por precio):");
        productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .forEach(System.out::println);

        // 2. Agrupar por categoría y calcular stock total
        System.out.println("\n2. Stock total por categoría:");
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        stockPorCategoria.forEach((cat, stock) ->
                System.out.printf("  %s: %d unidades\n", cat, stock));

        // 3. String con nombre;precio separados por ";"
        System.out.println("\n3. Productos en formato nombre;precio:");
        String productosConcatenados = productos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(productosConcatenados);

        // 4. Precio promedio general y por categoría
        System.out.println("\n4. Precio promedio:");
        double precioPromedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.printf("  General: $%.2f\n", precioPromedioGeneral);

        System.out.println("  Por categoría:");
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        promedioPorCategoria.forEach((cat, promedio) ->
                System.out.printf("    %s: $%.2f\n", cat, promedio));
    }

    // ============== CASO 3: LIBROS ==============
    public static void caso3Libros() {
        System.out.println("============== CASO 3: LIBROS ==============\n");

        List<Libro> libros = Arrays.asList(
                new Libro("El umbral del invierno", "Isabel Allende", 380, 20.50),
                new Libro("La sombra del viento", "Carlos Ruiz Zafón", 700, 30.00),
                new Libro("El código Da Vinci", "Dan Brown", 600, 18.99),
                new Libro("Crimen y castigo", "Fiódor Dostoyevski", 670, 24.50),
                new Libro("Ensayo sobre la ceguera", "José Saramago", 350, 15.99),
                new Libro("Historia de dos ciudades", "Charles Dickens", 450, 19.95),
                new Libro("Moby Dick", "Herman Melville", 750, 26.99),
                new Libro("Orgullo y prejuicio", "Jane Austen", 400, 17.00)
        );

        // 1. Títulos de libros con más de 300 páginas, ordenados alfabéticamente
        System.out.println("1. Libros con más de 300 páginas (orden alfabético):");
        libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .forEach(titulo -> System.out.println("  - " + titulo));

        // 2. Promedio de páginas
        System.out.println("\n2. Promedio de páginas:");
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.printf("  %.2f páginas\n", promedioPaginas);

        // 3. Agrupar por autor y contar libros
        System.out.println("\n3. Cantidad de libros por autor:");
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        librosPorAutor.forEach((autor, cantidad) ->
                System.out.printf("  %s: %d libro(s)\n", autor, cantidad));

        // 4. Libro más caro
        System.out.println("\n4. Libro más caro:");
        libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .ifPresent(libro -> System.out.println("  " + libro));
    }

    // ============== CASO 4: EMPLEADOS ==============
    public static void caso4Empleados() {
        System.out.println("============== CASO 4: EMPLEADOS ==============\n");

        List<Empleado> empleados = Arrays.asList(
                new Empleado("Héctor Gil", "Desarrollo", 3700.00, 32),
                new Empleado("Laura Pérez", "Administración", 2900.00, 26),
                new Empleado("Javier Mora", "Desarrollo", 4500.00, 45),
                new Empleado("Eva Soto", "Marketing", 2700.00, 30),
                new Empleado("Daniel Vega", "Desarrollo", 4000.00, 24),
                new Empleado("Clara Noya", "Administración", 3100.00, 36),
                new Empleado("Sergio Rico", "Marketing", 2300.00, 22),
                new Empleado("Marta Díaz", "Marketing", 2800.00, 28)
        );

        // 1. Empleados con salario > 2000, ordenados por salario descendente
        System.out.println("1. Empleados con salario mayor a $2000 (orden descendente):");
        empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .forEach(System.out::println);

        // 2. Salario promedio general
        System.out.println("\n2. Salario promedio general:");
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.printf("  $%.2f\n", salarioPromedio);

        // 3. Agrupar por departamento y sumar salarios
        System.out.println("\n3. Suma de salarios por departamento:");
        Map<String, Double> salariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        salariosPorDepartamento.forEach((dept, total) ->
                System.out.printf("  %s: $%.2f\n", dept, total));

        // 4. Los 2 empleados más jóvenes
        System.out.println("\n4. Los 2 empleados más jóvenes:");
        empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .forEach(nombre -> System.out.println("  - " + nombre));
    }
}