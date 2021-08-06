package com.valdir.poolart.resources;

import com.valdir.poolart.domain.Artist;
import com.valdir.poolart.domain.dto.ArtistDTO;
import com.valdir.poolart.services.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/artists")
public class ArtistResource {

    @Autowired
    private ArtistService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDTO> findById(@PathVariable Integer id) {
        Artist obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.map(obj, ArtistDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> findAll() {
        List<Artist> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> mapper.map(obj, ArtistDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> create(@Valid @RequestBody ArtistDTO obj) {
        obj = mapper.map(service.create(obj), ArtistDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
