package com.adzhiasan.info.servingwebcontent;

import com.adzhiasan.info.models.Student;
import com.adzhiasan.info.models.StudyingGroup;
import com.adzhiasan.info.repo.StudentRepository;
import com.adzhiasan.info.repo.StudyingGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudyingGroupRepository groupRepository;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/group/observe")
    public String observeGroups(Model model) {
        Iterable<StudyingGroup> studyingGroups = groupRepository.findAll();
        model.addAttribute("studyingGroups", studyingGroups);
        return "group-observe";
    }

    @GetMapping("/group/add")
    public String groupAdd(Model model) {
        Iterable<StudyingGroup> studyingGroups = groupRepository.findAll();
        model.addAttribute("studyingGroups", studyingGroups);
        return "group-add";
    }

    @PostMapping("/group/add")
    public String groupItemAdd(@RequestParam int num,
                               @RequestParam String curator,
                               @RequestParam String fieldOfStudy,
                               Model model){
        StudyingGroup studyingGroup = new StudyingGroup(num, curator, fieldOfStudy);
        groupRepository.save(studyingGroup);
        return "redirect:/";
    }

    @GetMapping("/group/{id}/edit")
    public String editChosenGroup(@PathVariable(value = "id") Long id,
                                      Model model) {
        if(!groupRepository.existsById(id))
            return "redirect:/group/observe";
        Optional<StudyingGroup> sGroup = groupRepository.findById(id);
        ArrayList<StudyingGroup> res = new ArrayList<>();
        sGroup.ifPresent(res::add);
        model.addAttribute("group", res);
        return "group-edit";
    }

    @PostMapping("/group/{id}/edit")
    public String groupItemEdit(@PathVariable(value = "id") Long id,
                                @RequestParam int num,
                               @RequestParam String curator,
                               @RequestParam String fieldOfStudy,
                               Model model){
        StudyingGroup sGroup = groupRepository.findById(id).orElseThrow();
        sGroup.setNum(num);
        sGroup.setCurator(curator);
        sGroup.setFieldOfStudy(fieldOfStudy);

        groupRepository.save(sGroup);

        return "redirect:/group/observe";
    }

    @GetMapping("/student/observe")
    public String observeStudents(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student-observe";
    }

    @GetMapping("/student/observe/{num}")
    public String observeChosenStudents(@PathVariable(value = "num") int num,
                                        Model model) {
        int numn = (int)num;
        StudyingGroup group = groupRepository.findGroupByNum(num);
        Iterable<Student> students = studentRepository.findByGroup(group);
        model.addAttribute("students", students);
        return "student-observe";
    }



    @GetMapping("/student/add")
    public String studentAdd(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student-add";
    }

    @PostMapping("/student/add")
    public String studentItemAdd(@RequestParam String surname,
                                 @RequestParam String name,
                                 @RequestParam String fatherName,
//                                 @RequestParam LocalDateTime birthDate,
                                 @RequestParam String gender,
                                 @RequestParam int entryYear,
                                 @RequestParam int studyingGroupNum,
                                 Model model){
        StudyingGroup studyingGroup = groupRepository.findGroupByNum(studyingGroupNum);
        Student student = new Student(surname,name,fatherName,gender,entryYear,studyingGroup);
        studentRepository.save(student);
        return "redirect:/student/observe";
    }

    @GetMapping("/student/{id}/edit")
    public String editChosenStudent(@PathVariable(value = "id") Long id,
                                  Model model) {
        if(!studentRepository.existsById(id))
            return "redirect:/student/observe";
        Optional<Student> student = studentRepository.findById(id);
        ArrayList<Student> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        return "student-edit";
    }

    @PostMapping("/student/{id}/edit")
    public String studentItemEdit(@PathVariable(value = "id") Long id,
                                  @RequestParam String surname,
                                  @RequestParam String name,
                                  @RequestParam String fatherName,
                                  @RequestParam String gender,
                                  @RequestParam int entryYear,
                                  @RequestParam int studyingGroupNum,
                                Model model){
        Student student = studentRepository.findById(id).orElseThrow();
        StudyingGroup studyingGroup = groupRepository.findGroupByNum(studyingGroupNum);
        student.setSurname(surname);
        student.setName(name);
        student.setFatherName(fatherName);
        student.setGender(gender);
        student.setEntryYear(entryYear);
        student.setStudyingGroup(studyingGroup);

        studentRepository.save(student);

        return "redirect:/student/observe";
    }

    @PostMapping("/student/{id}/remove")
    public String studentItemRemove(@PathVariable(value = "id") Long id,
                                  Model model){
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);

        return "redirect:/student/observe";
    }


//        @PostMapping("/group/{id}/remove")
//    public String groupItemRemove(@PathVariable(value = "id") Long id,
//                                Model model){
//        StudyingGroup sGroup = groupRepository.findById(id).orElseThrow();
//        groupRepository.delete(sGroup);
//
//        return "redirect:/group/observe";
//    }

//    @GetMapping("/student/observe/{num}")
//    public String observeChosenGroup(@PathVariable(value="num") int num,
//                                     Model model) {
////        Iterable<StudyingGroup> studyingGroups = groupRepository.findAll();
//        StudyingGroup studyingGroups = groupRepository.findGroupByNum(num);
//        Optional<Student> students = studentRepository.
//        model.addAttribute("studyingGroups", studyingGroups);
//        return "group-observe";
//    }
}
