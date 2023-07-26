package bg.sofia.sirma.interview;

import bg.sofia.sirma.interview.project.ProjectWork;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CompareDates {

    public static LocalDate dateBefore(LocalDate date1, LocalDate date2) {
        if (date1 == null) {
            date1 = LocalDate.now();
        }
        if (date2 == null) {
            date2 = LocalDate.now();
        }

        return date1.isBefore(date2) ? date1 : date2;
    }

    public static LocalDate dateAfter(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2) ? date1 : date2;
    }

    public static long daysBetween(ProjectWork pWork1, ProjectWork pWork2) {
        long result = ChronoUnit.DAYS.between(
                CompareDates.dateAfter(pWork1.dateFrom(), pWork2.dateFrom()),
                CompareDates.dateBefore(pWork1.dateTo(), pWork2.dateTo()));
        return result > 0 ? result : 0;
    }
}
