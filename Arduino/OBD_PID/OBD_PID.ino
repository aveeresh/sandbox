/*
  CAN Receive Example

  This will setup the CAN controller(MCP2515) to receive CAN frames.
  Received frames will be printed to the Serial port.

  MIT License
  https://github.com/codeljo/AA_MCP2515
*/

#include "AA_MCP2515.h"

typedef void (*SIDReqHandlerTable)(CANFrame);
void Process_Mode_01_Request(CANFrame);
void Process_Mode_02_Request(CANFrame);
void Process_Mode_03_Request(CANFrame);
void Process_Mode_04_Request(CANFrame);

// TODO: modify CAN_BITRATE, CAN_PIN_CS(Chip Select) pin, and CAN_PIN_INT(Interrupt) pin as required.
const CANBitrate::Config CAN_BITRATE = CANBitrate::Config_16MHz_500kbps;
const uint8_t CAN_PIN_CS = 10;
const int8_t CAN_PIN_INT = 2;

CANConfig config(CAN_BITRATE, CAN_PIN_CS, CAN_PIN_INT);
CANController CAN(config);

uint8_t PID_00_Data[] = { 0x06, 0x41, 0x00, 0xBE, 0x1F, 0xA8, 0x13, 0x55 };
uint8_t PID_04_Data[] = { 0x03, 0x41, 0x04, 0x04, 0x55, 0x55, 0x55, 0x55 };
uint8_t PID_05_Data[] = { 0x04, 0x41, 0x05, 0x05, 0x55, 0x55, 0x55, 0x55 };
uint8_t PID_0C_Data[] = { 0x04, 0x41, 0x0C, 0x0C, 0x0C, 0x55, 0x55, 0x55 };
uint8_t PID_20_Data[] = { 0x06, 0x41, 0x20, 0xEB, 0xF1, 0x8A, 0x31, 0x55 };
uint8_t PID_22_Data[] = { 0x04, 0x41, 0x22, 0x22, 0x22, 0x55, 0x55, 0x55 };
uint8_t PID_2C_Data[] = { 0x04, 0x41, 0x2C, 0x2C, 0x55, 0x55, 0x55, 0x55 };
uint8_t PID_31_Data[] = { 0x04, 0x41, 0x31, 0x31, 0x31, 0x55, 0x55, 0x55 };
uint8_t PID_NS_Data[] = { 0x7F, 0x01, 0x12, 0x55, 0x55, 0x55, 0x55, 0x55 };

const uint8_t *PID_Ptr_Arr[] = 
{
  PID_00_Data, /* PID_00 */
  NULL,        /* PID_01 */
  NULL,        /* PID_02 */
  NULL,        /* PID_03 */
  PID_04_Data, /* PID_04 */
  PID_05_Data, /* PID_05 */
  NULL,        /* PID_06 */
  NULL,        /* PID_07 */
  NULL,        /* PID_08 */
  NULL,        /* PID_09 */
  NULL,        /* PID_0A */
  NULL,        /* PID_0B */
  PID_0C_Data, /* PID_0C */
  NULL,        /* PID_0D */
  NULL,        /* PID_0E */
  NULL,        /* PID_0F */
  NULL,        /* PID_10 */
  NULL,        /* PID_11 */
  NULL,        /* PID_12 */
  NULL,        /* PID_13 */
  NULL,        /* PID_14 */
  NULL,        /* PID_15 */
  NULL,        /* PID_16 */
  NULL,        /* PID_17 */
  NULL,        /* PID_18 */
  NULL,        /* PID_19 */
  NULL,        /* PID_1A */
  NULL,        /* PID_1B */
  NULL,        /* PID_1C */
  NULL,        /* PID_1D */
  NULL,        /* PID_1E */
  NULL,        /* PID_1F */
  PID_20_Data, /* PID_20 */
  NULL,        /* PID_21 */
  PID_22_Data, /* PID_22 */
  NULL,        /* PID_23 */
  NULL,        /* PID_24 */
  NULL,        /* PID_25 */
  NULL,        /* PID_26 */
  NULL,        /* PID_27 */
  NULL,        /* PID_28 */
  NULL,        /* PID_29 */
  NULL,        /* PID_2A */
  NULL,        /* PID_2B */
  PID_2C_Data, /* PID_2C */
  NULL,        /* PID_2D */
  NULL,        /* PID_2E */
  NULL,        /* PID_2F */
  NULL,        /* PID_30 */
  PID_31_Data, /* PID_31 */
  NULL,        /* PID_32 */
  NULL,        /* PID_33 */
  NULL,        /* PID_34 */
  NULL,        /* PID_35 */
  NULL,        /* PID_36 */
  NULL,        /* PID_37 */
  NULL,        /* PID_38 */
  NULL,        /* PID_39 */
  NULL,        /* PID_3A */
  NULL,        /* PID_3B */
  NULL,        /* PID_3C */
  NULL,        /* PID_3D */
  NULL,        /* PID_3E */
  NULL,        /* PID_3F */
};

