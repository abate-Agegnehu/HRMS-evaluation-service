package com.saas.evaluation.enums;

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

    /* Evaluation Criteria */
    ADD_EVALUATION_CRITERIA("Add Evaluation Criteria"),
    GET_ALL_EVALUATION_CRITERIA("Get All Evaluation Criteria "),
    GET_EVALUATION_CRITERIA_BY_ID("Get Evaluation Criteria Details"),
    UPDATE_EVALUATION_CRITERIA("Update Evaluation Criteria "),
    DELETE_EVALUATION_CRITERIA("Delete Evaluation Criteria "),

    /* Evaluation Category */
    ADD_EVALUATION_CATEGORY("Add Category Criteria"),
    GET_ALL_EVALUATION_CATEGORY("Get All Category Criteria"),
    GET_EVALUATION_CATEGORY_BY_ID("Get Category Criteria Details"),
    UPDATE_EVALUATION_CATEGORY("Update Category Criteria"),
    DELETE_EVALUATION_CATEGORY("Delete Category Criteria"),

    /* Evaluation  Session*/
    ADD_EVALUATION_SESSION("Add Evaluation Session"),
    GET_ALL_EVALUATION_SESSION("Get All Evaluation Session"),
    GET_EVALUATION_SESSION_BY_ID("Get Evaluation Session Details"),
    UPDATE_EVALUATION_SESSION("Update Evaluation Session"),
    DELETE_EVALUATION_SESSION("Delete Evaluation Session"),

    /* Evaluation Result*/
    ADD_EVALUATION_RESULT("Add Evaluation  Result"),
    GET_ALL_EVALUATION_RESULT("Get All Evaluation  Result"),
    GET_EVALUATION_RESULT_BY_ID("Get Evaluation Result Details"),
    UPDATE_EVALUATION_RESULT("Update Evaluation Result"),
    DELETE_EVALUATION_RESULT("Delete Evaluation Result"),

    /* Evaluation Level */
    ADD_EVALUATION_LEVEL("Add Evaluation Level"),
    GET_ALL_EVALUATION_LEVEL("Get All Evaluation Level"),
    GET_EVALUATION_LEVEL_BY_ID("Get Evaluation Level Details"),
    UPDATE_EVALUATION_LEVEL("Update Evaluation Level"),
    DELETE_EVALUATION_LEVEL("Delete Evaluation Level");

    private final String value;

    private ResourceName(String value) {
        this.value = value;
    }
}
