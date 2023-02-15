package persist.controller;

import org.springframework.web.bind.annotation.*;
import persist.controller.dto.TransferRequestDto;
import persist.model.Account;
import persist.service.SpringDataTransferService;

/**
 * author : anger
 * date : 2022/7/26 08:34
 */
@RestController
@RequestMapping("/data")
public class SpringDataAccountController {

    private final SpringDataTransferService service;

    public SpringDataAccountController(SpringDataTransferService service) {
        this.service = service;
    }

    @GetMapping("/accounts")
    public Iterable<Account> findByName(
        @RequestParam(required = false) String name) {
        if (name == null ) return service.findAllAccounts();
        return service.findAccountByName(name);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto requestDto) {
        service.transfer(requestDto.getPayerId(),
            requestDto.getReceiverId(),
            requestDto.getAmount());
    }
}
