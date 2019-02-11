package ru.damir.server.user_storing.service.mapping;

import org.springframework.stereotype.Component;
import ru.damir.server.user_storing.controllers.request.UserRequest;
import ru.damir.server.user_storing.controllers.response.UserResponse;
import ru.damir.server.user_storing.dao.entities.User;

import java.util.stream.Collectors;

@Component
public class UserDtoMapper {
    public User toModel(UserRequest user) {
        return new User(user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getEmail());
    }

    public UserResponse fromModel(User user) {
        return new UserResponse(
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getAccounts()
                        .stream()
                        .map(account -> String.valueOf(account.getNumber()))
                        .collect(Collectors.joining(", ")));
    }
}
