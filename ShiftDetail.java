package org.bisag.ocbis.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;





@Entity
@Table(name = "drill_shift_detail")
public class ShiftDetail {
    

    @Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

    private Long drillUnitId;
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

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Long getDrillUnitId() {
        return drillUnitId;
    }
    public void setDrillUnitId(Long drillUnitId) {
        this.drillUnitId = drillUnitId;
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
}
