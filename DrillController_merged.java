package org.bisag.ocbis.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bisag.ocbis.payloads.request.CasingPlanStore;
import org.bisag.ocbis.models.CasingDetails;
import org.bisag.ocbis.models.DailyProgressDowntimeReason;
import org.bisag.ocbis.models.DailyProgressFormation;
import org.bisag.ocbis.models.DrillingUnitDeployment;
import org.bisag.ocbis.models.EngineDetails;
import org.bisag.ocbis.models.PumpDetails;
import org.bisag.ocbis.models.PumpEngineDetails;
import org.bisag.ocbis.payloads.request.EncryptedRequest;
import org.bisag.ocbis.payloads.request.GetId;
import org.bisag.ocbis.payloads.request.MonthlyFormData;
import org.bisag.ocbis.payloads.request.RegionName;
import org.bisag.ocbis.payloads.request.Report;
import org.bisag.ocbis.payloads.response.EncryptedResponse;
import org.bisag.ocbis.repository.CasingDetailsRepo;
import org.bisag.ocbis.repository.DailyProgressDowntimeReasonRepository;
import org.bisag.ocbis.repository.DailyProgressFormationRepository;
import org.bisag.ocbis.repository.DrillRepo;
import org.bisag.ocbis.repository.DrillingUnitDeploymentRepository;
import org.bisag.ocbis.repository.EngineDetailsRepository;
import org.bisag.ocbis.repository.PumpDetailsRepository;
import org.bisag.ocbis.repository.PumpEngineDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Transient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/drill")
public class DrillController {

  @Autowired
  DrillingUnitDeploymentRepository drillRepo;
 @Autowired
  CasingDetailsRepo casingdetailsrepo;
  // @Autowired
  // DailyProgressDetailsRepository dailyProgRepo;

  @Autowired
  private DailyProgressFormationRepository dailyProgressFormationRepo;

  @Autowired
  private DailyProgressDowntimeReasonRepository dailyProgressDowntimeReasonRepo;

  // @Autowired
  // DrillRigsRepository drillrigsRepo;

  @Autowired
  DrillRepo drilllRepo;

  @Autowired
  EngineDetailsRepository engineDetailsRepository;

  @Autowired
  PumpDetailsRepository pumpDetailsRepository;

  @Autowired
  PumpEngineDetailsRepository pumpenginedsetailsRepository;

