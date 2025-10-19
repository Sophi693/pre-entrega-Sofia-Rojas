import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner enter = new Scanner(System.in);
    ArrayList<String> productos = obtenerProductosPetshop(); // Cargar lista inicial
    int opcionUsuario;

    System.out.println("\n🐾¡Te damos la bienvenida a la app de Petshop!🐾");
    System.out.println("\n=======================================");
    System.out.println("\n=========== Menú Principal ============");
    System.out.println("\n=======================================");

    while (true) {
      System.out.println("1 - Agregar Producto");
      System.out.println("2 - Listar Productos");
      System.out.println("3 - Buscar producto");
      System.out.println("4 - Editar y actualizar producto");
      System.out.println("5 - Eliminar producto");
      System.out.println("6 - Crear pedido");
      System.out.println("7 - Salir");
      System.out.print("Elija una opción: ");

      if (!enter.hasNextInt()) {
        System.out.println("❌ Por favor, ingresá un número válido.");
        enter.nextLine();
        continue;
      }

      opcionUsuario = enter.nextInt();
      enter.nextLine(); // Limpia el salto de línea

      switch (opcionUsuario) {
        case 1:
          agregarProducto(productos, enter);
          break;
        case 2:
          listarProductos(productos);
          break;
        case 3:
          buscarProducto(productos, enter);
          break;
        case 4:
          editarProducto(productos, enter);
          break;
        case 5:
          eliminarProducto(productos, enter);
          break;
        case 6:
          crearPedido(productos, enter);
          break;
        case 7:
          System.out.println("¡Gracias por usar la app!");
          enter.close();
          return;
        default:
          System.out.println("⚠️ Opción inválida, intentá nuevamente.");
      }

      pause(enter);
    }
  }

  public static ArrayList<String> obtenerProductosPetshop() {
    ArrayList<String> lista = new ArrayList<>();
    lista.add("Snacks $2500");
    lista.add("Collar para gato $3500");
    lista.add("Piedra sanitaria $20000");
    lista.add("Accesorios $15500");
    lista.add("Transportadora $36000");
    return lista;
  }

  public static void crearPedido(ArrayList<String> productos, Scanner enter) {
    System.out.print("Ingrese el nombre del producto: ");
    String nombreProducto = enter.nextLine().toLowerCase();

    System.out.print("Ingrese la cantidad del producto: ");
    int cantidadPedido;

    try {
      cantidadPedido = Integer.parseInt(enter.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("❌ Cantidad inválida.");
      return;
    }

    boolean encontrado = false;

    for (String item : productos) {
      String[] partes = item.split("\\$");
      if (partes.length != 2) {
        continue;
      }

      String nombre = partes[0].trim().toLowerCase();
      int precio = Integer.parseInt(partes[1].trim());

      if (nombre.contains(nombreProducto)) {
        int total = precio * cantidadPedido;
        System.out.println("Producto: " + nombre);
        System.out.println("Precio unitario: $" + precio);
        System.out.println("Cantidad: " + cantidadPedido);
        System.out.println("Costo total: $" + total);
        encontrado = true;
        break;
      }
    }

    if (!encontrado) {
      System.out.println("❌ El producto ingresado no se encuentra en la lista.");
    }
  }

  public static void agregarProducto(ArrayList<String> productos, Scanner enter) {
    System.out.print("Ingrese el nombre del nuevo producto: ");
    String nombre = enter.nextLine();

    System.out.print("Ingrese el precio del producto: ");
    int precio;

    try {
      precio = Integer.parseInt(enter.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("❌ Precio inválido.");
      return;
    }

    String productoFormateado = nombre + " $" + precio;
    productos.add(productoFormateado);
    System.out.println("✅ Producto agregado: " + productoFormateado);
  }

  public static void listarProductos(ArrayList<String> productosPet) {
    System.out.println("\n📋 Lista de productos");
    if (productosPet.isEmpty()) {
      System.out.println("🚫 No hay productos registrados.");
    } else {
      int i = 1;
      for (String producto : productosPet) {
        System.out.println(i + ". " + producto);
        i++;
      }
    }
  }

  public static void buscarProducto(ArrayList<String> productosPet, Scanner enter) {
    System.out.print("🔍 Ingrese el nombre a buscar: ");
    String busqueda = enter.nextLine().toLowerCase();
    boolean encontrado = false;

    for (String producto : productosPet) {
      if (producto.toLowerCase().contains(busqueda)) {
        System.out.println("✅ Producto encontrado: " + producto);
        encontrado = true;
      }
    }

    if (!encontrado) {
      System.out.println("❌ Producto no encontrado.");
    }
  }

  public static void editarProducto(ArrayList<String> productos, Scanner enter) {
    listarProductos(productos);
    System.out.print("Ingrese el número del producto que desea editar: ");
    int indice;

    try {
      indice = Integer.parseInt(enter.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("❌ Número inválido.");
      return;
    }

    if (indice >= 1 && indice <= productos.size()) {
      System.out.print("Ingrese el nuevo nombre: ");
      String nuevoNombre = enter.nextLine();

      System.out.print("Ingrese el nuevo precio: ");
      int nuevoPrecio;

      try {
        nuevoPrecio = Integer.parseInt(enter.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("❌ Precio inválido.");
        return;
      }

      String productoEditado = nuevoNombre + " $" + nuevoPrecio;
      productos.set(indice - 1, productoEditado);
      System.out.println("✅ Producto actualizado: " + productoEditado);
    } else {
      System.out.println("❌ Número inválido.");
    }
  }

  public static void eliminarProducto(ArrayList<String> productosPetshop, Scanner enter) {
    listarProductos(productosPetshop);
    System.out.print("Ingrese el número del producto que desea eliminar: ");
    int indice;

    try {
      indice = Integer.parseInt(enter.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("❌ Número inválido.");
      return;
    }

    if (indice >= 1 && indice <= productosPetshop.size()) {
      String eliminado = productosPetshop.remove(indice - 1);
      System.out.println("🗑️ Producto eliminado: " + eliminado);
    } else {
      System.out.println("❌ Número inválido.");
    }
  }

  public static void pause(Scanner enter) {
    System.out.println("\nPresioná Enter para continuar...");
    enter.nextLine();
  }
}
