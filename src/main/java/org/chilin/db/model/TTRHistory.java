package org.chilin.db.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityScan
@Table(name = "ttrhistory")
@ToString
@EqualsAndHashCode
@Data
public class TTRHistory {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ttr;

    private Integer qttr;

    private Timestamp fecha;

}
