package charlyday.timefold.application;

import ai.timefold.solver.core.api.score.analysis.ScoreAnalysis;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import charlyday.timefold.domain.*;
import charlyday.timefold.tools.DataReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// initialisation du solveur
		SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
		SolverFactory<Planification> solverFactory = SolverFactory.create(solverConfig);
		Solver<Planification> solver = solverFactory.buildSolver();

		long startTime = System.nanoTime();

		// affiche chaque amélioration de la solution
		solver.addEventListener(event -> {
			Planification newBestSolution = event.getNewBestSolution();
			System.out.println("New best solution found with score: " + newBestSolution.getScore().toString() + " after " + (System.nanoTime() - startTime) + " ns");

		});


		Planification problem = DataReader.read("src/main/resources/etudiant/02_pb_complexes/Probleme_10_nbSalaries_26_nbClients_26_nbTaches_3.csv");
		Planification solution = solver.solve(problem);

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		System.out.println("Solution found in " + TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS) + " seconds");
		System.out.println(solution);
		analyser(solution, solverFactory);
	}

	private static void analyser(Planification solution, SolverFactory<Planification> solverFactory) {
		SolutionManager<Planification, HardSoftScore> solutionManager = SolutionManager.create(solverFactory);
		ScoreAnalysis<HardSoftScore> scoreAnalysis = solutionManager.analyze(solution);

		List<ConstraintJustification> brokenCosntraints = new ArrayList<>();

		scoreAnalysis.constraintMap().forEach((x, constraintAnalysis) -> {
			if (constraintAnalysis.matches() == null) return;
			constraintAnalysis.matches().forEach(matchAnalysis -> {
				ConstraintJustification justification = matchAnalysis.justification();
				brokenCosntraints.add(justification);
				// brokenCosntraints.add("Constraint : " + matchAnalysis.constraintRef());
			});
		});

		System.out.println("Broken constraints:");
		brokenCosntraints.forEach(System.out::println);
	}

	private static Planification getPlanification() {
		List<Utilisateur> clients = List.of(
				new Utilisateur(UUID.randomUUID(), "Antoine_C"),
				new Utilisateur(UUID.randomUUID(), "Brigitte_C"),
				new Utilisateur(UUID.randomUUID(), "Cedric_C")
		);
		List<Competence> competences = List.of(
				new Competence(UUID.randomUUID(), "BR"),
				new Competence(UUID.randomUUID(), "MN"),
				new Competence(UUID.randomUUID(), "AD"),
				new Competence(UUID.randomUUID(), "JD"),
				new Competence(UUID.randomUUID(), "IF")
		);
		List<Utilisateur> salaries = List.of(
				new Utilisateur(UUID.randomUUID(), "Alice", List.of(
						new SalarieCompetence(UUID.randomUUID(), competences.get(0), 4),
						new SalarieCompetence(UUID.randomUUID(), competences.get(1), 7),
						new SalarieCompetence(UUID.randomUUID(), competences.get(2), 1)
				)),
				new Utilisateur(UUID.randomUUID(), "Bernard", List.of(
						new SalarieCompetence(UUID.randomUUID(), competences.get(0), 5),
						new SalarieCompetence(UUID.randomUUID(), competences.get(2), 7)
				)),
				new Utilisateur(UUID.randomUUID(), "Charlotte", List.of(
						new SalarieCompetence(UUID.randomUUID(), competences.get(3), 8),
						new SalarieCompetence(UUID.randomUUID(), competences.get(4), 5),
						new SalarieCompetence(UUID.randomUUID(), competences.get(2), 4)
				))
		);
		LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 0, 0);
		List<Besoin> besoins = List.of(
				new Besoin(UUID.randomUUID(), clients.get(0), "Besoin 1", competences.get(0), dateTime, 60),
				new Besoin(UUID.randomUUID(), clients.get(0), "Besoin 2", competences.get(1), dateTime, 60),
				new Besoin(UUID.randomUUID(), clients.get(1), "Besoin 3", competences.get(1), dateTime, 60),
				new Besoin(UUID.randomUUID(), clients.get(2), "Besoin 4", competences.get(4), dateTime, 60)
		);
		return new Planification(salaries, besoins);
	}
}
