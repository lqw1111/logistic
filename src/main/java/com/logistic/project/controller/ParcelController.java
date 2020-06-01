package com.logistic.project.controller;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ParcelDTO createParcel(@RequestBody ParcelDTO parcelDTO) throws LogisticException {
        return parcelService.createParcel(parcelDTO);
    }

    @RequestMapping(value = "/delete/{parcelId}/{parcelUserOrderId}", method = RequestMethod.DELETE)
    public void deleteParcel(@PathVariable Integer parcelId, @PathVariable Integer parcelUserOrderId) throws LogisticException {
        parcelService.deleteParcel(parcelId, parcelUserOrderId);
    }

    @RequestMapping(value = "/moveout/{parcelId}/{parcelUserOrderId}", method = RequestMethod.PUT)
    public void moveOutParcelFromUserOrder(@PathVariable Integer parcelId, @PathVariable Integer parcelUserOrderId) throws LogisticException {
        parcelService.deleteParcelFromUserOrder(parcelId, parcelUserOrderId);
    }

    @RequestMapping(value = "/findAll/{userOrderId}", method = RequestMethod.GET)
    public List<ParcelDTO> getAllParcel(@PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return parcelService.findAllParcelByUserOrderId(userOrderId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ParcelDTO updateParcel(@RequestBody ParcelDTO parcelDTO) throws LogisticException {
        return parcelService.updateParcelInformation(parcelDTO);
    }

    @RequestMapping(value = "/move")
    public List<ParcelDTO> update(@RequestParam("newOrderId") Integer newOrderId,
                            @RequestParam("orginOrderId") Integer orginOrderId,
                            @RequestBody List<Integer> parcelIds) throws LogisticException {
        return parcelService.moveParcelForUserOrder(parcelIds, newOrderId, orginOrderId);
    }

}
