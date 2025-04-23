
// Injected for multiple shift management
import { useForm, FormProvider } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { useState } from "react";
import { fetcher } from "@services/fetcher";

const dailyShiftSchema = z.object({
  typeOfShift: z.string().min(1),
  shiftDate: z.string().min(1),
  typeOfDrill: z.string().min(1),
  shiftDetails: z.string().min(1),
  depthFrom: z.number().optional(),
  depthTo: z.number().optional(),
  coreRecovery: z.number().optional(),
  remarks: z.string().optional(),
  bitType: z.string().optional(),
  bitNum: z.string().optional(),
  rshellNum: z.number().optional(),
  runHrsOfRig: z.string().optional(),
  dowType: z.string().optional(),
  natureOfHoliday: z.string().optional(),
  downReason: z
    .array(
      z.object({
        downReason: z.string(),
        numOfHrs: z.number(),
        remark: z.string(),
      })
    )
    .optional(),
});

const formSchema = z.object({
  shifts: z.array(dailyShiftSchema),
});

const methods = useForm({
  resolver: zodResolver(formSchema),
  defaultValues: { shifts: [{}] },
});

const [forms, setForms] = useState([{ id: Date.now() }]);

const addNewShift = () => {
  setForms([...forms, { id: Date.now() }]);
};

const removeShift = (id) => {
  setForms(forms.filter((form) => form.id !== id));
};

const onSubmit = (data) => {
  console.log("Submitting all shifts:", data.shifts);
  fetcher({
    path: "/drill/save-daily-shifts",
    method: "POST",
    json: data.shifts,
  });
};


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



<FormProvider {...methods}>
  <form onSubmit={methods.handleSubmit(onSubmit)}>
    {forms.map((form, idx) => (
      <DailyShiftDetails
        key={form.id}
        formId={idx}
        onRemove={() => removeShift(form.id)}
        isLast={idx === forms.length - 1}
        onAddNewShift={addNewShift}
      />
    ))}
    <Button type="submit" mt={4}>Save All Shifts</Button>
  </form>
</FormProvider>
