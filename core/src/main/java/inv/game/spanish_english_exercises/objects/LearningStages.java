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
            {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve", "veinte"},
            {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"}
        };
    }

    public String[][] stage2() {
      return new String[][] {
          {"gato", "perro", "elefante", "caballo", "tortuga", "oveja", "pato", "pez", "rana", "oso", "tigre", "leon", "cabra", "gallina", "conejo", "ballena", "vaca", "lobo", "zorro", "cebra"},
          {"cat", "dog", "elephant", "horse", "turtle", "sheep", "duck", "fish", "frog", "bear", "tiger", "lion", "goat", "chicken", "rabbit", "whale", "cow", "wolf", "fox", "zebra"}
      };
    }

    public String[][] stage3() {
      return new String[][] {
          {"el tiempo", "la semana", "el reloj", "el segundo", "el minuto", "el mes", "el año", "la hora", "el día", "ahora", "el pasado", "el momento", "la década", "más tarde", "el siglo", "la edad", "la época", "el futuro", "el calendario", "la estación"},
          {"time", "week", "clock", "second", "minute", "month", "year", "hour", "day", "now", "past", "moment", "decade", "later", "century", "age", "epoch", "future", "calendar", "season"}
      };
    }

    public String[][] stage4() {
      return new String[][] {
          {"hablar", "trabajar", "estudiar", "cantar", "ayudar", "viajar", "escuchar", "caminar","comer","beber","leer","correr","aprender","romper", "ver", "vivir", "escribir","abrir","compartir", "escalar"},
          {"to speak", "to work", "to study", "to sing", "to help", "to travel", "to listen", "to walk", "to eat", "to drink", "to read", "to run", "to learn", "to break", "to see", "to live", "to write", "to open", "to share", "to climb"},
      };
    }

    public String[][] stage5() {
      return new String[][]{
        {"venir", "ir", "dar", "dormir", "reír", "traer", "hacer", "tener", "saber", "querer", "construir", "aparecer", "contar", "encontrar", "entrar", "estar / ser", "gustar", "jugar", "utilizar", "intentar"},
        {"to come", "to go", "to give", "to sleep", "to laugh", "to bring", "to do", "to have", "to know", "to want", "to build", "to appear", "to count", "to find", "to enter", "to be", "to like", "to play", "to use", "to try"}
      };
    }

    public String[][] stage6() {
      return new String[][]{
        {"rojo", "azul", "negro", "blanco", "verde", "amarillo", "morado", "azul claro", "coral", "beige", "violeta", "fucsia", "dorado", "gris", "marrón", "turquesa", "rosa", "plateado", "naranja", "lila"},
        {"red", "blue", "black", "white", "green", "yellow", "purple", "light blue", "coral", "beige", "violet", "fuchsia", "golden", "gray", "brown", "turquoise", "pink", "silver", "orange", "lilac"}
      };
    }

    public String[][] stage7() {
      return new String[][] {
        {"la cocina", "el plato", "la cuchara", "el tenedor","el cuchillo", "el vaso", "el alimento", "la olla", "el colador", "el cucharón","el cuenco", "el pelador", "el rallador", "el rodillo", "el refrigerador", "el hervidor", "el congelador", "la licuadora", "la batidora", "la jarra"},
        {"kitchen", "plate", "spoon", "fork", "knife", "glass", "food", "pot", "strainer", "ladle", "bowl", "peeler", "grater", "rolling pin", "refrigerator", "kettle", "freezer", "blender", "mixer", "jug"}
      };
    }

    public String[][] stage8() {
      return new String[][]{
        {"el escritorio", "la silla", "el cuaderno", "el lápiz", "el borrador", "el libro", "el proyector", "la mochila", "el marcador", "la regla", "el papel", "la computadora", "la calculadora", "la pluma", "la aula", "las tijeras", "el pegamento", "el mapa", "la pizarra", "la grapadora"},
        {"desk", "chair", "notebook", "pencil", "eraser", "book", "projector", "backpack", "marker", "ruler", "paper", "computer", "calculator", "pen", "classroom", "scissors", "glue", "map", "board", "stapler"}
      };
    }

    public String[][] stage9() {
      return new String[][] {
        {"la montaña", "el río", "el lago", "el bosque", "el árbol", "la flor", "la roca", "el desierto", "la playa", "la cascada", "la isla", "la cueva", "el valle", "la colina", "el océano", "la nube", "el sol", "la luna", "la nieve", "el viento"},
        {"mountain", "river", "lake", "forest", "tree", "flower", "rock", "desert", "beach", "waterfall", "island", "cave", "valley", "hill", "ocean", "cloud", "sun", "moon", "snow", "wind"}
      };
    }

    public String[][] stage10() {
      return new String[][] {
        {"la manzana", "el plátano", "la naranja", "la uva", "la piña", "la fresa", "la sandía", "el mango", "el limón", "el durazno", "la pera", "la cereza", "la ciruela", "el albaricoque", "el coco", "el aguacate", "el kiwi", "la papaya", "el higo", "el granada"},
        {"apple", "banana", "orange", "grape", "pineapple", "strawberry", "watermelon", "mango", "lemon", "peach", "pear", "cherry", "plum", "apricot", "coconut", "avocado", "kiwi", "papaya", "fig", "pomegranate"}
      };
    }

    public String[][] stage11() {
      return new String[][] {
        {"la rosa", "el lirio", "la margarita", "el tulipán", "el girasol", "la orquídea", "el clavel", "el jazmín", "la lavanda", "la peonía", "el crisantemo", "la violeta", "la caléndula", "la begonia", "la amapola", "el narciso", "la dalia", "el loto", "la magnolia", "el hibisco"},
        {"rose", "lily", "daisy", "tulip", "sunflower", "orchid", "carnation", "jasmine", "lavender", "peony", "chrysanthemum", "violet", "marigold", "begonia", "poppy", "daffodil", "dahlia", "lotus", "magnolia", "hibiscus"}
      };
    }

    public String[][] stage12() {
      return new String[][]{
        {"la casa", "la escuela", "el hospital", "la biblioteca", "la torre", "el granero", "el castillo", "el palacio", "el teatro", "el museo", "el estadio", "la embajada", "la estación de tren", "la fábrica", "la tienda", "el centro comercial", "el apartamento", "la oficina", "el restaurante", "el hotel"},
        {"house", "school", "hospital", "library", "tower", "barn", "castle", "palace", "theater", "museum", "stadium", "embassy", "train station", "factory", "shop", "mall", "apartment", "office", "restaurant", "hotel"}
      };
    }

    public String[][] stage13() {
      return new String[][] {
        {"el pan", "el arroz", "la pasta", "el queso", "el pollo", "la carne de res", "el pescado", "el huevo", "la leche", "la mantequilla", "el azúcar", "la sal", "el chocolate", "la pizza", "la hamburguesa", "la salchicha", "la sopa", "el bistec", "el taco", "el panqueque"},
        {"bread", "rice", "pasta", "cheese", "chicken", "beef", "fish", "egg", "milk", "butter", "sugar", "salt", "chocolate", "pizza", "burger", "sausage", "soup", "steak", "taco", "pancake"}
      };
    }

    public String[][] stage14() {
      return new String[][] {
        {"la suma", "la resta", "la multiplicación", "la división", "la ecuación", "la variable", "la función", "la integral", "la derivada", "la fracción", "el porcentaje", "la raíz cuadrada", "el exponente", "la matriz", "el ángulo", "el triángulo", "el círculo", "el radio", "el diámetro", "el logaritmo"},
        {"addition", "subtraction", "multiplication", "division", "equation", "variable", "function", "integral", "derivative", "fraction", "percentage", "square root", "exponent", "matrix", "angle", "triangle", "circle", "radius", "diameter", "logarithm"}
      };
    }

    public String[][] stage15() {
      return new String[][] {
        {"la cabeza", "el ojo", "la nariz", "la boca", "la oreja", "el brazo", "la pierna", "la mano", "el pie", "el corazón", "el pulmón", "el estómago", "el cerebro", "la piel", "el hueso", "el músculo", "el dedo", "el dedo del pie", "la espalda", "la rodilla"},
        {"head", "eye", "nose", "mouth", "ear", "arm", "leg", "hand", "foot", "heart", "lung", "stomach", "brain", "skin", "bone", "muscle", "finger", "toe", "back", "knee"}
      };
    };

    public String[][] stage16() {
      return new String[][] {
        {"el diamante", "el rubí", "la esmeralda", "el zafiro", "la amatista", "el topacio", "el granate", "el ópalo", "la aguamarina", "la turquesa", "el citrino", "el peridoto", "la tanzanita", "la alejandrita", "la espinela", "la morganita", "la turmalina", "el crisoberilo", "el circón", "la piedra de la luna"},
        {"diamond", "ruby", "emerald", "sapphire", "amethyst", "topaz", "garnet", "opal", "aquamarine", "turquoise", "citrine", "peridot", "tanzanite", "alexandrite", "spinel", "morganite", "tourmaline", "chrysoberyl", "zircon", "moonstone"}
      };
    }

    public String[][] stage17() {
      return new String[][]{
        {"el oro", "la plata", "el platino", "el cobre", "el hierro", "el aluminio", "el titanio", "el zinc", "el níquel", "el plomo", "el estaño", "el cobalto", "el cromo", "el paladio", "el rodio", "el iridio", "el tungsteno", "el mercurio", "el magnesio", "el litio"},
        {"gold", "silver", "platinum", "copper", "iron", "aluminum", "titanium", "zinc", "nickel", "lead", "tin", "cobalt", "chromium", "palladium", "rhodium", "iridium", "tungsten", "mercury", "magnesium", "lithium"}
      };
    }

    public String[][] stage18() {
      return new String[][] {
        {"el pasaporte", "el equipaje", "la aventura", "el avión", "la moneda", "el boleto", "el destino", "las vacaciones", "el viaje", "la brújula", "la linterna", "el cámping", "la gorra", "el vuelo", "la reserva", "el turismo", "el recorrido", "el crucero", "el aeropuerto", "el idioma"},
        {"passport", "luggage", "adventure", "airplane", "currency", "ticket", "destination", "vacation", "journey", "compass", "torch", "camping", "cap", "flight", "reservation", "sightseeing", "tour", "cruise", "airport", "language"}
      };
    };  

    public String[][] stage19() {
      return new String[][] {
        {"el cojín", "la mesa", "las escaleras", "la cama", "la alfombra", "la televisión", "la estantería", "el taburete", "el gabinete", "la cómoda", "la mesita de noche", "el espejo", "el sillón", "la puerta", "la ventana", "la luz", "la pared", "la lámpara", "la mesa de comedor", "el mueble de televisión"},
        {"cushion", "table", "stairs", "bed", "rug", "television", "bookshelf", "stool", "cabinet", "dresser", "nightstand", "mirror", "armchair", "door", "window", "light", "wall", "lamp", "dining table", "TV stand"}
      };
    }

    public String[][] stage20() {
      return new String[][] {
        {"la zanahoria", "la patata", "el tomate", "el pepino", "la lechuga", "la espinaca", "el brócoli", "la coliflor", "la cebolla", "el ajo", "los guisantes", "el calabacín", "la berenjena", "el pimiento", "el repollo", "el rábano", "la calabaza", "el maíz", "la remolacha", "las judías verdes"},
        {"carrot", "potato", "tomato", "cucumber", "lettuce", "spinach", "broccoli", "cauliflower", "onion", "garlic", "peas", "zucchini", "eggplant", "pepper", "cabbage", "radish", "pumpkin", "corn", "beetroot", "green beans"}
      };
    }
}