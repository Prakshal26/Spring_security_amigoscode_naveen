package com.spring_security_navenn.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.spring_security_navenn.demo.security.ApplicationUserPermission.*;

/*
We have created three kinds of user one is STUDENT, ADMIN and ADMINTRAINEE.
For user we do not want to give any permisiion that's why only empty hashset.
For Admin we have given 4: permission and to trainess 2 permission.
 */
public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, ApplicationUserPermission.COURSE_WRITE, STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}