  @PostMapping("/save-drill-unit-deployment")
  public <json> EncryptedResponse saveDrillUnit(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {

    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    Map<String, Object> responseMap = new HashMap<>();
    try {
      DrillingUnitDeployment existing = drillRepo.findByfspCode(body.getFspCode());

      if (existing == null) {
        // This is a new record
        savedBody = body;
        savedBody = drillRepo.save(savedBody);
      } else {

        existing.setFspYear(body.getFspYear());
        existing.setRegion(body.getRegion());
        existing.setFspCode(body.getFspCode());
        existing.setMission(body.getMission());
        existing.setTypeOfSource(body.getTypeOfSource());
        existing.setUnitNumber(body.getUnitNumber());
        existing.setModel(body.getModel());
        existing.setClearanceRequired(body.getClearanceRequired());
        existing.setOutSourcingAgency(body.getOutSourcingAgency());
        existing.setAreaOfOperation(body.getAreaOfOperation());
        existing.setRemarks(body.getRemarks());
        existing.setTotalDrillingQuantum(body.getTotalDrillingQuantum());
        existing.setFspTarget(body.getFspTarget());
        existing.setUnitTarget(body.getUnitTarget());
        existing.setFspProjectincharge(body.getFspProjectincharge());
        existing.setAreaIncharge(body.getAreaIncharge());
        existing.setUnitIncharge(body.getUnitIncharge());
        existing.setAsstOfficer(body.getAsstOfficer());

        savedBody = drillRepo.save(existing);
      }

      // Prepare the response
      responseMap.put("id", savedBody.getId());
      responseMap.put("message", "Saved");
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    return new EncryptedResponse(responseMap);
  }

  @PostMapping("/get-drill-details-form-wise")
  public EncryptedResponse getAlreadyFilledDetailsFormWise(@RequestBody EncryptedRequest req) throws Exception {
     
      var body = req.bodyAs(GetId.class);
  
     
      String fcp = body.FspCode();
      List<Map<String, Object>> fspDetail;
  
     
      if (fcp != null) {
         
          fspDetail = drillRepo.getFSPCODE(fcp);

          

         
      } else {
         
          Long id = Long.parseLong(body.id());
          fspDetail = drillRepo.getAlreadyFilledDetailsFormWise(id);
      }
  
     
      return new EncryptedResponse(fspDetail);
  }

  @PostMapping("/get-Array-List")
  public EncryptedResponse getArraylistof(@RequestBody EncryptedRequest req) throws Exception {
    var body = req.bodyAs(GetId.class);
    Long id = Long.parseLong(body.id());

    List<Map<String, Object>> pumpDetails = pumpDetailsRepository.getPumpDetails(id);
    List<Map<String, Object>> pumpEngineDetails = pumpenginedsetailsRepository.getPumpEngineDetails(id);
    List<Map<String, Object>> DrillengineDetails = engineDetailsRepository.getDrillEngineDetails(id);
    List<Map<String, Object>> combinedDetails = new ArrayList<>();

    Map<String, Object> pumpDataMap = new HashMap<>();
    pumpDataMap.put("pumpdata", pumpDetails);
    combinedDetails.add(pumpDataMap);

    Map<String, Object> pumpEngineDataMap = new HashMap<>();
    pumpEngineDataMap.put("pumpengine", pumpEngineDetails);
    combinedDetails.add(pumpEngineDataMap);

    Map<String, Object> DrillEngineDataMap = new HashMap<>();
    DrillEngineDataMap.put("Drillengine", DrillengineDetails);
    combinedDetails.add(DrillEngineDataMap);

    return new EncryptedResponse(combinedDetails);
  }

  @Transient
  @PostMapping("/save-drill_daily_progress_details")
  public EncryptedResponse saveDrillDailyProgress(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    // var body = req.bodyAs(DrillRigs.class);
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {
        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());

        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }

        existing.setUnitNum(body.getUnitNum());
        existing.setAreaOfOper(body.getAreaOfOper());
        existing.setBoreStartDate(body.getBoreStartDate());
        existing.setBasicDrillModel(body.getBasicDrillModel());
        existing.setMineral(body.getMineral());
        existing.setDrillEndDate(body.getDrillEndDate());
        existing.setBasicDrillFspCode(body.getBasicDrillFspCode());
        existing.setBoreHoleNumber(body.getBoreHoleNumber());
        existing.setBoreCloseDate(body.getBoreCloseDate());
        existing.setBasicDrillMission(body.getBasicDrillMission());
        existing.setAngle(body.getAngle());
        existing.setBoreSiteCloseDate(body.getBoreSiteCloseDate());
        existing.setBasicDrillRegion(body.getBasicDrillRegion());
        existing.setElevation(body.getElevation());
        existing.setUnitInChar(body.getUnitInChar());
        existing.setStateUnit(body.getStateUnit());
        existing.setBearing(body.getBearing());
        existing.setAsstOff(body.getAsstOff());
        existing.setMineTectBeltBasin(body.getMineTectBeltBasin());
        existing.setBoreAllDate(body.getBoreAllDate());
        existing.setDrillStartDate(body.getDrillStartDate());
        savedBody = drillRepo.save(existing);
      } else {
        savedBody = drillRepo.save(body);
      }
    }

