import React, { useState } from "react";
 // Updated to import the modified component
import { Button, Box } from "@chakra-ui/react";
import DailyShiftDetails from "./index";

// Parent component to manage multiple DailyShiftDetails instances
const DailyShiftContainer = () => {
  const [shiftForms, setShiftForms] = useState<number[]>([1]); // Initial form with ID 1

  // Handle adding a new shift form
  const handleAddNewShift = () => {
    setShiftForms((prev) => [...prev, prev.length + 1]); // Add new form with unique ID
  };

  // Handle removing a specific shift form
  const handleRemoveShift = (formId: number) => {
    setShiftForms((prev) => prev.filter((id) => id !== formId));
  };

  return (
    <Box>
      {/* Button to add a new shift form */}
      <Button colorScheme="facebook" onClick={handleAddNewShift} mb={4}>
        Add New Shift
      </Button>
      {shiftForms.map((formId, index) => (
        <Box key={formId} mb={4}>
          {/* Pass formId, onRemove, and isLast to control button visibility */}
          <DailyShiftDetails
            formId={formId}
            onRemove={() => handleRemoveShift(formId)}
            isLast={index === shiftForms.length - 1} // True only for the last form
            // onAddNewShift={handleAddNewShift} // Pass callback for Add New Record
          />
        </Box>
      ))}
    </Box>
  );
};

export default DailyShiftContainer;
