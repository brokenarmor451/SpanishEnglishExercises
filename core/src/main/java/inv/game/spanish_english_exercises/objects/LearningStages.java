package inv.game.spanish_english_exercises.objects;

import java.util.HashMap;

public final class LearningStages {

    public String[][] get(int stage) {
        switch(stage) {
          case 0:
            return stage1();
          case 1:
            return stage2();
          case 2:
            return stage3();
           case 3:
            return stage4();
          case 4:
            return stage5();
          case 5:
            return stage6();
          case 6:
            return stage7();
          case 7:
            return stage8();
          case 8:
            return stage9();
          case 9:
            return stage10();
          case 10:
            return stage11();
          case 11:
            return stage12();
          case 12:
            return stage13();
          case 13:
            return stage14();
          case 14:
            return stage15();
          case 15:
            return stage16();
          case 16:
            return stage17();
          case 17:
            return stage18();
          case 18:
            return stage19();
          case 19:
            return stage20();
        }
        
        return null;
      }

    public String[][] stage1() {
        return new String[][] {
            { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Once", "Doce", "Trece", "Catorce", "Quince", "Dieciséis", "Diecisiete", "Dieciocho", "Diecinueve", "Veinte"},
            { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"}
        };
    }

    public String[][] stage2() {
      return new String[][] {
          {"Gato", "Perro", "Elefante", "Caballo", "Tortuga", "Oveja", "Pato", "Pez", "Rana", "Oso", "Tigre", "Leon", "Cabra", "Gallina", "Conejo", "Ballena", "Vaca", "Lobo", "Zorro", "Cebra"},
          {"Cat", "Dog", "Elephant", "Horse", "Turtle", "Sheep", "Duck", "Fish", "Frog", "Bear", "Tiger", "Lion", "Goat", "Chicken", "Rabbit", "Whale", "Cow", "Wolf", "Fox", "Zebra"}
      };
    }

    public String[][] stage3() {
      return new String[][] {
          {"Tiempo", "Semana", "Reloj", "Segundo", "Minuto", "Mes", "Año", "Hora", "Día", "Ahora", "Pasado", "Momento", "Década", "Crepúsculo", "Siglo", "Edad", "Época", "Futuro", "Calendario", "Estación"},
          {"Time", "Week", "Clock", "Second", "Minute", "Month", "Year", "Hour", "Day", "Now", "Past", "Moment", "Decade", "Dusk", "Century", "Age", "Epoch", "Future", "Calendar", "Season"}
      };
    }

    public String[][] stage4() {
      return new String[][] {
          {"Hablar", "Trabajar", "Estudiar", "Cantar", "Ayudar", "Viajar", "Escuchar", "Caminar","Comer","Beber","Leer","Correr","Aprender","Romper", "Ver", "Vivir", "Escribir","Abrir","Compartir", "Subir"},
          {"To speak", "To work", "To study", "To sing", "To help", "To travel", "To listen", "To walk", "To eat", "To drink", "To read", "To run", "To learn", "To break", "To see", "To live", "To write", "To open", "To share", "To climb"},
      };
    }

    public String[][] stage5() {
      return new String[][]{
        {"Venir", "Ir", "Dar", "Dormir", "Reír", "Traer", "Hacer", "Tener", "Saber", "Querer", "Construir", "Aparecer", "Contar", "Encontrar", "Entrar", "Estar", "Gustar", "Jugar", "Utilizar", "Intentar"},
        {"To come", "To go", "To give", "To sleep", "To laugh", "To bring", "To do", "To have", "To know", "To want", "To build", "To appear", "To count", "To find", "To enter", "To be", "To like", "To play", "To use", "To try"}
      };
    }

    public String[][] stage6() {
      return new String[][]{
        {"Rojo", "Azul", "Negro", "Blanco", "Verde", "Amarillo", "Morado", "Azul claro", "Coral", "Beige", "Violeta", "Fucsia", "Dorado", "Gris", "Marrón", "Turquesa", "Rosa", "Plateado", "Naranja", "Lila"},
        {"Red", "Blue", "Black", "White", "Green", "Yellow", "Purple", "Light blue", "Coral", "Beige", "Violet", "Fuchsia", "Golden", "Gray", "Brown", "Turquoise", "Pink", "Silver", "Orange", "Lilac"}
      };
    }

    public String[][] stage7() {
      return new String[][] {
        {"Cocina", "Plato", "Cuchara", "Tenedor","Cuchillo", "Vaso", "Alimento", "Cacerola", "Colador", "Cucharón","Cuenco", "Pelador", "Rallador", "Rodillo", "Refrigerador", "Hervidor", "Congelador", "Licuadora", "Batidora", "Jarra"},
        {"Kitchen", "Plate", "Spoon", "Fork", "Knife", "Glass", "Food", "Pan", "Colander", "Ladle", "Bowl", "Peeler", "Grater", "Rolling pin", "Refrigerator", "Kettle", "Freezer", "Blender", "Mixer", "Jug"}
      };
    }

    public String[][] stage8() {
      return new String[][]{
        {"Escritorio", "Silla", "Cuaderno", "Lápiz", "Borrador", "Libro", "Estudiante", "Mochila", "Marcador", "Regla", "Papel", "Computadora", "Calculadora", "Pluma", "Aula", "Tijeras", "Pegamento", "Mapa", "Pizarra", "Grapadora"},
        {"Desk", "Chair", "Notebook", "Pencil", "Eraser", "Book", "Stduent", "Backpack", "Marker", "Ruler", "Paper", "Computer", "Calculator", "Pen", "Classroom", "Scissors", "Glue", "Map", "Blackboard", "Stapler"}
      };
    }

    public String[][] stage9() {
      return new String[][] {
        {"Montaña", "Río", "Lago", "Bosque", "Árbol", "Flor", "Roca", "Desierto", "Playa", "Cascada", "Isla", "Cueva", "Valle", "Colina", "Océano", "Nube", "Sol", "Luna", "Nieve", "Viento"},
        {"Mountain", "River", "Lake", "Forest", "Tree", "Flower", "Rock", "Desert", "Beach", "Waterfall", "Island", "Cave", "Valley", "Hill", "Ocean", "Cloud", "Sun", "Moon", "Snow", "Wind"}
      };
    }

    public String[][] stage10() {
      return new String[][] {
        {"Manzana", "Plátano", "Naranja", "Uvas", "Piña", "Fresa", "Sandía", "Mango", "Limón", "Durazno", "Pera", "Cereza", "Ciruela", "Albaricoque", "Coco", "Aguacate", "Kiwi", "Papaya", "Higo", "Granada"},
        {"Apple", "Banana", "Orange", "Grapes", "Pineapple", "Strawberry", "Watermelon", "Mango", "Lemon", "Peach", "Pear", "Cherry", "Plum", "Apricot", "Coconut", "Avocado", "Kiwi", "Papaya", "Fig", "Pomegranate"}
      };
    }

    public String[][] stage11() {
      return new String[][] {
        {"Rosa", "Lirio", "Margarita", "Tulipán", "Girasol", "Orquídea", "Clavel", "Jazmín", "Lavanda", "Peonía", "Crisantemo", "Violeta", "Caléndula", "Begonia", "Amapola", "Narciso", "Iris", "Loto", "Magnolia", "Hibisco"},
        {"Rose", "Lily", "Daisy", "Tulip", "Sunflower", "Orchid", "Carnation", "Jasmine", "Lavender", "Peony", "Chrysanthemum", "Violet", "Marigold", "Begonia", "Poppy", "Daffodil", "Iris", "Lotus", "Magnolia", "Hibiscus"}
      };
    }

    public String[][] stage12() {
      return new String[][]{
        {"Casa", "Escuela", "Hospital", "Biblioteca", "Torre", "Granero", "Castillo", "Palacio", "Teatro", "Museo", "Estadio", "Aeropuerto", "Estación de tren", "Fábrica", "Almacén", "Centro comercial", "Apartamento", "Oficina", "Restaurante", "Hotel"},
        {"House", "School", "Hospital", "Library", "Tower", "Barn", "Castle", "Palace", "Theater", "Museum", "Stadium", "Airport", "Train station", "Factory", "Warehouse", "Mall", "Apartment", "Office", "Restaurant", "Hotel"}
      };
    }

    public String[][] stage13() {
      return new String[][] {
        {"Pan", "Arroz", "Pasta", "Queso", "Pollo", "Carne de res", "Pescado", "Huevo", "Leche", "Mantequilla", "Azúcar", "Sal", "Chocolate", "Pizza", "Hamburguesa", "Salchicha", "Sopa", "Bistec", "Taco", "Panqueque"},
        {"Bread", "Rice", "Pasta", "Cheese", "Chicken", "Beef", "Fish", "Egg", "Milk", "Butter", "Sugar", "Salt", "Chocolate", "Pizza", "Burger", "Sausage", "Soup", "Steak", "Taco", "Pancake"}
      };
    }

    public String[][] stage14() {
      return new String[][] {
        {"Suma", "Resta", "Multiplicación", "División", "Ecuación", "Variable", "Función", "Integral", "Derivada", "Fracción", "Porcentaje", "Raíz cuadrada", "Exponente", "Matriz", "Ángulo", "Triángulo", "Círculo", "Radio", "Diámetro", "Logaritmo"},
        {"Addition", "Subtraction", "Multiplication", "Division", "Equation", "Variable", "Function", "Integral", "Derivative", "Fraction", "Percentage", "Square root", "Exponent", "Matrix", "Angle", "Triangle", "Circle", "Radius", "Diameter", "Logarithm"}
      };
    }

    public String[][] stage15() {
      return new String[][] {
        {"Cabeza", "Ojo", "Nariz", "Boca", "Oreja", "Brazo", "Pierna", "Mano", "Pie", "Corazón", "Pulmón", "Estómago", "Cerebro", "Piel", "Hueso", "Músculo", "Dedo", "Dedo del pie", "Espalda", "Rodilla"},
        {"Head", "Eye", "Nose", "Mouth", "Ear", "Arm", "Leg", "Hand", "Foot", "Heart", "Lung", "Stomach", "Brain", "Skin", "Bone", "Muscle", "Finger", "Toe", "Back", "Knee"}
      };
    };

    public String[][] stage16() {
      return new String[][] {
        {"Diamante", "Rubí", "Esmeralda", "Zafiro", "Amatista", "Topacio", "Granate", "Ópalo", "Aguamarina", "Turquesa", "Citrino", "Peridoto", "Tanzanita", "Alejandrita", "Espinela", "Morganita", "Turmalina", "Crisoberilo", "Circón", "Piedra de Luna"},
        {"Diamond", "Ruby", "Emerald", "Sapphire", "Amethyst", "Topaz", "Garnet", "Opal", "Aquamarine", "Turquoise", "Citrine", "Peridot", "Tanzanite", "Alexandrite", "Spinel", "Morganite", "Tourmaline", "Chrysoberyl", "Zircon", "Moonstone"}
      };
    }

    public String[][] stage17() {
      return new String[][]{
        {"Oro", "Plata", "Platino", "Cobre", "Hierro", "Aluminio", "Titanio", "Zinc", "Níquel", "Plomo", "Estaño", "Cobalto", "Cromo", "Paladio", "Rodio", "Iridio", "Tungsteno", "Mercurio", "Magnesio", "Litio"},
        {"Gold", "Silver", "Platinum", "Copper", "Iron", "Aluminum", "Titanium", "Zinc", "Nickel", "Lead", "Tin", "Cobalt", "Chromium", "Palladium", "Rhodium", "Iridium", "Tungsten", "Mercury", "Magnesium", "Lithium"}
      };
    }

    public String[][] stage18() {
      return new String[][] {
        {"Pasaporte", "Equipaje", "Aventura", "Avión", "Moneda", "Boleto", "Destino", "Vacaciones", "Viaje", "Brújula", "Guía", "Camping", "Turista", "Vuelo", "Reserva", "Turismo", "Tour", "Crucero", "Aeropuerto", "Idioma"},
        {"Passport", "Luggage", "Adventure", "Airplane", "Currency", "Ticket", "Destination", "Vacation", "Journey", "Compass", "Guide", "Campamento", "Tourist", "Flight", "Reservation", "Sightseeing", "Tour", "Cruise", "Airport", "Language"}
      };
    };  

    public String[][] stage19() {
      return new String[][] {
        {"Cojín", "Mesa", "Sofá", "Cama", "Alfombra", "Sillón reclinable", "Estantería", "Taburete", "Gabinete", "Cómoda", "Mesita de noche", "Espejo", "Sillón", "Banco", "Diván", "Estante", "Cajón", "Cuna", "Mesa de comedor", "Mueble para televisor"},
        {"Cushion", "Table", "Sofa", "Bed", "Rug", "Recliner", "Bookshelf", "Stool", "Cabinet", "Dresser", "Nightstand", "Mirror", "Armchair", "Bench", "Couch", "Shelf", "Drawer", "Crib", "Dining table", "TV stand"}
      };
    }

    public String[][] stage20() {
      return new String[][] {
        {"Zanahoria", "Patata", "Tomate", "Pepino", "Lechuga", "Espinaca", "Brócoli", "Coliflor", "Cebolla", "Ajo", "Guisantes", "Calabacín", "Berenjena", "Pimiento", "Repollo", "Rábano", "Calabaza", "Maíz", "Remolacha", "Judías verdes"},
        {"Carrot", "Potato", "Tomato", "Cucumber", "Lettuce", "Spinach", "Broccoli", "Cauliflower", "Onion", "Garlic", "Peas", "Zucchini", "Eggplant", "Pepper", "Cabbage", "Radish", "Pumpkin", "Corn", "Beetroot", "Green beans"}
      };
    }
}