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
import java.util.Calendar;

/**
 *
 * @author ADMIN
 */
public class Manager {

    ArrayList<Candidate> candidates = new ArrayList<>();
    Validation validation = new Validation();

    public Manager() {
        //pre-add candidates for testing purposes
        candidates.add(new Experience(3, "Flip burgers", 1, "Little", "John", 1980, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));
        candidates.add(new Experience(4, "ASMR", 2, "Johnny", "English", 1992, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));
        candidates.add(new Experience(6, "Gaming", 3, "Amish", "Smith", 1945, "Wall St", "0123456789", "johndoe12@gmail.com", Candidate.CandidateType.Experience));

        candidates.add(new Fresher(1950, Fresher.GraduationRank.Excellence, "FPT University", 4, "Gwen", "Stacy", 2002, "Wall St", "0123456789", "top9wen@gmail.com", Candidate.CandidateType.Fresher));
        candidates.add(new Fresher(2002, Fresher.GraduationRank.Fair, "FPT PolyTechnic", 5, "Mary", "Jane", 1975, "Wall St", "0123456789", "therealMJ12@gmail.com", Candidate.CandidateType.Fresher));
        candidates.add(new Fresher(2024, Fresher.GraduationRank.Good, "PTIT", 6, "Emma", "Stone", 1984, "Wall St", "0123456789", "emmaW2@gmail.com", Candidate.CandidateType.Fresher));

        candidates.add(new Intern("SoftEn", 6, "FPT University", 7, "Donald", "Trump", 1984, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
        candidates.add(new Intern("GD", 3, "FPT University", 8, "Adolf", "Hitler", 1984, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
        candidates.add(new Intern("Japanology", 6, "HANU", 9, "Minh", "Dang", 2002, "Wall St", "0123456789", "pierced2nite@gmail.com", Candidate.CandidateType.Intern));
    }

    public void displayMenu() {
        System.out.println("");
        System.out.println("Number of candidates: " + candidates.size());
        System.out.println("CANDIDATE MANAGEMENT SYSTEM");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Internship");
        System.out.println("4. Searching");
        System.out.println("5. Exit");
    }

    public void createCandidate(int candidateType) {
        //list of newly created candidates during the creating session
        ArrayList<Candidate> newCandidates = new ArrayList<>();
        while (true) {
            //loop until id entered is unique
            int id;
            while (true) {
                id = validation.getInt("Enter Id: ");
                if (validation.checkIdExist(candidates, id)) {
                    System.err.println("Id already exist!");
                }else{
                    break;
                }
            }
            
            //input
            String firstName = validation.getString("Enter first name: ", validation.NAME_PATTERN, 
                    "Name cannot contain number and special characters!");
            String lastName = validation.getString("Enter last name: ", validation.NAME_PATTERN, 
                    "Name cannot contain number and special characters!");
            int birthDate = validation.getInt("Enter birth date (1900 - now): ", 1900, Calendar.getInstance().get(Calendar.YEAR));
            String address = validation.getString("Enter address: ");
            String phone = validation.getString("Enter phone number: ", validation.PHONE_PATTERN, 
                    "Phone number must start with a 0 and be 10 digit long!");
            String email = validation.getString("Enter email: ", 
                    validation.EMAIL_PATTERN, "Invalid email format!");
            //handle input for each type of candidate
            switch(candidateType){
                //experience
                case 0:
                    //exp cant exceed age
                    int expInYear = validation.getInt("Enter year of experience: ", 0, Calendar.getInstance().get(Calendar.YEAR)-birthDate);
                    String proSkill = validation.getString("Enter professional skill: ");
                    candidates.add(new Experience(expInYear, proSkill, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Experience));
                    newCandidates.add(new Experience(expInYear, proSkill, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Experience));
                    break;
                //fresher
                case 1:
                    //grad date cant exceed age
                    int graduationDate = validation.getInt("Enter graduation date: ", birthDate, Calendar.getInstance().get(Calendar.YEAR));
                    int graduationRank = validation.getInt("Enter graduation rank (1-Excellent, 2-Good, 3-Fair, 4-Poor): ", 0, 3);
                    String education = validation.getString("Enter education: ");
                    candidates.add(new Fresher(graduationDate, Fresher.GraduationRank.getRankByInt(graduationRank), education, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Fresher));
                    newCandidates.add(new Fresher(graduationDate, Fresher.GraduationRank.getRankByInt(graduationRank), education, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Fresher));
                    break;
                //intern
                case 2:
                    String major = validation.getString("Enter major: ");
                    int semester = validation.getInt("Enter semester: ", 1, Integer.MAX_VALUE);
                    String uniName = validation.getString("Enter university name: ");
                    candidates.add(new Intern(major, semester, uniName, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Intern));
                    newCandidates.add(new Intern(major, semester, uniName, id, firstName, lastName, birthDate, address, phone, email, Candidate.CandidateType.Intern));
                    break;
            }
            //Ask to continue or stop adding. If stop, print out list of all candidates just created
            if (!validation.checkInputYN("Do you want to continue (Y/N): ")) {
                displayCandidates(newCandidates, true);
                return;
            }
        }
    }

    public void searchCandidate() {
        //check list empty
        if (candidates.isEmpty()) {
            System.err.println("List is empty!");
        }

        //display list candidates
        displayCandidates(candidates, false);

        //request input from user
        String search = validation.getString("Input Candidate name (First name or Last name): ").toLowerCase();
        int candidateValue = validation.getInt("Input type of candidate: ", 0, 2);

        //conduct search
        boolean isFound = false;
        System.out.println("The candidates found: ");
        for (Candidate candidate : candidates) {
            if (candidate.getCandidateType().getValue() == candidateValue) {
                if (candidate.getFirstName().toLowerCase().contains(search)
                        || candidate.getLastName().toLowerCase().contains(search)) {
                    isFound = true;
                    System.out.println(candidate.toString());
                }
            }
        }
        if (!isFound) {
            System.out.println("No matching candidate found!");
        }
    }

    private void displayCandidates(ArrayList<Candidate> listCandidate, boolean addOrSearch) {
        ArrayList<Experience> listExperience = new ArrayList<>();
        ArrayList<Fresher> listFresher = new ArrayList<>();
        ArrayList<Intern> listIntern = new ArrayList<>();

        for (Candidate candidate : listCandidate) {
            if (candidate.getCandidateType() == Candidate.CandidateType.Experience) {
                listExperience.add((Experience) candidate);
            }
            if (candidate.getCandidateType() == Candidate.CandidateType.Fresher) {
                listFresher.add((Fresher) candidate);
            }
            if (candidate.getCandidateType() == Candidate.CandidateType.Intern) {
                listIntern.add((Intern) candidate);
            }
        }
        //print lists. True for created list, false for search list
        if(addOrSearch){
            System.out.println("CANDIDATES NEWLY ADDED: ");
        }else{
            System.out.println("List of candidate: ");
        }
        System.out.println("===========EXPERIENCE CANDIDATE============");
        for (Experience experience : listExperience) {
            System.out.println(experience.getFirstName() + " " + experience.getLastName());
        }
        System.out.println("==========FRESHER CANDIDATE==============");
        for (Fresher fresher : listFresher) {
            System.out.println(fresher.getFirstName() + " " + fresher.getLastName());
        }
        System.out.println("===========INTERN CANDIDATE==============");
        for (Intern intern : listIntern) {
            System.out.println(intern.getFirstName() + " " + intern.getLastName());
        }
    }

}
