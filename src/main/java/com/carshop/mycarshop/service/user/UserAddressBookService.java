package com.carshop.mycarshop.service.user;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.domain.user.UserAddressBook;
import com.carshop.mycarshop.dto.user.UserAddressBookReqDTO;
import com.carshop.mycarshop.dto.user.UserAddressBookResDTO;

import java.util.List;

public interface UserAddressBookService {

    UserAddressBook getUserAddressBook(Long userAddressBookId);
    List<UserAddressBook> getListUserAddressBook(User user);
    UserAddressBookResDTO getUserAddressBookInfo(Long userAddressBookId);
    UserAddressBookResDTO getMainAddressInfo(User user);
    List<UserAddressBookResDTO> getAllUserAddressBookInfo(User user);
    void registerAddressBook(User user, UserAddressBookReqDTO userAddressBookReqDTO);
    void modifyAddressBook(User user, UserAddressBookReqDTO userAddressBookReqDTO);
    void deleteAddressBook(User user, Long userAddressBookId);

}