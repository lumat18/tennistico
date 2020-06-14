package com.gruzini.tennistico.models.score;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
@Builder
@Validated
public class Score {
    private Set set1;
    private Set set2;
    private Set set3;
    private Set set4;
    private Set set5;

    @AssertTrue
    public boolean isScoreValid() {
        if (nonNull(set1) && isNull(set2) && isNull(set3) && isNull(set4) && isNull(set5)) {
            return true;
        }
        if (nonNull(set1) && nonNull(set2) && isNull(set3) && isNull(set4) && isNull(set5)) {
            return true;
        }
        if (nonNull(set1) && nonNull(set2) && nonNull(set3) && isNull(set4) && isNull(set5)) {
            return true;
        }
        if (nonNull(set1) && nonNull(set2) && nonNull(set3) && nonNull(set4) && isNull(set5)) {
            return true;
        }
        return nonNull(set1) && nonNull(set2) && nonNull(set3) && nonNull(set4);
    }
}
