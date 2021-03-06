package org.chilin.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode
public class TTRHistoryVO {

    private Integer ttr;

    private Integer qttr;

    @EqualsAndHashCode.Exclude
    private Timestamp fecha;

}
