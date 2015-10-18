
//Crhistopher Chiroy
//Boris Cifuentes
//Diego de León


//Algoritmos y estructura de datos
//Hoja de trabajo 9

import java.io.*;
import java.util.Scanner;

class WordTypeCounter {
	public static void main(String[] args) throws Exception
	{
		Scanner teclado = new Scanner(System.in);
		if(2 > 1)
		{
			// DeclaraciÃ³n e inicializaciÃ³n de variables.
			// el primer parametro indica el nombre del archivo con las definiciones de las palabras
			File wordFile = new File("C:\\Users\\Alejandro\\Desktop\\Hoja9-master\\words.txt");
			
			// el segundo parametro indica el nombre del archivo que tiene el texto a analizar
			File textFile = new File("C:\\Users\\Alejandro\\Desktop\\Hoja9-master\\text.txt");
			
			
			//  3 Splay Tree
			
			int implementacion = 0;
			System.out.print("Ingrese el numero de la implementacion\n\n"
                + "1.SimpleSet\n2.RedBlackTree\n3.SplayTree\n4.HashTable\n5.TreeMap\n");
                        System.out.print("\nOpcion:"); 
			implementacion=teclado.nextInt();
			
			BufferedReader wordreader;
			BufferedReader textreader;
			
			int verbs=0;
			int nouns=0;
			int adjectives=0;
			int adverbs=0;
			int gerunds=0;
			
			long starttime;
			long endtime;
			
			// Verificar que los dos parÃ¡metros que se pasaron sean archivos que existen
			if(wordFile.isFile() && textFile.isFile() && implementacion>=1 && implementacion<=5) {
				// Leer archivos
				try
				{
					wordreader = new BufferedReader(new FileReader(wordFile));
					textreader = new BufferedReader(new FileReader(textFile));
				}
				catch (Exception ex)
				{
					System.out.println("Error al leer!");
					return;
				}

				// Crear un WordSet para almacenar la lista de palabras
				// se selecciona la implementacion de acuerdo al tercer parametro pasado en la linea
				// de comando
				// =====================================================
				WordSet words =  WordSetFactory.generateSet(implementacion);
				// =====================================================
				
				String line = null;
				String[] wordParts;
				
				// leer el archivo que contiene las palabras y cargarlo al WordSet.
				starttime = System.currentTimeMillis();
				line = wordreader.readLine();
				while(line!=null)
				{
					wordParts = line.split("\\.");  // lo que esta entre comillas es una expresiÃ³n regular.
					if(wordParts.length == 2)
					{
						words.add(new Word(wordParts[0].trim(),wordParts[1].trim()));
					}
					line = wordreader.readLine();
				}
				wordreader.close();
				endtime = System.currentTimeMillis();
				
				System.out.println("Palabras cargadas en " + (endtime-starttime) + " ms.");
				
				// Procesar archivo de texto
				starttime = System.currentTimeMillis();
				line = textreader.readLine();
				String[] textParts;
				Word currentword;
				Word lookupword = new Word();
				
				while(line!=null)
				{
					// Separar todas las palabras en la lÃ­nea.
					textParts = line.split("[^\\w-]+"); // utilizar de separador cualquier caracter que no sea una letra, nÃºmero o guiÃ³n.
					
					// Revisar cada palabra y verificar de que tipo es. 
					for(int i=0;i<textParts.length;i++)
					{
						lookupword.setWord(textParts[i].trim().toLowerCase());
						currentword = words.get(lookupword);
						if(currentword != null)
						{
							if(currentword.getType().equals("v-d") || currentword.getType().equals("v") || currentword.getType().equals("q"))
								verbs++;
							else if(currentword.getType().equals("g") )
								gerunds++;
							else if(currentword.getType().equals("a-s") || currentword.getType().equals("a-c") || currentword.getType().equals("a"))
								adjectives++;
							else if(currentword.getType().equals("e"))
								adverbs++;
							else 
								nouns++;
						}
					}
					
					line = textreader.readLine();
				}
				textreader.close();
				endtime = System.currentTimeMillis();
				System.out.println("Texto analizado en " + (endtime-starttime) + " ms.");
				
				// Presentar estadÃ­sticas
				System.out.println("El texto tiene:");
				System.out.println(verbs + " verbos");
				System.out.println(nouns + " sustantivos");
				System.out.println(adjectives + " adjetivos");
				System.out.println(adverbs + " adverbios");
				System.out.println(gerunds + " gerundios");
				
			}
			else if (implementacion<1 || implementacion>5){
				System.out.println("\nOpcion ingresada invalida.");
			}
			else {
				System.out.println("\nCambiar direccion de los documentos de Texto ");
			}
		}
		else
		{
			System.out.println("Faltan Parametros.");
		}
	}
}
