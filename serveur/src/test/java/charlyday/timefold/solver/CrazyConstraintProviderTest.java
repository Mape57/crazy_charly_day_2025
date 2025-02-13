package charlyday.timefold.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.test.api.score.stream.ConstraintVerifier;
import charlyday.timefold.domain.*;
import charlyday.timefold.tools.DataReader;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class CrazyConstraintProviderTest {
	@Autowired
	private ConstraintVerifier<CrazyConstraintProvider, Planification> constraintVerifier;

	@Nested
	class JourneeTravail {
		@Test
		void OK_jour_different() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 8, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 2, 1, 8, 0);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1", null);
			Besoin besoin1 = new Besoin(UUID.randomUUID(), null, "besoin 1", null, date1, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), null, "besoin 2", null, date2, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::tacheSimultanement)
					.given(besoin1, besoin2)
					.penalizesBy(0);
		}

		@Test
		void OK_heure_differente() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 8, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 9, 0);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1", null);
			Besoin besoin1 = new Besoin(UUID.randomUUID(), null, "besoin 1", null, date1, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), null, "besoin 2", null, date2, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::tacheSimultanement)
					.given(besoin1, besoin2)
					.penalizesBy(0);
		}

		@Test
		void KO_meme_heure() {
			LocalDateTime date = LocalDateTime.of(2021, 1, 1, 8, 0);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1", null);
			Besoin besoin1 = new Besoin(UUID.randomUUID(), null, "besoin 1", null, date, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), null, "besoin 2", null, date, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::tacheSimultanement)
					.given(besoin1, besoin2)
					.penalizesBy(2);
		}

		@Test
		void KO_chevauchement_heure() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 8, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 8, 30);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1", null);
			Besoin besoin1 = new Besoin(UUID.randomUUID(), null, "besoin 1", null, date1, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), null, "besoin 2", null, date2, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::tacheSimultanement)
					.given(besoin1, besoin2)
					.penalizesBy(2);
		}
	}

	@Nested
	class CompetenceNecessaire {
		private final List<Competence> competences = List.of(
				new Competence(UUID.randomUUID(), "BR"),
				new Competence(UUID.randomUUID(), "MN"),
				new Competence(UUID.randomUUID(), "AD"),
				new Competence(UUID.randomUUID(), "JD"),
				new Competence(UUID.randomUUID(), "IF")
		);

		private final Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "Alice", List.of(
				new SalarieCompetence(UUID.randomUUID(), competences.get(0), 4),
				new SalarieCompetence(UUID.randomUUID(), competences.get(1), 7),
				new SalarieCompetence(UUID.randomUUID(), competences.get(2), 1)
		));

		@Test
		void OK_competence_presente() {
			LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 0, 0);
			Besoin besoin = new Besoin(UUID.randomUUID(), null, "Besoin 1", competences.get(0), dateTime, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::competenceNecessaire)
					.given(besoin)
					.penalizesBy(0);
		}

		@Test
		void KO_competence_absente() {
			LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 0, 0);
			Besoin besoin = new Besoin(UUID.randomUUID(), null, "Besoin 1", competences.get(3), dateTime, 60, salarie);

			constraintVerifier.verifyThat(CrazyConstraintProvider::competenceNecessaire)
					.given(besoin)
					.penalizesBy(1);
		}
	}

	@Nested
	class UtiliteDegressives {
		@Test
		void KO_sujet() {
			List<Utilisateur> clients = List.of(
					new Utilisateur(UUID.randomUUID(), "Hugues"),
					new Utilisateur(UUID.randomUUID(), "Iris"),
					new Utilisateur(UUID.randomUUID(), "Jacques")
			);
			List<Competence> competences = List.of(
					new Competence(UUID.randomUUID(), "jardinage"),
					new Competence(UUID.randomUUID(), "bricolage")
			);
			List<Utilisateur> salaries = List.of(
					new Utilisateur(UUID.randomUUID(), "Delphine", List.of(
							new SalarieCompetence(UUID.randomUUID(), competences.get(0), 8),
							new SalarieCompetence(UUID.randomUUID(), competences.get(1), 4)
					)),
					new Utilisateur(UUID.randomUUID(), "Bertrand", List.of(
							new SalarieCompetence(UUID.randomUUID(), competences.get(1), 7)
					)),
					new Utilisateur(UUID.randomUUID(), "Francis", List.of(
							new SalarieCompetence(UUID.randomUUID(), competences.get(0), 6),
							new SalarieCompetence(UUID.randomUUID(), competences.get(1), 5)
					))
			);
			LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 8, 0);
			List<Besoin> besoins = List.of(
					new Besoin(UUID.randomUUID(), clients.get(0), "hugues jardinage", competences.get(0), dateTime, 60, salaries.get(0)),
					new Besoin(UUID.randomUUID(), clients.get(0), "hugues bricolage", competences.get(1), dateTime, 60, salaries.get(1)),
					new Besoin(UUID.randomUUID(), clients.get(1), "iris bricolage", competences.get(1), dateTime, 60, salaries.get(2))
			);

			constraintVerifier.verifyThat(CrazyConstraintProvider::utiliteDegressives)
					.given(besoins.get(0), besoins.get(1), besoins.get(2))
					.rewardsWith(19);
		}
	}

	@Nested
	class MinimumSatisfaction {
		@Test
		void OK_satisfait() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 8, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 8, 30);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1");
			Utilisateur client = new Utilisateur(UUID.randomUUID(), "client 1");
			Besoin besoin1 = new Besoin(UUID.randomUUID(), client, "besoin 1", null, date1, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), client, "besoin 2", null, date2, 60);

			constraintVerifier.verifyThat(CrazyConstraintProvider::minimumSatisfaction)
					.given(besoin1, besoin2)
					.penalizesBy(0);
		}

		@Test
		void KO_insatisfait() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 8, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 8, 30);
			Utilisateur salarie = new Utilisateur(UUID.randomUUID(), "salarie 1");
			Utilisateur client = new Utilisateur(UUID.randomUUID(), "client 1");
			Utilisateur client2 = new Utilisateur(UUID.randomUUID(), "client 2");
			Besoin besoin1 = new Besoin(UUID.randomUUID(), client, "besoin 1", null, date1, 60, salarie);
			Besoin besoin2 = new Besoin(UUID.randomUUID(), client2, "besoin 2", null, date2, 60);

			constraintVerifier.verifyThat(CrazyConstraintProvider::minimumSatisfaction)
					.given(besoin1, besoin2)
					.penalizesBy(10);
		}
	}

	@Nested
	class SujetSolution {
		@Test
		void problem_1() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_1_nbSalaries_10_nbClients_10_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_1_nbSalaries_10_nbClients_10_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_2() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_2_nbSalaries_15_nbClients_15_nbTaches_1.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_2_nbSalaries_15_nbClients_15_nbTaches_1_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_3() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_3_nbSalaries_15_nbClients_15_nbTaches_1.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_3_nbSalaries_15_nbClients_15_nbTaches_1_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_4() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_4_nbSalaries_15_nbClients_15_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_4_nbSalaries_15_nbClients_15_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_5() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_5_nbSalaries_15_nbClients_15_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_5_nbSalaries_15_nbClients_15_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_6() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_6_nbSalaries_20_nbClients_15_nbTaches_5.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_6_nbSalaries_20_nbClients_15_nbTaches_5_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_7() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_7_nbSalaries_20_nbClients_20_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_7_nbSalaries_20_nbClients_20_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_8() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_8_nbSalaries_20_nbClients_20_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_8_nbSalaries_20_nbClients_20_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void proble_9() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_9_nbSalaries_26_nbClients_26_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_9_nbSalaries_26_nbClients_26_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}

		@Test
		void problem_10() {
			String path = "src/main/resources/etudiant/02_pb_complexes/Probleme_10_nbSalaries_26_nbClients_26_nbTaches_3.csv";
			String pathSol = "src/main/resources/etudiant/02_pb_complexes/Probleme_10_nbSalaries_26_nbClients_26_nbTaches_3_Sol.csv";
			Pair<Planification, Integer> problem = DataReader.readSolution(path, pathSol);

			constraintVerifier.verifyThat()
					.givenSolution(problem.a)
					.scores(HardSoftScore.ofSoft(problem.b));
		}
	}
}