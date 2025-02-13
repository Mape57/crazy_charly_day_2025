package charlyday.timefold.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Competence {
	private UUID id;
	private String libelle;

	@Override
	public String toString() {
		return libelle;
	}
}
