
import React, { useState } from "react";
import DailyShiftDetails from "./index";
import { Button } from "@chakra-ui/react";
import { FormProvider, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
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

export type FormSchema = z.infer<typeof formSchema>;

const DailyShiftContainer = () => {
  const methods = useForm<FormSchema>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      shifts: [{}],
    },
  });

  const [forms, setForms] = useState([{ id: Date.now() }]);

  const addNewShift = () => {
    setForms([...forms, { id: Date.now() }]);
  };

  const removeShift = (id: number) => {
    setForms(forms.filter((form) => form.id !== id));
  };

  const onSubmit = (data: FormSchema) => {
    console.log("Submitting all shifts:", data);
    fetcher({
      path: "/drill/save-daily-shifts",
      method: "POST",
      json: data.shifts,
    });
  };

  return (
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
        <Button type="submit" mt={4}>
          Save All Shifts
        </Button>
      </form>
    </FormProvider>
  );
};

export default DailyShiftContainer;
