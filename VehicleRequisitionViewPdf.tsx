import React from "react";
import { Document, Page, View, StyleSheet, Text } from "@react-pdf/renderer";

const VehicleRequisitionViewPdf = ({ data }: any) => {
  const styles = StyleSheet.create({
    page: {
      padding: 20,
    },
    header: {
      textAlign: "center",
      fontWeight: "bold",
      marginBottom: 20,
      backgroundColor: "#005cab",
      padding: 10,
      color: "white",
      borderRadius: 5,
    },
    section: {
      marginBottom: 20,
    },
    paragraph: {
      color: "black",
      fontSize: 14,
      fontWeight: 400,
    },
    listContainer: {
      marginBottom: 20,
      paddingLeft: 20,
    },
    listItem: {
      display: "flex",
      flexDirection: "row",
      marginBottom: 12,
    },
    label: {
      fontSize: 12,
      fontWeight: "bold",
      width: "30%",
    },
    value: {
      fontSize: 12,
      width: "70%",
    },
    separator: {
      borderBottom: "1px solid #3182ce",
      marginVertical: 10,
    },
    title: {
      fontSize: 16,
      fontWeight: "bold",
      color: "#005cab",
      marginBottom: 10,
    },
  });
  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <Text style={styles.header}>Vehicle Requisition Details</Text>
        <View style={styles.separator} />

        <View style={styles.listItem}>
          <Text style={styles.label}>Requisition ID:</Text>
          <Text style={styles.value}>{data?.requistion_id}</Text>
        </View>

        <View style={styles.listItem}>
          <Text style={styles.label}>Allocation Type:</Text>
          <Text style={styles.value}>{data?.allocation_type}</Text>
        </View>
        {/* <View style={styles.listItem}>
          <Text style={styles.label}>Contact Number:</Text>
          <Text style={styles.value}>{data?.contact_of_requester}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Created Date:</Text>
          <Text style={styles.value}>
            {new Date(data?.created_on).toLocaleDateString()}
          </Text>
        </View> */}
         <View style={styles.listItem}>
          <Text style={styles.label}>FSP Code:</Text>
          <Text style={styles.value}>{data?.fsp_code}</Text>
        </View>
         <View style={styles.listItem}>
          <Text style={styles.label}>Start Date:</Text>
          <Text style={styles.value}>
            {new Date(data?.start_date).toLocaleDateString()}
          </Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>End Date:</Text>
          <Text style={styles.value}>
            {new Date(data?.end_date).toLocaleDateString()}
          </Text>
        </View>
        {/* <View style={styles.listItem}>
          <Text style={styles.label}>From Date:</Text>
          <Text style={styles.value}>{data?.from_date}</Text>
        </View> */}
       
        {/* <View style={styles.listItem}>
          <Text style={styles.label}>Number of Officers:</Text>
          <Text style={styles.value}>{data?.no_of_officers}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Number of Vehicles:</Text>
          <Text style={styles.value}>{data?.no_of_vehicle}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Purpose:</Text>
          <Text style={styles.value}>{data?.purpose}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Region:</Text>
          <Text style={styles.value}>{data?.region}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Remarks:</Text>
          <Text style={styles.value}>{data?.remarks}</Text>
        </View>
        <View style={styles.listItem}>
          <Text style={styles.label}>Reporting Time:</Text>
          <Text style={styles.value}>{data?.reporting_time}</Text>
        </View> */}
        
        {/* <View style={styles.listItem}>
          <Text style={styles.label}>State Unit:</Text>
          <Text style={styles.value}>{data?.state_unit}</Text>
        </View> */}
        <View style={styles.listItem}>
          <Text style={styles.label}>Status:</Text>
          <Text style={styles.value}>{data?.status}</Text>
        </View>
        {/* <View style={styles.listItem}>
          <Text style={styles.label}>To Date:</Text>
          <Text style={styles.value}>{data?.to_date}</Text>
        </View> */}
      </Page>
    </Document>
  );
};

export default VehicleRequisitionViewPdf;
