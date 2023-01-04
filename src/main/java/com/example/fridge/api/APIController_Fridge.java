package com.example.fridge.api;

import ReadFromFile.ReadFromFile;
import com.example.fridge.controllers.FridgeService;
import com.example.fridge.controllers.ManufacturersService;
import com.example.fridge.controllers.ProductsService;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class APIController_Fridge {

    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private ProductsService productsService;
    @Autowired
    private ManufacturersService manufacturersService;

    @GetMapping("/items")
    public List<FridgeEntity> getAllItems() {
        return fridgeService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public FridgeEntity getItemById(@PathVariable("id") Integer id) {
        return fridgeService.getItemById(id);
    }

    @GetMapping("/mainTable/{cat}")
    public List<FridgeEntity> getMainTable(@PathVariable("cat") String cat) {
        return fridgeService.getMainTable(cat);
    }

    @GetMapping("/mainTable")
    public List<FridgeEntity> getMainTable() {
        return fridgeService.getMainTable2();
    }

    @GetMapping("/desiredproducts/{product}")
    public List<FridgeEntity> getDesProduct(@PathVariable("product") String product) {
        return fridgeService.getDesProduct(product);
    }

    @DeleteMapping("/items/{id}")
    public void deleteProduct(@PathVariable("id") Integer id){
        fridgeService.deleteProduct(id);
    }

    @PutMapping("/items/{id}")
    public void updatePcs(@PathVariable("id") Integer id, @RequestBody FridgeEntity item){
        fridgeService.updatePcs(id, item);
    }

    @PostMapping("/upload")
    public ResponseEntity submit(@RequestParam("file")MultipartFile file){

        Map<String, Integer> barcodes = ReadFromFile.parseFile(file);

        for(Map.Entry<String, Integer> key : barcodes.entrySet()) {
            FridgeEntity entity = ReadFromFile.entityFromBarcode(key.getKey(), key.getValue().intValue(), productsService, manufacturersService,fridgeService);
            if(entity == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fridgeService.addEntry(entity);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/uploadSingle")
    public ResponseEntity submitSingle(@RequestParam("barcode") String barcode){
        FridgeEntity entity = ReadFromFile.entityFromBarcode(barcode, 1, productsService, manufacturersService, fridgeService);
        if(entity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        fridgeService.addEntry(entity);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
