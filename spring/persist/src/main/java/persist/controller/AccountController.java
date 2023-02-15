package persist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import persist.controller.dto.TransferRequestDto;
import persist.model.Account;
import persist.service.TransferService;

import java.util.List;

/**
 * author : anger
 * date : 2022/7/25 18:41
 */
@Slf4j
@RestController
public class AccountController {
    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return transferService.findAllAccounts();
    }

    @PostMapping("/transfer")
    public void transfer(
        @RequestBody TransferRequestDto transferRequestDto) {
        log.info("收到转账请求，请求参数：{}", transferRequestDto);
        transferService.transfer(
            transferRequestDto.getPayerId(),
            transferRequestDto.getReceiverId(),
            transferRequestDto.getAmount());
    }
}
