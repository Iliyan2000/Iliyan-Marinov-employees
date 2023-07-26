package bg.sofia.sirma.interview.project;

import bg.sofia.sirma.interview.CommonInfo;
import bg.sofia.sirma.interview.CompareDates;
import bg.sofia.sirma.interview.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProjectAnalyser {

    private final List<ProjectWork> works;

    /**
     * Loads the dataset from the given {@code reader}.
     *
     * @param file File path from which the dataset can be read.
     */
    public ProjectAnalyser(Path file) {

        try (var br = Files.newBufferedReader(file)) {

            works = br.lines().map(ProjectWork::of).toList();

        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not load dataset", ex);
        }

    }

    /**
     * Calculate the two employees worked for the longest period of time on same project
     *
     * @return the pair of two employees with the information when they worked on the project
     */
    public Pair longestPeriodTeam() {

        Map<ProjectWork, ProjectWork> mostTimeSpent = works.stream()
                .collect(Collectors.toMap(Function.identity(), t -> t.mostCommonWork(works)));

        Pair result = null;
        long mostTimeSpentTogether = 0;
        for (var entry : mostTimeSpent.entrySet()) {

            long timeSpentTogether = CompareDates.daysBetween(entry.getKey(), entry.getValue());

            if (timeSpentTogether > mostTimeSpentTogether) {
                result = new Pair(entry.getKey(), entry.getValue());
                mostTimeSpentTogether = timeSpentTogether;
            }
        }

        return result;
    }

    /**
     * Finds all their common project they worked on and calculate the period of time in days
     *
     * @param empID1 the ID of the first employee
     * @param empID2 the ID of the second employee
     * @return list of their common projects and the days spent
     */
    public List<CommonInfo> findCommonProjects(int empID1, int empID2) {

        List<ProjectWork> emp1Works = works.stream().filter(t -> t.empID() == empID1).toList();
        List<ProjectWork> emp2Works = works.stream().filter(t -> t.empID() == empID2).toList();

        List<CommonInfo> result = new ArrayList<>();
        for (ProjectWork work1 : emp1Works) {

            long timeTogether = 0;
            for (ProjectWork work2 : emp2Works) {
                if (work1.projectID() == work2.projectID()) {

                    timeTogether += CompareDates.daysBetween(work1, work2);

                }
            }

            boolean flag = false;
            for (CommonInfo work : result) {
                if (work1.projectID() == work.getProjectID()) {

                    work.addCommonDays(timeTogether);
                    flag = true;
                    break;

                }
            }

            if (!flag && timeTogether > 0) {

                result.add(new CommonInfo(empID1, empID2, work1.projectID(), timeTogether));

            }
        }

        return result;
    }

}
