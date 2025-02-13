package charlyday.timefold.domain;

import ai.timefold.solver.core.api.domain.solution.*;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@PlanningSolution
public class Planification {
	// ===== PROBLEM FACTS ===== //
	@ProblemFactCollectionProperty
	@ValueRangeProvider
	private List<Utilisateur> salaries;


	// ===== PLANNING ENTITIES ===== //
	@PlanningEntityCollectionProperty
	private List<Besoin> besoins;


	// ===== PLANNING SCORE ===== //
	@Setter
	@PlanningScore
	private HardSoftScore score;

	public Planification(List<Utilisateur> salaries, List<Besoin> besoins) {
		this.salaries = salaries;
		this.besoins = besoins;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Besoin besoin : besoins) {
			Utilisateur client = besoin.getClient();
			Utilisateur salarie = besoin.getSalarie();
			String competence = besoin.getCompetence().getLibelle();
			if (salarie == null) {
				res.append(client.getNom()).append(" -> ").append(competence).append(" pour null\n");
				continue;
			}
			res.append(client.getNom()).append(" -> ").append(competence).append(" pour ").append(salarie.getNom()).append("\n");
		}
		res.append("Score : ").append(score);
		return res.toString();
	}
}
