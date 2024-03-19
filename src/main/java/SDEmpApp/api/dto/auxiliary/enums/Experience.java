package SDEmpApp.api.dto.auxiliary.enums;

import lombok.Getter;

@Getter
public enum Experience {
    _0("0"),
    _0_1("0 >= 1"),
    MIN_1("1 > 2"),
    MIN_2("2 > 5"),
    MIN_5("5 > "),
    ;
    private final String experience;
    Experience(String experience) {
        this.experience = experience;
    }

}