    catch (Exception e) {
      return new EncryptedResponse(e);
    }
    return new EncryptedResponse("success");
  }

  @Transient
  @PostMapping("/save-daily-shift-details")
  public EncryptedResponse saveDailShift(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {
        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());

        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }
        existing.setTypeOfShift(body.getTypeOfShift());
        existing.setShiftDate(body.getShiftDate());
        existing.setTypeOfDrill(body.getTypeOfDrill());
        existing.setShiftDetails(body.getShiftDetails());
        existing.setDepthFrom(body.getDepthFrom());
        existing.setDepthTo(body.getDepthTo());
        existing.setCoreRecovery(body.getCoreRecovery());
        existing.setRshellSize(body.getRshellSize());
        existing.setBitType(body.getBitType());
        existing.setBitSize(body.getBitSize());
        existing.setBitNum(body.getBitNum());
        existing.setRshellNum(body.getRshellNum());
        existing.setStrtDate(body.getStrtDate());
        existing.setEndDate(body.getEndDate());
        existing.setDailyShiftremarks(body.getDailyShiftremarks());
        savedBody = drillRepo.save(existing);
      } else {
        savedBody = drillRepo.save(body);
      }
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    if (body.getAddFormation() != null) {

      for (DailyProgressFormation dailyProgressFormation : body.getAddFormation()) {
        DailyProgressFormation dpFormation = new DailyProgressFormation();
        dpFormation.setFormationType(dailyProgressFormation.getFormationType());
        dpFormation.setDrillUnitId(savedBody.getId()); // Use the ID of the saved drillpro
        dailyProgressFormationRepo.save(dpFormation);
      }
    }

    if (body.getDownReason() != null) {

      for (DailyProgressDowntimeReason dailyProgressDowntimeReason : body.getDownReason()) {
        DailyProgressDowntimeReason dpDowntimeReason = new DailyProgressDowntimeReason();
        dpDowntimeReason.setDownReason(dailyProgressDowntimeReason.getDownReason());
        dpDowntimeReason.setNumOfHrs(dailyProgressDowntimeReason.getNumOfHrs());
        dpDowntimeReason.setRemark(dailyProgressDowntimeReason.getRemark());
        dpDowntimeReason.setDrillUnitId(savedBody.getId());
        dailyProgressDowntimeReasonRepo.save(dpDowntimeReason);
      }
    }

    return new EncryptedResponse("success");
  }


  @Transient
  @PostMapping("/save-drill-rigs-details")
  public <json> EncryptedResponse saveDrillRigs(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {

        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());
        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }
        existing.setRegionOthers(body.getRegionOthers());
        existing.setMachineSinumber(body.getMachineSinumber());
        existing.setTypeOfRig(body.getTypeOfRig());
        existing.setStatus(body.getStatus());
        existing.setDescription(body.getDescription());
        existing.setBookValue(body.getBookValue());
        existing.setRigsUnitNumber(body.getRigsUnitNumber());

        existing.setInitialDateOfCommissioning(body.getInitialDateOfCommissioning());
        existing.setIvNumber(body.getIvNumber());
        existing.setDisposalDetails(body.getDisposalDetails());
        existing.setIvDate(body.getIvDate());
        existing.setHoursTill31stMarch2017(body.getHoursTill31stMarch2017());
        existing.setMake(body.getMake());
        existing.setExpenditureTill31stMarch2017(body.getExpenditureTill31stMarch2017());

        existing.setRisModel(body.getRisModel());
        existing.setDateOfWithdrawal(body.getDateOfWithdrawal());
        existing.setRatedCapacity(body.getRatedCapacity());
        existing.setWorkingCapacity(body.getWorkingCapacity());
        existing.setPresentDeployment(body.getPresentDeployment());
        savedBody = drillRepo.save(existing);
        for (EngineDetails engineDetails : body.getEnginedetails()) {

          if (engineDetails != null) {
            EngineDetails engines = new EngineDetails();
            engines.setEngineSerialNumber(engineDetails.getEngineSerialNumber());
            engines.setDescription(engineDetails.getDescription());
            engines.setInitialDateOfCommissioning(engineDetails.getInitialDateOfCommissioning());
            engines.setDateOfWithdrawal(engineDetails.getDateOfWithdrawal());
            engines.setMake(engineDetails.getMake());
            engines.setModel(engineDetails.getModel());
            engines.setRating(engineDetails.getRating());

            engines.setIvNumber(engineDetails.getIvNumber());
            engines.setIvDate(engineDetails.getIvDate());

            engines.setDrillUnitId(savedBody.getId());
            engineDetailsRepository.save(engines);
          }

          for (PumpDetails pumpDetails : body.getPumpdetails()) {
            if (pumpDetails != null) {
              PumpDetails pumps = new PumpDetails();
              pumps.setDescription(pumpDetails.getDescription());
              pumps.setInitialDateOfCommissioning(pumpDetails.getInitialDateOfCommissioning());
              pumps.setDateOfWithdrawal(pumpDetails.getDateOfWithdrawal());
              pumps.setMake(pumpDetails.getMake());
              pumps.setRating(pumpDetails.getRating());
              pumps.setDepartmentNumber(pumpDetails.getDepartmentNumber());
              pumps.setPumpSerialNumber(pumpDetails.getPumpSerialNumber());
              pumps.setModel(pumpDetails.getModel());
              pumps.setIvNumber(pumpDetails.getIvNumber());
              pumps.setDisposalDetails(pumpDetails.getDisposalDetails());
              pumps.setDrillexpenditureTill31stMarch(pumpDetails.getDrillexpenditureTill31stMarch());
              pumps.setDrillhoursTill31stMarch(pumpDetails.getDrillhoursTill31stMarch());
              pumps.setIvDate(pumpDetails.getIvDate());
              pumps.setDrillUnitId(savedBody.getId());
              pumpDetailsRepository.save(pumps);
            }
          }

          for (PumpEngineDetails pumpEngineDetails : body.getPumpengineDetails()) {
            if (pumpEngineDetails != null) {
              PumpEngineDetails pumpengine = new PumpEngineDetails();
              pumpengine.setPumpSerialNumber(pumpEngineDetails.getPumpSerialNumber());
              pumpengine.setEngineSerialNumber(pumpEngineDetails.getEngineSerialNumber());
              pumpengine.setEngineDescription(pumpEngineDetails.getEngineDescription());
              pumpengine.setEngineMake(pumpEngineDetails.getEngineMake());
              pumpengine.setEngineModel(pumpEngineDetails.getEngineModel());
              pumpengine.setEngineIvNumber(pumpEngineDetails.getEngineIvNumber());
              pumpengine.setEngineIvDate(pumpEngineDetails.getEngineIvDate());
              pumpengine.setEngineRating(pumpEngineDetails.getEngineRating());
              pumpengine.setEngineInitialDateOfCommissioning(pumpEngineDetails.getEngineInitialDateOfCommissioning());
              pumpengine.setEngineHoursTill31stMarch(pumpEngineDetails.getEngineHoursTill31stMarch());
              pumpengine.setEngineExpenditureTill31stMarch(pumpEngineDetails.getEngineExpenditureTill31stMarch());
              pumpengine.setEngineDateOfWithdrawal(pumpEngineDetails.getEngineDateOfWithdrawal());
              pumpengine.setDrillUnitId(savedBody.getId());
              pumpenginedsetailsRepository.save(pumpengine);
            }
          }
        }
      } else {
        savedBody = drillRepo.save(body);
      }
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    return new EncryptedResponse("success");
  }

  @PostMapping("/get-dril-request-report")
  public EncryptedResponse getFspForPeerReview(@RequestBody EncryptedRequest req)
      throws Exception {

    var body = req.bodyAs(Report.class);
    var pageable = PageRequest.of(body.pagination().page(), body.pagination().size());
    Page<Map<String, Object>> result = drilllRepo.getDrillRequestReview(pageable);
    return new EncryptedResponse(result);
  }

  // @PostMapping("/get-region-name")
  // public <json> EncryptedResponse getRegionName(
  // HttpServletResponse response) throws Exception {
  // // var body = Json.deserialize(CreateFspRequest.class, req.data());
  // // String username = body.username();
  // List<Map<String, Object>> result = drillRepo.getRegionName();
  // return new EncryptedResponse(result);
  // }

  @PostMapping("/get-fsp-year")
  public <json> EncryptedResponse getFspYear(
      HttpServletResponse req) throws Exception {

    List<Map<String, Object>> result = drilllRepo.getFspYear();
    return new EncryptedResponse(result);
  }

  @PostMapping("/get-all-region")
  public <json> EncryptedResponse getAllRegion(

      HttpServletResponse response) throws Exception {
    List<Map<String, Object>> result = drilllRepo.getAllRegion();
    return new EncryptedResponse(result);
  }

  @PostMapping("/get-region-name")
  public <json> EncryptedResponse getRegionName(
      HttpServletResponse response) throws Exception {
    List<Map<String, Object>> result = drillRepo.getRegionName();
    return new EncryptedResponse(result);
  }

  @PostMapping("/get-state-unit")
  public <json> EncryptedResponse getStateUnit(
      HttpServletResponse response) throws Exception {
    List<Map<String, Object>> result = drillRepo.getStateUnit();
    return new EncryptedResponse(result);
  }

  @PostMapping("/get-fsp-id")
