package com.github.galuverior.cdd.bill.service;

import com.github.galuverior.cdd.bill.dto.BillDTO;
import com.github.galuverior.cdd.bill.entity.Bill;
import com.github.galuverior.cdd.bill.repository.BillRepository;
import com.github.galuverior.cdd.bill.repository.CategoryRepository;
import com.github.galuverior.cdd.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Optional<Bill> getBillByClient(Long clientId) {
        return billRepository.getBillByClient(clientId);
    }

    public BillDTO getBillDTO(Bill bill) {

        BillDTO billDTO = new BillDTO();

        billDTO.setId(bill.getId());
        billDTO.setDescricao(bill.getDescricao());
        billDTO.setCompetencia(bill.getCompetencia());
        billDTO.setVencimento(bill.getVencimento());
        billDTO.setValor(bill.getValor());
        billDTO.setCategoria(bill.getCategoria());
        billDTO.setSituacao(bill.getSituacao());
        billDTO.setCliente(bill.getCliente());

        return billDTO;
    }

    public Bill registerBill(BillDTO billDTO) {

        Bill bill = new Bill();

//        if() {
            bill.setId(billDTO.getId());
            bill.setDescricao(billDTO.getDescricao());
            bill.setCompetencia(billDTO.getCompetencia());
            bill.setVencimento(billDTO.getVencimento());
            bill.setValor(billDTO.getValor());
            bill.setCategoria(categoryRepository.findById(billDTO.getCategoriaId()).orElse(null));
            bill.setSituacao(billDTO.getSituacao());
            bill.setCliente(clientRepository.findById(billDTO.getClienteId()).orElse(null));

            billRepository.save(bill);
//        } else {
//            bill = null;
//        }
        return bill;
    }

    public Bill updateBill(Long id, BillDTO billDTO) {

        Bill bill = getBillById(id).orElse(null);

        bill.setVencimento(Objects.nonNull(billDTO.getVencimento()) ? billDTO.getVencimento() : bill.getVencimento());
        bill.setValor(Objects.nonNull(billDTO.getValor()) ? billDTO.getValor() : bill.getValor());
        bill.setSituacao(Objects.nonNull(billDTO.getSituacao()) ? billDTO.getSituacao() : bill.getSituacao());

        billRepository.save(bill);
        return bill;
    }

    public void deleteBill(Long id) throws Exception {

        Bill bill = getBillById(id).orElse(null);

        billRepository.delete(bill);
    }
}
