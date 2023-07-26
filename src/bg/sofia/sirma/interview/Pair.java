package bg.sofia.sirma.interview;

import bg.sofia.sirma.interview.project.ProjectWork;

import java.util.Objects;

public record Pair(ProjectWork key, ProjectWork value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return key == pair.key &&
                value == pair.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
