package com.saas.discipline.enums;

import lombok.Getter;

@Getter
public enum ResourceName {

    /* Resource */
    GET_ALL_RESOURCES("Get All Resources"),
    GET_RESOURCES_BY_ROLE_NAME("Get Resources by Role"),
    GET_RESOURCE_BY_ID("Get Resource Details"),
    GET_RESOURCE_BY_NAME("Get Resource by Name"),
    GRANT_RESOURCE_ACCESS_TO_ROLE("Grant Resource Access to Role"),
    REVOKE_RESOURCE_ACCESS_FROM_ROLE("Revoke Resource Access from Role"),

    /* Discipline Appeal */
    ADD_DISCIPLINE_APPEAL("Add Discipline Appeal"),
    GET_ALL_DISCIPLINE_APPEAL("Get All Discipline Appeal "),
    GET_DISCIPLINE_APPEAL_BY_ID("Get Discipline Appeal Details"),
    UPDATE_DISCIPLINE_APPEAL("Update Discipline Appeal "),
    DELETE_DISCIPLINE_APPEAL("Delete Discipline Appeal "),

    /* Discipline  Offense*/
    ADD_DISCIPLINE_OFFENSE("Add Discipline Offense"),
    GET_ALL_DISCIPLINE_OFFENSE("Get All Discipline Offense"),
    GET_DISCIPLINE_OFFENSE_BY_ID("Get Discipline Offense Details"),
    UPDATE_DISCIPLINE_OFFENSE("Update Discipline Offense"),
    DELETE_DISCIPLINE_OFFENSE("Delete Discipline Offense"),

    /* Discipline Penalty */
    ADD_DISCIPLINE_PENALTY("Add Discipline Penalty"),
    GET_ALL_DISCIPLINE_PENALTY("Get All Discipline Penalty"),
    GET_DISCIPLINE_PENALTY_BY_ID("Get Discipline Penalty Details"),
    UPDATE_DISCIPLINE_PENALTY("Update Discipline Penalty"),
    DELETE_DISCIPLINE_PENALTY("Delete Discipline Penalty"),

    /* Discipline  */
    ADD_DISCIPLINE("Add Discipline "),
    GET_ALL_DISCIPLINE("Get All Discipline "),
    GET_DISCIPLINE_BY_ID("Get Discipline  Details"),
    UPDATE_DISCIPLINE("Update Discipline "),
    DELETE_DISCIPLINE("Delete Discipline "),
    APPROVE_DISCIPLINE("Approve Discipline ");

    private final String value;

    ResourceName(String value) {
        this.value = value;
    }
}
