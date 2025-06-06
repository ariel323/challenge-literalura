package com.literatura.literalura;

import com.literatura.literalura.model.Book;
import com.literatura.literalura.service.BookService;
import com.literatura.literalura.service.GutendexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            BookService bookService = ctx.getBean(BookService.class);
            GutendexService gutendexService = ctx.getBean(GutendexService.class);

            boolean running = true;
            while (running) {
                System.out.println("\n--- Menú Literalura ---");
                System.out.println("1. Buscar libro por título");
                System.out.println("2. Listar libros registrados");
                System.out.println("3. Listar autores");
                System.out.println("4. Listar autores vivos en un año");
                System.out.println("5. Listar libros por idioma");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Título a buscar: ");
                        String titulo = scanner.nextLine();
                        System.out.println(gutendexService.getBooks(titulo));
                        break;
                    case "2":
                        List<Book> books = bookService.getAllBooks();
                        if (books.isEmpty()) {
                            System.out.println("No hay libros registrados.");
                        } else {
                            System.out.println("\n--- Libros Registrados ---");
                            imprimirLibros(books);
                        }
                        break;
                    case "3":
                        List<Book> allBooks = bookService.getAllBooks();
                        System.out.println("\n--- Autores Registrados ---");
                        imprimirAutoresUnicos(allBooks);
                        break;
                    case "4":
                        try {
                            System.out.print("Año: ");
                            int year = Integer.parseInt(scanner.nextLine());
                            List<Book> booksByYear = bookService.getAllBooks();
                            List<String> autoresVivos = booksByYear.stream()
                                .filter(b -> b.getAuthorBirthYear() != null && b.getAuthorBirthYear() <= year &&
                                        (b.getAuthorDeathYear() == null || b.getAuthorDeathYear() >= year))
                                .map(Book::getAuthor)
                                .distinct()
                                .toList();
                            if (autoresVivos.isEmpty()) {
                                System.out.println("No se encontraron autores vivos en ese año.");
                            } else {
                                System.out.println("\n--- Autores vivos en " + year + " ---");
                                imprimirAutores(autoresVivos);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingresa un año válido.");
                        }
                        break;
                    case "5":
                         List<Book> booksByLang = bookService.getAllBooks();
                        imprimirIdiomasUnicos(booksByLang); // Muestra los idiomas disponibles
                        System.out.print("Idioma (código ISO): ");
                        String idioma = scanner.nextLine().trim();
                        if (idioma.isEmpty()) {
                            System.out.println("Debes ingresar un código de idioma.");
                            break;
                        }
                        List<Book> filtrados = booksByLang.stream()
                            .filter(b -> b.getLanguage() != null && b.getLanguage().equalsIgnoreCase(idioma))
                            .toList();
                        if (filtrados.isEmpty()) {
                            System.out.println("No se encontraron libros en ese idioma.");
                        } else {
                            System.out.println("\n--- Libros en idioma " + idioma + " ---");
                            imprimirLibros(filtrados);
                        }
                        break;
                    case "6":
                        running = false;
                        System.out.println("¡Hasta luego!");
                        break;
                    case "7":
                        List<Book> allBooksStats = bookService.getAllBooks();
                        DoubleSummaryStatistics stats = allBooksStats.stream()
                            .mapToDouble(Book::getDownloadCount)
                            .summaryStatistics();
                        System.out.println("Estadísticas de descargas:");
                        System.out.println("Total: " + stats.getSum());
                        System.out.println("Promedio: " + stats.getAverage());
                        System.out.println("Máximo: " + stats.getMax());
                        System.out.println("Mínimo: " + stats.getMin());
                        break;
                    case "8":
                        List<Book> top10 = bookService.getAllBooks().stream()
                            .sorted((b1, b2) -> Integer.compare(
                                b2.getDownloadCount() != null ? b2.getDownloadCount() : 0,
                                b1.getDownloadCount() != null ? b1.getDownloadCount() : 0))
                            .limit(10)
                            .toList();
                        System.out.println("--- Top 10 libros más descargados ---");
                        imprimirLibros(top10);
                        break;
                    case "9":
                        System.out.print("Nombre del autor a buscar: ");
                        String nombreAutor = scanner.nextLine().trim();
                        List<Book> librosAutor = bookService.getAllBooks().stream()
                            .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().contains(nombreAutor.toLowerCase()))
                            .toList();
                        if (librosAutor.isEmpty()) {
                            System.out.println("No se encontraron libros de ese autor.");
                        } else {
                            imprimirLibros(librosAutor);
                        }
                        break;
                    case "10":
                        System.out.print("Año de nacimiento: ");
                        try {
                            int nacimiento = Integer.parseInt(scanner.nextLine());
                            List<String> autoresNacidos = bookService.getAllBooks().stream()
                                .filter(b -> b.getAuthorBirthYear() != null && b.getAuthorBirthYear() == nacimiento)
                                .map(Book::getAuthor)
                                .distinct()
                                .toList();
                            if (autoresNacidos.isEmpty()) {
                                System.out.println("No se encontraron autores nacidos en ese año.");
                            } else {
                                imprimirAutores(autoresNacidos);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingresa un año válido.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        };
    }

    // Método auxiliar para imprimir una lista de libros
    private static void imprimirLibros(List<Book> books) {
        books.forEach(book -> {
            System.out.println("Título: " + book.getTitle());
            System.out.println("Autor: " + book.getAuthor());
            System.out.println("Idioma: " + book.getLanguage());
            System.out.println("-------------------------");
        });
    }

    // Método auxiliar para imprimir una lista de autores únicos
    private static void imprimirAutoresUnicos(List<Book> books) {
        books.stream()
            .map(Book::getAuthor)
            .distinct()
            .forEach(System.out::println);
    }

    // Método auxiliar para imprimir una lista de autores
    private static void imprimirAutores(List<String> autores) {
        autores.forEach(System.out::println);
    }

    // Método auxiliar para imprimir una lista de idiomas únicos
    private static void imprimirIdiomasUnicos(List<Book> books) {
        System.out.println("Idiomas disponibles:");
        books.stream()
            .map(Book::getLanguage)
            .filter(lang -> lang != null && !lang.isBlank())
            .distinct()
            .forEach(System.out::println);
    }
}
