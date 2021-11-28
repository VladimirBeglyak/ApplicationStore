package by.beglyakdehterenok.store.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDto {
    private Long id;
    private String login;
    private Double accountAmount;
}
