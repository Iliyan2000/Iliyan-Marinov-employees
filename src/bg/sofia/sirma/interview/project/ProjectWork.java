package bg.sofia.sirma.interview.project;

import bg.sofia.sirma.interview.CompareDates;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record ProjectWork(int empID, int projectID, LocalDate dateFrom, LocalDate dateTo) {

    private static final int EMP_ID = 0;
    private static final int PROJECT_ID = 1;
    private static final int DATE_FROM = 2;
    private static final int DATE_TO = 3;

    /**
     * Returns a bg.sofia.sirma.interview.project.ProjectWork instance, based on the given @{line} from the dataset.
     *
     * @param line line from the dataset text file
     * @return bg.sofia.sirma.interview.project.ProjectWork instance
     **/
    public static ProjectWork of(String line) {

        String[] tokens = line.split(",");

        int empID = Integer.parseInt(tokens[EMP_ID]);
        int projectID = Integer.parseInt(tokens[PROJECT_ID]);
        LocalDate dateFrom = LocalDate.parse(tokens[DATE_FROM]);
        LocalDate dateTo = !tokens[DATE_TO].equalsIgnoreCase("null") ?
                LocalDate.parse(tokens[DATE_TO]) :
                null;

        return new ProjectWork(empID, projectID, dateFrom, dateTo);
    }

    /**
     * Finds the employee with the most common days on same project
     *
     * @param list list of employees and their project information
     * @return the employee with most common days with all information about the project
     */
    public ProjectWork mostCommonWork(List<ProjectWork> list) {

        ProjectWork result = null;
        long resultCommonDays = 0;

        for (ProjectWork work : list) {

            if (this != work &&
                projectID == work.projectID) {
                long commonDays = CompareDates.daysBetween(this, work);
                if (commonDays > resultCommonDays) {

                result = work;
                }
            }

        }

        return  result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWork projectWork = (ProjectWork) o;
        return empID == projectWork.empID &&
                projectID == projectWork.projectID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empID, projectID);
    }
}
