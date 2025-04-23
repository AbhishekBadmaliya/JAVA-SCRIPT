
import React, { useEffect, useState } from "react";
import {
  FormControl,
  FormLabel,
  Input,
  Button,
} from "@chakra-ui/react";
import { useFormContext } from "react-hook-form";

interface DailyShiftDetailsProps {
  formId: number;
  onRemove: () => void;
  isLast: boolean;
  onAddNewShift: () => void;
}

const DailyShiftDetails = ({
  formId,
  onRemove,
  isLast,
  onAddNewShift,
}: DailyShiftDetailsProps) => {
  const { register } = useFormContext();

  return (
    <div style={{ border: "1px solid #ccc", padding: 10, marginBottom: 10 }}>
      <FormControl>
        <FormLabel>Type of Shift</FormLabel>
        <Input {...register(`shifts.${formId}.typeOfShift`)} />
      </FormControl>
      <FormControl>
        <FormLabel>Shift Date</FormLabel>
        <Input type="date" {...register(`shifts.${formId}.shiftDate`)} />
      </FormControl>
      <FormControl>
        <FormLabel>Type of Drill</FormLabel>
        <Input {...register(`shifts.${formId}.typeOfDrill`)} />
      </FormControl>
      <FormControl>
        <FormLabel>Shift Details</FormLabel>
        <Input {...register(`shifts.${formId}.shiftDetails`)} />
      </FormControl>
      {isLast && (
        <Button onClick={onAddNewShift} mt={2}>
          Add New Shift
        </Button>
      )}
      <Button onClick={onRemove} mt={2} ml={2} colorScheme="red">
        Remove
      </Button>
    </div>
  );
};

export default DailyShiftDetails;