public <json> EncryptedResponse getFSPByRegionName(
    @RequestBody EncryptedRequest req, 
    HttpServletResponse response) throws Exception {
    var body = req.bodyAs(RegionName.class);
    String regionName = body.regionName();
    List<Map<String, Object>> result = drillRepo.getFSPByRegionName(regionName);
    System.out.println(regionName);
    System.out.println(result);
    return new EncryptedResponse(result);
}

@Transactional
  @PostMapping("/save-casing-plan-info")
  public EncryptedResponse saveCasingPlanDetail(@Valid @RequestBody EncryptedRequest req,
      HttpServletRequest request) throws Exception {

    try {
      System.out.println("called");
      var body = req.bodyAs(CasingPlanStore.class);
      List<CasingDetails> casingDetailsToSave = new ArrayList<>();

      if (body.casingDetails() != null && !body.casingDetails().isEmpty()) {
        for (CasingDetails caseplan : body.casingDetails()) {
          CasingDetails casingplanInfo = new CasingDetails();

          // Set common fields from the parent object
          casingplanInfo.setUnitNumber(body.unitNumber());
          casingplanInfo.setRegion(body.region());
          casingplanInfo.setBorehole(body.borehole());

          // Set fields from the current casing detail
          casingplanInfo.setType_select(caseplan.getType_select());
          casingplanInfo.setDepthFrom(caseplan.getDepthFrom());
          casingplanInfo.setDepthTo(caseplan.getDepthTo());
          casingplanInfo.setTotalMeter(caseplan.getTotalMeter());
          casingplanInfo.setRemarks(caseplan.getRemarks());

          casingDetailsToSave.add(casingplanInfo); // Add the new object to our list
        }

        casingdetailsrepo.saveAll(casingDetailsToSave);
        return new EncryptedResponse("Saved Successfully");
      } else {
        return new EncryptedResponse("No casing details provided");
      }
    } catch (Exception e) {
      e.printStackTrace(); // Log the full stack trace
      return new EncryptedResponse("Error: " + e.getMessage());
    }
  }

  @PostMapping("/get-dril-casing-report")
  public EncryptedResponse getcasingReviewReport(@RequestBody EncryptedRequest req)
      throws Exception {

    var body = req.bodyAs(Report.class);
    var pageable = PageRequest.of(body.pagination().page(), body.pagination().size());
    Page<Map<String, Object>> result = casingdetailsrepo.getDrillcasingReport(pageable);
    return new EncryptedResponse(result);
  }


// Injected endpoint to save multiple shift records
@PostMapping("/save-daily-shifts")
public ResponseEntity<String> saveAllShifts(@RequestBody List<ShiftDetail> shiftDetails) {
    try {
        for (ShiftDetail shift : shiftDetails) {
            // Save logic - assume drillRepo or relevant repository handles this
            drillRepo.save(shift);
        }
        return ResponseEntity.ok("Shifts saved successfully.");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error saving shifts: " + e.getMessage());
    }
}

}