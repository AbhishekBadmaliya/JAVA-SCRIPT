
@RestController
@RequestMapping("/drill")
public class DrillController {

    @Autowired
    private ShiftDetailRepository shiftDetailRepository;

    @PostMapping("/save-daily-shifts")
    public ResponseEntity<String> saveAllShifts(@RequestBody List<ShiftDetail> shiftDetails) {
        shiftDetailRepository.saveAll(shiftDetails);
        return ResponseEntity.ok("Shifts saved successfully.");
    }
}
