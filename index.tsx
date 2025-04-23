import React, { useEffect, useState } from "react";
import scss from "./dailyshiftdetails.module.scss";
import {
  Button,
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Text,
  Select,
  Card,
} from "@chakra-ui/react";
import { SubmitHandler, useForm, FormProvider } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { fetcher } from "@services/fetcher";
import Productive from "../ProductiveAndNon-Productive/productive";
import NonProductive from "../ProductiveAndNon-Productive/nonProductive";

// Validation schema (unchanged)
const dailyShiftSchema = z.object({
  typeOfShift: z.string().min(1, { message: "Required" }),
  shiftDate: z.string().min(1, { message: "Required" }),
  typeOfDrill: z.string().min(1, { message: "Required" }),
  shiftDetails: z.string().min(1, { message: "Required" }),
  depthFrom: z.number().optional(),
  depthTo: z.number().optional(),
  coreRecovery: z.number().optional(),
  addFormation: z.array(z.object({ formationType: z.string() })).optional(),
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

type DailyShiftSchema = z.infer<typeof dailyShiftSchema>;

interface DailyShiftDetailsProps {
  formId: number; // Unique ID for each form instance
  onRemove: () => void; // Callback to remove this form
  isLast: boolean; // Flag to indicate if this is the last form
  onAddNewShift: () => void; // Callback to add a new form
}

const DailyShiftDetails = ({ formId, onRemove, isLast, onAddNewShift }: DailyShiftDetailsProps) => {
  const methods = useForm<DailyShiftSchema>({
    resolver: zodResolver(dailyShiftSchema),
    defaultValues: {
      typeOfShift: "",
      shiftDate: "",
      typeOfDrill: "",
      shiftDetails: "",
      depthFrom: undefined,
      depthTo: undefined,
      coreRecovery: undefined,
      addFormation: [],
      remarks: "",
      bitType: "",
      bitNum: "",
      rshellNum: undefined,
      runHrsOfRig: "",
      dowType: "",
      natureOfHoliday: "",
      downReason: [],
    },
  });

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    formState: { errors },
  } = methods;

  const [productive, setProductive] = useState<string>("");

  const typeOfShift = watch("typeOfShift");

  useEffect(() => {
    if (typeOfShift === "productive") {
      setProductive("productive");
      setValue("dowType", "");
      setValue("natureOfHoliday", "");
      setValue("downReason", []);
      setValue("remarks", "");
    } else if (typeOfShift === "non-productive") {
      setProductive("non-productive");
      setValue("depthFrom", undefined);
      setValue("depthTo", undefined);
      setValue("coreRecovery", undefined);
      setValue("addFormation", []);
      setValue("bitType", "");
      setValue("bitNum", "");
      setValue("rshellNum", undefined);
      setValue("runHrsOfRig", "");
      setValue("remarks", "");
    }
  }, [typeOfShift, setValue]);

  const onSubmit: SubmitHandler<DailyShiftSchema> = async (data) => {
    console.log(`Submitting form with ID: ${formId}`, data);

    const payload = {
      id: formId, // Include formId in payload for backend
      typeOfShift: data.typeOfShift,
      shiftDate: data.shiftDate,
      typeOfDrill: data.typeOfDrill,
      depthFrom: data.depthFrom,
      depthTo: data.depthTo,
      coreRecovery: data.coreRecovery,
      dailyShiftremarks: data.remarks,
      bitType: data.bitType,
      bitNum: data.bitNum,
      rshellNum: data.rshellNum,
      runHrsOfRig: data.runHrsOfRig,
      dowType: data.dowType,
      ...(data.natureOfHoliday ? { remarks: data.natureOfHoliday } : {}),
      ...(data.downReason && data.downReason.length > 0
        ? {
            remarks: data.downReason
              .map((reason) => `${reason.downReason}: ${reason.remark} (${reason.numOfHrs} hrs)`)
              .join("; "),
          }
        : {}),
    };

    try {
      const res = await fetcher(
        { path: "/drill/save-daily-shift-details" },
        { json: payload }
      );
      console.log("Response from server:", res);
      if (res.message === "success") {
        alert("Data saved successfully!");
        reset();
        setProductive("");
      } else {
        alert("Error saving data. Please try again.");
      }
    } catch (error) {
      console.error("Error saving data:", error);
      alert("An error occurred while saving data. Please try again.");
    }
  };

  return (
    <FormProvider {...methods}>
      <form noValidate className="mx-auto" onSubmit={handleSubmit(onSubmit)}>
        <div className={scss.header}>
          <Text as="h5" color="orange.500">
            Daily Shift Details 
          </Text>
        </div>

        <div className={scss.progress}>
          <div className="row">
            <div className="col-md-4">
              <FormControl isInvalid={!!errors.typeOfShift}>
                <FormLabel>Type of Shift</FormLabel>
                <Select placeholder="Select option" {...register("typeOfShift")}>
                  <option value="productive">Productive</option>
                  <option value="non-productive">Non Productive</option>
                </Select>
                <FormErrorMessage>{errors.typeOfShift?.message}</FormErrorMessage>
              </FormControl>
            </div>

            <div className="col-md-4">
              <FormControl isInvalid={!!errors.typeOfDrill}>
                <FormLabel>Type of Drilling</FormLabel>
                <Select placeholder="Select option" {...register("typeOfDrill")}>
                  <option value="augure">Augure</option>
                  <option value="dry">Dry</option>
                  <option value="geo tech">Geo Tech</option>
                  <option value="wet">Wet</option>
                </Select>
                <FormErrorMessage>{errors.typeOfDrill?.message}</FormErrorMessage>
              </FormControl>
            </div>
          </div>

          <div className="mt-4">
            <div className="row">
              <div className="col-md-4">
                <FormControl isInvalid={!!errors.shiftDate}>
                  <FormLabel>Date</FormLabel>
                  <Input placeholder="select date" type="date" {...register("shiftDate")} />
                  <FormErrorMessage>{errors.shiftDate?.message}</FormErrorMessage>
                </FormControl>
              </div>

              <div className="col-md-4">
                <FormControl isInvalid={!!errors.shiftDetails}>
                  <FormLabel>Shift Details</FormLabel>
                  <Select placeholder="Select option" {...register("shiftDetails")}>
                    <option value="general">General</option>
                    <option value="extended">Extended</option>
                    <option value="first">First</option>
                    <option value="second">Second</option>
                    <option value="third">Third</option>
                  </Select>
                  <FormErrorMessage>{errors.shiftDetails?.message}</FormErrorMessage>
                </FormControl>
              </div>
            </div>
          </div>

          <div className="md-6 mt-5">
            {productive === "productive" && (
              <Card>
                <Productive />
              </Card>
            )}
            {productive === "non-productive" && (
              <Card>
                <NonProductive />
              </Card>
            )}
          </div>

          <div className="flex gap-3 flex-row-reverse mt-4 mb-2">
            {/* Close button in all forms */}
            <Button colorScheme="red" onClick={onRemove}>
              Close
            </Button>
            {/* Submit and Add New Record buttons only in the last form */}
            {isLast && (
              <>
                <Button type="submit" colorScheme="facebook">
                  Submit
                </Button>
                {/* <Button colorScheme="facebook" onClick={onAddNewShift}>
                  Add New Record
                </Button> */}
              </>
            )}
          </div>
        </div>
      </form>
    </FormProvider>
  );
};

export default DailyShiftDetails;