const SIDReqHandlerTable DistTable[] = 
{
  NULL,
  Process_Mode_01_Request,
  NULL,
  Process_Mode_03_Request,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  Process_Mode_03_Request
};

void setup() 
{
  Serial.begin(115200);

  while (CAN.begin(CANController::Mode::Normal) != CANController::OK) 
  {
    Serial.println("CAN begin FAIL - delaying for 1 second");
    delay(1000);
  }

  randomSeed(analogRead(0));

  //CAN.setCNF(0x40, 0xA4, 0x04);
  Serial.println("OBD demo begin");
}

void Process_Mode_01_Request(CANFrame OBDReq) {
  uint8_t PID;
  CANFrame TxFrame;

  PID = OBDReq.getData()[2];

  TxFrame.setId(0x7E8);

  if (PID_Ptr_Arr[PID] != NULL) {
    if (PID == 0x00) 
    {
      TxFrame.setData(PID_00_Data, 8);
    } 
    else if (PID == 0x04) 
    {
      PID_04_Data[3] = random(0x00, 0xFF);
      TxFrame.setData(PID_04_Data, 8);
    } 
    else if (PID == 0x05) 
    {
      PID_05_Data[3] = random(0x00, 0xFF);
      TxFrame.setData(PID_05_Data, 8);
    } 
    else if (PID == 0x0C) 
    {
      PID_0C_Data[3] = random(0x00, 0xFF);
      PID_0C_Data[4] = random(0x00, 0xFF);
      TxFrame.setData(PID_0C_Data, 8);
    } 
    else if (PID == 0x20) 
    {
      TxFrame.setData(PID_20_Data, 8);
    } 
    else if (PID == 0x22)
    {
      PID_22_Data[3] = random(0x00, 0xFF);
      PID_22_Data[4] = random(0x00, 0xFF);
      TxFrame.setData(PID_22_Data, 8);
    } 
    else if (PID == 0x2C) 
    {
      PID_2C_Data[3] = random(0x00, 0xFF);
      TxFrame.setData(PID_2C_Data, 8);
    } 
    else if (PID == 0x31) 
    {
      PID_31_Data[3] = random(0x00, 0xFF);
      PID_31_Data[4] = random(0x00, 0xFF);
      TxFrame.setData(PID_31_Data, 8);
    } 
    else 
    {
      TxFrame.setData(PID_NS_Data, 8);
    }
  }
  TxFrame.print("OBD Resp");
  CAN.write(TxFrame);
}

void Process_Mode_03_Request(CANFrame OBDReq) {
  CANFrame TxFrame;
  uint8_t Mode_03_Data[] = { 0x06, 0x43, 0x02, 0x01, 0x02, 0x03, 0x04, 0x55 };

  TxFrame.setId(0x7E8);

  TxFrame.setData(Mode_03_Data, 8);

  TxFrame.print("OBD Resp");
  CAN.write(TxFrame);
}

void Process_Mode_09_Request(CANFrame OBDReq) {
}

void loop() {

  CANFrame RxFrame;
  uint8_t *RxData;
  uint8_t SID;

  while (1) {
    if (CAN.read(RxFrame) == CANController::IOResult::OK) {
      RxFrame.print("OBD Req ");

      if (RxFrame.getId() == 0x7DF || RxFrame.getId() == 0x7E0) {
        RxData = RxFrame.getData();
        SID = RxData[1];
        //Serial.println(SID, HEX);

        if (DistTable[SID] != NULL) {
          DistTable[SID](RxFrame);
        } else {
          Serial.println("Mode not supported");
        }

        Serial.println("------------------------------------------------------------------");
      }
    }
  }
}
