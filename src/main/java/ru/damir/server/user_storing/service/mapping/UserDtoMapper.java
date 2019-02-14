package ru.damir.server.user_storing.service.mapping;

import org.springframework.stereotype.Component;
import ru.damir.server.user_storing.controllers.response.UserResponse;
import ru.damir.server.user_storing.dao.entities.Account;
import ru.damir.server.user_storing.dao.entities.User;

import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public UserResponse fromModel(User user) {
        if (user == null) {
            return new UserResponse();
        }
        return new UserResponse(
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getAccounts()
                        .stream()
                        .map(Account::getNumber)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")));
    }
}
