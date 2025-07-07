package com.mowitow.mower;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MowerKataApplication {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Main <fichier_input>");
			System.exit(1);
		}

		try {
			Path inputFile = Paths.get(args[0]);
			// Parse le fichier d'entrée et lance la simulation
			MowerSimulator simulator = new MowerSimulator();
			List<String> results = simulator.simulateFromFile(inputFile);

			// Affichage des résultats
			for (String result : results) {
				System.out.println(result);
			}

		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.err.println("Erreur dans le format du fichier: " + e.getMessage());
			System.exit(1);
		}
	}

}
