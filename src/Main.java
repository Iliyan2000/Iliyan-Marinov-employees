import bg.sofia.sirma.interview.CommonInfo;
import bg.sofia.sirma.interview.project.ProjectAnalyser;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();

        var projectAnalyser = new ProjectAnalyser(Path.of(file).toAbsolutePath());
        CommonInfo teamWorkedMostTogether = projectAnalyser.longestPeriodTeam();

        List<CommonInfo> commonProjects = projectAnalyser
                .findCommonProjects(teamWorkedMostTogether.empID1(),
                        teamWorkedMostTogether.empID2());

        System.out.println("empID1, empID2, projectID, Days worked");
        for (CommonInfo project : commonProjects) {
            System.out.println(project.toString());
        }
    }
}