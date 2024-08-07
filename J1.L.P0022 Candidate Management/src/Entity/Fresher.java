/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class Fresher extends Candidate {

    public enum GraduationRank {
        Excellence(0),
        Good(1),
        Fair(2),
        Poor(3);
        private final int value;

        private GraduationRank(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static GraduationRank getRankByInt(int type) {
            switch (type) {
                case 0:
                    return Excellence;
                case 1:
                    return Good;
                case 2:
                    return Fair;
                case 3:
                    return Poor;
                default:
                    throw new IllegalArgumentException("Invalid graduation rank value: " + type);
            }
        }

    }
    private int graduationDate;
    private GraduationRank graduationRank;
    private String education;

    public Fresher() {
        super();
    }

    public Fresher(int graduationDate, GraduationRank graduationRank, String education, int id, String firstName, String lastName, int birthDate, String address, String phone, String email, CandidateType candidateType) {
        super(id, firstName, lastName, birthDate, address, phone, email, candidateType);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(int graduationDate) {
        this.graduationDate = graduationDate;
    }

    public GraduationRank getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(GraduationRank graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

}
