package pl.devfoundry.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {

        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));

    }

    @Test
    void getNoActiveAccounts() {

        //given

        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Arrays.asList());

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(0));

    }

    private List<Account> prepareAccountData(){
        Address address1 = new Address("Kwiatowa", "33/5");
        Account account1 = new Account(address1);
        account1.isActive();

        Account account2 = new Account();

        Address address2 = new Address("Piekarska", "12b");
        Account account3 = new Account(address2);
        account3.isActive();

        return Arrays.asList(account1, account2, account3);
    }

    @Test
    void getAccountsByName(){


            //given

            AccountRepository accountRepository = mock(AccountRepository.class);
            AccountService accountService = new AccountService(accountRepository);
            given(accountRepository.getByName("Dawid")).willReturn(Collections.singletonList("Nowak"));

            //when
            List<String> accountNames = accountService.findByName("Dawid");

            //then
            assertThat(accountNames, contains("Nowak"));


    }
}
