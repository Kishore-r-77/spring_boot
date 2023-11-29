package com.futura.Purple.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Permissions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long serviceId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceId", updatable = false, insertable = false)
    private Service service;

    private Long userGroupId;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "userGroupId", updatable = false, insertable = false)
//    private UserGroup userGroup;

    private Long userId;

    private String method;


}
