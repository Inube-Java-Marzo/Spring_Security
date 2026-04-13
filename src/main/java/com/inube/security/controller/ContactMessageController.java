package com.inube.security.controller;

import com.inube.security.dto.RequestMessangeDto;
import com.inube.security.dto.ResponsesDto;
import com.inube.security.service.IContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.inube.security.util.Util.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final IContactMessageService service;

    private ResponsesDto buildResponse(Boolean success, String mensaje, Object data) {
        ResponsesDto res = new ResponsesDto();
        res.setSuccess(success);
        res.setMensaje(mensaje);
        res.setData(data);
        return res;
    }


    // 1. Crear mensaje
    @PostMapping("/create")
    public ResponsesDto create(@RequestBody RequestMessangeDto dto) {

        service.create(dto);

        return buildResponse(OKSUCCESS, OKQUERY, null);
    }

    // 2. Marcar como leído
    @PostMapping("/read")
    public ResponsesDto markAsRead(@RequestBody RequestMessangeDto dto) {

        service.markAsRead(dto);

        return buildResponse(OKSUCCESS, OKQUERY, null);
    }

    // 3. Marcar como contestado
    @PostMapping("/answered")
    public ResponsesDto markAsAnswered(@RequestBody RequestMessangeDto dto) {

        service.markAsAnswered(dto);

        return buildResponse(OKSUCCESS, OKQUERY, null);
    }

    // 4. Borrado lógico
    @DeleteMapping("/{id}")
    public ResponsesDto delete(@PathVariable String id) {

        service.deleteLogical(id);

        return buildResponse(OKSUCCESS, REGDISABLE, null);
    }

    // 5. Todos
    @GetMapping
    public ResponsesDto getAll() {

        return buildResponse(OKSUCCESS, OKFOUND, service.getAll());
    }

    // 6. Leídos
    @GetMapping("/read")
    public ResponsesDto getRead() {

        return buildResponse(OKSUCCESS, OKFOUND, service.getRead());
    }

    // 7. Contestados
    @GetMapping("/answered")
    public ResponsesDto getAnswered() {

        return buildResponse(OKSUCCESS, OKFOUND, service.getAnswered());
    }

}
