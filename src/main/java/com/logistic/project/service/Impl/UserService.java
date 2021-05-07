package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserInfoRepository usersRepository;

    @Transactional(readOnly = true)
    public void writeToOutputStream(final OutputStream outputStream) {
        try (Stream<UserInfo> usersResultStream = usersRepository.findAllByCustomQueryAndStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {

                usersResultStream.forEach(emp -> {
                    try {
                        oos.write(emp.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
