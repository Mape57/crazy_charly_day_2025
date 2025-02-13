package charlyday.timefold.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BesoinTest {
	@Nested
	class OverlapTime {
		@Test
		void OK_not_same_day() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 0, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 2, 1, 0, 0);

			Besoin besoin1 = new Besoin(null, null, null, null, date1, 60);
			Besoin besoin2 = new Besoin(null, null, null, null, date2, 60);

			assertFalse(besoin1.overlapTime(besoin2));
		}

		@Test
		void OK_different_hour() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 0, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 2, 0);

			Besoin besoin1 = new Besoin(null, null, null, null, date1, 60);
			Besoin besoin2 = new Besoin(null, null, null, null, date2, 60);

			assertFalse(besoin1.overlapTime(besoin2));
		}

		@Test
		void OK_following_hour() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 0, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 1, 0);

			Besoin besoin1 = new Besoin(null, null, null, null, date1, 60);
			Besoin besoin2 = new Besoin(null, null, null, null, date2, 60);

			assertFalse(besoin1.overlapTime(besoin2));
		}

		@Test
		void KO_same_hour() {
			LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);

			Besoin besoin1 = new Besoin(null, null, null, null, date, 60);
			Besoin besoin2 = new Besoin(null, null, null, null, date, 60);

			assertTrue(besoin1.overlapTime(besoin2));
		}

		@Test
		void KO_overlap_hour() {
			LocalDateTime date1 = LocalDateTime.of(2021, 1, 1, 0, 0);
			LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 0, 30);

			Besoin besoin1 = new Besoin(null, null, null, null, date1, 60);
			Besoin besoin2 = new Besoin(null, null, null, null, date2, 60);

			assertTrue(besoin1.overlapTime(besoin2));
		}
	}
}
