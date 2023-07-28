package bg.sofia.sirma.interview.project;

import bg.sofia.sirma.interview.CommonInfo;
import bg.sofia.sirma.interview.CompareDates;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

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
     * @return the common information about the employees worked on same project for longest period
     */
    public CommonInfo mostCommonWork(List<ProjectWork> list) {

        int resultEmpID = 0;
        long resultCommonWork = 0;
        Map<Integer, Long> commonWork = generateCommonWork(list);

        for (var work : commonWork.entrySet()) {
            if (work.getValue() > resultCommonWork) {
                resultCommonWork = work.getValue();
                resultEmpID = work.getKey();
            }
        }

        return new CommonInfo(this.empID, resultEmpID, this.projectID, resultCommonWork);
    }

    /**
     * Generate pairs of employees and their common work based on the given list
     *
     * @param list list of employees and their project information
     * @return map of empIDs and their common work in days on same project
     */
    private Map<Integer, Long> generateCommonWork(List<ProjectWork> list) {

        Map<Integer, Long> result = new TreeMap<>();

        for (ProjectWork work : list) {

            if (this != work &&
                    projectID == work.projectID) {

                long commonDays = CompareDates.daysBetween(this, work);

                if (result.containsKey(work.empID)) {
                    result.replace(work.empID, result.get(work.empID) + commonDays);
                }
                else {
                    result.put(work.empID, commonDays);
                }

            }

        }

        return result;
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
