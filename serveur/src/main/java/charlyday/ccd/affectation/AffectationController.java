package charlyday.ccd.affectation;

import ai.timefold.solver.core.api.score.analysis.ScoreAnalysis;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.SolverJob;
import ai.timefold.solver.core.api.solver.SolverManager;
import charlyday.ccd.besoinUtilisateur.BesoinUtilisateurEntity;
import charlyday.ccd.besoinUtilisateur.BesoinUtilisateurKey;
import charlyday.ccd.besoins.BesoinsEntity;
import charlyday.ccd.besoins.BesoinsService;
import charlyday.ccd.competences.CompetenceEntity;
import charlyday.ccd.competences.CompetenceService;
import charlyday.ccd.salarieCompetence.SalarieCompetenceEntity;
import charlyday.ccd.salarieCompetence.SalarieCompetenceKey;
import charlyday.ccd.salarieCompetence.SalarieCompetenceService;
import charlyday.ccd.utilisateur.UtilisateurEntity;
import charlyday.ccd.utilisateur.UtilisateurService;
import charlyday.timefold.domain.*;
import charlyday.timefold.tools.DataReader;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("affectation")
public class AffectationController {
	private BesoinsService besoinsService;
	private UtilisateurService utilisateurService;
	private SalarieCompetenceService salarieCompetenceService;
	private CompetenceService competenceService;
	private SolverManager<Planification, UUID> solverManager;
	private SolutionManager<Planification, HardSoftScore> solutionManager;

	public AffectationController(BesoinsService besoinsService, UtilisateurService utilisateurService,
								 SalarieCompetenceService salarieCompetenceService, CompetenceService competenceService,
								 SolverManager<Planification, UUID> solverManager, SolutionManager<Planification, HardSoftScore> solutionManager) {
		this.besoinsService = besoinsService;
		this.utilisateurService = utilisateurService;
		this.salarieCompetenceService = salarieCompetenceService;
		this.competenceService = competenceService;
		this.solverManager = solverManager;
	}

	@GetMapping
	public List<BesoinUtilisateurEntity> solveAndGetAffectation() {
		Planification problem;

		List<Utilisateur> salariesDomain = utilisateurService.getAllByRole(1).stream()
				.map(this::convertToDomain).toList();
		List<Besoin> besoinsDomain = besoinsService.getAllBesoins().stream()
				.map(this::convertToDomain).toList();

		UUID problemId = UUID.randomUUID();
		problem = new Planification(salariesDomain, besoinsDomain);

		SolverJob<Planification, UUID> solverJob = solverManager.solve(problemId, problem);

		Planification solution;
		try {
			solution = solverJob.getFinalBestSolution();
		} catch (InterruptedException | ExecutionException e) {
			throw new IllegalStateException("Erreur lors de la résolution du problème", e);
		}

		ScoreAnalysis<HardSoftScore> scoreAnalysis = solutionManager.analyze(solution);

		List<String> brokenCosntraints = new ArrayList<>();

		scoreAnalysis.constraintMap().forEach((x, constraintAnalysis) -> {
			if (constraintAnalysis.matches() == null) return;
			constraintAnalysis.matches().forEach(matchAnalysis -> {
				Object justification = matchAnalysis.justification();
				brokenCosntraints.add("Justification : " + justification);
				// brokenCosntraints.add("Constraint : " + matchAnalysis.constraintRef());
			});
		});

		// brokenCosntraints : liste des différentes contraintes non respectées
		// solution : solution du problème

		System.out.println(solution);
		System.out.println(String.join("\n", brokenCosntraints));

		List<BesoinUtilisateurEntity> affectations = new ArrayList<>();

		for (Besoin besoin : solution.getBesoins()) {
			UUID salarieId = besoin.getClient().getId();
			UUID clientId = besoin.getSalarie().getId();
			BesoinUtilisateurEntity affectation = new BesoinUtilisateurEntity();
			affectation.getBesoinUtilisateurKey().setSalarieId(salarieId);
			affectation.getBesoinUtilisateurKey().setBesoinId(clientId);

			affectations.add(affectation);
		}

		return affectations;
	}

	@GetMapping("/test")
	public ResponseEntity<String> saveData() {
		String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_3_nbSalaries_15_nbClients_15_nbTaches_1.csv";
		Planification problem = DataReader.read(path);

		List<UtilisateurEntity> utilisateurEntities = problem.getSalaries().stream()
				.map(this::convertToEntity).toList();
		return null; // todo
	}

	private Besoin convertToDomain(BesoinsEntity besoinsEntity) {
		UUID id = besoinsEntity.getId();
		UtilisateurEntity clientEntity = utilisateurService.getUtilisateurById(besoinsEntity.getClientId());
		Utilisateur client = convertToDomain(clientEntity);
		Competence competence = convertToDomain(competenceService.getCompetenceById(besoinsEntity.getCompetenceId()));
		LocalDateTime date = LocalDateTime.ofEpochSecond(besoinsEntity.getDateService().toInstant().getEpochSecond(), 0, ZoneOffset.UTC);

		return new Besoin(id, client, besoinsEntity.getDescription(), competence, date, besoinsEntity.getDuree());
	}

	private Utilisateur convertToDomain(UtilisateurEntity utilisateurEntity) {
		List<SalarieCompetence> competences = salarieCompetenceService.getCompetenceForSalarie(utilisateurEntity.getId()).stream()
				.map(this::convertToDomain).toList();

		return new Utilisateur(utilisateurEntity.getId(), utilisateurEntity.getNom(), competences);
	}

	private SalarieCompetence convertToDomain(SalarieCompetenceEntity salarieCompetenceEntity) {
		UUID competenceId = salarieCompetenceEntity.getSalarieCompetenceKey().getCompetenceId();
		Competence competence = convertToDomain(competenceService.getCompetenceById(competenceId));

		return new SalarieCompetence(salarieCompetenceEntity.getSalarieCompetenceKey().getSalarieId(), competence, salarieCompetenceEntity.getInteret());
	}

	private Competence convertToDomain(CompetenceEntity competenceEntity) {
		return new Competence(competenceEntity.getId(), competenceEntity.getLibelle());
	}

	private UtilisateurEntity convertToEntity(Utilisateur utilisateur) {
		UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
		utilisateurEntity.setId(utilisateur.getId());
		utilisateurEntity.setNom(utilisateur.getNom());
		utilisateurEntity.setEmail("default@mail.com");
		utilisateurEntity.setPassword("default");
		utilisateurEntity.setRole(utilisateurEntity.getRole());

		List<SalarieCompetenceEntity> salarieCompetenceEntities = utilisateur.getCompetences().stream()
				.map(this::convertToEntity)
				.toList();

		salarieCompetenceEntities.forEach(salarieCompetenceEntity -> {
			salarieCompetenceService.createSalarieCompetence(salarieCompetenceEntity);
		});

		return utilisateurEntity;
	}

	private SalarieCompetenceEntity convertToEntity(SalarieCompetence salarieCompetence) {
		SalarieCompetenceEntity salarieCompetenceEntity = new SalarieCompetenceEntity();

		SalarieCompetenceKey salarieCompetenceKey = new SalarieCompetenceKey();
		salarieCompetenceKey.setSalarieId(salarieCompetence.getSalarie().getId());
		salarieCompetenceKey.setCompetenceId(salarieCompetence.getCompetence().getId());
		salarieCompetenceEntity.setSalarieCompetenceKey(salarieCompetenceKey);

		salarieCompetenceEntity.setInteret(salarieCompetence.getInteret());

		return salarieCompetenceEntity;
	}
}
