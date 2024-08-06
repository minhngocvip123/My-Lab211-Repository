/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Candidate;
import Entity.Experience;
import Entity.Fresher;
import Entity.Intern;
import Validation.Validation;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Manager {

    ArrayList<Candidate> candidates = new ArrayList<>();
    Validation validation = new Validation();

    public Manager() {
        //pre-add candidates for testing purposes
        candidates.add(new Experience(3, "Flip burgers", 1, "John", "Doe", 1980, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));
        candidates.add(new Experience(4, "ASMR", 2, "John", "Doherty", 1992, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));
        candidates.add(new Experience(6, "Gaming", 3, "John", "Though", 1945, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));
        
        candidates.add(new Fresher("1950", Fresher.GraduationRank.Excellence, "FPT University", 4, "Gwen", "Stacy", 2002, "Wall St", "0123456789", "top9wen@gmail.com", Candidate.CandidateType.Fresher));
        candidates.add(new Fresher("2002", Fresher.GraduationRank.Fair, "FPT PolyTechnic", 5, "Mary", "Jane", 1975, "Wall St", "0123456789", "therealMJ12@gmail.com", Candidate.CandidateType.Fresher));
        candidates.add(new Fresher("2024", Fresher.GraduationRank.Good, "PTIT", 6, "Emma", "Stone", 1984, "Wall St", "0123456789", "emmaW2@gmail.com", Candidate.CandidateType.Fresher));
        
        candidates.add(new Intern("SoftEn", 6, "FPT University", 7, "Donald", "Trump", 1984, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
        candidates.add(new Intern("GD", 3, "FPT University", 8, "Adolf", "Hitler", 1984, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
        candidates.add(new Intern("Japanology", 6, "HANU", 9, "Minh", "Dang", 2002, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
    }
    
    public void displayMenu() {
        System.out.println("");
        System.out.println("CANDIDATE MANAGEMENT SYSTEM");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Internship");
        System.out.println("4. Searching");
        System.out.println("5. Exit");
    }

    public void createCandidate(int candidateType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void searchCandidate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
