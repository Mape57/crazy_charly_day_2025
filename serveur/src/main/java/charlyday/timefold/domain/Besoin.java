package charlyday.timefold.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@PlanningEntity
public class Besoin {
	@PlanningId
	private UUID id;
	private Utilisateur client;
	private String description;
	private Competence competence;
	private LocalDateTime dateService;
	private int duree;

	@Setter
	@PlanningVariable(allowsUnassigned = true)
	private Utilisateur salarie;

	public Besoin(UUID id, Utilisateur client, String description, Competence competence, LocalDateTime dateService, int duree) {
		this.id = id;
		this.client = client;
		this.description = description;
		this.competence = competence;
		this.dateService = dateService;
	}

	public boolean overlapTime(Besoin other) {
		if (this.dateService.isEqual(other.dateService)) return true;


		LocalDateTime start1 = dateService;
		LocalDateTime start2 = other.dateService;

		LocalDateTime end1 = dateService.plusMinutes(duree);
		LocalDateTime end2 = other.dateService.plusMinutes(other.duree);

		return !end2.isEqual(start1) && !end2.isBefore(start1) && !end1.isEqual(start2) && !end1.isBefore(start2);
	}

	public int interetSalarie() {
		List<SalarieCompetence> salarieCompetences = this.getSalarie().getCompetences();

		for (SalarieCompetence salarieCompetence : salarieCompetences) {
			if (salarieCompetence.getCompetence().equals(this.getCompetence())) {
				return salarieCompetence.getInteret();
			}
		}
		return -1;
	}

	public boolean estSalarieCompetent() {
		return this.interetSalarie() != -1;
	}

	@Override
	public String toString() {
		return (client == null ? "null" : client.getNom()) + " : "
				+ (competence == null ? "null" : competence.getLibelle()) + " - "
				+ (salarie == null ? "Aucun salarié affecté" : salarie.getNom() + " " + this.dateService.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	}
}
