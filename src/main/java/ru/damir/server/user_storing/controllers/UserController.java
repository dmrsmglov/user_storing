package ru.damir.server.user_storing.controllers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.damir.server.user_storing.controllers.request.FindUser;
import ru.damir.server.user_storing.controllers.request.Pagination;
import ru.damir.server.user_storing.controllers.response.UserResponse;
import ru.damir.server.user_storing.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/find")
    public UserResponse getUser(@Valid @RequestBody FindUser findUser) {
        UserResponse response;
        if (findUser.getEmail() == null) {
            response = userService.getUser(findUser.getAccountNumber());
        } else {
            response = userService.getUser(findUser.getEmail(), findUser.getAccountNumber());
        }
        return response;
    }

    @PostMapping(value = "/all")
    public List<UserResponse> getAllUsers(@Valid @RequestBody Pagination pagination) {
        return  userService.getUsers(pagination.getLimit(), pagination.getOffset());
    }

    @PostMapping(value = "/all/csv", produces = "text/csv")
    public void getAllUsersInCSV(HttpServletResponse httpServletResponse) {
        List<UserResponse> userResponses = userService.getUsers();

        try {
            ColumnPositionMappingStrategy mapStrategy
                    = new ColumnPositionMappingStrategy();

            mapStrategy.setType(UserResponse.class);
            mapStrategy.generateHeader();

            String[] columns = new String[]{"firstName", "middleName",
                    "lastName", "email", "accounts"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(httpServletResponse.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();
            btcsv.write(userResponses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
