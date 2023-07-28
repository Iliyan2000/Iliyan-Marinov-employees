package bg.sofia.sirma.interview;

import java.util.Objects;

public record CommonInfo(int empID1, int empID2, int projectID, long commonDays) {

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
