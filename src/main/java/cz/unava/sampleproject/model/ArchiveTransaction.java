package cz.unava.sampleproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.unava.sampleproject.utils.DateTimeFormatter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ArchiveTransaction {

    @JsonProperty
    public Long amount;

    @JsonProperty
    public Date startTransaction;

    @JsonProperty
    public Date endTransaction;

    @JsonProperty
    public String responseCode;

    @Override
    public String toString() {
        return String.format("ArchiveTransaction[amount=%s, startTransaction=%s, endTransaction=%s, responseCode=%s]",
                amount, DateTimeFormatter.formatDateTime(startTransaction), DateTimeFormatter.formatDateTime(endTransaction), responseCode);
    }

}
