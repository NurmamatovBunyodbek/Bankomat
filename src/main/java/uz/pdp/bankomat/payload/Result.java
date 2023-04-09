package uz.pdp.bankomat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String message;
    private boolean success;

    private Object object;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}
