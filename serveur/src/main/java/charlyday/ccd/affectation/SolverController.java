package charlyday.ccd.affectation;

import ai.timefold.solver.core.api.score.analysis.ScoreAnalysis;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import charlyday.timefold.domain.Planification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
public class SolverController {
	private SolverManager<Planification, UUID> solverManager;
	private SolutionManager<Planification, HardSoftScore> solutionManager;

	@Autowired
	public SolverController(SolverManager<Planification, UUID> solverManager, SolutionManager<Planification, HardSoftScore> solutionManager) {
		this.solverManager = solverManager;
		this.solutionManager = solutionManager;
	}

//	public Planification solve(Planification planification) {
//		UUID problemId = UUID.randomUUID();
//
//		SolverJob<Planification, UUID> solverJob = solverManager.solve(problemId, planification);
//
//		Planification solution;
//		try {
//			solution = solverJob.getFinalBestSolution();
//		} catch (InterruptedException | ExecutionException e) {
//			throw new IllegalStateException("Erreur lors de la résolution du problème", e);
//		}
//
//		ScoreAnalysis<HardSoftScore> scoreAnalysis = solutionManager.analyze(solution);
//
//		List<String> brokenCosntraints = new ArrayList<>();
//
//		scoreAnalysis.constraintMap().forEach((x, constraintAnalysis) -> {
//			if (constraintAnalysis.matches() == null) return;
//			constraintAnalysis.matches().forEach(matchAnalysis -> {
//				Object justification = matchAnalysis.justification();
//				brokenCosntraints.add("Justification : " + justification);
//				// brokenCosntraints.add("Constraint : " + matchAnalysis.constraintRef());
//			});
//		});
//
//		// brokenCosntraints : liste des différentes contraintes non respectées
//		// solution : solution du problème
//
//		System.out.println(solution);
//		System.out.println(String.join("\n", brokenCosntraints));
//
//		return solution;
//	}
}
