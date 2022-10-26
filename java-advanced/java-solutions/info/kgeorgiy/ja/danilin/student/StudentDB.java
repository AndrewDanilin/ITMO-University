package info.kgeorgiy.ja.danilin.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentDB implements StudentQuery {

    private static final Comparator<Student> LAST_NAME_COMPARATOR = Comparator.comparing(Student::getLastName);

    private static final Comparator<Student> FIRST_NAME_COMPARATOR = Comparator.comparing(Student::getFirstName);

    private static final Comparator<Student> ID_COMPARATOR = Comparator.comparingInt(Student::getId);

    private static final Comparator<Student> NAME_COMPARATOR = LAST_NAME_COMPARATOR
            .thenComparing(FIRST_NAME_COMPARATOR)
            .reversed()
            .thenComparing(ID_COMPARATOR);

    private <T> List<T> studentsToMappingList(List<Student> students, Function<Student, T> handler) {
        return students.stream().map(handler).collect(Collectors.toList());
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return studentsToMappingList(students, Student::getFirstName);
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return studentsToMappingList(students, Student::getLastName);
    }

    @Override
    public List<GroupName> getGroups(List<Student> students) {
        return studentsToMappingList(students, Student::getGroup);
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return studentsToMappingList(students, student -> student.getFirstName() + " " + student.getLastName());
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return students.stream()
                .sorted(FIRST_NAME_COMPARATOR)
                .map(Student::getFirstName)
                .collect(Collectors.toSet());
    }

    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students.stream()
                .max(ID_COMPARATOR)
                .map(Student::getFirstName)
                .orElse("");
    }

    private List<Student> sortStudentsUsingComparator(Collection<Student> students, Comparator<Student> comparator) {
        return students.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return sortStudentsUsingComparator(students, ID_COMPARATOR);
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return sortStudentsUsingComparator(students, NAME_COMPARATOR);
    }

    private List<Student> findStudentsBySpecifiedInfo(Collection<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .sorted(NAME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return findStudentsBySpecifiedInfo(students, student -> student.getFirstName().equals(name));
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return findStudentsBySpecifiedInfo(students, student -> student.getLastName().equals(name));
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        return findStudentsBySpecifiedInfo(students, student -> student.getGroup().equals(group));
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return findStudentsByGroup(students, group).stream()
                .collect(Collectors.toMap(Student::getLastName, Student::getFirstName, BinaryOperator.minBy(String::compareTo)));
    }

}
