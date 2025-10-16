package run;

import config.JPAUtil;
import entities.Autor;
import entities.Categoria;
import entities.Libro;
import jakarta.persistence.EntityManager;
import repository.DAO.AutorDAO;
import repository.DAO.CategoriaDAO;
import repository.DAO.LibroDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        AutorDAO autorDAO = new AutorDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        LibroDAO libroDAO = new LibroDAO(em);

        try {
            // ====== 1) Crear e insertar autores ======
            Autor a1 = new Autor("Julio Verne", "Francia", LocalDate.of(1828, 2, 8));
            Autor a2 = new Autor("Isaac Asimov", "Rusia/EE.UU.", LocalDate.of(1920, 1, 2));

            a1 = autorDAO.guardar(a1);
            a2 = autorDAO.guardar(a2);

            // ====== 2) Crear e insertar categorías (ManyToMany) ======
            Categoria cFiccion = new Categoria("Ficción");
            Categoria cCiencia = new Categoria("Ciencia");

            cFiccion = categoriaDAO.guardar(cFiccion);
            cCiencia = categoriaDAO.guardar(cCiencia);

            // ====== 3) Crear libros, relacionar autor y categorías ======
            // Libro 1
            Libro l1 = new Libro("Viaje al centro de la Tierra", 1864);
            l1.setAutor(a1);
            l1.addCategoria(cFiccion);

            // Libro 2
            Libro l2 = new Libro("De la Tierra a la Luna", 1865);
            l2.setAutor(a1);
            l2.addCategoria(cFiccion);

            // Libro 3
            Libro l3 = new Libro("Fundación", 1951);
            l3.setAutor(a2);
            l3.addCategoria(cFiccion);
            l3.addCategoria(cCiencia);

            // Guardar libros
            l1 = libroDAO.guardar(l1);
            l2 = libroDAO.guardar(l2);
            l3 = libroDAO.guardar(l3);

            // ====== 4) Consulta: listar libros con su autor y categorías ======
            System.out.println("\n=== LIBROS / AUTOR / CATEGORÍAS ===");
            List<Libro> libros = libroDAO.listarConAutorYCategorias(); // usa JOIN FETCH + DISTINCT
            for (Libro l : libros) {
                String categorias = l.getCategorias().stream()
                        .map(Categoria::getNombre)
                        .sorted()
                        .collect(Collectors.joining(", "));
                System.out.printf("- %s (%d) | Autor: %s | Categorías: %s%n",
                        l.getTitulo(),
                        l.getAnioPublicacion(),
                        l.getAutor().getNombre(),
                        categorias.isEmpty() ? "—" : categorias
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}