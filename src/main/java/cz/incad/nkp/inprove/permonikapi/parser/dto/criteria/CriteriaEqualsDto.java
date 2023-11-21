package cz.incad.nkp.inprove.permonikapi.parser.dto.criteria;

import cz.incad.nkp.inprove.permonikapi.parser.dto.criteria.base.CriteriaBaseDto;
import lombok.*;
import org.springframework.data.solr.core.query.Criteria;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaEqualsDto extends CriteriaBaseDto {

    private String field;

    private EqualsOperation operation;

    private Object value;

    @Override
    public Criteria visit(Criteria criteria) {
        return join(criteria, field).is(value);
    }

    public enum EqualsOperation {
        EQ
    }
}