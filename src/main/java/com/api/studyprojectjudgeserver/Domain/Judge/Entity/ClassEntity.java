package com.api.studyprojectjudgeserver.Domain.Judge.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * CREATE TABLE `Class` (
 *   `ID` int(11) NOT NULL AUTO_INCREMENT,
 *   `className` varchar(100) NOT NULL,
 *   `subjectID` int(11) NOT NULL,
 *   `public` tinyint(1) DEFAULT '0',
 *   `available` tinyint(1) DEFAULT '1',
 *   `show_up` int(10) NOT NULL DEFAULT '1',
 *   PRIMARY KEY (`ID`),
 *   KEY `subjectID` (`subjectID`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
 */
@Entity
@Getter
@Setter
@Table(name="class")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classId")
    private int class_id;

    @Column(name = "className")
    private String className;

    @Column(name="subjectId")
    private String subjectId;

    @Column(name = "public")
    private boolean isPublic;

    @Column(name = "available")
    private boolean available;

    @Column(name = "show_up")
    private int show_up;
}
