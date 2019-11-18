package com.course.phonedirectory.service;

import com.course.phonedirectory.exception.ServiceException;
import com.course.phonedirectory.model.MobileOperator;
import com.course.phonedirectory.model.UserAccount;
import com.course.phonedirectory.rep.PhoneNumberRepository;
import com.course.phonedirectory.rep.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserAccountService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional(rollbackFor = ServiceException.class)
    public void changeMobileOperator(int userAccountId, int phoneNumberId, String newNumber, String companyName) throws ServiceException {
        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(() -> new ServiceException("No such user"));
        Integer cash = userAccount.getCash();
        String substring = newNumber.substring(0, 3);
        MobileOperator number = Arrays.stream(MobileOperator.values())
                .filter(x -> x.getNumberPrefix().equals(substring))
                .findAny().orElse(MobileOperator.OTHER);
        if (cash < number.getOperatorCost()) {
            throw new ServiceException("Rollback occurred. Not enough cash to go to this tariff\n");
        } else {
            phoneNumberRepository.updateNumberById(companyName, newNumber, phoneNumberId);
            userAccount.setCash(cash - number.getOperatorCost());
        }
    }


}
