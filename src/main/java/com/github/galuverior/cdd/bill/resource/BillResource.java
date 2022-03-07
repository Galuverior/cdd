package com.github.galuverior.cdd.bill.resource;

import com.github.galuverior.cdd.bill.dto.BillDTO;
import com.github.galuverior.cdd.bill.entity.Bill;
import com.github.galuverior.cdd.bill.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("conta")
@CrossOrigin
public class BillResource {

    private final BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> billById(@PathVariable Long id) {

        Optional<Bill> optionalBill = billService.getBillById(id);

        return optionalBill.isPresent() ? ResponseEntity.ok().body(optionalBill.get()) :
                ResponseEntity.notFound().build();

    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Bill> billByClientId(@PathVariable Long id) {

        Optional<Bill> optionalBill = billService.getBillByClient(id);

        return optionalBill.isPresent() ? ResponseEntity.ok().body(optionalBill.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Bill> registerBill(@RequestBody BillDTO billDTO) {

        Bill bill = billService.registerBill(billDTO);

        return (Objects.nonNull(bill)) ? ResponseEntity.ok().body(bill) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody BillDTO billDTO) {

        Bill bill = billService.updateBill(id, billDTO);

        return (Objects.nonNull(bill)) ? ResponseEntity.ok().body(bill) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Bill> deleteBill(@PathVariable Long id) {

        try {
            billService.deleteBill(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
