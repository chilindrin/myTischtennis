package org.chilin.db.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ttrhistory")
@ToString
@EqualsAndHashCode
public class TTRHistory {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer ttr;

    private Integer qttr;

    private Timestamp fecha;

}
