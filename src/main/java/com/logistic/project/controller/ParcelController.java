package com.logistic.project.controller;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parcel")
public class ParcelController extends BaseController {

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ParcelDTO createParcel(@RequestBody ParcelDTO parcelDTO) throws LogisticException {
        return parcelService.createParcel(parcelDTO);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/delete/parcel/{parcelId}/userorder/{parcelUserOrderId}", method = RequestMethod.DELETE)
    public void deleteParcel(@PathVariable Integer parcelId, @PathVariable Integer parcelUserOrderId) throws LogisticException {
        parcelService.deleteParcel(parcelId, parcelUserOrderId);
    }

    @RequestMapping(value = "/moveout/parcel/{parcelIds}/userorder/{parcelUserOrderId}", method = RequestMethod.PUT)
    public List<ParcelDTO> moveOutParcelFromUserOrder(@PathVariable("parcelIds") List<Integer> parcelIds, @PathVariable("parcelUserOrderId") Integer parcelUserOrderId) throws LogisticException {
        return parcelService.deleteParcelFromUserOrder(parcelIds, parcelUserOrderId);
    }

    //find by id
    @RequestMapping(value = "/find/{parcelId}", method = RequestMethod.GET)
    public ParcelDTO findById(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.findById(parcelId, getPrincipal());
    }

    @RequestMapping(value = "/findAll/{userOrderId}", method = RequestMethod.GET)
    public List<ParcelDTO> getAllParcel(@PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return parcelService.findAllParcelByUserOrderId(userOrderId, getPrincipal());
    }

    @RequestMapping(value = "/findAll/userid/{userId}", method = RequestMethod.GET)
    public List<ParcelDTO> getAllParcelByUserId(@PathVariable("userId") Integer userId) throws LogisticException {
        return parcelService.findAllParcelByUserId(userId, getPrincipal());
    }

    @RequestMapping(value = "/findAll/username/{userName}", method = RequestMethod.GET)
    public List<ParcelDTO> getAllParcelByUserId(@PathVariable("userName") String userName) throws LogisticException {
        return parcelService.findAllParcelByUserName(userName);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ParcelDTO updateParcel(@RequestBody ParcelDTO parcelDTO) throws LogisticException {
        return parcelService.updateParcelInformation(parcelDTO, getPrincipal());
    }

    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public List<ParcelDTO> update(@RequestParam("newOrderId") Integer newOrderId,
                            @RequestParam("orginOrderId") Integer orginOrderId,
                            @RequestBody List<Integer> parcelIds) throws LogisticException {
        return parcelService.moveParcelForUserOrder(parcelIds, newOrderId, orginOrderId);
    }

    @RequestMapping(value = "/{parcelId}/waiting", method = RequestMethod.PUT)
    public ParcelDTO updateParcelStatusToWaiting(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.updateParcelToWaiting(parcelId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/{parcelId}/problem", method = RequestMethod.PUT)
    public ParcelDTO updateParcelStatusToProblem(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.updateParcelToProblem(parcelId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/{parcelId}/verify", method = RequestMethod.PUT)
    public ParcelDTO updateParcelStatusToVerify(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.updateParcelToVerify(parcelId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/{parcelId}/shipping", method = RequestMethod.PUT)
    public ParcelDTO updateParcelStatusToShipping(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.updateParcelToShipping(parcelId);
    }

    @RequestMapping(value = "/{parcelId}/finish", method = RequestMethod.PUT)
    public ParcelDTO updateParcelStatusToFinish(@PathVariable("parcelId") Integer parcelId) throws LogisticException {
        return parcelService.updateParcelToFinish(parcelId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ParcelDTO> findAll() throws LogisticException {
        return parcelService.findAll();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/waiting", method = RequestMethod.GET)
    public List<ParcelDTO> findAllWaiting() throws LogisticException {
        return parcelService.findAllWaiting();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/problem", method = RequestMethod.GET)
    public List<ParcelDTO> findAllProblem() throws LogisticException {
        return parcelService.findAllProblem();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/verify", method = RequestMethod.GET)
    public List<ParcelDTO> findAllVerify() throws LogisticException {
        return parcelService.findAllVerify();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/shipping", method = RequestMethod.GET)
    public List<ParcelDTO> findAllShipping() throws LogisticException {
        return parcelService.findAllShipping();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/finish", method = RequestMethod.GET)
    public List<ParcelDTO> findAllFinish() throws LogisticException {
        return parcelService.findAllFinish();
    }

    @RequestMapping(value = "/statistics/user/{userId}", method = RequestMethod.GET)
    public Map<String, Integer> statisticsData(@PathVariable("userId") Integer userId) throws LogisticException {
        return parcelService.statisticsData(userId);
    }
}
