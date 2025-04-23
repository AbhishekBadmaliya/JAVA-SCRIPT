package org.bisag.ocbis.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "drill_drilling_unit_deployment")

public class DrillingUnitDeployment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fspYear;
    private String region;
    private String fspCode;
    private String mission;
    private String typeOfSource;
    private String unitNumber;
    private String model;
    private String clearanceRequired;
    private String outSourcingAgency;
    private String areaOfOperation;
    private String remarks;
    private Long totalDrillingQuantum;
    private Long fspTarget;
    private Integer unitTarget;
    private String fspProjectincharge;
    private String areaIncharge;
    private String unitIncharge;
    private String asstOfficer;

    // Drill Rigs Details
    private String regionOthers;
    private String machineSinumber;
    private String typeOfRig;
    private String status;
    private String description;
    private String bookValue;

    private String rigsUnitNumber;
    
    private String initialDateOfCommissioning;
    private String ivNumber;
    private String disposalDetails;
    private String ivDate;
    private Long hoursTill31stMarch2017;
    private String make;
    private Long expenditureTill31stMarch2017;
    private String risModel;

    private String dateOfWithdrawal;
    private String ratedCapacity;
    private String workingCapacity;
    private String presentDeployment;

    // Basic Drill Run Information

    private String unitNum;
    private String areaOfOper;
    private String boreStartDate;
    private String basicDrillModel;
    private String mineral;
    private String drillEndDate;
    private String basicDrillFspCode;
    private String boreHoleNumber;
    private String boreCloseDate;
    private String drillStartDate;
    public String getDrillStartDate() {
        return drillStartDate;
    }

    public void setDrillStartDate(String drillStartDate) {
        this.drillStartDate = drillStartDate;
    }

    private String basicDrillMission;
    private Long angle;
    private String boreSiteCloseDate;
    private String basicDrillRegion;
    private Long elevation;
    private String unitInChar;
    private String stateUnit;
    private String bearing;
    private String asstOff;
    private String mineTectBeltBasin;
    private String boreAllDate;


    // Daily Shift Details
    private String typeOfShift;
    private String shiftDate;
    private String typeOfDrill;
    private String shiftDetails;
    private Long depthFrom;
    private Long depthTo;
    private Long coreRecovery;
    private String rshellSize;
    private String bitType;
    private String bitSize;
    private String bitNum;
    private String rshellNum;
    private Long runHrsOfRig;
    private String strtDate;
    private String endDate;
    private String dowType;
    private String dailyShiftremarks;


    @Transient
    EngineDetails[] enginedetails;


    @Transient
    ShiftDetail[] shiftDetail;

  

    @Transient
    PumpDetails[] pumpdetails;

    @Transient
    PumpEngineDetails[] pumpengineDetails;

    @Transient
    DailyProgressFormation[] addFormation;

    @Transient
    DailyProgressDowntimeReason[] downReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFspYear() {
        return fspYear;
    }

    public void setFspYear(String fspYear) {
        this.fspYear = fspYear;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFspCode() {
        return fspCode;
    }

    public void setFspCode(String fspCode) {
        this.fspCode = fspCode;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getTypeOfSource() {
        return typeOfSource;
    }

    public void setTypeOfSource(String typeOfSource) {
        this.typeOfSource = typeOfSource;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClearanceRequired() {
        return clearanceRequired;
    }

    public void setClearanceRequired(String clearanceRequired) {
        this.clearanceRequired = clearanceRequired;
    }

    public String getOutSourcingAgency() {
        return outSourcingAgency;
    }

    public void setOutSourcingAgency(String outSourcingAgency) {
        this.outSourcingAgency = outSourcingAgency;
    }

    public String getAreaOfOperation() {
        return areaOfOperation;
    }

    public void setAreaOfOperation(String areaOfOperation) {
        this.areaOfOperation = areaOfOperation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getTotalDrillingQuantum() {
        return totalDrillingQuantum;
    }

    public void setTotalDrillingQuantum(Long totalDrillingQuantum) {
        this.totalDrillingQuantum = totalDrillingQuantum;
    }

    public Long getFspTarget() {
        return fspTarget;
    }

    public void setFspTarget(Long fspTarget) {
        this.fspTarget = fspTarget;
    }

    public Integer getUnitTarget() {
        return unitTarget;
    }

    public void setUnitTarget(Integer unitTarget) {
        this.unitTarget = unitTarget;
    }

    public String getFspProjectincharge() {
        return fspProjectincharge;
    }

    public void setFspProjectincharge(String fspProjectincharge) {
        this.fspProjectincharge = fspProjectincharge;
    }

    public String getAreaIncharge() {
        return areaIncharge;
    }

    public void setAreaIncharge(String areaIncharge) {
        this.areaIncharge = areaIncharge;
    }

    public String getUnitIncharge() {
        return unitIncharge;
    }

    public void setUnitIncharge(String unitIncharge) {
        this.unitIncharge = unitIncharge;
    }

    public String getAsstOfficer() {
        return asstOfficer;
    }

    public void setAsstOfficer(String asstOfficer) {
        this.asstOfficer = asstOfficer;
    }

    public String getRegionOthers() {
        return regionOthers;
    }

    public void setRegionOthers(String regionOthers) {
        this.regionOthers = regionOthers;
    }

    public String getMachineSinumber() {
        return machineSinumber;
    }

    public void setMachineSinumber(String machineSinumber) {
        this.machineSinumber = machineSinumber;
    }

    public String getTypeOfRig() {
        return typeOfRig;
    }

    public void setTypeOfRig(String typeOfRig) {
        this.typeOfRig = typeOfRig;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
    }

    public String getRigsUnitNumber() {
        return rigsUnitNumber;
    }

    public void setRigsUnitNumber(String rigsUnitNumber) {
        this.rigsUnitNumber = rigsUnitNumber;
    }

    public String getInitialDateOfCommissioning() {
        return initialDateOfCommissioning;
    }

    public void setInitialDateOfCommissioning(String initialDateOfCommissioning) {
        this.initialDateOfCommissioning = initialDateOfCommissioning;
    }

    public String getIvNumber() {
        return ivNumber;
    }

    public void setIvNumber(String ivNumber) {
        this.ivNumber = ivNumber;
    }

    public String getDisposalDetails() {
        return disposalDetails;
    }

    public void setDisposalDetails(String disposalDetails) {
        this.disposalDetails = disposalDetails;
    }

    public String getIvDate() {
        return ivDate;
    }

    public void setIvDate(String ivDate) {
        this.ivDate = ivDate;
    }

    public Long getHoursTill31stMarch2017() {
        return hoursTill31stMarch2017;
    }

    public void setHoursTill31stMarch2017(Long hoursTill31stMarch2017) {
        this.hoursTill31stMarch2017 = hoursTill31stMarch2017;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Long getExpenditureTill31stMarch2017() {
        return expenditureTill31stMarch2017;
    }

    public void setExpenditureTill31stMarch2017(Long expenditureTill31stMarch2017) {
        this.expenditureTill31stMarch2017 = expenditureTill31stMarch2017;
    }

    public String getRisModel() {
        return risModel;
    }

    public void setRisModel(String risModel) {
        this.risModel = risModel;
    }

    public String getDateOfWithdrawal() {
        return dateOfWithdrawal;
    }

    public void setDateOfWithdrawal(String dateOfWithdrawal) {
        this.dateOfWithdrawal = dateOfWithdrawal;
    }

    public String getRatedCapacity() {
        return ratedCapacity;
    }

    public void setRatedCapacity(String ratedCapacity) {
        this.ratedCapacity = ratedCapacity;
    }

    public String getWorkingCapacity() {
        return workingCapacity;
    }

    public void setWorkingCapacity(String workingCapacity) {
        this.workingCapacity = workingCapacity;
    }

    public String getPresentDeployment() {
        return presentDeployment;
    }

    public void setPresentDeployment(String presentDeployment) {
        this.presentDeployment = presentDeployment;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getAreaOfOper() {
        return areaOfOper;
    }

    public void setAreaOfOper(String areaOfOper) {
        this.areaOfOper = areaOfOper;
    }

    public String getBoreStartDate() {
        return boreStartDate;
    }

    public void setBoreStartDate(String boreStartDate) {
        this.boreStartDate = boreStartDate;
    }

    public String getBasicDrillModel() {
        return basicDrillModel;
    }

    public void setBasicDrillModel(String basicDrillModel) {
        this.basicDrillModel = basicDrillModel;
    }

    public String getMineral() {
        return mineral;
    }

    public void setMineral(String mineral) {
        this.mineral = mineral;
    }

    public String getDrillEndDate() {
        return drillEndDate;
    }

    public void setDrillEndDate(String drillEndDate) {
        this.drillEndDate = drillEndDate;
    }

    public String getBasicDrillFspCode() {
        return basicDrillFspCode;
    }

    public void setBasicDrillFspCode(String basicDrillFspCode) {
        this.basicDrillFspCode = basicDrillFspCode;
    }

    public String getBoreHoleNumber() {
        return boreHoleNumber;
    }

    public void setBoreHoleNumber(String boreHoleNumber) {
        this.boreHoleNumber = boreHoleNumber;
    }

    public String getBoreCloseDate() {
        return boreCloseDate;
    }

    public void setBoreCloseDate(String boreCloseDate) {
        this.boreCloseDate = boreCloseDate;
    }

    public String getBasicDrillMission() {
        return basicDrillMission;
    }

    public void setBasicDrillMission(String basicDrillMission) {
        this.basicDrillMission = basicDrillMission;
    }

    public Long getAngle() {
        return angle;
    }

    public void setAngle(Long angle) {
        this.angle = angle;
    }

    public String getBoreSiteCloseDate() {
        return boreSiteCloseDate;
    }

    public void setBoreSiteCloseDate(String boreSiteCloseDate) {
        this.boreSiteCloseDate = boreSiteCloseDate;
    }

    public String getBasicDrillRegion() {
        return basicDrillRegion;
    }

    public void setBasicDrillRegion(String basicDrillRegion) {
        this.basicDrillRegion = basicDrillRegion;
    }

    public Long getElevation() {
        return elevation;
    }

    public void setElevation(Long elevation) {
        this.elevation = elevation;
    }

    public String getUnitInChar() {
        return unitInChar;
    }

    public void setUnitInChar(String unitInChar) {
        this.unitInChar = unitInChar;
    }

    public String getStateUnit() {
        return stateUnit;
    }

    public void setStateUnit(String stateUnit) {
        this.stateUnit = stateUnit;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getAsstOff() {
        return asstOff;
    }

    public void setAsstOff(String asstOff) {
        this.asstOff = asstOff;
    }

    public String getMineTectBeltBasin() {
        return mineTectBeltBasin;
    }

    public void setMineTectBeltBasin(String mineTectBeltBasin) {
        this.mineTectBeltBasin = mineTectBeltBasin;
    }

    public String getBoreAllDate() {
        return boreAllDate;
    }

    public void setBoreAllDate(String boreAllDate) {
        this.boreAllDate = boreAllDate;
    }

    public String getTypeOfShift() {
        return typeOfShift;
    }

    public void setTypeOfShift(String typeOfShift) {
        this.typeOfShift = typeOfShift;
    }

    public String getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getTypeOfDrill() {
        return typeOfDrill;
    }

    public void setTypeOfDrill(String typeOfDrill) {
        this.typeOfDrill = typeOfDrill;
    }

    public String getShiftDetails() {
        return shiftDetails;
    }

    public void setShiftDetails(String shiftDetails) {
        this.shiftDetails = shiftDetails;
    }

    public Long getDepthFrom() {
        return depthFrom;
    }

    public void setDepthFrom(Long depthFrom) {
        this.depthFrom = depthFrom;
    }

    public Long getDepthTo() {
        return depthTo;
    }

    public void setDepthTo(Long depthTo) {
        this.depthTo = depthTo;
    }

    public Long getCoreRecovery() {
        return coreRecovery;
    }

    public void setCoreRecovery(Long coreRecovery) {
        this.coreRecovery = coreRecovery;
    }

    public String getRshellSize() {
        return rshellSize;
    }

    public void setRshellSize(String rshellSize) {
        this.rshellSize = rshellSize;
    }

    public String getBitType() {
        return bitType;
    }

    public void setBitType(String bitType) {
        this.bitType = bitType;
    }

    public String getBitSize() {
        return bitSize;
    }

    public void setBitSize(String bitSize) {
        this.bitSize = bitSize;
    }

    public String getBitNum() {
        return bitNum;
    }

    public void setBitNum(String bitNum) {
        this.bitNum = bitNum;
    }

    public String getRshellNum() {
        return rshellNum;
    }

    public void setRshellNum(String rshellNum) {
        this.rshellNum = rshellNum;
    }

    public Long getRunHrsOfRig() {
        return runHrsOfRig;
    }

    public void setRunHrsOfRig(Long runHrsOfRig) {
        this.runHrsOfRig = runHrsOfRig;
    }

    public String getStrtDate() {
        return strtDate;
    }

    public void setStrtDate(String strtDate) {
        this.strtDate = strtDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDowType() {
        return dowType;
    }

    public void setDowType(String dowType) {
        this.dowType = dowType;
    }

    public String getDailyShiftremarks() {
        return dailyShiftremarks;
    }

    public void setDailyShiftremarks(String dailyShiftremarks) {
        this.dailyShiftremarks = dailyShiftremarks;
    }

    public EngineDetails[] getEnginedetails() {
        return enginedetails;
    }

    public void setEnginedetails(EngineDetails[] enginedetails) {
        this.enginedetails = enginedetails;
    }

    public PumpDetails[] getPumpdetails() {
        return pumpdetails;
    }

    public void setPumpdetails(PumpDetails[] pumpdetails) {
        this.pumpdetails = pumpdetails;
    }

    public PumpEngineDetails[] getPumpengineDetails() {
        return pumpengineDetails;
    }

    public void setPumpengineDetails(PumpEngineDetails[] pumpengineDetails) {
        this.pumpengineDetails = pumpengineDetails;
    }

    public DailyProgressFormation[] getAddFormation() {
        return addFormation;
    }

    public void setAddFormation(DailyProgressFormation[] addFormation) {
        this.addFormation = addFormation;
    }

    public DailyProgressDowntimeReason[] getDownReason() {
        return downReason;
    }

    public void setDownReason(DailyProgressDowntimeReason[] downReason) {
        this.downReason = downReason;
    }

    public ShiftDetail[] getShiftDetail() {
        return shiftDetail;
    }

    public void setShiftDetail(ShiftDetail[] shiftDetail) {
        this.shiftDetail = shiftDetail;
    }
}
