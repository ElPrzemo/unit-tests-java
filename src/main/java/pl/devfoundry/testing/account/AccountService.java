package pl.devfoundry.testing.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = accountRepository;
    }

    public AccountService(AccountRepository accountRepositoryStub) {
    }



    List<Account> getAllActiveAccounts(){
        return accountRepository.getAllAcounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }
}
