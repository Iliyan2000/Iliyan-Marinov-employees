package bg.sofia.sirma.interview.project;

import bg.sofia.sirma.interview.CommonInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProjectAnalyser {

    private final List<ProjectWork> works;
    private List<CommonInfo> commonWork;

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
     * @return the pair of two employees with the common time spent on project
     */
    public CommonInfo longestPeriodTeam() {

        CommonInfo result = null;
        long mostTimeSpentTogether = 0;
        prepareCommonWork();

        for (var work : commonWork) {

            if (work.commonDays() > mostTimeSpentTogether) {
                result = work;
                mostTimeSpentTogether = work.commonDays();
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

        return commonWork.stream()
                .filter(t -> t.empID1() == empID1 && t.empID2() == empID2)
                .toList();
    }

    /**
     * Prepares information about employees working together
     */
    private void prepareCommonWork() {

        commonWork = works.stream()
                .map(t -> t.mostCommonWork(works))
                .filter(t -> t.commonDays() != 0)
                .toList();

    }

}
