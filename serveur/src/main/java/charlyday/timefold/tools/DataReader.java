package charlyday.timefold.tools;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import charlyday.timefold.domain.*;
import org.antlr.v4.runtime.misc.Pair;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DataReader {
	public static void main(String[] args) {
		System.out.println(read("src/main/resources/etudiant/01_pb_simples/Probleme_1_nbSalaries_3_nbClients_3_nbTaches_2.csv"));
	}

	public static Planification read(String path) {
		try (BufferedReader file = new BufferedReader(new FileReader(path))) {
			String line = file.readLine();
			// skip first line
			if (line != null) line = file.readLine();
			Map<String, List<String>> csv_besoins = new HashMap<>();
			Map<String, List<Pair<String, Integer>>> csv_competences = new HashMap<>();

			boolean readingBesoins = true;
			while (line != null) {
				List<String> data = List.of(line.split(";"));
				if (data.getFirst().equals("competences")) {
					readingBesoins = false;
					line = file.readLine();
					continue;
				}
				if (readingBesoins) {
					csv_besoins.computeIfAbsent(data.get(1), (k) -> new ArrayList<>())
							.add(data.get(2));
				} else {
					csv_competences.computeIfAbsent(data.get(1), (k) -> new ArrayList<>())
							.add(new Pair<>(data.get(2), Integer.parseInt(data.get(3))));
				}

				line = file.readLine();
			}

			List<Utilisateur> clients = csv_besoins.keySet().stream()
					.map(client -> new Utilisateur(UUID.randomUUID(), client))
					.toList();
			List<String> competences_string = csv_competences.values().stream()
					.flatMap(List::stream)
					.map(pair -> pair.a)
					.collect(Collectors.toList());
			competences_string.addAll(csv_besoins.values().stream()
					.flatMap(List::stream)
					.toList());
			List<Competence> competences = competences_string.stream()
					.distinct()
					.map(competence -> new Competence(UUID.randomUUID(), competence))
					.toList();

			List<Utilisateur> salaries = new ArrayList<>();
			for (Map.Entry<String, List<Pair<String, Integer>>> entry : csv_competences.entrySet()) {
				List<SalarieCompetence> salarieCompetences = new ArrayList<>();
				for (Pair<String, Integer> pair : entry.getValue()) {
					Competence competence = competences.stream()
							.filter(c -> c.getLibelle().equals(pair.a))
							.findFirst()
							.orElseThrow();
					salarieCompetences.add(new SalarieCompetence(UUID.randomUUID(), competence, pair.b));
				}
				salaries.add(new Utilisateur(UUID.randomUUID(), entry.getKey(), salarieCompetences));
			}

			LocalDateTime date = LocalDateTime.of(2025, 2, 13, 8, 0);
			List<Besoin> besoins = new ArrayList<>();
			for (Map.Entry<String, List<String>> entry : csv_besoins.entrySet()) {
				Utilisateur client = clients.stream()
						.filter(c -> c.getNom().equals(entry.getKey()))
						.findFirst()
						.orElseThrow();
				for (String competence : entry.getValue()) {
					Competence c = competences.stream()
							.filter(comp -> comp.getLibelle().equals(competence))
							.findFirst()
							.orElseThrow();
					besoins.add(new Besoin(UUID.randomUUID(), client, "Besoin", c, date, 60));
				}
			}

			return new Planification(salaries, besoins);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static Pair<Planification, Integer> readSolution(String path, String pathSol) {
		try (BufferedReader file = new BufferedReader(new FileReader(pathSol))) {
			Planification planification = read(path);

			String line = file.readLine();
			int score = Integer.parseInt(List.of(line.split(";")).getFirst());
			line = file.readLine();

			while (line != null) {
				List<String> data = List.of(line.split(";"));

				String competence = data.get(1);
				String client = data.get(2);

				Utilisateur salarie = planification.getSalaries().stream()
						.filter(s -> s.getNom().equals(data.getFirst()))
						.findFirst().orElseThrow();

				planification.getBesoins().stream()
						.filter(b -> b.getClient().getNom().equals(client)
								&& b.getCompetence().getLibelle().equals(competence))
						.findFirst().orElseThrow()
						.setSalarie(salarie);

				line = file.readLine();
			}

			planification.setScore(HardSoftScore.ofSoft(score));

			return new Pair<>(planification, score);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}