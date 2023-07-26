package bg.sofia.sirma.interview;

import java.util.Objects;

public class CommonInfo {

    private final int empID1;
    private final int empID2;
    private final int projectID;
    private long commonDays;

    public CommonInfo(int empID1, int empID2, int projectID, long commonDays) {
        this.empID1 = empID1;
        this.empID2 = empID2;
        this.projectID = projectID;
        this.commonDays = commonDays;
    }

    /**
     * Getter of projectID
     *
     * @return projectID
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Adds additional days
     *
     * @param additionalDays number of days to be added
     */
    public void addCommonDays(long additionalDays) {
        commonDays += additionalDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonInfo commonInfo = (CommonInfo) o;
        return projectID == commonInfo.projectID &&
                commonDays == commonInfo.commonDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, commonDays);
    }

    @Override
    public String toString() {
        return empID1 +
                ", " + empID2 +
                ", " + projectID +
                ", " + commonDays;
    }
}
