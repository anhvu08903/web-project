package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Admin;
import com.example.projectwebbackend.entity.Coach;

public class AdminCoach {
    private Admin admin;
    private Coach coach;

    public AdminCoach() {
    }

    public AdminCoach(Admin admin, Coach coach) {
        this.admin = admin;
        this.coach = coach;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
