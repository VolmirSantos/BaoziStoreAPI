package br.com.baozistore.controller;

import br.com.baozistore.model.Pedido;
import br.com.baozistore.repository.ClienteRepository;
import br.com.baozistore.repository.PedidoRepository;
import br.com.baozistore.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Pedido pedido) {
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            return ResponseEntity.badRequest().body("Cliente informado não existe.");
        }

        if (!produtoRepository.existsById(pedido.getProdutoId())) {
            return ResponseEntity.badRequest().body("Produto informado não existe.");
        }

        pedido.setId(null);
        Pedido salvo = pedidoRepository.save(pedido);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dados) {
        if (!clienteRepository.existsById(dados.getClienteId())) {
            return ResponseEntity.badRequest().body("Cliente informado não existe.");
        }

        if (!produtoRepository.existsById(dados.getProdutoId())) {
            return ResponseEntity.badRequest().body("Produto informado não existe.");
        }

        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setClienteId(dados.getClienteId());
                    pedido.setProdutoId(dados.getProdutoId());
                    pedido.setQuantidade(dados.getQuantidade());
                    return ResponseEntity.ok(pedidoRepository.save(pedido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
