package cloud.webflux;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/11 10:25
 */
public class NumberFunctions {


    private Integer number;

    private NumberFunctions() {

    }

    private static NumberFunctions numberFunctions = new NumberFunctions();

    public static NumberFunctions of(Integer number) {
        numberFunctions.number = number;
        return numberFunctions;
    }

    public NumberFunctions add(Integer number) {
        numberFunctions.number += number;
        return numberFunctions;
    }

    public NumberFunctions subtraction(Integer number) {
        numberFunctions.number -= number;
        return numberFunctions;
    }

    public Optional<NumberFunctions> filter(Predicate<Integer> predicate) {
        if (predicate.test(this.number)) return Optional.of(numberFunctions);
        return Optional.of(new NumberFunctions());

    }

    public void operate(OperateNumberFunctions functions) {
        functions.operate(this.number);
    }

}
