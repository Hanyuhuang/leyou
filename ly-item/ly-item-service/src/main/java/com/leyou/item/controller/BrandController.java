package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page,rows,sortBy,desc, key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Integer> deleteBrandByPrimaryKey(@PathVariable("id") Long id){
        int result = brandService.deleteBrandByPrimaryKey(id);
        if (result<1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(null);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Brand> queryBrandByPrimaryKey(@PathVariable("id") Long id){
        Brand brand = brandService.queryBrandByPrimaryKey(id);
        if (brand==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(brand);
    }

    @PutMapping()
    public ResponseEntity<Integer> updateBrand(Brand brand){
        int result = brandService.updateBrand(brand);
        if (result<1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<Integer> addBrand(Brand brand){
        int result = brandService.insertBrand(brand);
        if (result<1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(null);
    }
}
